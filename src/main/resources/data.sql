DELETE FROM inventario;
DELETE FROM producto;
DELETE FROM categoria;

INSERT INTO categoria (id, nombre, descripcion) VALUES
    ('11111111-1111-4111-8111-111111111111', 'Electrónica', 'Dispositivos y accesorios tecnológicos'),
    ('22222222-2222-4222-8222-222222222222', 'Hogar', 'Productos para el hogar y la cocina'),
    ('33333333-3333-4333-8333-333333333333', 'Deportes', 'Artículos deportivos y de actividad'),
    ('44444444-4444-4444-8444-444444444444', 'Belleza', 'Productos de cuidado personal'),
    ('55555555-5555-4555-8555-555555555555', 'Jardín', 'Accesorios y herramientas para exteriores');

INSERT INTO producto (id, nombre, descripcion, estado, precio, moneda, codigo_sku, categoria_id)
SELECT
    RANDOM_UUID(),
    CONCAT(
        CASE MOD(x, 25)
            WHEN 0 THEN 'Dell'
            WHEN 1 THEN 'Samsung'
            WHEN 2 THEN 'Lenovo'
            WHEN 3 THEN 'Apple'
            WHEN 4 THEN 'Sony'
            WHEN 5 THEN 'HP'
            WHEN 6 THEN 'Asus'
            WHEN 7 THEN 'LG'
            WHEN 8 THEN 'Philips'
            WHEN 9 THEN 'Bosch'
            WHEN 10 THEN 'Nike'
            WHEN 11 THEN 'Adidas'
            WHEN 12 THEN 'Wilson'
            WHEN 13 THEN 'Giro'
            WHEN 14 THEN 'CamelBak'
            WHEN 15 THEN 'Maybelline'
            WHEN 16 THEN 'LOréal'
            WHEN 17 THEN 'Nivea'
            WHEN 18 THEN 'Dove'
            WHEN 19 THEN 'Stihl'
            ELSE 'Ryobi'
        END,
        ' ',
        CASE MOD(x, 22)
            WHEN 0 THEN 'Laptop'
            WHEN 1 THEN 'Smartphone'
            WHEN 2 THEN 'Monitor'
            WHEN 3 THEN 'Teclado'
            WHEN 4 THEN 'Mouse'
            WHEN 5 THEN 'Auriculares'
            WHEN 6 THEN 'Router'
            WHEN 7 THEN 'Televisor'
            WHEN 8 THEN 'Licuadora'
            WHEN 9 THEN 'Cafetera'
            WHEN 10 THEN 'Aspiradora'
            WHEN 11 THEN 'Microondas'
            WHEN 12 THEN 'Bicicleta'
            WHEN 13 THEN 'Balón'
            WHEN 14 THEN 'Raqueta'
            WHEN 15 THEN 'Mancuernas'
            WHEN 16 THEN 'Serum'
            WHEN 17 THEN 'Perfume'
            WHEN 18 THEN 'Crema'
            WHEN 19 THEN 'Motosierra'
            WHEN 20 THEN 'Taladro'
            ELSE 'Maceta'
        END,
        ' ',
        CASE MOD(x, 12)
            WHEN 0 THEN 'Pro'
            WHEN 1 THEN 'Lite'
            WHEN 2 THEN 'Max'
            WHEN 3 THEN 'Ultra'
            WHEN 4 THEN 'Eco'
            WHEN 5 THEN 'Plus'
            WHEN 6 THEN 'Smart'
            WHEN 7 THEN 'Air'
            WHEN 8 THEN 'Prime'
            WHEN 9 THEN 'Flex'
            WHEN 10 THEN 'Home'
            ELSE 'X'
        END,
        ' ',
        CAST((x / 5) + 1 AS VARCHAR)
    ),
    CONCAT(
        'Producto premium ',
        CASE MOD(x, 25)
            WHEN 0 THEN 'Dell'
            WHEN 1 THEN 'Samsung'
            WHEN 2 THEN 'Lenovo'
            WHEN 3 THEN 'Apple'
            WHEN 4 THEN 'Sony'
            WHEN 5 THEN 'HP'
            WHEN 6 THEN 'Asus'
            WHEN 7 THEN 'LG'
            WHEN 8 THEN 'Philips'
            WHEN 9 THEN 'Bosch'
            WHEN 10 THEN 'Nike'
            WHEN 11 THEN 'Adidas'
            WHEN 12 THEN 'Wilson'
            WHEN 13 THEN 'Giro'
            WHEN 14 THEN 'CamelBak'
            WHEN 15 THEN 'Maybelline'
            WHEN 16 THEN 'LOréal'
            WHEN 17 THEN 'Nivea'
            WHEN 18 THEN 'Dove'
            WHEN 19 THEN 'Stihl'
            ELSE 'Ryobi'
        END,
        ' para uso comercial, deportivo, doméstico o personal con tecnología moderna y materiales de primera calidad.'
    ),
    'ACTIVO',
    CAST(89000 + (x * 1750) + MOD(x, 13) * 330 AS DECIMAL(10,2)),
    'PESO',
    CONCAT('SKU-', LPAD(CAST(x AS VARCHAR), 4, '0')),
    CASE MOD(x, 5)
        WHEN 0 THEN '55555555-5555-4555-8555-555555555555'
        WHEN 1 THEN '11111111-1111-4111-8111-111111111111'
        WHEN 2 THEN '22222222-2222-4222-8222-222222222222'
        WHEN 3 THEN '33333333-3333-4333-8333-333333333333'
        ELSE '44444444-4444-4444-8444-444444444444'
    END
FROM SYSTEM_RANGE(1, 1000) AS seq(x);

INSERT INTO inventario (id, stock, id_producto)
SELECT
    RANDOM_UUID(),
    ((x - 1) % 90) + 10,
    p.id
FROM SYSTEM_RANGE(1, 1000) AS seq(x)
JOIN producto p ON p.codigo_sku = CONCAT('SKU-', LPAD(CAST(x AS VARCHAR), 4, '0'));

DELETE FROM rol_permiso_entity;
DELETE FROM permiso_entity;
DELETE FROM rol_usuario;
DELETE FROM user_entity;

INSERT INTO user_entity (id, segundo_apellido, primer_apellido, nombre, email, password_hash, telefono, estado, fecha_creacion, fecha_actualizacion) VALUES
    ('10000000-0000-4000-8000-000000000001', 'Pérez', 'García', 'Ana', 'ana.garcia@tienda.com', 'hash_ana_123', '+56911111111', 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('10000000-0000-4000-8000-000000000002', 'Rojas', 'Muñoz', 'Luis', 'luis.munoz@tienda.com', 'hash_luis_123', '+56922222222', 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('10000000-0000-4000-8000-000000000003', 'Torres', 'Silva', 'Maria', 'maria.silva@tienda.com', 'hash_maria_123', '+56933333333', 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO rol_usuario (id, nombre, descripcion) VALUES
    ('20000000-0000-4000-8000-000000000001', 'ADMINISTRADOR', 'Administrador del sistema con acceso total.'),
    ('20000000-0000-4000-8000-000000000002', 'VENDEDOR', 'Encargado de ventas y atención al cliente.'),
    ('20000000-0000-4000-8000-000000000003', 'CLIENTE', 'Usuario final que realiza compras y consulta catalogo.');

INSERT INTO permiso_entity (id, codigo, descripcion) VALUES
    ('30000000-0000-4000-8000-000000000001', 'GESTIONAR_USUARIOS', 'Crear, editar y desactivar usuarios.'),
    ('30000000-0000-4000-8000-000000000002', 'GESTIONAR_ROLES', 'Administrar roles y asignarlos a usuarios.'),
    ('30000000-0000-4000-8000-000000000003', 'GESTIONAR_PERMISOS', 'Asignar o retirar permisos de los roles.'),
    ('30000000-0000-4000-8000-000000000004', 'GESTIONAR_PRODUCTOS', 'Crear, editar y desactivar productos.'),
    ('30000000-0000-4000-8000-000000000005', 'VER_TODAS_LAS_VENTAS', 'Consultar todas las ventas.'),
    ('30000000-0000-4000-8000-000000000006', 'VER_REPORTES', 'Consultar reportes del negocio.'),
    ('30000000-0000-4000-8000-000000000007', 'CREAR_VENTA', 'Registrar una venta a un cliente.'),
    ('30000000-0000-4000-8000-000000000008', 'VER_VENTAS_PROPIAS', 'Consultar las ventas que registró.'),
    ('30000000-0000-4000-8000-000000000009', 'CREAR_CLIENTE', 'Registrar los datos de un comprador, sin crearle credenciales.'),
    ('30000000-0000-4000-8000-000000000010', 'VER_CLIENTES', 'Consultar clientes para registrar ventas.'),
    ('30000000-0000-4000-8000-000000000011', 'VER_CATALOGO', 'Consultar productos disponibles y sus precios de venta.'),
    ('30000000-0000-4000-8000-000000000012', 'CREAR_PEDIDO_PROPIO', 'Realizar una compra para sí mismo.'),
    ('30000000-0000-4000-8000-000000000013', 'VER_PEDIDOS_PROPIOS', 'Consultar únicamente sus pedidos.'),
    ('30000000-0000-4000-8000-000000000014', 'EDITAR_PERFIL_PROPIO', 'Actualizar sus datos personales, sin modificar su rol.');

INSERT INTO rol_permiso_entity (id, rol_usuario_id, permiso_entity_id) VALUES
    ('40000000-0000-4000-8000-000000000001', '20000000-0000-4000-8000-000000000001', '30000000-0000-4000-8000-000000000001'),
    ('40000000-0000-4000-8000-000000000002', '20000000-0000-4000-8000-000000000001', '30000000-0000-4000-8000-000000000002'),
    ('40000000-0000-4000-8000-000000000003', '20000000-0000-4000-8000-000000000001', '30000000-0000-4000-8000-000000000003'),
    ('40000000-0000-4000-8000-000000000004', '20000000-0000-4000-8000-000000000001', '30000000-0000-4000-8000-000000000004'),
    ('40000000-0000-4000-8000-000000000005', '20000000-0000-4000-8000-000000000001', '30000000-0000-4000-8000-000000000005'),
    ('40000000-0000-4000-8000-000000000006', '20000000-0000-4000-8000-000000000001', '30000000-0000-4000-8000-000000000006'),
    ('40000000-0000-4000-8000-000000000007', '20000000-0000-4000-8000-000000000001', '30000000-0000-4000-8000-000000000007'),
    ('40000000-0000-4000-8000-000000000008', '20000000-0000-4000-8000-000000000001', '30000000-0000-4000-8000-000000000009'),
    ('40000000-0000-4000-8000-000000000009', '20000000-0000-4000-8000-000000000001', '30000000-0000-4000-8000-000000000010'),
    ('40000000-0000-4000-8000-000000000010', '20000000-0000-4000-8000-000000000001', '30000000-0000-4000-8000-000000000011'),
    ('40000000-0000-4000-8000-000000000011', '20000000-0000-4000-8000-000000000002', '30000000-0000-4000-8000-000000000004'),
    ('40000000-0000-4000-8000-000000000012', '20000000-0000-4000-8000-000000000002', '30000000-0000-4000-8000-000000000005'),
    ('40000000-0000-4000-8000-000000000013', '20000000-0000-4000-8000-000000000002', '30000000-0000-4000-8000-000000000007'),
    ('40000000-0000-4000-8000-000000000014', '20000000-0000-4000-8000-000000000002', '30000000-0000-4000-8000-000000000008'),
    ('40000000-0000-4000-8000-000000000015', '20000000-0000-4000-8000-000000000002', '30000000-0000-4000-8000-000000000009'),
    ('40000000-0000-4000-8000-000000000016', '20000000-0000-4000-8000-000000000002', '30000000-0000-4000-8000-000000000010'),
    ('40000000-0000-4000-8000-000000000017', '20000000-0000-4000-8000-000000000003', '30000000-0000-4000-8000-000000000011'),
    ('40000000-0000-4000-8000-000000000018', '20000000-0000-4000-8000-000000000003', '30000000-0000-4000-8000-000000000012'),
    ('40000000-0000-4000-8000-000000000019', '20000000-0000-4000-8000-000000000003', '30000000-0000-4000-8000-000000000013'),
    ('40000000-0000-4000-8000-000000000020', '20000000-0000-4000-8000-000000000003', '30000000-0000-4000-8000-000000000014');
