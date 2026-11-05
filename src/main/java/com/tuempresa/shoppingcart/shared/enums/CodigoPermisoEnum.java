package com.tuempresa.shoppingcart.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodigoPermisoEnum {
    GESTIONAR_USUARIOS("1", "GESTIONAR_USUARIOS", "Crear, editar y desactivar usuarios."),
    GESTIONAR_ROLES("2", "GESTIONAR_ROLES", "Administrar roles y asignarlos a usuarios."),
    GESTIONAR_PERMISOS("3", "GESTIONAR_PERMISOS", "Asignar o retirar permisos de los roles."),
    GESTIONAR_PRODUCTOS("4", "GESTIONAR_PRODUCTOS", "Crear, editar y desactivar productos."),
    VER_TODAS_LAS_VENTAS("5", "VER_TODAS_LAS_VENTAS", "Consultar todas las ventas."),
    VER_REPORTES("6", "VER_REPORTES", "Consultar reportes del negocio."),
    CREAR_VENTA("7", "CREAR_VENTA", "Registrar una venta a un cliente."),
    VER_VENTAS_PROPIAS("8", "VER_VENTAS_PROPIAS", "Consultar las ventas que registró."),
    CREAR_CLIENTE("9", "CREAR_CLIENTE", "Registrar los datos de un comprador, sin crearle credenciales."),
    VER_CLIENTES("10", "VER_CLIENTES", "Consultar clientes para registrar ventas."),
    VER_CATALOGO("11", "VER_CATALOGO", "Consultar productos disponibles y sus precios de venta."),
    CREAR_PEDIDO_PROPIO("12", "CREAR_PEDIDO_PROPIO", "Realizar una compra para sí mismo."),
    VER_PEDIDOS_PROPIOS("13", "VER_PEDIDOS_PROPIOS", "Consultar únicamente sus pedidos."),
    EDITAR_PERFIL_PROPIO("14", "EDITAR_PERFIL_PROPIO", "Actualizar sus datos personales, sin modificar su rol.");

    private String id;
    private String codigo;
    private String descripripcion;
}
