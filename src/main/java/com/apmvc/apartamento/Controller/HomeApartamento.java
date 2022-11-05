package com.apmvc.apartamento.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeApartamento {
    @GetMapping("/cadastrarAp")
    public String cadastrarAp() {
        return "cadastrarAp";
    }

    @GetMapping("listarAp")
    public String listarAp() {
        return "listarAp";
    }
}
