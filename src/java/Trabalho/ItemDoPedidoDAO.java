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
import java.util.List;

/**
 *
 * @author Junior
 */
public class ItemDoPedidoDAO {

    private String stmtSelect = "select * from pedido_has_produto where id_pedido = ?;";
    private String stmtInsert = "insert into pedido_has_produto(id_pedido, id_produto, qtdade) values(?, ?, ?);";
    private String stmtDeleteAll = "delete from pedido_has_produto where id_pedido = ?";
    private String stmtDelete = "delete from pedido_has_produto where id_pedido = ? and id_produto = ?";
    
    public List<ItemDoPedido> getLista(int idPedido) throws SQLException, ClassNotFoundException {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelect, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idPedido);
            rs = stmt.executeQuery();
            List<ItemDoPedido> lstItemDoPedido = new ArrayList();

            while (rs.next()) {
                // criando o objeto Usuario
                ItemDoPedido ItemDoPedido = new ItemDoPedido();
                ItemDoPedido.setId_pedido(rs.getInt("id_pedido"));
                ItemDoPedido.setQtdade(rs.getInt("qtdade"));
                ProdutoDAO ProdutoDAO = new ProdutoDAO();
                ItemDoPedido.setProduto(ProdutoDAO.getById(rs.getInt("id_produto")));

                lstItemDoPedido.add(ItemDoPedido);
            }
            return lstItemDoPedido;

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

    public void insert(Pedido pedido) throws RuntimeException {
        Connection con = null;
        PreparedStatement stmt = null;
        int idPedido = pedido.getId();
        List<ItemDoPedido> lstItemDoPedido = new ArrayList();
        lstItemDoPedido = pedido.getLstItemDoPedido();
        try {
            for (ItemDoPedido ItemDoPedido : lstItemDoPedido) {
                stmt = null;
                con = ConnectionFactory.getConnection();
                stmt = con.prepareStatement(stmtInsert, Statement.RETURN_GENERATED_KEYS);

                stmt.setInt(1, idPedido);
                stmt.setInt(2, ItemDoPedido.getProduto().getId());
                stmt.setInt(3, ItemDoPedido.getQtdade());

                stmt.execute();
                //Seta o id 
                ResultSet rs = stmt.getGeneratedKeys();
                while (rs.next()) {
                }
            }
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

    public void deleteAll(int idPedido) {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        try {

            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtDeleteAll);
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();

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
    
    public void delete(int idPedido, int idProduto) {
        com.mysql.jdbc.Connection con = null;
        PreparedStatement stmt = null;
        try {

            con = (com.mysql.jdbc.Connection) ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtDelete);
            stmt.setInt(1, idPedido);
            stmt.setInt(2, idProduto);
            stmt.executeUpdate();

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
