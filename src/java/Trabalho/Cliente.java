/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho;

import java.util.List;

/**
 *
 * @author Junior
 */
public class Cliente {
    int id;
    String cpf;
    String nome;
    String sobreNome;
    private List<Pedido> Pedido;

    public List<Pedido> getPedido() {
        return Pedido;
    }

    public void setPedido(List<Pedido> Pedido) {
        this.Pedido = Pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    void setSobrenome(String sobrenome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
