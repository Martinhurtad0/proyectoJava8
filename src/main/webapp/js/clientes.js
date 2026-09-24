
    function eliminarCliente(id) {
        if (!confirm("¿Seguro que deseas eliminar este cliente?")) return;

        fetch('clientes?id=' + id, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    location.reload();
                } else {
                    return response.json().then(err => alert("Error: " + err.error));
                }
            })
            .catch(() => alert("Error de conexión"));
    }

    function eliminarProducto(id) {
            if (!confirm("¿Seguro que deseas eliminar este producto?")) return;

            fetch('productos?idProducto=' + id, { method: 'DELETE' })
                .then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        return response.json().then(err => alert("Error: " + err.error));
                    }
                })
                .catch(() => alert("Error de conexión"));
        }
