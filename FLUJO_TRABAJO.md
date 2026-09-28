# Flujo de Trabajo Recomendado

Para estudiar programación de forma sólida y coherente, se recomienda seguir este orden:

## Fase 1: Fundamentos de Arquitectura

1. **Crear la estructura del proyecto Maven con Spring Boot**
   - Configurar pom.xml con dependencias básicas
   - Configurar H2 como base de datos
   - Crear la clase principal de la aplicación
   - Validar que la aplicación corre sin errores

2. **Entender DDD y bounded contexts**
   - Leer sobre los bounded contexts del proyecto
   - Comprender cómo se separan responsabilidades
   - Identificar las capas: domain, application, infrastructure

## Fase 2: Desarrollo del Domain

3. **Crear entidades y value objects**
   - Comenzar con el contexto de Catálogo
   - Definir las entidades: Categoría, Producto, Inventario
   - Crear value objects: Precio, Cantidad
   - Luego con Customer context: Cliente
   - Luego con Cart context: Carrito, LineaCarrito
   - Finalmente con Order context: Pedido, LineaPedido, EstadoPedido

4. **Definir agregados**
   - Identificar qué es agregado raíz
   - Definir límites de cada agregado
   - Implementar invariantes de negocio

5. **Crear interfaces de repositorio**
   - No implementar aún, solo definir contratos
   - Una interfaz por agregado raíz

6. **Crear excepciones personalizadas**
   - Excepciones del domain
   - Usar para validaciones de negocio

## Fase 3: Persistencia

7. **Implementar repositorios con JPA**
   - Crear entidades JPA correspondientes
   - Crear mappers entre entidades JPA y domain
   - Implementar interfaces de repositorio
   - Comenzar con Catálogo
   - Continuar con Customer, Cart, Order

8. **Configurar la base de datos H2**
   - Crear configuración de conexión
   - Crear script de inicialización SQL
   - Validar que los datos persisten

## Fase 4: Casos de Uso

9. **Implementar casos de uso (Application)**
   - Casos de uso de Catálogo
   - Casos de uso de Customer
   - Casos de uso de Cart
   - Casos de uso de Order
   - Cada caso de uso: recibe DTOs, orquesta domain, retorna DTOs

10. **Crear DTOs y mappers**
    - DTOs de request
    - DTOs de response
    - Mappers entre DTOs y domain entities

## Fase 5: Presentación

11. **Crear controladores REST**
    - Comenzar con CategoriaController
    - ProductoController
    - ClienteController
    - CarritoController
    - PedidoController
    - Cada controller inyecta casos de uso y mapea DTOs

12. **Probar endpoints manualmente**
    - Usar Postman o curl
    - Validar que todos los endpoints funcionan
    - Verificar respuestas y códigos HTTP

## Fase 6: Testing

13. **Crear pruebas unitarias**
    - Pruebas de entidades del domain
    - Pruebas de value objects
    - Pruebas de casos de uso con mocks
    - Pruebas de mappers

14. **Crear pruebas de integración**
    - Pruebas de repositories
    - Pruebas de controladores con MockMvc
    - Pruebas end-to-end

## Fase 7: Refinamiento

15. **Refactorizar si es necesario**
    - Revisar acoplamiento
    - Mejorar nombres
    - Eliminar duplicación
    - Validar que la arquitectura es clara

16. **Crear documentación**
    - README con instrucciones de setup
    - Documentar endpoints principales
    - Crear guía de cómo extender el proyecto

## Indicadores de Progreso

- ✅ Fase 1: La aplicación corre sin errores
- ✅ Fase 2: Todas las entidades y value objects están definidos
- ✅ Fase 3: Puedo guardar y recuperar entidades de la BD
- ✅ Fase 4: Los casos de uso orquestan correctamente el domain
- ✅ Fase 5: Los endpoints REST responden correctamente
- ✅ Fase 6: Las pruebas tienen buena cobertura
- ✅ Fase 7: El código es limpio y fácil de mantener

## Notas Importantes

- **No implementar lógica de negocio**: Tu responsabilidad es agregar las reglas
- **Separación de capas**: Mantener domain independiente de infraestructura
- **Nombres claros**: Usar nombres que reflejen el negocio, no la técnica
- **Refactorizar temprano**: Si algo no se siente bien, mejóralo antes de continuar
- **Testing desde el inicio**: Escribir tests te ayuda a validar el diseño
