package cl.thomas.mojitosecommerce.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.thomas.mojitosecommerce.entity.ItemCarrito;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrito")
public class VentaController {

    @GetMapping("/finalizar")
    public String finalizarCompra(HttpSession session, Model model) {
        @SuppressWarnings("unchecked")
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");

        if (carrito == null || carrito.isEmpty()) {
            return "redirect:/productos";
        }

        // Aquí podrías guardar en la BD usando un VentaService
        // ventaService.guardarVenta(carrito); 

        // Calculamos el total por última vez para mostrarlo en el ticket
        double total = carrito.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
        
        // Generamos un número de orden aleatorio para la vista
        String numOrden = "MOJ-" + System.currentTimeMillis() / 1000;

        model.addAttribute("nroOrden", numOrden);
        model.addAttribute("total", total);

        // ¡IMPORTANTE! Limpiamos el carrito de la sesión
        session.removeAttribute("carrito");

        return "compra-exitosa";
    }
}