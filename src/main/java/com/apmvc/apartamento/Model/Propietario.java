package com.apmvc.apartamento.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Propietario {
    private int idProp;
    private String nome,telefone;
}
