/*package com.taskm.task_manager.controller;

import com.taskm.task_manager.model.Progetti;
import com.taskm.task_manager.service.ProgettiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/progetti")
public class ProgettiViewController {

    private final ProgettiService service;

    public ProgettiViewController(ProgettiService service) {
        this.service = service;
    }

    @GetMapping
    public String listaProgetti(Model model) {
        model.addAttribute("progetti", service.findAll());
        return "progetti/lista";
    }

    @GetMapping("/nuovo")
    public String nuovoProgettoForm(Model model) {
        model.addAttribute("progetto", new Progetti());
        return "progetti/nuovo";
    }

    @PostMapping
    public String salvaProgetto(@ModelAttribute Progetti progetto) {
        service.salva(progetto);
        return "redirect:/progetti";
    }

    @GetMapping("/{id}")
    public String dettaglioProgetto(@PathVariable Long id, Model model) {
        Progetti progetto = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Progetto non trovato"));
        model.addAttribute("progetto", progetto);
        return "progetti/dettaglio";
    }

    @GetMapping("/elimina/{id}")
    public String eliminaProgetto(@PathVariable Long id) {
        service.elimina(id);
        return "redirect:/progetti";
    }
}*/
