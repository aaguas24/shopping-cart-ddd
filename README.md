# shopping-cart-ddd
Proyecto de carrito de compras con arquitectura DDD, Spring Boot, Maven, PostgreSQL y TDD.

## PostgreSQL local

La aplicación se conecta por defecto a `jdbc:postgresql://localhost:5432/shoppingcartdb`
con el usuario `postgres`. Crea la base de datos antes de iniciar la aplicación:

```sql
CREATE DATABASE shoppingcartdb;
```

Configura las credenciales de tu instalación mediante variables de entorno. En
PowerShell:

```powershell
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "tu-clave"
mvn spring-boot:run
```

También puedes sobrescribir la URL con `DB_URL`. Hibernate actualiza el esquema
y ejecuta `src/main/resources/data.sql` al iniciar:

```powershell
mvn spring-boot:run
```

El script elimina y vuelve a crear los datos de ejemplo en las tablas de
inventario, productos, categorías, usuarios, roles y permisos en cada arranque.
No lo uses con una base que contenga datos que quieras conservar.

certificado kafka
keytool -importcert -noprompt -alias kafka-ca `
  -file "C:\proyectos\nombre-del-certificado.pem" `
-keystore "C:\proyectos\kafka-truststore.p12" `
-storetype PKCS12