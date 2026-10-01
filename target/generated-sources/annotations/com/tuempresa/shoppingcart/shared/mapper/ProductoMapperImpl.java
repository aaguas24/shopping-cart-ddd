package com.tuempresa.shoppingcart.shared.mapper;

import com.tuempresa.shoppingcart.domain.catalog.entity.Producto;
import com.tuempresa.shoppingcart.domain.catalog.valueobject.Precio;
import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.CategoriaEntity;
import com.tuempresa.shoppingcart.infraestructure.persistence.jpa.entity.ProductoEntity;
import com.tuempresa.shoppingcart.shared.enums.EstadoProducto;
import com.tuempresa.shoppingcart.shared.enums.Moneda;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-01T16:10:16-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public Producto toDomain(ProductoEntity producto) {
        if ( producto == null ) {
            return null;
        }

        UUID idCategoria = null;
        UUID id = null;
        String nombre = null;
        String descripcion = null;
        String sku = null;
        EstadoProducto estado = null;

        idCategoria = productoCategoriaId( producto );
        id = producto.getId();
        nombre = producto.getNombre();
        descripcion = producto.getDescripcion();
        sku = producto.getSku();
        estado = producto.getEstado();

        Precio precio = new Precio(producto.getPrecio(), producto.getMoneda());

        Producto producto1 = new Producto( id, nombre, descripcion, precio, sku, idCategoria, estado );

        return producto1;
    }

    @Override
    public List<Producto> toDomain(List<ProductoEntity> productos) {
        if ( productos == null ) {
            return null;
        }

        List<Producto> list = new ArrayList<Producto>( productos.size() );
        for ( ProductoEntity productoEntity : productos ) {
            list.add( toDomain( productoEntity ) );
        }

        return list;
    }

    @Override
    public ProductoEntity toEntity(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        ProductoEntity productoEntity = new ProductoEntity();

        productoEntity.setPrecio( productoPrecioPrecio( producto ) );
        productoEntity.setMoneda( productoPrecioMoneda( producto ) );
        productoEntity.setId( producto.getId() );
        productoEntity.setNombre( producto.getNombre() );
        productoEntity.setDescripcion( producto.getDescripcion() );
        productoEntity.setEstado( producto.getEstado() );
        productoEntity.setSku( producto.getSku() );

        return productoEntity;
    }

    private UUID productoCategoriaId(ProductoEntity productoEntity) {
        if ( productoEntity == null ) {
            return null;
        }
        CategoriaEntity categoria = productoEntity.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        UUID id = categoria.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private BigDecimal productoPrecioPrecio(Producto producto) {
        if ( producto == null ) {
            return null;
        }
        Precio precio = producto.getPrecio();
        if ( precio == null ) {
            return null;
        }
        BigDecimal precio1 = precio.getPrecio();
        if ( precio1 == null ) {
            return null;
        }
        return precio1;
    }

    private Moneda productoPrecioMoneda(Producto producto) {
        if ( producto == null ) {
            return null;
        }
        Precio precio = producto.getPrecio();
        if ( precio == null ) {
            return null;
        }
        Moneda moneda = precio.getMoneda();
        if ( moneda == null ) {
            return null;
        }
        return moneda;
    }
}
