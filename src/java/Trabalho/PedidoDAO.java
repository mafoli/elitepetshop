/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Junior
 */
public class PedidoDAO {
    private String stmtSelect = "select * from pedido where id_cliente = ?;";
    private String stmtInsert = "insert into pedido(data, id_cliente) values(?, ?);";
    private String stmtSelectById = "select * from pedido where id = ?;";
    
    public List<Pedido> getLista(int idCliente) throws SQLException, ClassNotFoundException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelect, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            List<Pedido> lstPedido = new ArrayList();

            while (rs.next()) {
                // criando o objeto Usuario
                Pedido Pedido = new Pedido();
                Pedido.setId(rs.getInt("id"));
                Pedido.setData(rs.getDate("data"));
                Pedido.setId_cliente(rs.getInt("id_cliente"));
                
                ItemDoPedidoDAO ItemDoPedidoDAO = new ItemDoPedidoDAO();
                Pedido.setLstItemDoPedido(ItemDoPedidoDAO.getLista(rs.getInt("id")));
                
                lstPedido.add(Pedido);
            }
            return lstPedido;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage());
            };
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }
    
    public void insert(Pedido pedido) {
        Connection con = null;
        PreparedStatement stmt = null;
        int idObjeto = 0;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInsert, Statement.RETURN_GENERATED_KEYS);

            stmt.setDate(1, (java.sql.Date) (Date) pedido.getData());
            stmt.setInt(2, pedido.getId_cliente());

            stmt.execute();
            //Seta o id 
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                idObjeto = rs.getInt(1);
            }
            pedido.setId(idObjeto);
            
            ItemDoPedidoDAO itemDAO = new ItemDoPedidoDAO();
            itemDAO.insert(pedido);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }
    
    public Pedido getById(int id) throws ClassNotFoundException {
        Pedido Pedido = new Pedido();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelectById, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido.setId(rs.getInt("id"));
                Pedido.setData(rs.getDate("data"));
                Pedido.setId_cliente(rs.getInt("id_cliente"));
                
                ItemDoPedidoDAO ItemDoPedidoDAO = new ItemDoPedidoDAO();
                Pedido.setLstItemDoPedido(ItemDoPedidoDAO.getLista(rs.getInt("id")));
            }
            return Pedido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }
    
}
