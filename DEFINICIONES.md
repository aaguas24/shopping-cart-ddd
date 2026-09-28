# Definiciones del Proyecto

## Objetivo General del Proyecto

Construir una aplicación de carrito de compras con una arquitectura orientada al dominio (DDD), separando responsabilidades en capas:
- **Dominio**: entidades, agregados, value objects, interfaces de repositorio
- **Aplicación**: casos de uso
- **Infraestructura**: persistencia, configuración, DB, REST
- **Presentación**: API REST
- **Pruebas**: unitarias e integración

La intención es que el proyecto sirva para aprender:
- Arquitectura hexagonal / DDD
- Spring Boot
- Maven
- JPA/Hibernate
- H2 en memoria
- API REST
- Testing
- Separación por bounded contexts
- Buenas prácticas de código

## Definiciones Clave del Proyecto

### Producto
Un artículo disponible para venta dentro del sistema. Tiene atributos como identificador, nombre, descripción, precio, SKU, categoría y estado de disponibilidad.

### Cliente
Persona que realiza compras. Tiene identidad, datos de contacto y acceso al catálogo y al carrito.

### Carrito de Compras
Contenedor temporal donde el cliente agrega productos antes de confirmar la compra. Puede tener múltiples líneas de productos y cantidades.

### Línea del Carrito
Representa una unidad de producto dentro del carrito. Incluye producto, cantidad y subtotal parcial.

### Inventario
Registro de disponibilidad real de cada producto. Define cuántas unidades existen y si un producto está activo o no.

### Pedido
Resultado del proceso de compra una vez que el cliente confirma el carrito. Es la representación de la compra final.

### Orden de Compra
Documento generado a partir del pedido con su estado, total, cliente, productos y fecha.

### Pago
Proceso asociado a la confirmación de compra. No se implementa lógica de pagos reales, solo la estructura para integrarlo.

### Categoría
Clasificación del producto para navegación y filtros.

### Usuario Administrativo
Persona que gestiona catálogo, inventario y disponibilidad de productos.

### Bounded Context
Contexto del dominio donde cada funcionalidad tiene un significado claro y delimitado: catálogo, carrito, pedidos, clientes e inventario.
