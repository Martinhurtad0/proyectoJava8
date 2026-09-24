package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Parámetros de conexión basados en tu contenedor Docker
    private static final String URL = "jdbc:postgresql://localhost:5433/facturacion";
    private static final String USER = "pgAdmin";
    private static final String PASSWORD = "pgAdmin123";

    // Método para obtener la conexión a la base de datos
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Registrar el driver de PostgreSQL (opcional en versiones modernas de Java, pero recomendado)
            Class.forName("org.postgresql.Driver");

            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡Conexión exitosa a PostgreSQL!");

        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de PostgreSQL. Asegúrate de agregar el JAR al proyecto.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos. Verifica las credenciales o el estado del contenedor.");
            e.printStackTrace();
        }
        return connection;
    }
}
