/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Junior
 */
public class Pedido implements Serializable{
    private int id;
    private Date data;
    private int id_cliente;
    private List<ItemDoPedido> lstItemDoPedido;

    public List<ItemDoPedido> getLstItemDoPedido() {
        return lstItemDoPedido;
    }

    public void setLstItemDoPedido(List<ItemDoPedido> lstItemDoPedido) {
        this.lstItemDoPedido = lstItemDoPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    
}
