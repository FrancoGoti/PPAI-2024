package com.example.PPAI_2024.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.PPAI_2024.entity.Enofilo;

import com.example.PPAI_2024.service.EnofiloService;
import com.example.PPAI_2024.service.SiguiendoService;
import com.example.PPAI_2024.service.UsuarioService;
import com.example.PPAI_2024.service.VinoService;

@Controller
@RequestMapping("/enofilos")
public class EnofiloController {

    @Autowired
    private final EnofiloService enofiloService;

    @Autowired
    private final SiguiendoService siguiendoService;

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final VinoService vinoService;

    public EnofiloController(EnofiloService enofiloService, SiguiendoService siguiendoService, UsuarioService usuarioService, VinoService vinoService) {
        this.enofiloService = enofiloService;
        this.siguiendoService = siguiendoService;
        this.usuarioService = usuarioService;
        this.vinoService = vinoService;
    }

    @GetMapping
    public String listarEnofilo(Model model) {
        var enofilo = enofiloService.listarTodos();
        model.addAttribute("enofilo", enofilo);
        return "enofilos"; // Vista con los enofilos
    }

    @GetMapping("/nuevo")
    public String agregarEnofilo(Model model) {
        var enofilo = new Enofilo();
        var seguidos = siguiendoService.getAllSiguiendo(); // Obtener todos los siguiendo
        var usuarios = usuarioService.obtenerTodos(); // Obtener todos los usuarios
        var vinos = vinoService.obtenerTodos(); // Obtener todos los usuarios
        model.addAttribute("enofilo", enofilo);
        model.addAttribute("seguidos", seguidos); 
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("favoritos", vinos);
        model.addAttribute("modo", "nuevo");
        return "formulario-enofilo"; // Vista del formulario para agregar un enofilo
    }

    // Guardar un nuevo enofilo
    @PostMapping("/guardar")
    public String guardarEnofilo(Enofilo enofilo, BindingResult error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("modo", "nuevo");
            return "formulario-enofilo"; // Regresar al formulario si hay errores
        }
        enofiloService.guardar(enofilo);
        return "redirect:/enofilos"; // Redirigir a la lista de enofilos
    }

        // Mostrar formulario para editar un enofilo
        @GetMapping("/editar/{id}")
    public String editarEnofilo(@PathVariable Long id, Model model) {
        Enofilo enofilo = enofiloService.obtenerPorId(id);
        if (enofilo != null) {
            var seguido = enofilo.getSeguido();
            var seguidos = siguiendoService.getAllSiguiendo();
            var usuario = enofilo.getUsuario();
            var usuarios = usuarioService.obtenerTodos();
            var favorito = enofilo.getFavorito();
            var favoritos = vinoService.obtenerTodos();
            model.addAttribute("enofilo", enofilo);
            model.addAttribute("seguido", seguido);  
            model.addAttribute("seguidos", seguidos);
            model.addAttribute("usuario", usuario);  
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("favorito", favorito);  
            model.addAttribute("favoritos", favoritos);
            model.addAttribute("modo", "editar");
            return "formulario-enofilo";
        }
        return "redirect:/enofilos";
    }

    // Actualizar un enofilo
    @PostMapping("/actualizar/{id}")
    public String actualizarEnofilo(@PathVariable Long id, @ModelAttribute Enofilo enofilo) {
        enofiloService.actualizarDatos(id, enofilo);
        return "redirect:/enofilos"; // Redirigir a la lista de enofilos
    }

    // Eliminar un enofilo
    @GetMapping("/eliminar/{id}")
    public String eliminarEnofilo(@PathVariable Long id) {
        enofiloService.eliminarEnofilo(id);
        return "redirect:/enofilos"; // Redirigir a la lista de enofilos
    }
}

