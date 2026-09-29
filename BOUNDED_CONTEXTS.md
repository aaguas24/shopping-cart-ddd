# Bounded Contexts

Los bounded contexts son contextos delimitados del dominio donde cada concepto tiene un significado claro, reglas propias y responsabilidades bien separadas.

## CatalogoContext
Responsable de gestionar el catálogo de productos disponibles para la venta.

### Entidades y Agregados
- **Producto**: agregado raíz del catálogo; es la principal entrada para crear, consultar y modificar la información comercial de un producto.
  - **ID único del producto**: identificador irrepetible del producto dentro del sistema.
  - **Nombre**: nombre comercial visible para usuarios y procesos de negocio.
  - **Descripción**: detalle funcional o comercial que explica qué es el producto.
  - **Precio**: valor monetario del producto; idealmente modelado como un Value Object tipo `Money`.
  - **SKU**: código único de inventario o referencia comercial para identificar el producto operativamente.
  - **Categoría**: clasificación a la que pertenece el producto, usada para organización y búsqueda.
  - **Estado de disponibilidad**: indica si el producto está activo, inactivo, agotado o disponible para venta.

- **Categoría**: agregado raíz o entidad principal de clasificación; organiza productos por grupos de negocio.
  - **ID único**: identificador exclusivo de la categoría.
  - **Nombre**: nombre de la categoría, por ejemplo `Electrónica` o `Hogar`.
  - **Descripción**: explica qué tipo de productos agrupa esa categoría.

- **Inventario**: componente del dominio que controla existencias; puede modelarse como entidad o agregado si tiene reglas propias.
  - **Cantidad disponible**: número de unidades actualmente vendibles.
  - **Stock mínimo**: umbral a partir del cual se considera necesario reabastecer.
  - **Producto referenciado**: relación con el producto al que pertenece ese inventario; en DDD conviene usar `ProductoId`.

### Interfaces de Repositorio
- **ProductoRepository**: contrato del dominio para guardar, buscar y recuperar productos.
- **CategoriaRepository**: contrato del dominio para persistir y consultar categorías.
- **InventarioRepository**: contrato del dominio para consultar y actualizar existencias.

### Casos de Uso (Application)
- **RegistrarProductoUseCase**: crea y registra un nuevo producto en el catálogo.
- **ConsultarCatalogoUseCase**: obtiene la lista general de productos disponibles.
- **BuscarProductoPorIdUseCase**: recupera un producto específico a partir de su identificador.
- **FiltrarProductosPorCategoriaUseCase**: lista productos según una categoría determinada.
- **ActualizarInventarioUseCase**: modifica la cantidad disponible o el estado de inventario de un producto.

---

## CustomerContext
Responsable de la gestión de clientes y sus datos principales.

### Entidades y Agregados
- **Cliente**: agregado raíz del contexto de clientes; concentra la identidad y datos relevantes del comprador.
  - **ID único del cliente**: identificador exclusivo del cliente dentro del sistema.
  - **Nombre**: nombre completo o nombre comercial del cliente.
  - **Email**: correo electrónico del cliente; idealmente modelado como Value Object para validar formato y reglas.
  - **Teléfono**: número de contacto del cliente.
  - **Dirección**: ubicación física del cliente; puede modelarse como un Value Object compuesto.

### Interfaces de Repositorio
- **ClienteRepository**: contrato del dominio para almacenar y consultar clientes.

### Casos de Uso (Application)
- **RegistrarClienteUseCase**: crea un nuevo cliente en el sistema.
- **ConsultarClientePorIdUseCase**: recupera un cliente por su identificador.
- **ListarClientesUseCase**: devuelve la lista de clientes registrados.

---

## CartContext
Responsable de gestionar los carritos de compra antes de confirmar una compra.

### Entidades y Agregados
- **Carrito**: agregado raíz del contexto de carrito; agrupa productos seleccionados por un cliente.
  - **ID único del carrito**: identificador exclusivo del carrito.
  - **Cliente referenciado**: cliente dueño del carrito; en DDD suele representarse mediante `ClienteId`.
  - **Lista de líneas del carrito**: conjunto de ítems agregados al carrito con producto, cantidad y precio.
  - **Estado (abierto, confirmado)**: indica si el carrito sigue editable o si ya fue usado para generar la compra.

- **LineaCarrito**: entidad interna del agregado `Carrito`; representa un producto concreto dentro del carrito.
  - **ID único**: identificador de la línea dentro del carrito.
  - **Producto referenciado**: producto asociado a la línea; normalmente se representa por `ProductoId`.
  - **Cantidad**: número de unidades agregadas del producto.
  - **Precio unitario**: precio del producto al momento de ser agregado al carrito.

### Interfaces de Repositorio
- **CarritoRepository**: contrato del dominio para guardar, recuperar y actualizar carritos.

### Casos de Uso (Application)
- **CrearCarritoUseCase**: crea un carrito nuevo para un cliente.
- **AgregarProductoAlCarritoUseCase**: añade un producto al carrito existente.
- **ModificarCantidadProductoUseCase**: cambia la cantidad de un producto ya agregado.
- **EliminarProductoDelCarritoUseCase**: quita un producto del carrito.
- **ConsultarCarritoUseCase**: obtiene el estado actual del carrito con sus líneas.
- **CalcularTotalCarritoUseCase**: calcula el importe total del carrito.
- **VaciarCarritoUseCase**: elimina todos los productos contenidos en el carrito.

---

## OrderContext
Responsable de gestionar los pedidos generados a partir de una compra confirmada.

### Entidades y Agregados
- **Pedido**: agregado raíz del contexto de pedidos; representa la compra formalizada del cliente.
  - **ID único del pedido**: identificador exclusivo del pedido.
  - **Cliente referenciado**: cliente que realizó la compra; normalmente se modela con `ClienteId`.
  - **Carrito referenciado**: referencia al carrito desde el que se originó el pedido.
  - **Lista de líneas del pedido**: detalle de productos comprados, cantidades y precios.
  - **Total**: importe final del pedido; idealmente un Value Object tipo `Money`.
  - **Fecha de creación**: momento en que el pedido fue generado.
  - **Estado del pedido**: situación actual del pedido dentro del flujo operativo.

- **LineaPedido**: entidad interna del agregado `Pedido`; representa cada producto comprado.
  - **ID único**: identificador de la línea del pedido.
  - **Producto referenciado**: producto comprado en esa línea.
  - **Cantidad**: unidades compradas del producto.
  - **Precio unitario**: precio del producto al momento de la compra para mantener histórico.

- **EstadoPedido**: enumeración o Value Object que describe el ciclo de vida del pedido.
  - **CREADO**: el pedido fue generado pero aún no avanza en el flujo posterior.
  - **CONFIRMADO**: el pedido fue aceptado formalmente para ser procesado.
  - **PROCESANDO**: el pedido está siendo preparado o gestionado internamente.
  - **ENVIADO**: el pedido ya salió hacia entrega.
  - **ENTREGADO**: el pedido fue recibido por el cliente.
  - **CANCELADO**: el pedido fue anulado y no seguirá su flujo normal.

### Interfaces de Repositorio
- **PedidoRepository**: contrato del dominio para persistir y recuperar pedidos.

### Casos de Uso (Application)
- **ConfirmarCompraUseCase**: transforma un carrito válido en un pedido confirmado.
- **ConsultarPedidoPorIdUseCase**: obtiene un pedido a partir de su identificador.
- **ListarPedidosDelClienteUseCase**: devuelve los pedidos asociados a un cliente.
- **ActualizarEstadoPedidoUseCase**: cambia el estado del pedido según reglas del negocio.
- **ListarTodosPedidosUseCase**: lista todos los pedidos, normalmente para administración o monitoreo.

## Observaciones DDD

- Las referencias entre contextos deberían preferir **IDs** (`ProductoId`, `ClienteId`, `CarritoId`) en lugar de acoplar entidades completas.
- Campos como **Precio**, **Email**, **Dirección** o **Total** se benefician de ser modelados como **Value Objects**.
- `Inventario` conviene definirlo con más precisión: si solo expresa valores, puede ser un Value Object; si tiene reglas, reservas o ciclo de vida propio, debería ser entidad o agregado.
- Los casos de uso pertenecen a la **capa de aplicación**, no al dominio; el dominio contiene las reglas, y la aplicación las orquesta.
