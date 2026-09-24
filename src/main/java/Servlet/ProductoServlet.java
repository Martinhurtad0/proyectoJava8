package Servlet;

import Config.DatabaseConnection;
import Dao.ClienteDao;
import Dao.ProductoDao;
import Model.Cliente;
import Model.Producto;
import Service.ClienteService;
import Service.ProductoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    private  ProductoService productoService;

    @Override
    public void init() throws ServletException {
        DatabaseConnection databaseConnection =
                new DatabaseConnection();

        ProductoDao ProductoDao =
                new ProductoDao(databaseConnection);

      productoService =
                new ProductoService(ProductoDao);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String precioStr = request.getParameter("precio");
        String  nombreProducto= request.getParameter("nombreproducto");
        Double precio = Double.parseDouble(precioStr);
        String proveedor = request.getParameter("proveedor");


        Producto producto = new Producto(
                nombreProducto,
                precio,
                proveedor

        );

        try {
            ProductoDao productoDao = new ProductoDao(); // O como inicialices tu DAO
            ProductoService productoService = new ProductoService(productoDao);

            // 3. Guardar el producto usando la instancia del servicio
            productoService.guardarProducto(producto);


            // Cambia tu línea actual por esta:
            response.sendRedirect(request.getContextPath() + "/productos");



        } catch (Exception exception) {
            // Esto imprime el error detallado con la línea exacta en la consola de tu IDE (Muy importante)
            exception.printStackTrace();

            // Enviamos el mensaje real del error (ej: "Connection refused" o "Column not found") al JSP
            request.setAttribute(
                    "error",
                    "Error en la Base de Datos: " + exception.getMessage()
            );

            // Mantenemos los datos que el usuario ya escribió para que no los pierda
            request.setAttribute("producto", producto);

            request.getRequestDispatcher(
                    "/views/productos.jsp"
            ).forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1. Obtienes la lista de clientes desde tu servicio
            List<Producto> productos = productoService.listarProductos();

            // 2. CORREGIDO: Pasas la variable 'clientes' que acabas de crear arriba
            request.setAttribute("productos", productos);

            // 3. Reenvías la petición al archivo JSP para que dibuje la interfaz con los datos
            request.getRequestDispatcher("/views/productos.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(); // <-- ESTO ES VITAL. Revisa la consola de tu IDE apenas recargues la página.
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/productos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String idParam = request.getParameter("idProducto");
            if (idParam == null || idParam.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"ID requerido\"}");
                return;
            }

            int productoId = Integer.parseInt(idParam);
            productoService.eliminarProducto(productoId);

            response.setStatus(HttpServletResponse.SC_OK);
            out.print("{\"mensaje\":\"Producto eliminado\"}");

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
