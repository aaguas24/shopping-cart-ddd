# Requisitos Funcionales

## RF-01: Registrar Productos
El sistema debe permitir registrar productos.
- El producto debe tener código único, nombre, descripción, precio, categoría y disponibilidad.
- No se debe permitir duplicar productos con el mismo código.

## RF-02: Consultar Catálogo
El sistema debe permitir consultar el catálogo de productos.
- El usuario puede listar todos los productos.
- El usuario puede buscar productos por nombre o categoría.

## RF-03: Registrar Clientes
El sistema debe permitir registrar clientes.
- Cada cliente debe tener identificación, nombre y datos de contacto básicos.

## RF-04: Crear Carrito de Compras
El sistema debe permitir crear un carrito de compras.
- El carrito debe estar asociado a un cliente.
- El carrito debe poder estar vacío o con productos.

## RF-05: Agregar Productos al Carrito
El sistema debe permitir agregar productos al carrito.
- Se agrega un producto y cantidad.
- Si el producto ya existe en el carrito, la cantidad se debe sumar o actualizar.

## RF-06: Modificar Cantidad de Producto en Carrito
El sistema debe permitir modificar la cantidad de un producto en el carrito.
- La cantidad debe ser mayor o igual a 1.
- Si la cantidad resulta en 0, el producto debe eliminarse.

## RF-07: Eliminar Producto del Carrito
El sistema debe permitir eliminar un producto del carrito.
- La línea debe ser eliminada del carrito.

## RF-08: Visualizar Contenido del Carrito
El sistema debe permitir visualizar el contenido del carrito.
- Debe mostrar productos, cantidades, precios unitarios y subtotal.

## RF-09: Calcular Total del Carrito
El sistema debe permitir calcular el total del carrito.
- El total debe sumarse con base en cantidades y precio del producto.

## RF-10: Confirmar la Compra
El sistema debe permitir confirmar la compra.
- El carrito debe convertirse en pedido.
- El sistema debe generar un pedido con estado inicial válido.

## RF-11: Consultar Pedidos
El sistema debe permitir consultar pedidos.
- El cliente o administrador puede consultar los pedidos generados.

## RF-12: Actualizar Estado del Pedido
El sistema debe permitir actualizar el estado del pedido.
- Este estado puede cambiar según el flujo deseado por el desarrollador.

## RF-13: Gestionar Disponibilidad del Inventario
El sistema debe permitir gestionar la disponibilidad del inventario.
- Cada producto debe tener cantidad disponible.
- El inventario debe reflejar si un producto está activo o agotado.

## RF-14: Filtrar por Categoría
El sistema debe permitir filtros por categoría.
- El usuario puede consultar productos por categoría.

## RF-15: Registrar Categorías
El sistema debe permitir registrar categorías.
- Cada categoría tiene nombre y descripción básica.

## RF-16: Consultar Producto por ID
El sistema debe permitir consultar un producto por ID.
- Debe devolver la información del producto seleccionado.

## RF-17: Consultar Cliente por ID
El sistema debe permitir consultar clientes por ID.
- Debe devolver la información del cliente.

## RF-18: Listar Todos los Clientes
El sistema debe permitir listar todos los clientes.
- La información debe estar disponible para administración.

## RF-19: Listar Pedidos de un Cliente
El sistema debe permitir listar los pedidos de un cliente.
- El cliente debe poder ver su historial de compras.

## RF-20: Asegurar Consistencia del Carrito
El sistema debe permitir asegurar que el carrito no quede inconsistente.
- No debe permitir líneas sin producto o cantidades inválidas.
