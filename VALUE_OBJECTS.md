# Value Objects - Lógica de Negocio

Los Value Objects son objetos de valor inmutables que encapsulan conceptos del dominio. A continuación se detallan todos los value objects del proyecto con su lógica de negocio pura.

---

## 1. Precio (Bounded Context: Catálogo)

### Descripción
Representa el precio de un producto. Encapsula el valor monetario y la moneda como un concepto único e indivisible del dominio.

### Lógica de Negocio
- El precio no puede ser negativo ni nulo. Un producto siempre debe tener un precio válido.
- La moneda debe ser válida, representada por su código ISO (máximo 3 caracteres como USD, EUR, COP).
- El precio es completamente inmutable: una vez creado, no puede modificarse. Cualquier operación como sumar o multiplicar genera un nuevo objeto Precio.
- Soporta operaciones aritméticas: sumar, restar, multiplicar y dividir precios.
- Dos precios son considerados iguales si tienen exactamente el mismo valor y la misma moneda.
- No se permite operar con precios de diferentes monedas.

---

## 2. Email (Bounded Context: Cliente)

### Descripción
Representa una dirección de correo electrónico válida. Encapsula validación de formato y garantiza que solo existan emails legítimos en el sistema.

### Lógica de Negocio
- El email no puede ser nulo ni vacío. Todo cliente debe tener un email válido.
- Debe cumplir con un formato válido de correo electrónico (validación básica de RFC 5322).
- El email es único dentro del sistema. No pueden existir dos clientes con el mismo email.
- Comparaciones de emails son case-insensitive (usuario@dominio.com = USUARIO@DOMINIO.COM).
- El email es inmutable una vez creado.
- Es posible extraer información del email como el usuario y el dominio de manera segura.

---

## 3. Teléfono (Bounded Context: Cliente)

### Descripción
Representa un número de teléfono válido con componentes internacionales: código de país, código de área y número.

### Lógica de Negocio
- El código de país debe ser válido, representado con el símbolo + seguido de números (ejemplo: +1, +34, +57).
- El código de área debe tener entre 1 a 5 dígitos.
- El número debe tener entre 7 a 15 dígitos para asegurar que sea un número válido en la mayoría de países.
- El teléfono completo no puede ser nulo ni vacío.
- Es inmutable: una vez creado, no puede modificarse.
- Dos teléfonos son iguales si tienen exactamente el mismo número completo (código país + área + número).
- El sistema puede formatear el teléfono de manera legible para el usuario.

---

## 4. Cantidad (Bounded Context: Carrito)

### Descripción
Representa la cantidad de un producto agregado al carrito. Encapsula restricciones sobre valores válidos de cantidad.

### Lógica de Negocio
- La cantidad debe ser positiva: mínimo 1 unidad, máximo 1000 unidades.
- La cantidad no puede ser nula ni negativa.
- Es inmutable: cualquier cambio en la cantidad (sumar, restar) genera una nueva instancia de Cantidad.
- Soporta operaciones aritméticas: sumar cantidad a cantidad, restar, multiplicar por un factor.
- Permite comparaciones entre cantidades: mayor que, igual a, menor que.
- Si una operación resulta en una cantidad inválida (menor a 1 o mayor a 1000), se debe lanzar una excepción.

---

## 5. Total (Bounded Context: Pedido)

### Descripción
Representa el total monetario de un pedido. Encapsula el cálculo completo del total: precio base, descuentos e impuestos.

### Lógica de Negocio
- El precio base es la suma de los precios de todos los artículos en el pedido.
- El descuento es un monto que reduce el precio base. No puede ser mayor que el precio base mismo.
- El impuesto se calcula siempre sobre el subtotal (precio base menos descuento).
- El total final se calcula como: (precio base - descuento) + impuesto.
- El total nunca puede ser negativo.
- Es inmutable: cualquier cambio en componentes genera un nuevo objeto Total.
- El sistema puede calcular dinámicamente componentes como el porcentaje de impuesto aplicado.

---

## 6. EstadoPedido (Bounded Context: Pedido)

### Descripción
Encapsula los estados posibles de un pedido y las transiciones válidas entre ellos. Garantiza que el pedido solo cambie de estado de maneras que tengan sentido en el negocio.

### Estados Válidos
- **PENDIENTE**: Estado inicial cuando se crea un pedido. El cliente no ha confirmado aún.
- **CONFIRMADO**: El cliente ha confirmado explícitamente la compra.
- **PAGADO**: El pago ha sido procesado y aprobado.
- **ENVIADO**: El pedido ha sido entregado al transportista.
- **ENTREGADO**: El pedido ha llegado al cliente.
- **CANCELADO**: El pedido ha sido cancelado (puede ocurrir desde cualquier estado anterior al envío).
- **DEVUELTO**: El cliente ha devuelto el pedido después de recibirlo.

### Lógica de Negocio
- No todas las transiciones de estado son válidas. Solo están permitidas transiciones específicas:
  - De PENDIENTE solo a CONFIRMADO o CANCELADO.
  - De CONFIRMADO solo a PAGADO o CANCELADO.
  - De PAGADO solo a ENVIADO o CANCELADO.
  - De ENVIADO solo a ENTREGADO o CANCELADO.
  - De ENTREGADO solo a DEVUELTO (dentro de un período permitido).
  - Desde cualquier estado anterior al envío a CANCELADO.
- Un pedido cancelado es final: no puede cambiar a otro estado.
- Un pedido entregado no puede volver a estados anteriores, excepto a DEVUELTO.
- Un pedido que ha sido devuelto tampoco puede cambiar de estado.
- El sistema debe validar cada cambio de estado para asegurar que solo ocurran transiciones legales.

---

## Principios Transversales de los Value Objects

### Inmutabilidad
Todos los value objects son completamente inmutables. Una vez creados, sus atributos no pueden cambiar. Esta característica garantiza que el objeto mantiene su estado consistente durante toda su vida útil y es seguro de compartir entre múltiples partes del sistema sin riesgo de efectos secundarios.

### Validación en Construcción
La validación ocurre en el momento de crear el value object, en el constructor. Si los datos no cumplen con las reglas de negocio, se lanza una excepción inmediatamente. Esto garantiza que nunca existe un value object en estado inválido.

### Identidad por Valor
Los value objects no tienen identificador único. Dos value objects son considerados idénticos si todos sus atributos son iguales. Esto es diferente a las entidades, que tienen identidad única.

### Encapsulación de Lógica
Cada value object encapsula la lógica de negocio relacionada con el concepto que representa. Por ejemplo, Precio sabe cómo sumar precios, Cantidad sabe cómo validar cantidades, Total sabe cómo calcular totales.

### Excepciones de Negocio
Cuando algo viola las reglas de negocio, se lanzan excepciones específicas del dominio. Estas excepciones comunican claramente qué regla fue violada.

---

## Beneficios de los Value Objects

1. **Claridad del Dominio**: El código es más legible porque usa conceptos del negocio, no tipos primitivos (Precio en lugar de BigDecimal).

2. **Seguridad de Tipos**: El compilador ayuda a detectar errores al usar tipos específicos (no se puede pasar un Email donde se espera un Teléfono).

3. **Reutilización**: Los value objects pueden compartirse libremente entre diferentes partes del sistema sin efectos secundarios.

4. **Validación Centralizada**: Las reglas de negocio están en un solo lugar, facilitando el mantenimiento.

5. **Comparaciones Confiables**: Al comparar valor por valor, la lógica es transparente y predecible.

6. **Persistencia Consistente**: El mapeo a base de datos es más claro cuando se trata con conceptos del dominio.

---

## Ubicación en el Proyecto

Los value objects deben ubicarse en carpetas específicas dentro de cada bounded context:

- **Precio**: En la carpeta `domain/catalog/valueobject/`
- **Email y Teléfono**: En la carpeta `domain/customer/valueobject/`
- **Cantidad**: En la carpeta `domain/cart/valueobject/`
- **Total y EstadoPedido**: En la carpeta `domain/order/valueobject/`

Las excepciones específicas de cada value object deben ubicarse en `domain/{contexto}/exception/`



