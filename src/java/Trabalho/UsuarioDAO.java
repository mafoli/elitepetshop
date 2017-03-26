package Trabalho;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cliente
 */
class UsuarioDAO {
   private String stmtGetBy = "select * from usuario where login =? and senha=?";
       
    public Usuario verificaLogin(String login, String senha) throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Usuario Usuario = new Usuario();
        com.mysql.jdbc.Connection con=null;
        PreparedStatement stmt = null;
        
        ResultSet rs = null;
        try {
            con = (com.mysql.jdbc.Connection) Conectar.getConnection();
            
            stmt = con.prepareStatement(stmtGetBy, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario.setId(rs.getInt("id"));
                Usuario.setLogin(rs.getString("login"));
                Usuario.setSenha(rs.getString("senha"));
                
            }
            
            return Usuario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }  
    }
    
}
