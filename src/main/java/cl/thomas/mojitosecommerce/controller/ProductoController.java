package cl.thomas.mojitosecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import cl.thomas.mojitosecommerce.entity.Producto;
import cl.thomas.mojitosecommerce.service.ProductoService;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    
    @GetMapping("/productos")
    public String verPaginaDeProductos(Model model) {
        // 1. Obtenemos los datos del servicio y filtramos por estado activo
        List<Producto> lista = productoService.listarTodos().stream()
                .toList();
        
        // 2. Pasamos la lista al HTML usando el "model"
        // El atributo "listadoDePlanes" es el que debe ser recorrido en Thymeleaf
        model.addAttribute("listadoDePlanes", lista);
        
        // 3. Devolvemos el nombre del archivo HTML
        return "productos"; 
    }
/**
    // 1. LISTAR PRODUCTOS (Vista Administración)
    @GetMapping("/admin")
    public String listarAdmin(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "productos"; // Ruta a tu carpeta de plantillas
    }

    // 2. MOSTRAR FORMULARIO DE CREACIÓN
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("titulo", "Agregar Nuevo Mojito");
        return "admin/producto-form";
    }

    // 3. GUARDAR PRODUCTO (Crear o Actualizar)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto, RedirectAttributes flash) {
        try {
            productoService.guardar(producto);
            flash.addFlashAttribute("mensajeExito", "¡Producto guardado correctamente!");
        } catch (Exception e) {
            flash.addFlashAttribute("mensajeError", "Error al guardar el producto.");
        }
        return "redirect:/productos/admin";
    }

    // 4. EDITAR PRODUCTO
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {
    	Producto producto = productoService.obtenerPorId(id);
    	if (producto == null) {
            flash.addFlashAttribute("mensajeError", "El producto no existe.");
            return "redirect:/productos/admin";
        }
        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Editar Mojito: " + producto.getNombre());
        return "admin/producto-form";
    }

    // 5. ELIMINAR PRODUCTO
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        try {
            productoService.eliminar(id);
            flash.addFlashAttribute("mensajeExito", "Producto eliminado con éxito.");
        } catch (Exception e) {
            flash.addFlashAttribute("mensajeError", "No se pudo eliminar el producto.");
        }
        return "redirect:/productos/admin";
    }**/
}
