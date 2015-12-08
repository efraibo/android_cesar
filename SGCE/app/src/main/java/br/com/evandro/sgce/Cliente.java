package br.com.evandro.sgce;

import java.io.Serializable;

public class Cliente implements Serializable{

    public static final String TABELA_CLIENTES = "clientes";
    public static final String ID = "id";
    public static final String NOME = "nome";

    private String id;
    private String nome;

    public Cliente(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
