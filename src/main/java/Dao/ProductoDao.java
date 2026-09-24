package Dao;

import Config.DatabaseConnection;

import Model.Producto;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDao {

    private   DatabaseConnection databaseConnection;

    public  ProductoDao (DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public ProductoDao() {
    }

    public Producto  guardarProducto (Producto producto ) throws  Exception{

        String sql = "INSERT INTO producto (nombreproducto, precio, proveedor) "
                +"VALUES(?,?,?)";

        try(Connection connection = databaseConnection.getConnection();
            PreparedStatement registro = connection.prepareStatement(sql)){

            registro.setString(1, producto.getNombreProducto());
            registro.setDouble(2, producto.getPrecio());
            registro.setString(3, producto.getProveedor());

           registro.executeUpdate();

            return  producto;

        }catch (SQLException e){
            throw new Exception("Error al insertar datos " + e.getMessage(), e);
        }



    }


    public List<Producto> ListarProductos() throws  Exception{
        List<Producto> productos = new ArrayList();
        String sql = "SELECT idProducto, nombreproducto, precio, proveedor FROM producto";
        try(Connection connection = databaseConnection.getConnection();
              PreparedStatement traerProductos = connection.prepareStatement(sql);
              ResultSet result = traerProductos.executeQuery()) {

            while (result.next()){
                Producto producto = new Producto();
                producto.setIdProducto(result.getInt("idProducto"));
                producto.setNombreProducto(result.getString("nombreproducto"));
                producto.setPrecio(result.getDouble("precio"));
                producto.setProveedor(result.getString("proveedor"));

                productos.add(producto);
            }
            return  productos;
        }catch (SQLException e){
            throw new Exception( e.getMessage(), e);

        }
    }

    public boolean eliminarProducto(int idProducto) throws Exception {
        String sql = "DELETE FROM public.producto WHERE idProducto = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            throw new Exception("Error eliminando producto " + idProducto, e);
        }
    }

}
