package cl.thomas.mojitosecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cl.thomas.mojitosecommerce.service.ProductoService;

@Controller
public class MojitoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Carga la página de inicio con el catálogo dinámico de productos.
     */
    @GetMapping("/")
    public String index(Model model) {
        // 'listaProductos' es el nombre que usamos en el th:each del HTML
        model.addAttribute("listaProductos", productoService.listarTodos());
        
        // Retorna el nombre del archivo index.html (sin la extensión)
        return "index";
    }
}
