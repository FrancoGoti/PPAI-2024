package com.example.PPAI_2024.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.PPAI_2024.entity.Usuario;
import com.example.PPAI_2024.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos los usuarios
    @GetMapping
    public String listarUsuarios(Model model) {
        var usuarios = usuarioService.obtenerTodos();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    // Mostrar formulario para agregar un usuario
    @GetMapping("/nuevo")
    public String agregarUsuario(Model model) {
        var usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("modo", "nuevo");
        return "formulario-usuario";
    }

    // Guardar un nuevo usuario
    @PostMapping("/guardar")
    public String guardarUsuario(Usuario usuario, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("modo", "nuevo");
            return "formulario-usuario";
        }
        usuarioService.guardar(usuario);
        return "redirect:/usuarios";
    }

    // Mostrar formulario para editar un usuario
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        var usuario = usuarioService.obtenerPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("modo", "editar");
        return "formulario-usuario";
    }

    // Actualizar un usuario
    @PostMapping("/actualizar")
    public String actualizarUsuario(Usuario usuario, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("modo", "editar");
            return "formulario-usuario";
        }
        usuarioService.guardar(usuario);
        return "redirect:/usuarios";
    }

    // Eliminar un usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }
}
