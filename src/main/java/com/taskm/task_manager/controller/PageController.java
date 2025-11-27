package com.taskm.task_manager.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {



    @GetMapping("/progetti")
    public String progetti() {
        return "progetti";
    }

    @GetMapping("/utenti")
    public String utenti() {
        return "utenti";
    }

    @GetMapping("/task")
    public String task() {
        return "task";
    }

    @GetMapping("/report")
    public String report() {
        return "report";
    }


}