package com.apmvc.apartamento.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apmvc.apartamento.model.Apartamento;

import java.util.List;
@Controller
public class HomeApartamento {

    @Autowired
    JdbcTemplate db;

    @GetMapping("/cadastrarAp")
    public String cadastrarAp() {
        return "cadastrarAp";
    }

    @GetMapping("listarAp")
        public String listarAp(Model modelo) {
            List<Apartamento> listaDeApartamentos = db.query(
                    "select * from apartamento",
                    (res, rowNum) -> {
                        Apartamento apartamento = new Apartamento(
                                res.getInt("numPortas"),
                                res.getInt("qtdQuartos"),
                                res.getString("tipo"),
                                res.getInt("idAp"),
                                res.getInt("proprietario"));
                        return apartamento;
                    });
    
            modelo.addAttribute("apartamentos", listaDeApartamentos);
            return "listarAp";
    }

    @GetMapping("cadastrarAp")
    public String exibeFormApartamento(Model model){
         model.addAttribute("apartamento", new Apartamento());
         return "cadastrarAp";
    }

    @PostMapping("cadastrarAp")
    public String gravaDadosApartamento(Apartamento apartamento) {
        db.update("insert into apartamento (numPortas, qtdQuartos, tipo) values (?,?,?)",
                apartamento.getNumPortas(), apartamento.getNumPortas(), apartamento.getTipo());
        return "redirect:/cadastrarAp";
    }
    
    @GetMapping("excluirAp")
    public String apagarApartamento(@RequestParam(value = "id", required = true) int id) {
        db.update("delete from apartamento where id=?", id);
        return "redirect:/listarAp";
    }

    @GetMapping("editarAp")
    public String exibeFormAlteracaoApartamento(@RequestParam(value = "id", required = true) int idAp, Model model) {
        Apartamento apartamento = db.queryForObject(
                "select * from apartamento where idAp = ?",
                (rs, rowNum) -> {
                    Apartamento edited = new Apartamento();
                    edited.setNumPortas(idAp);
                    edited.setQtdQuartos(idAp);
                    edited.setTipo(rs.getString("tipo"));
                    edited.setId(rs.getInt("id"));
                    edited.setProprietario(rs.getInt("proprietario"));
                    return edited;
                },idAp);
        model.addAttribute("apartamentoEditado", apartamento);
        return "editarAp";
    }

    @PostMapping("gravaapartamentoeditado")
    public String gravaApartamentoEditado(Apartamento apartamento) {
        db.update(
                "update apartamento set numPortas=?, qtdQuartos=?, tipo=? where idAp = ?",
                apartamento.getNumPortas(),
                apartamento.getQtdQuartos(),
                apartamento.getTipo(),
                apartamento.getIdAp());
        return "redirect:/listarAp";

    }
}
