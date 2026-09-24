package Dao;

import Config.DatabaseConnection;
import Model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDao {
    private final DatabaseConnection databaseConnection;

    public  ClienteDao(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public  void  guardar (Cliente cliente) throws Exception
    {
        String sql = "INSERT INTO public.cliente (nombre_cliente, identificacion, email, direccion) VALUES (?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombreCliente());
            statement.setString(2, cliente.getIdentificacion());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getDireccion());
            statement.executeUpdate();



        }
        catch (SQLException e) {
            // ¡Esto es vital! Si no relanzas la excepción, el servicio creerá que todo salió bien
            throw new Exception("Error al ejecutar el INSERT: " + e.getMessage(), e);
        }
    }

    public List<Cliente> listar() throws Exception {

        List<Cliente> clientes = new ArrayList<>();

        // CORRECCIÓN: Quitamos los paréntesis para que devuelva columnas individuales
        String sql = "SELECT id, nombre_cliente, identificacion, email, direccion FROM public.cliente";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(result.getInt("id"));
                cliente.setIdentificacion(result.getString("identificacion"));
                cliente.setNombreCliente(result.getString("nombre_cliente"));
                cliente.setDireccion(result.getString("direccion"));
                cliente.setEmail(result.getString("email"));

                // CORRECCIÓN: Agregamos el cliente solo una vez a la lista
                clientes.add(cliente);

                // Si quieres imprimir en consola, hazlo después de añadirlo

            }
        }
        catch (SQLException e) {
            throw new Exception("Error al listar: " + e.getMessage(), e);
        }

        return clientes;
    }

    public void eliminarCliente(int idCliente) throws Exception {
        // CORRECCIÓN: Sintaxis estándar de SQL 'DELETE FROM ... WHERE ... = ?'
        String sql = "DELETE FROM public.cliente WHERE id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Asignamos de forma segura el ID al parámetro '?'
            statement.setInt(1, idCliente);

            // Ejecutamos la eliminación
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente con ID " + idCliente + " eliminado exitosamente.");
            } else {
                System.out.println("No se encontró ningún cliente con el ID: " + idCliente);
            }
        }
        catch (SQLException e) {
            throw new Exception("Error al eliminar el cliente: " + e.getMessage(), e);
        }
    }




}
