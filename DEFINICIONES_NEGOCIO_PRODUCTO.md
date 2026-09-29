# Definiciones de Negocio del Producto

## Objetivo
Consolidar en un solo documento las definiciones de negocio existentes para el agregado `Producto` dentro del `CatalogoContext`, tomando como fuente la documentación funcional y el modelo actual del proyecto.

## Fuentes consolidadas
- `REQUISITOS_FUNCIONALES.md`
- `BOUNDED_CONTEXTS.md`
- `HISTORIAS_USUARIO.md`
- `VALUE_OBJECTS.md`
- `DEFINICIONES.md`
- `src/main/java/com/tuempresa/shoppingcart/domain/catalog/entity/Producto.java`
- `src/main/java/com/tuempresa/shoppingcart/domain/catalog/valueobject/Precio.java`
- `src/main/java/com/tuempresa/shoppingcart/shared/enums/EstadoProducto.java`

---

## 1. Propósito del agregado `Producto`
`Producto` es la raíz del agregado dentro del contexto de catálogo. Su responsabilidad es representar un artículo comercial disponible para la venta y concentrar la información principal que permite:
- registrarlo en el catálogo,
- consultarlo,
- clasificarlo,
- conocer su disponibilidad,
- y permitir su referencia desde otros contextos como carrito y pedidos.

Desde negocio, `Producto` es la entidad principal sobre la que gira la oferta comercial del sistema.

---

## 2. Atributos de negocio del producto

### 2.1 Identificador único
- Corresponde al identificador irrepetible del producto dentro del sistema.
- Su propósito es distinguir un producto de cualquier otro sin ambigüedad.
- Debe permitir que otros contextos lo referencien de forma desacoplada.

### 2.2 Nombre
- Es el nombre comercial visible para usuarios y procesos de negocio.
- Permite identificar el producto en listados, búsquedas y vistas del catálogo.
- Debe ser un dato obligatorio del producto.

### 2.3 Descripción
- Explica qué es el producto desde el punto de vista funcional o comercial.
- Complementa el nombre con contexto útil para la compra.
- Debe formar parte del registro del producto.

### 2.4 Precio
- Es el valor monetario unitario del producto.
- Está modelado como un `Value Object`.
- Debe incluir valor y moneda.
- El precio debe ser positivo.
- Las operaciones monetarias solo son válidas entre precios con la misma moneda.
- El precio debe tratarse como un valor consistente e inmutable.

### 2.5 SKU
- Es el código único del producto para fines operativos e inventario.
- Permite identificar el producto comercialmente y evitar duplicados.
- Es obligatorio y no puede repetirse.

### 2.6 Categoría
- Es la clasificación del producto dentro del negocio.
- Permite agrupar, organizar y filtrar productos.
- Es parte obligatoria de la definición del producto.

### 2.7 Estado de disponibilidad
- Define la situación actual del producto dentro del catálogo.
- El estado condiciona si puede venderse o no.
- Los estados identificados actualmente son:
  - `ACTIVO`
  - `INACTIVO`
  - `AGOTADO`

---

## 3. Reglas de negocio para registrar productos
Según los requisitos funcionales, al registrar un producto deben cumplirse estas reglas:

1. El producto debe tener:
   - código único,
   - nombre,
   - descripción,
   - precio,
   - categoría,
   - disponibilidad.

2. No se debe permitir duplicar productos con el mismo código.

3. El registro del producto debe crear una representación válida del artículo desde su origen.

4. La información mínima del producto debe ser suficiente para que el catálogo pueda consultarlo y usarlo comercialmente.

---

## 4. Reglas de unicidad

### 4.1 Unicidad del identificador
- Cada producto debe tener un identificador único dentro del sistema.

### 4.2 Unicidad del SKU
- No pueden existir dos productos con el mismo SKU.
- La validación de duplicidad debe realizarse antes de confirmar el registro.

### 4.3 Prevención de duplicados de catálogo
- La unicidad del código comercial evita inconsistencias en inventario, carrito y pedidos.

---

## 5. Definición de disponibilidad e inventario
La documentación separa conceptualmente el producto de la gestión cuantitativa de existencias.

### 5.1 Disponibilidad
- El producto expresa su disponibilidad mediante su estado.
- La disponibilidad determina si puede ser ofrecido o agregado al carrito.

### 5.2 Inventario
- Cada producto debe tener cantidad disponible.
- El inventario debe reflejar si un producto está activo o agotado.
- Existe el concepto de stock mínimo como umbral de reabastecimiento.

### 5.3 Relación entre inventario y estado
- Si no hay existencias disponibles, el producto debe reflejar estado `AGOTADO`.
- Si el producto está habilitado para venta y tiene existencias, debe poder considerarse `ACTIVO`.
- El estado `INACTIVO` representa una deshabilitación que no depende necesariamente del stock.

---

## 6. Estados del producto

### 6.1 `ACTIVO`
- El producto está disponible para la venta.
- Puede formar parte del catálogo utilizable por el cliente.
- Puede ser agregado al carrito, sujeto a reglas de cantidad e inventario.

### 6.2 `INACTIVO`
- El producto no está habilitado comercialmente.
- No debería poder agregarse a nuevos carritos.
- Puede responder a decisiones administrativas o comerciales.

### 6.3 `AGOTADO`
- El producto no tiene disponibilidad de inventario.
- No debe poder agregarse al carrito mientras permanezca agotado.
- Su condición está ligada a la cantidad disponible.

---

## 7. Relación del producto con el carrito de compras
El producto participa en el flujo del carrito como referencia de negocio para crear líneas de compra.

### 7.1 Agregar producto al carrito
- El sistema permite agregar un producto y una cantidad al carrito.
- Si el producto ya existe en el carrito, la cantidad debe sumarse o actualizarse.

### 7.2 Restricciones implícitas para agregar al carrito
- El producto debe estar disponible para la venta.
- No debe estar `INACTIVO` ni `AGOTADO`.
- La cantidad debe ser válida y coherente con la disponibilidad.

### 7.3 Cantidad en carrito
- La cantidad modificada debe ser mayor o igual a 1.
- Si la cantidad llega a 0, la línea debe eliminarse.

### 7.4 Precio en carrito
- El carrito debe mostrar precios unitarios y subtotales.
- El total se calcula con base en cantidades y precio del producto.
- Esto implica que `Producto` aporta el precio unitario como valor base para la línea del carrito.

---

## 8. Restricciones y validaciones del dominio

### 8.1 Validaciones mínimas del producto
Como definición de negocio, un producto válido debe tener:
- identificador,
- nombre,
- descripción,
- precio,
- SKU,
- categoría,
- estado.

### 8.2 Validaciones del precio
Con base en el `Value Object` `Precio`:
- el valor no puede ser nulo,
- el valor debe ser mayor que cero,
- la moneda debe ser válida,
- no se deben realizar operaciones entre monedas diferentes,
- no se puede dividir por cero.

### 8.3 Consistencia comercial
- Un producto no debe quedar registrado en un estado inconsistente.
- La disponibilidad debe corresponder con la realidad del inventario.
- El producto debe poder ser consultado por identificador y clasificado por categoría.

---

## 9. Decisiones de dominio implícitas

### 9.1 `Producto` como agregado raíz
El producto es la entrada principal del contexto de catálogo para crear, consultar y modificar información comercial.

### 9.2 Referencias por identificador
Otros contextos deberían referenciar al producto por ID y no por acoplamiento directo a la entidad completa.

### 9.3 `Precio` como `Value Object`
La representación monetaria no debe tratarse como dato primitivo aislado, sino como un valor con reglas propias.

### 9.4 Inventario como concepto propio
El inventario tiene reglas de negocio suficientemente claras como para considerarse un componente diferenciado dentro del contexto de catálogo.

### 9.5 Categoría como clasificación de negocio
La categoría no es decorativa: participa en filtros, organización del catálogo y consulta comercial.

### 9.6 El producto condiciona la venta
El hecho de que un producto esté activo, inactivo o agotado impacta directamente su posibilidad de formar parte de un carrito y, por tanto, de una compra.

---

## 10. Responsabilidades de negocio del producto
`Producto` es responsable de:
- representar un artículo comercial del catálogo,
- mantener su identidad única,
- exponer sus atributos comerciales esenciales,
- sostener su precio como valor monetario válido,
- expresar su disponibilidad,
- y servir de referencia para procesos de carrito y pedido.

`Producto` no debería asumir por sí solo:
- la persistencia,
- la orquestación de casos de uso,
- ni toda la gestión transaccional del inventario si este se modela como componente separado.

---

## 11. Resumen ejecutivo
Las definiciones de negocio encontradas muestran que `Producto` debe entenderse como el centro del catálogo comercial y no solo como una clase de datos. El dominio ya establece que:
- un producto tiene identidad, clasificación, precio y disponibilidad,
- su código debe ser único,
- su precio tiene reglas monetarias propias,
- su disponibilidad depende del estado y de las existencias,
- y su uso principal es habilitar consulta de catálogo y agregación al carrito.

Este documento recoge únicamente definiciones de negocio existentes; no introduce diseño técnico nuevo fuera de lo implícitamente documentado en el proyecto.

