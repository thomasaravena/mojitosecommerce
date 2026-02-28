package cl.thomas.mojitosecommerce.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.thomas.mojitosecommerce.entity.Producto;
import cl.thomas.mojitosecommerce.repository.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Transactional
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    /**
     * Nombre actualizado a obtenerPorId para coincidir con el Controller
     */
    @Transactional
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Transactional
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
