package Service;

import Dao.ClienteDao;
import Model.Cliente;

import java.util.List;

public class ClienteService {

    private  final ClienteDao clienteDao;

    public  ClienteService(ClienteDao clienteDao){
        this.clienteDao = clienteDao;
    }

    public  void   guardarCliente (Cliente cliente) throws Exception
    {

        if (cliente == null){
            throw new IllegalArgumentException("El cliente es obligatorio");
        }

        if (cliente.getIdentificacion() == null){
            throw  new IllegalArgumentException("El documento no puede estar vacio");

        }

        if (cliente.getEmail()== null) {
            throw  new IllegalArgumentException("El email no puede estar vacio ");

        }

          clienteDao.guardar(cliente);


    }

    public List<Cliente> listarClientes ()throws Exception{
        return  clienteDao.listar();
    }

    public void  EliminarCliente(int clienteId) throws Exception{
          clienteDao.eliminarCliente(clienteId);
    }

}
