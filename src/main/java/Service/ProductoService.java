package Service;

import Dao.ProductoDao;
import Model.Producto;

import java.util.List;

public class ProductoService {

    private final ProductoDao productoDao;

    public ProductoService (ProductoDao productoDao){
        this.productoDao = productoDao;
    }



    public Producto guardarProducto (Producto producto)throws  Exception{
         if(producto.getNombreProducto()==null){
             System.out.println("El producto no puede estar vacio");
         }

         Producto productoGuardado = productoDao.guardarProducto(producto);
         return  productoGuardado;
    }


    public List<Producto> listarProductos() throws Exception{
        return  productoDao.ListarProductos();

    }

    public  boolean eliminarProducto(int idProducto) throws  Exception{
         return    productoDao.eliminarProducto(idProducto);
    }
}
