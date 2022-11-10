package com.apmvc.apartamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apartamento {
  
    public Apartamento(int int1, int int2, String string, int int3, int int4) {
    }
    private int idAp;
    private int numPortas;
    private int qtdQuartos;
    private String tipo;
    private Propietario propietario;
    public void setId(int int1) {
    }
    public void setProprietario(int int1) {
    }
}
