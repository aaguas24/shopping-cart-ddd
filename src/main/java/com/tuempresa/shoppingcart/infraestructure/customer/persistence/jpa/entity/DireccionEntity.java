package com.tuempresa.shoppingcart.infraestructure.customer.persistence.jpa.entity;

import com.tuempresa.shoppingcart.shared.enums.DireccionCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "direccion_entity")
public class DireccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "referencia", length = 50)
    private String referencia;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    @Column(name = "ciudad", length = 60)
    private String ciudad;

    @Column(name = "estado_provincia")
    private String estadoProvincia;

    @Column(name = "codigo_pais", length = 60)
    private String codigoPais;

    @Column(name = "pais", length = 10)
    private String pais;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_direccion")
    private DireccionCliente tipoDireccion;

}