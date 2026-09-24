package Servlet;

import Config.DatabaseConnection;
import Dao.ClienteDao;
import Model.Cliente;
import Service.ClienteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {

    private ClienteService clienteService;

    @Override
    public void init() throws ServletException {
        DatabaseConnection databaseConnection =
                new DatabaseConnection();

        ClienteDao clienteDAO =
                new ClienteDao(databaseConnection);

        clienteService =
                new ClienteService(clienteDAO);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String identificacion = request.getParameter("identificacion");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");

        String email = request.getParameter("email");

        Cliente cliente = new Cliente(
                nombre,
                identificacion,
                direccion,
                email
        );

        try {
            clienteService.guardarCliente(cliente);


                    // Cambia tu línea actual por esta:
            response.sendRedirect(request.getContextPath() + "/clientes");



        } catch (Exception exception) {
            // Esto imprime el error detallado con la línea exacta en la consola de tu IDE (Muy importante)
            exception.printStackTrace();

            // Enviamos el mensaje real del error (ej: "Connection refused" o "Column not found") al JSP
            request.setAttribute(
                    "error",
                    "Error en la Base de Datos: " + exception.getMessage()
            );

            // Mantenemos los datos que el usuario ya escribió para que no los pierda
            request.setAttribute("cliente", cliente);

            request.getRequestDispatcher(
                    "/views/formulario.jsp"
            ).forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1. Obtienes la lista de clientes desde tu servicio
            List<Cliente> clientes = clienteService.listarClientes();

            // 2. CORREGIDO: Pasas la variable 'clientes' que acabas de crear arriba
            request.setAttribute("clientes", clientes);

            // 3. Reenvías la petición al archivo JSP para que dibuje la interfaz con los datos
            request.getRequestDispatcher("/views/formulario.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(); // <-- ESTO ES VITAL. Revisa la consola de tu IDE apenas recargues la página.
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/formulario.jsp").forward(request, response);
        }

    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"ID requerido\"}");
                return;
            }

            int clienteId = Integer.parseInt(idParam);
            clienteService.EliminarCliente(clienteId);

            response.setStatus(HttpServletResponse.SC_OK);
            out.print("{\"mensaje\":\"Cliente eliminado\"}");

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"ID inválido\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        } finally {
            out.flush();
        }
    }



}