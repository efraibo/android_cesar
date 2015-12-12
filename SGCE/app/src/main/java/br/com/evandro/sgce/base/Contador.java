package br.com.evandro.sgce.base;

import java.io.Serializable;

public class Contador implements Serializable{

    public static final String TABELA_CONTADOR = "contador";
    public static final String ID = "_id";
    public static final String DATA = "data_leitura";
    public static final String NUMERO_LEITURA = "numero_leitura";

    private String id;
    private String data;
    private String numero_leitura;

    //para commitar
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public String getNumero_leitura() { return numero_leitura; }

    public void setNumero_leitura(String numero_leitura) { this.numero_leitura = numero_leitura; }
}
