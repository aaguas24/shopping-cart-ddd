# Bounded Contexts

Los bounded contexts son los contextos delimitados del dominio donde cada funcionalidad tiene un significado claro y delimitado.

## CatalogoContext
Responsable de gestionar el catálogo de productos disponibles.

### Entidades y Agregados
- **Producto**: Agregado raíz
  - ID único del producto
  - Nombre
  - Descripción
  - Precio
  - SKU
  - Categoría
  - Estado de disponibilidad

- **Categoría**: Agregado raíz
  - ID único
  - Nombre
  - Descripción

- **Inventario**: Value Object o Agregado
  - Cantidad disponible
  - Stock mínimo
  - Producto referenciado

### Interfaces de Repositorio
- ProductoRepository
- CategoriaRepository
- InventarioRepository

### Casos de Uso (Application)
- RegistrarProductoUseCase
- ConsultarCatalogoUseCase
- BuscarProductoPorIdUseCase
- FiltrarProductosPorCategoriaUseCase
- ActualizarInventarioUseCase

---

## CustomerContext
Responsable de la gestión de clientes.

### Entidades y Agregados
- **Cliente**: Agregado raíz
  - ID único del cliente
  - Nombre
  - Email
  - Teléfono
  - Dirección

### Interfaces de Repositorio
- ClienteRepository

### Casos de Uso (Application)
- RegistrarClienteUseCase
- ConsultarClientePorIdUseCase
- ListarClientesUseCase

---

## CartContext
Responsable de gestionar los carritos de compra.

### Entidades y Agregados
- **Carrito**: Agregado raíz
  - ID único del carrito
  - Cliente referenciado
  - Lista de líneas del carrito
  - Estado (abierto, confirmado)

- **LineaCarrito**: Entity dentro del agregado Carrito
  - ID único
  - Producto referenciado
  - Cantidad
  - Precio unitario

### Interfaces de Repositorio
- CarritoRepository

### Casos de Uso (Application)
- CrearCarritoUseCase
- AgregarProductoAlCarritoUseCase
- ModificarCantidadProductoUseCase
- EliminarProductoDelCarritoUseCase
- ConsultarCarritoUseCase
- CalcularTotalCarritoUseCase
- VaciarCarritoUseCase

---

## OrderContext
Responsable de gestionar los pedidos.

### Entidades y Agregados
- **Pedido**: Agregado raíz
  - ID único del pedido
  - Cliente referenciado
  - Carrito referenciado
  - Lista de líneas del pedido
  - Total
  - Fecha de creación
  - Estado del pedido

- **LineaPedido**: Entity dentro del agregado Pedido
  - ID único
  - Producto referenciado
  - Cantidad
  - Precio unitario

- **EstadoPedido**: Enum o Value Object
  - CREADO
  - CONFIRMADO
  - PROCESANDO
  - ENVIADO
  - ENTREGADO
  - CANCELADO

### Interfaces de Repositorio
- PedidoRepository

### Casos de Uso (Application)
- ConfirmarCompraUseCase
- ConsultarPedidoPorIdUseCase
- ListarPedidosDelClienteUseCase
- ActualizarEstadoPedidoUseCase
- ListarTodosPedidosUseCase
