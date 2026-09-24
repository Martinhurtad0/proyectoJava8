package Model;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private  Double precio;
    private String proveedor;

    public Producto ( String nombreProducto, Double precio, String proveedor ){

        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.proveedor = proveedor;
    }

    public  Producto(){

    }

    public  int getIdProducto(){
        return idProducto;
    }

    public  int setIdProducto(int idProducto){
        return  this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }
}
