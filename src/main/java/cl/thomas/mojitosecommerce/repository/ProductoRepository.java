package cl.thomas.mojitosecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.thomas.mojitosecommerce.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    // Aquí podrías agregar métodos personalizados en el futuro, por ejemplo:
    // List<Producto> findByPrecioLessThan(Double precio);
}
