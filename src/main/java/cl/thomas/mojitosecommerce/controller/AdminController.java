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

    // Listado Neón
    @GetMapping("/productos")
    public String panelAdmin(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "admin/productos-lista"; 
    }

    // Formulario Nuevo
    @GetMapping("/productos/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "admin/producto-form";
    }

    // Editar (Usa el service para buscar el existente)
    @GetMapping("/productos/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        return "admin/producto-form";
    }

    // Guardar (POST)
    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/admin/productos";
    }

    // Eliminar
    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/admin/productos";
    }
}