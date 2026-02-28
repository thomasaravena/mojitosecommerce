package cl.thomas.mojitosecommerce.controller;

import cl.thomas.mojitosecommerce.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminConsultasController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @GetMapping("/consultas")
    public String listarConsultas(Model model) {
        // Traemos las consultas de la BD
        model.addAttribute("consultas", consultaRepository.findAll());
        // IMPORTANTE: Asegúrate que en src/main/resources/templates exista 
        // la carpeta 'admin' y dentro 'consultas-lista.html'
        return "admin/consultas-lista"; 
    }

    @GetMapping("/consultas/eliminar/{id}")
    public String eliminarConsulta(@PathVariable Long id, RedirectAttributes flash) {
        try {
            consultaRepository.deleteById(id);
            flash.addFlashAttribute("success", "Consulta eliminada correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar la consulta.");
        }
        return "redirect:/admin/consultas";
    }
}