# Estructura del Proyecto

La estructura recomendada del proyecto sigue el patrón de arquitectura hexagonal con DDD:

```
shopping-cart-ddd/
│
├── src/main/java/com/tuempresa/shoppingcart/
│   ├── application/
│   │   ├── command/
│   │   │   ├── catalog/
│   │   │   ├── customer/
│   │   │   ├── cart/
│   │   │   └── order/
│   │   ├── query/
│   │   │   ├── catalog/
│   │   │   ├── customer/
│   │   │   ├── cart/
│   │   │   └── order/
│   │   ├── handler/
│   │   │   ├── command/
│   │   │   └── query/
│   │   ├── service/
│   │   │   ├── catalog/
│   │   │   ├── customer/
│   │   │   ├── cart/
│   │   │   └── order/
│   │   ├── dto/
│   │   │   ├── request/
│   │   │   └── response/
│   │   └── mapper/
│   │
│   ├── domain/
│   │   ├── catalog/
│   │   │   ├── aggregate/
│   │   │   ├── entity/
│   │   │   ├── valueobject/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   ├── event/
│   │   │   ├── specification/
│   │   │   └── exception/
│   │   ├── customer/
│   │   │   ├── aggregate/
│   │   │   ├── entity/
│   │   │   ├── valueobject/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   ├── event/
│   │   │   ├── specification/
│   │   │   └── exception/
│   │   ├── cart/
│   │   │   ├── aggregate/
│   │   │   ├── entity/
│   │   │   ├── valueobject/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   ├── event/
│   │   │   ├── specification/
│   │   │   └── exception/
│   │   ├── order/
│   │   │   ├── aggregate/
│   │   │   ├── entity/
│   │   │   ├── valueobject/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   ├── event/
│   │   │   ├── specification/
│   │   │   └── exception/
│   │   └── shared/
│   │       ├── aggregate/
│   │       ├── entity/
│   │       ├── valueobject/
│   │       └── event/
│   │
│   ├── infrastructure/
│   │   ├── persistence/
│   │   │   ├── jpa/
│   │   │   │   ├── entity/
│   │   │   │   ├── mapper/
│   │   │   │   └── specification/
│   │   │   ├── repositories/
│   │   │   └── eventstore/
│   │   ├── config/
│   │   │   ├── AppConfig.java
│   │   │   ├── DataSourceConfig.java
│   │   │   └── JpaConfig.java
│   │   ├── rest/
│   │   │   ├── controllers/
│   │   │   └── mapper/
│   │   ├── bus/
│   │   │   ├── command/
│   │   │   └── event/
│   │   └── external/
│   │       └── (integraciones externas)
│   │
│   ├── shared/
│   │   ├── domain/
│   │   │   ├── aggregate/
│   │   │   ├── entity/
│   │   │   ├── valueobject/
│   │   │   └── event/
│   │   ├── exception/
│   │   ├── constants/
│   │   └── utils/
│   │
│   └── ShoppingCartApplication.java
│
├── src/test/java/com/tuempresa/shoppingcart/
│   ├── unit/
│   │   ├── domain/
│   │   ├── application/
│   │   └── infrastructure/
│   ├── integration/
│   ├── fixtures/
│   └── utils/
│
├── src/main/resources/
│   ├── application.properties
│   ├── application-dev.properties
│   ├── application-h2.properties
│   ├── application-prod.properties
│   ├── schema.sql
│   └── data.sql
│
├── src/test/resources/
│   ├── application-test.properties
│   └── test-data.sql
│
├── pom.xml
├── README.md
└── .gitignore
```

## Descripción de Carpetas

### application/
Capa de aplicación: orquestación de casos de uso, DTOs y mappers.
**⚠️ IMPORTANTE**: Esta capa es llamada por los REST controllers de infraestructura.

Flujo de llamadas:
```
REST Controller (infrastructure) → Application Service (application) → Domain (business logic)
```

- **command/**: Comandos que modifican el estado (Write Model) - Optional (si usas CQRS)
  - Contiene clases Command para cada caso de uso
  - Ejemplo: `CreateOrderCommand`, `AddItemToCartCommand`
- **query/**: Consultas de lectura (Read Model) - Optional (si usas CQRS)
  - Contiene clases Query para obtener datos
  - Ejemplo: `GetOrderByIdQuery`, `ListProductsQuery`
- **handler/**: Manejadores de comandos y consultas - Optional (si usas CQRS)
  - **command/**: Implementan la lógica de casos de uso para modificar estado
  - **query/**: Implementan la lógica de consultas
- **service/**: Application Services (RECOMENDADO - Siempre presente)
  - **Responsabilidad**: Orquestar el domain sin contener lógica de negocio
  - **Llamadas**: Coordinan domain services, repositories y publican eventos
  - Ejemplo: `CreateOrderApplicationService`, `GetOrderApplicationService`
  - **Quién las llama**: REST controllers inyectan estas services
- **dto/**: Data Transfer Objects (separados del domain)
  - **request/**: DTOs para entrada de datos desde REST
  - **response/**: DTOs para salida de datos hacia REST
- **mapper/**: Mapeos entre DTOs y comandos/queries/objetos de dominio

### domain/
Capa de dominio: lógica de negocio pura, aislada de frameworks. **Corazón del DDD**
- Separado por **bounded context** (catalog, customer, cart, order)
- Cada bounded context contiene:
  - **aggregate/**: Agregados raíz que encapsulan la lógica de negocio
  - **entity/**: Entidades del dominio con identidad única
  - **valueobject/**: Objetos de valor inmutables (Money, ProductId, etc.)
  - **repository/**: Interfaces de repositorio (sin implementación)
  - **service/**: Domain Services para lógica que no cabe en una entidad
  - **event/**: Eventos de dominio (DomainEvent) que registran cambios importantes
  - **specification/**: Especificaciones para queries complejas
  - **exception/**: Excepciones propias del dominio
- **shared/**: Código compartido entre bounded contexts
  - Agregados, entidades y value objects comunes

### infrastructure/
Capa de infraestructura: implementación de detalles técnicos.
- **persistence/**: Toda la lógica de persistencia
  - **jpa/**: Mapeo ORM con JPA
    - **entity/**: Entidades JPA (sin lógica de negocio)
    - **mapper/**: Mapeo bidireccional entre JPA entities y domain entities
    - **specification/**: Especificaciones JPA para queries dinámicas
  - **repositories/**: Implementación de interfaces de repositorio del domain
  - **eventstore/**: Almacenamiento de eventos (Event Sourcing)
- **config/**: Configuración de Spring
  - AppConfig: Configuración general
  - DataSourceConfig: Configuración de base de datos
  - JpaConfig: Configuración de JPA
- **rest/**: Controladores REST (Puertos de entrada - Adaptadores)
  - **controllers/**: Endpoints REST que LLAMAN a Application Services
    - **Inyectan**: Application Services en el constructor
    - **Transforman**: Requests REST → DTOs → Comandos/Consultas
    - **Coordinan**: REST Controller → Application Service → Domain
  - **mapper/**: Mapeo entre requests REST y DTOs
- **bus/**: Implementación de buses de eventos y comandos
  - **command/**: Bus de comandos (dispatcher)
  - **event/**: Bus de eventos para publicar DomainEvents
- **external/**: Integraciones con servicios externos (APIs, mensajería, etc.)

### shared/
Código compartido y transversal en toda la aplicación.
- **domain/**: Elementos de dominio compartidos
  - Bases para agregados, entidades, value objects
  - Interfaz base para DomainEvent
- **exception/**: Excepciones transversales
  - Excepciones de aplicación
  - Excepciones de validación
- **constants/**: Constantes del proyecto
- **utils/**: Utilidades generales

### tests/
Pruebas en diferentes niveles (TDD/BDD).
- **unit/**: Pruebas unitarias
  - **domain/**: Test de entidades, value objects y services
  - **application/**: Test de handlers y services
  - **infrastructure/**: Test de mappers y configuración
- **integration/**: Pruebas de integración
  - Test end-to-end con base de datos
  - Test de repositorios
- **fixtures/**: Datos de prueba y builders
- **utils/**: Utilidades para tests

### resources/
Configuración y scripts de base de datos.
- **main/resources/**:
  - `application.properties`: Configuración por defecto
  - `application-dev.properties`: Configuración para desarrollo
  - `application-h2.properties`: Configuración para H2 (test)
  - `application-prod.properties`: Configuración para producción
  - `schema.sql`: Script de creación de tablas
  - `data.sql`: Datos iniciales
- **test/resources/**:
  - `application-test.properties`: Configuración para tests
  - `test-data.sql`: Datos para tests

## Principios DDD aplicados ✨

1. **Ubiquitous Language**: Usa el lenguaje del negocio en el código
2. **Bounded Contexts**: Cada contexto está claramente separado
3. **Agregados**: Entidades raíz que protegen la consistencia transaccional
4. **Value Objects**: Objetos inmutables para representar conceptos del dominio
5. **Domain Events**: Registra cambios importantes en el dominio
6. **Repositories**: Acceso a agregados mediante interfaces del dominio
7. **Domain Services**: Lógica que no cabe naturalmente en una entidad
8. **Application Services**: Orquestar el dominio sin contener lógica de negocio
9. **Inversión de Dependencias**: El domain NO depende de infrastructure

## Flujo de Llamadas entre Capas 🔄

### Caso típico: Crear una orden

```
┌─────────────────────────────────────────────────────────────────┐
│ 1. HTTP POST /orders (Cliente)                                   │
└──────────────────────────────┬──────────────────────────────────┘
                               ↓
┌─────────────────────────────────────────────────────────────────┐
│ 2. REST Controller (infrastructure/rest/controllers/)             │
│    - Recibe: CreateOrderRequest (JSON)                          │
│    - Mapea: Request → CreateOrderDTO                            │
│    - Inyecta: CreateOrderApplicationService                     │
│    - Llama: applicationService.create(dto)                      │
└──────────────────────────────┬──────────────────────────────────┘
                               ↓
┌─────────────────────────────────────────────────────────────────┐
│ 3. Application Service (application/service/order/)              │
│    - Recibe: CreateOrderDTO                                     │
│    - Mapea: DTO → Agregado Order (Domain)                       │
│    - Coordina: Llama a domain services y repositories           │
│    - Inyecta: OrderRepository (interfaz), CustomerService       │
│    - Persiste: repository.save(order)                           │
│    - Publica: domainEventPublisher.publish(OrderCreatedEvent)  │
│    - Retorna: OrderResponseDTO                                  │
└──────────────────────────────┬──────────────────────────────────┘
                               ↓
┌─────────────────────────────────────────────────────────────────┐
│ 4. Domain (Lógica de negocio)                                    │
│    - Order Aggregate (domain/order/aggregate/)                  │
│    - Value Objects (Money, Quantity, OrderStatus)              │
│    - Domain Services (OrderCalculationService)                 │
│    - Repository Interface (domain/order/repository/)            │
└──────────────────────────────┬──────────────────────────────────┘
                               ↓
┌─────────────────────────────────────────────────────────────────┐
│ 5. Infrastructure - Persistencia (infrastructure/persistence/)   │
│    - Repository Implementation: OrderRepositoryImpl             │
│    - JPA Mapper: Order (Domain) → OrderJpaEntity               │
│    - Database: Guarda la orden                                  │
└──────────────────────────────┬──────────────────────────────────┘
                               ↓
┌─────────────────────────────────────────────────────────────────┐
│ 6. Infrastructure - Event Bus (infrastructure/bus/event/)        │
│    - Event Publisher: Publica OrderCreatedEvent                │
│    - Event Listeners: Otros bounded contexts se enteras        │
└──────────────────────────────┬──────────────────────────────────┘
                               ↓
┌─────────────────────────────────────────────────────────────────┐
│ 7. REST Controller Retorna Respuesta                             │
│    - HTTP 201 CREATED con OrderResponseDTO                     │
└──────────────────────────────┬──────────────────────────────────┘
                               ↓
┌─────────────────────────────────────────────────────────────────┐
│ 8. Cliente Recibe Response                                       │
└─────────────────────────────────────────────────────────────────┘
```

### Dependencias entre capas (Inversión de Dependencias):

```
┌────────────────────┐
│  infrastructure/   │
│   (detalles)       │
└────────────────────┘
         ↑ (depende de)
         │
┌────────────────────┐
│   application/     │
│  (orquestación)    │
└────────────────────┘
         ↑ (depende de)
         │
┌────────────────────┐
│    domain/         │
│  (lógica pura)     │  ← NO DEPENDE DE NADA
└────────────────────┘
```

### Inyección de Dependencias:

```
REST Controller
  ├─ @Inject CreateOrderApplicationService
  │   ├─ @Inject OrderRepository (interfaz)
  │   │   └─ Implementada en: OrderRepositoryImpl
  │   ├─ @Inject CustomerRepository (interfaz)
  │   │   └─ Implementada en: CustomerRepositoryImpl
  │   └─ @Inject OrderCalculationDomainService
  │
  └─ @Inject OrderMapper
```

## Responsabilidades por capa:

| Capa | Responsabilidad | Ejemplos |
|------|-----------------|----------|
| **REST Controller** | Recibir HTTP, mapear DTOs, llamar services | POST /orders |
| **Application Service** | Orquestar el caso de uso, coordinar domain | CreateOrderApplicationService |
| **Domain Service** | Lógica de negocio compleja | CalculatePriceService |
| **Domain Aggregate** | Proteger invariantes, cambios de estado | Order entity |
| **Repository** | Persistencia de agregados | OrderRepository |
| **JPA Entity** | Mapeo a base de datos | OrderJpaEntity |

## Arquitectura Hexagonal - Puertos y Adaptadores:

```
    ┌─────────────────────────────────────┐
    │  REST Controller  (Adapter - Entry)  │
    └──────────────────┬──────────────────┘
                       │
                 Application
                   Service
                  (Puerto)
                       │
    ┌──────────────────┴──────────────────┐
    │          Domain Layer                │
    │    (Lógica de negocio pura)          │
    └──────────────────┬──────────────────┘
                       │
                  Repository
                  (Puerto)
                       │
    ┌──────────────────┴──────────────────┐
    │  JPA Repository  (Adapter - Exit)    │
    │  Database (Adapter - Exit)           │
    └──────────────────────────────────────┘
```

