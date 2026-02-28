package cl.thomas.mojitosecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.thomas.mojitosecommerce.entity.ItemCarrito;
import cl.thomas.mojitosecommerce.entity.Producto;
import cl.thomas.mojitosecommerce.service.ProductoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        Producto productoDeseado = productoService.obtenerPorId(id);

        if (productoDeseado != null) {
            boolean existe = false;
            for (ItemCarrito item : carrito) {
                // Comparamos usando el objeto Producto y su ID
                if (item.getProducto().getId().equals(id)) {
                    item.setCantidad(item.getCantidad() + 1);
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                carrito.add(new ItemCarrito(productoDeseado, 1));
            }
        }

        session.setAttribute("carrito", carrito);
        return "redirect:/carrito/ver";
    }

    @GetMapping("/ver")
    public String verCarrito(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        
        int itemsCount = carrito.size();
        
        // Uso de double para ser compatible con Producto.precio (Double)
        double total = carrito.stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();

        model.addAttribute("itemsCount", itemsCount);
        model.addAttribute("carrito", carrito);
        model.addAttribute("totalCarrito", total);

        return "carrito-vista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDelCarrito(@PathVariable Long id, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        
        if (carrito != null) {
            // CORRECCIÓN: Se cambió getPlan() por getProducto() para coincidir con tu entidad
            carrito.removeIf(item -> item.getProducto().getId().equals(id));
            session.setAttribute("carrito", carrito);
        }
        
        return "redirect:/carrito/ver";
    }

    @GetMapping("/vaciar")
    public String vaciarCarrito(HttpSession session) {
        session.removeAttribute("carrito");
        return "redirect:/carrito/ver";
    }

    @GetMapping("/checkout")
    public String irACheckout(HttpSession session, Model model) {
        @SuppressWarnings("unchecked")
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");

        if (carrito == null || carrito.isEmpty()) {
            return "redirect:/productos";
        }

        // Uso de double consistente con el resto del controlador
        double total = carrito.stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();

        // Nota: Asegúrate de tener la clase Reserva importada o creada
        // model.addAttribute("reserva", new Reserva()); 
        model.addAttribute("carrito", carrito); 
        model.addAttribute("totalCarrito", total);
        
        return "public/reserva-confirmacion-carrito"; 
    }
}