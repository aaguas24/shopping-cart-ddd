# Historias de Usuario

## Historia 1: Registro de Productos
**Como** administrador,  
**quiero** registrar productos en el sistema,  
**para** que estén disponibles para la venta.

### Criterios de Aceptación
- El producto se puede crear con nombre, descripción, precio y categoría.
- El sistema guarda el producto correctamente.
- El código del producto es único.
- Si falta información requerida, la operación falla.

---

## Historia 2: Consulta del Catálogo
**Como** cliente,  
**quiero** ver los productos disponibles,  
**para** decidir qué comprar.

### Criterios de Aceptación
- El sistema devuelve todos los productos activos.
- El usuario puede filtrar por categoría.
- El sistema devuelve productos ordenados por nombre o disponibilidad.

---

## Historia 3: Agregar Producto al Carrito
**Como** cliente,  
**quiero** agregar productos a mi carrito,  
**para** preparar una compra.

### Criterios de Aceptación
- El cliente puede agregar un producto con cantidad.
- El sistema agrega la línea al carrito.
- Si la línea ya existe, se actualiza la cantidad.

---

## Historia 4: Modificar Cantidad
**Como** cliente,  
**quiero** cambiar la cantidad de un producto en mi carrito,  
**para** ajustar la compra.

### Criterios de Aceptación
- La cantidad se actualiza correctamente.
- La cantidad mínima es 1.
- Si se elimina la cantidad, la línea se elimina.

---

## Historia 5: Eliminar Producto del Carrito
**Como** cliente,  
**quiero** quitar un producto del carrito,  
**para** eliminar artículos no deseados.

### Criterios de Aceptación
- La línea se elimina del carrito.
- El total se recalcula.
- La operación devuelve estado correcto.

---

## Historia 6: Ver Contenido del Carrito
**Como** cliente,  
**quiero** consultar los productos en mi carrito,  
**para** revisar el pedido antes de comprar.

### Criterios de Aceptación
- El sistema devuelve cada producto con cantidad y subtotal.
- El total del carrito se presenta correctamente.

---

## Historia 7: Confirmar Compra
**Como** cliente,  
**quiero** confirmar mi compra,  
**para** convertir el carrito en un pedido.

### Criterios de Aceptación
- El carrito se convierte en pedido.
- El pedido queda asociado al cliente.
- El sistema genera un identificador para el pedido.

---

## Historia 8: Consultar Pedidos
**Como** cliente,  
**quiero** ver mis pedidos anteriores,  
**para** revisar mi historial de compra.

### Criterios de Aceptación
- El sistema devuelve los pedidos del cliente.
- Cada pedido incluye productos y costos.

---

## Historia 9: Gestionar Inventario
**Como** administrador,  
**quiero** gestionar la disponibilidad de productos,  
**para** controlar qué artículos pueden venderse.

### Criterios de Aceptación
- Cada producto tiene cantidad disponible.
- El administrador puede actualizar la disponibilidad.
- Cuando el stock es cero, el producto queda no disponible.

---

## Historia 10: Registrar Clientes
**Como** administrador,  
**quiero** dar de alta clientes,  
**para** asociarlos a pedidos y carritos.

### Criterios de Aceptación
- El cliente queda almacenado con información base.
- El sistema evita duplicados por identificación.

---

## Historia 11: Consultar Producto por ID
**Como** usuario del sistema,  
**quiero** consultar un producto individual,  
**para** ver su detalle.

### Criterios de Aceptación
- El sistema devuelve el producto.
- Si no existe, responde con error válido.

---

## Historia 12: Gestionar Categorías
**Como** administrador,  
**quiero** crear y consultar categorías,  
**para** organizar el catálogo.

### Criterios de Aceptación
- La categoría se guarda correctamente.
- Puede visualizarse en el catálogo.
- El nombre de la categoría es único.

---

## Historias Técnicas para el Desarrollo en DDD

### Historia Técnica 1
**Como** desarrollador,  
**quiero** separar la capa de dominio de la capa de infraestructura,  
**para** mantener un código limpio y escalable.

### Historia Técnica 2
**Como** desarrollador,  
**quiero** crear entidades y agregados para cada contexto,  
**para** modelar mejor el negocio.

### Historia Técnica 3
**Como** desarrollador,  
**quiero** definir interfaces de repositorio,  
**para** desacoplar la lógica de persistencia.

### Historia Técnica 4
**Como** desarrollador,  
**quiero** usar JPA para persistencia,  
**para** guardar las entidades en H2.

### Historia Técnica 5
**Como** desarrollador,  
**quiero** crear casos de uso en la capa de aplicación,  
**para** mantener la lógica de orquestación separada del dominio.
