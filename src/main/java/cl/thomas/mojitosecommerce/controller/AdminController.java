package cl.thomas.mojitosecommerce.controller;

import cl.thomas.mojitosecommerce.entity.Producto;
import cl.thomas.mojitosecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public String panelAdmin(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "admin/productos-lista"; 
    }

    @GetMapping("/productos/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "admin/producto-form";
    }

    @GetMapping("/productos/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        return "admin/producto-form";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 1. Definimos la ruta de la carpeta física
                String folder = "src/main/resources/static/assets/image";
                Path directorioImagenes = Paths.get(folder);
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

                // 2. Obtenemos el nombre del archivo y creamos la ruta completa
                byte[] bytesImg = file.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + File.separator + file.getOriginalFilename());
                
                // 3. Escribimos el archivo en el disco
                Files.write(rutaCompleta, bytesImg);

                // 4. Guardamos la URL relativa que Thymeleaf usará: /assets/image/nombre.jpg
                producto.setImagenUrl("/assets/image/" + file.getOriginalFilename());
                
            } catch (IOException e) {
                System.out.println("Error al cargar la imagen: " + e.getMessage());
                e.printStackTrace();
            }
        } else if (producto.getId() != null) {
            // Si estamos editando y no subimos foto nueva, mantenemos la anterior
            Producto productoExistente = productoService.obtenerPorId(producto.getId());
            producto.setImagenUrl(productoExistente.getImagenUrl());
        }

        productoService.guardar(producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/admin/productos";
    }
}