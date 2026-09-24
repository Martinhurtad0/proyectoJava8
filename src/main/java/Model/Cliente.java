package Model;

public class Cliente {

    private int  id ;
    private String nombreCliente;
    private String identificacion;
    private String email;
    private String direccion;

    public  Cliente ( String nombreCliente, String identificacion,String direccion, String email  ){

        this.nombreCliente = nombreCliente;
        this.identificacion = identificacion;
        this.email = email;
        this.direccion = direccion;
    }

    public Cliente() {
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getId(){
       return this.id;
    }

    public  int setId (int id){
        return  this.id = id;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
