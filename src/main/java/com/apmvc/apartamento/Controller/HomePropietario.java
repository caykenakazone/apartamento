package com.apmvc.apartamento.Controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apmvc.apartamento.model.Propietario;

import org.springframework.ui.Model;

import java.util.List;

@Controller
public class HomePropietario {
    @Autowired
    JdbcTemplate db;

    @GetMapping("/cadastrarProp")
    public String cadastrarPropietario() {
        return "cadastrarProp";
    }

    @GetMapping("/listarProp")
    public String listarProp(Model model) {
        List<Propietario> listaDePropietarios = db.query(
            "select * from propietario",
            (res,rowNum)->{
                Propietario propietario = new Propietario(
                    res.getString("nome"),
                    res.getString("telefone"),
                    res.getString("id"));
                return propietario;
                
            });
            model.addAttribute("propietarios",listaDePropietarios);
            return "listarProp";            
    }
    @GetMapping("excluir-proprietario")
    public String apagarProprietario(@RequestParam(value = "id", required = true) int idProp) {
        db.update("delete from proprietario where id=?", idProp);
        return "redirect:/proprietarios";
    }

    @GetMapping("editar-proprietario")
    public String exibeFormAlteracaoProprietario(@RequestParam(value = "id", required = true) int idProp, Model model) {
        Propietario proprietario = db.queryForObject(
                "select * from proprietario where id = ?",
                (rs, rowNum) -> {
                    Propietario edited = new Propietario();
                    edited.setIdProp(rs.getInt("idProp"));
                    edited.setNome(rs.getString("nome"));
                    edited.setTelefone(rs.getString("telefone"));
                    return edited;
                }, idProp);
        model.addAttribute("proprietarioEditado", proprietario);
        return "editaproprietario";
    }

    @PostMapping("gravaproprietarioeditado")
    public String gravaProprietarioEditado(Propietario proprietario) {
        db.update(
                "update proprietario set nome=?, telefone=? where id = ?",
                proprietario.getNome(),
                proprietario.getTelefone(),
                proprietario.getIdProp());
        return "redirect:/proprietarios";
    }

}
