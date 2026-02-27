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

    /**
     * Obtiene la lista completa de mojitos disponibles.
     */
    @Transactional()
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    /**
     * Guarda un producto nuevo o actualiza uno existente.
     */
    @Transactional
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    /**
     * Busca un mojito específico por su ID.
     * Retorna null si no lo encuentra.
     */
    @Transactional()
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    /**
     * Elimina un producto de la base de datos por su ID.
     */
    @Transactional
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
