package cl.thomas.mojitosecommerce.controller;

import cl.thomas.mojitosecommerce.entity.Producto;
import cl.thomas.mojitosecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoService productoService;

    // Ruta principal del panel: muestra la lista de productos
    @GetMapping("/productos")
    public String panelAdmin(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "admin/productos-lista"; 
    }

    // Ruta para mostrar el formulario de nuevo producto
    @GetMapping("/productos/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "admin/producto-form";
    }

    // Guardar el producto (Crear o Editar)
    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/admin/productos";
    }

    // Eliminar producto
    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/admin/productos";
    }
}