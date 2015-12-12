package br.com.evandro.sgce.base;

import java.io.Serializable;

public class Usuario implements Serializable{

    public static final String TABELA_USUARIOS = "usuarios";
    public static final String ID = "id";
    public static final String ENDERECO = "endereco";
    public static final String NUMERO = "numero";

    private String id;
    private String endereco;
    private String numero;


    public Usuario(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() { return numero; }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
