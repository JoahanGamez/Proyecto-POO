package com.gremio.controller;

import com.gremio.model.Movimiento;
import com.gremio.service.MovimientoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovimientoController {
    private final MovimientoService service;

    public MovimientoController(MovimientoService service) { this.service = service; }

    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("movimientos", service.listarTodos());
        model.addAttribute("saldo", service.calcularSaldo());
        return "movimientos";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("movimiento", new Movimiento());
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Movimiento movimiento) {
        service.guardar(movimiento);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("movimiento", service.buscarPorId(id));
        return "formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/";
    }
}