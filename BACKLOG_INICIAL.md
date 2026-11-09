# Backlog Inicial

Este es el backlog ordenado del proyecto de carrito de compras. Se recomienda seguir este orden para un desarrollo estructurado y coherente.

## Sprint 1: Setup y Configuración Base

- [ ] Crear estructura Maven con Spring Boot 3.x
- [ ] Configurar H2 como base de datos en memoria
- [ ] Crear paquete de configuración central
- [ ] Configurar application.properties
- [ ] Crear clase principal    de la aplicación
- [ ] Validar que la aplicación corre sin errores

## Sprint 2: Domain - Bounded Context Catálogo

- [ ] Crear entidad Categoría en domain
- [ ] Crear entidad Producto en domain
- [ ] Crear value object Precio
- [ ] Crear agregado Inventario
- [ ] Crear excepciones de negocio del contexto
- [ ] Crear interfaces de repositorio (sin implementación)

## Sprint 3: Domain - Bounded Context Cliente

- [ ] Crear entidad Cliente en domain
- [ ] Crear value object Email
- [ ] Crear value object Teléfono
- [ ] Crear excepciones de negocio del contexto
- [ ] Crear interfaz de repositorio ClienteRepository

## Sprint 4: Domain - Bounded Context Carrito

- [ ] Crear agregado Carrito
- [ ] Crear entidad LineaCarrito
- [ ] Crear value object Cantidad
- [ ] Crear excepciones de negocio del contexto
- [ ] Crear interfaz de repositorio CarritoRepository

## Sprint 5: Domain - Bounded Context Pedido

- [ ] Crear agregado Pedido
- [ ] Crear entidad LineaPedido
- [ ] Crear enum EstadoPedido
- [ ] Crear value object Total
- [ ] Crear excepciones de negocio del contexto
- [ ] Crear interfaz de repositorio PedidoRepository

## Sprint 6: Infrastructure - Persistencia Catálogo

- [ ] Crear entidades JPA para Categoría
- [ ] Crear entidades JPA para Producto
- [ ] Crear entidades JPA para Inventario
- [ ] Implementar RepositoryCategoría con JPA
- [ ] Implementar RepositoryProducto con JPA
- [ ] Implementar RepositoryInventario con JPA
- [ ] Crear mappers entre domain y JPA

## Sprint 7: Infrastructure - Persistencia Cliente

- [ ] Crear entidad JPA para Cliente
- [ ] Implementar RepositoryCliente con JPA
- [ ] Crear mapper entre domain y JPA

## Sprint 8: Infrastructure - Persistencia Carrito

- [ ] Crear entidades JPA para Carrito
- [ ] Crear entidades JPA para LineaCarrito
- [ ] Implementar RepositoryCarrito con JPA
- [ ] Crear mappers entre domain y JPA

## Sprint 9: Infrastructure - Persistencia Pedido

- [ ] Crear entidades JPA para Pedido
- [ ] Crear entidades JPA para LineaPedido
- [ ] Implementar RepositoryPedido con JPA
- [ ] Crear mappers entre domain y JPA

## Sprint 10: Application - Casos de Uso Catálogo

- [ ] Implementar RegistrarCategoriaUseCase
- [ ] Implementar ConsultarCategoriaUseCase
- [ ] Implementar RegistrarProductoUseCase
- [ ] Implementar ConsultarProductoPorIdUseCase
- [ ] Implementar ConsultarCatalogoCompletoUseCase
- [ ] Implementar FiltrarProductosPorCategoriaUseCase
- [ ] Implementar ActualizarInventarioUseCase

## Sprint 11: Application - Casos de Uso Cliente

- [ ] Implementar RegistrarClienteUseCase
- [ ] Implementar ConsultarClientePorIdUseCase
- [ ] Implementar ListarClientesUseCase

## Sprint 12: Application - Casos de Uso Carrito

- [ ] Implementar CrearCarritoUseCase
- [ ] Implementar AgregarProductoAlCarritoUseCase
- [ ] Implementar ModificarCantidadProductoUseCase
- [ ] Implementar EliminarProductoDelCarritoUseCase
- [ ] Implementar ConsultarCarritoUseCase
- [ ] Implementar CalcularTotalCarritoUseCase
- [ ] Implementar VaciarCarritoUseCase

## Sprint 13: Application - Casos de Uso Pedido

- [ ] Implementar ConfirmarCompraUseCase
- [ ] Implementar ConsultarPedidoPorIdUseCase
- [ ] Implementar ListarPedidosDelClienteUseCase
- [ ] Implementar ActualizarEstadoPedidoUseCase
- [ ] Implementar ListarTodosPedidosUseCase

## Sprint 14: Application - DTOs y Mappers

- [ ] Crear DTOs de request/response para Categoría
- [ ] Crear DTOs de request/response para Producto
- [ ] Crear DTOs de request/response para Cliente
- [ ] Crear DTOs de request/response para Carrito
- [ ] Crear DTOs de request/response para Pedido
- [ ] Implementar mappers entre DTOs y domain

## Sprint 15: REST - Controladores Catálogo

- [ ] Crear CategoriaController
- [ ] Crear ProductoController
- [ ] Implementar endpoints GET, POST para Categoría
- [ ] Implementar endpoints GET, POST para Producto
- [ ] Implementar endpoint para filtrar por categoría

## Sprint 16: REST - Controladores Cliente

- [ ] Crear ClienteController
- [ ] Implementar endpoints GET, POST para Cliente
- [ ] Implementar endpoint para listar clientes

## Sprint 17: REST - Controladores Carrito

- [ ] Crear CarritoController
- [ ] Implementar endpoints para agregar producto
- [ ] Implementar endpoints para modificar cantidad
- [ ] Implementar endpoints para eliminar producto
- [ ] Implementar endpoint para consultar carrito
- [ ] Implementar endpoint para calcular total

## Sprint 18: REST - Controladores Pedido

- [ ] Crear PedidoController
- [ ] Implementar endpoint para confirmar compra
- [ ] Implementar endpoints para consultar pedidos
- [ ] Implementar endpoint para actualizar estado

## Sprint 19: Testing - Pruebas Unitarias

- [ ] Crear pruebas para entidades del domain
- [ ] Crear pruebas para value objects
- [ ] Crear pruebas para casos de uso
- [ ] Crear pruebas para mappers

## Sprint 20: Testing - Pruebas de Integración

- [ ] Crear pruebas de integración para repositories
- [ ] Crear pruebas de integración para controladores
- [ ] Crear fixtures de datos para testing

## Sprint 21: Documentación y Finalización

- [ ] Crear README.md con instrucciones de setup
- [ ] Documentar endpoints principales
- [ ] Crear script de datos iniciales
- [ ] Revisar código y refactorizar si es necesario
- [ ] Crear guía de extensión del proyecto
