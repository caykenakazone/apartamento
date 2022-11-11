package com.apmvc.apartamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Propietario {

    private int idProp;
    private String nome,telefone;
}
