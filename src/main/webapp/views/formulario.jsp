<%@ page import="java.util.List" %>
<%@ page import="model.Cliente" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Clientes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/clientes.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Gestión de Clientes</h1>
            <p>Registra y consulta los clientes del sistema.</p>

             <div class="navigation-actions" style="margin-top: 15px;">
                            <a href="${pageContext.request.contextPath}/productos" class="btn-productos" style="display: inline-block; padding: 10px 15px; background-color: #212529; color: white; text-decoration: none; border-radius: 4px; font-weight: bold;">
                                 Ver Productos
                            </a>
                        </div>
        </div>

        <!-- MENSAJE DE ERROR -->
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <div class="error">
                <%= error %>
            </div>
        <%
            }
        %>

        <!-- FORMULARIO -->
        <div class="card">
            <h2>Nuevo cliente</h2>
            <form action="${pageContext.request.contextPath}/clientes" method="POST">
                <div class="form-grid">
                    <div class="form-group">
                        <label for="identificacion">Documento</label>
                        <input type="text" id="identificacion" name="identificacion" required>
                    </div>
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" id="nombre" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="direccion">Dirección</label>
                        <input type="text" id="direccion" name="direccion">
                    </div>
                    <div class="form-group">
                        <label for="email">Correo</label>
                        <input type="email" id="email" name="email">
                    </div>
                </div>
                <div class="button-container">
                    <button type="submit">Guardar cliente</button>
                </div>
            </form>
        </div>

        <!-- LISTA DE CLIENTES -->
        <div class="card">
            <h2>Lista de clientes</h2>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                             <th>id </th>
                            <th>Documento</th>
                            <th>Nombre</th>
                            <th>Dirección</th>
                            <th>Correo</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                            if (clientes != null && !clientes.isEmpty()) {
                                for (Cliente cliente : clientes) {
                        %>
                                <tr>
                                   <td><%= cliente.getId() %></td>
                                    <td><%= cliente.getIdentificacion() %></td>
                                    <td><%= cliente.getNombreCliente() %></td>
                                    <td><%= cliente.getDireccion() %></td>
                                    <td><%= cliente.getEmail() %></td>
                                    <td>
                                                                    <button type="button"
                                                                            class="btn-eliminar"
                                                                            onclick="eliminarCliente(<%= cliente.getId() %>)">
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
    </div>

    <script src="${pageContext.request.contextPath}/js/clientes.js" > </script>
</body>
</html>
