<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Producto" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Nuevo Producto</title>
       <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productos.css">
</head>
<body>

<div class="container">
    <div class="card">
        <div class="card-header">
            <h4>Registrar Producto</h4>
        </div>
        <div class="card-body">

            <%--
              El action debe apuntar a la URL mapping de tu Servlet.
              Si tu servlet responde en "/guardarProducto", cambia el action a ese valor.
            --%>
            <form action="${pageContext.request.contextPath}/productos" method="POST">

                <!-- Campo: Nombre del Producto -->
                <div class="form-group">
                    <label for="nombreproducto" class="form-label">Nombre del Producto</label>
                    <input type="text"
                           class="form-control"
                           id="nombreproducto"
                           name="nombreproducto"
                           placeholder="Ej. Laptop Dell"
                           required>
                </div>

                <!-- Campo: Precio -->
                <div class="form-group">
                    <label for="precio" class="form-label">Precio</label>
                    <div class="input-group">
                        <span class="input-group-text">$</span>
                        <input type="number"
                               class="form-control"
                               id="precio"
                               name="precio"
                               step="0.01"
                               min="0"
                               placeholder="0.00"
                               required>
                    </div>
                    <span class="form-text">Usa puntos para los decimales (Ej. 19.99).</span>
                </div>

                <!-- Campo: Proveedor -->
                <div class="form-group">
                    <label for="proveedor" class="form-label">Proveedor</label>
                    <input type="text"
                           class="form-control"
                           id="proveedor"
                           name="proveedor"
                           placeholder="Ej. Distribuidora Tech"
                           required>
                </div>

                <!-- Botones de Acción -->
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary btn-lg">Guardar Producto</button>
                    <a href="${pageContext.request.contextPath}/productos" class="btn btn-outline-secondary">Cancelar</a>
                </div>

            </form>

        </div>
    </div>

</div>
<div class="card">
                <h2>Lista de  productos</h2>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                 <th>id </th>
                                <th>Nombre productos</th>
                                <th>Precio</th>
                                <th>Proveedor</th>

                                <th> </th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                                if (productos != null && !productos.isEmpty()) {
                                    for (Producto producto : productos) {
                            %>
                                    <tr>
                                       <td><%= producto.getIdProducto() %></td>
                                        <td><%= producto.getNombreProducto() %></td>
                                        <td><%= producto.getPrecio() %></td>
                                        <td><%= producto.getProveedor() %></td>

                                         <td>
                                                                                                            <button type="button"
                                                                                                                    class="btn-eliminar"
                                                                                                                    onclick="eliminarProducto(<%= producto.getIdProducto() %>)">
                                                                                                                Eliminar
                                                                                                            </button>
                                                                                                        </td>


                                    </tr>
                            <%
                                    }
                                } else {
                            %>
                                <tr>
                                    <td colspan="4" class="empty">No hay clientes registrados.</td>
                                </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
 <script src="${pageContext.request.contextPath}/js/clientes.js" > </script>
</body>
</html>