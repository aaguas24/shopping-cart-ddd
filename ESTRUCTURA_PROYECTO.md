# Estructura del Proyecto

La estructura recomendada del proyecto sigue el patrón de arquitectura hexagonal con DDD:

```
shopping-cart-ddd/
│
├── src/main/java/com/tuempresa/shoppingcart/
│   ├── application/
│   │   ├── usecases/
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
│   │   │   ├── entity/
│   │   │   ├── valueobject/
│   │   │   ├── repository/
│   │   │   └── exception/
│   │   ├── customer/
│   │   │   ├── entity/
│   │   │   ├── valueobject/
│   │   │   ├── repository/
│   │   │   └── exception/
│   │   ├── cart/
│   │   │   ├── entity/
│   │   │   ├── valueobject/
│   │   │   ├── repository/
│   │   │   └── exception/
│   │   ├── order/
│   │   │   ├── entity/
│   │   │   ├── valueobject/
│   │   │   ├── repository/
│   │   │   └── exception/
│   │   └── common/
│   │       ├── entity/
│   │       └── valueobject/
│   │
│   ├── infrastructure/
│   │   ├── persistence/
│   │   │   ├── jpa/
│   │   │   │   ├── entity/
│   │   │   │   └── mapper/
│   │   │   └── repositories/
│   │   ├── config/
│   │   │   └── AppConfig.java
│   │   └── rest/
│   │       ├── controllers/
│   │       ├── request/
│   │       └── response/
│   │
│   ├── shared/
│   │   ├── exception/
│   │   ├── constants/
│   │   └── utils/
│   │
│   └── ShoppingCartApplication.java
│
├── src/test/java/com/tuempresa/shoppingcart/
│   ├── unit/
│   ├── integration/
│   └── fixtures/
│
├── src/main/resources/
│   ├── application.properties
│   ├── application-h2.properties
│   └── data.sql
│
├── pom.xml
├── README.md
└── .gitignore
```

## Descripción de Carpetas

### application/
Contiene la lógica de orquestación de casos de uso, DTOs y mappers.
- **usecases/**: Implementación de casos de uso
- **dto/**: Data Transfer Objects
- **mapper/**: Mapeadores entre entidades y DTOs

### domain/
Contiene la lógica de negocio pura, entidades, agregados y value objects.
- Separado por bounded context
- Cada contexto tiene su carpeta con: entity, valueobject, repository, exception

### infrastructure/
Capa de infraestructura con detalles técnicos.
- **persistence/**: JPA, repositories, mappers de persistencia
- **config/**: Configuración de Spring
- **rest/**: Controladores REST

### shared/
Código compartido en toda la aplicación.
- **exception/**: Excepciones personalizadas
- **constants/**: Constantes del proyecto
- **utils/**: Utilidades generales

### tests/
Pruebas unitarias e integración.

### resources/
Configuración, scripts SQL y properties.
