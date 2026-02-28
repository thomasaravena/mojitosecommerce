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
    public String verPaginaDeProductos(Model model, jakarta.servlet.http.HttpSession session) {
        List<Producto> lista = productoService.listarTodos();
        model.addAttribute("listaProductos", lista);

        // Obtener cantidad de items del carrito de la sesión para el badge
        List<?> carrito = (List<?>) session.getAttribute("carrito");
        int itemsCount = (carrito != null) ? carrito.size() : 0;
        model.addAttribute("itemsCount", itemsCount);

        return "productos";
    }

}
