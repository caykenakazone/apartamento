package com.apmvc.apartamento.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePropietario {

    @GetMapping("/cadastrarProp")
    public String cadastrarPropietario() {
        return "home";
    }

    @GetMapping("/listarProp")
    public String listarProp() {
        return "cadastrarProp";
    }

}
