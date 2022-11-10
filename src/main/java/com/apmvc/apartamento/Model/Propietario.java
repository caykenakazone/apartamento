package com.apmvc.apartamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Propietario {
    public Propietario(String string, String string2, String string3) {
    }
    private int idProp;
    private String nome,telefone;
}
