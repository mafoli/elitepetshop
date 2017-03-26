/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cliente
 */
@WebServlet(name = "ProcessaItem", urlPatterns = {"/ProcessaItem"})
public class ProcessaItem extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
         {
            
            
            PedidoDAO PedidoDAO = new PedidoDAO();
            Pedido pedido = new Pedido();
            
            int idPedido = Integer.parseInt(request.getParameter("idPedido"));
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            Produto produto = new Produto();
            ProdutoDAO produtoDAO = new ProdutoDAO();

            List<ItemDoPedido> lstItemDoPedido = new ArrayList();
            ItemDoPedido ItemDoPedido = new ItemDoPedido();

            Date data = Converter.toDate(request.getParameter("data"));
            int idProduto = Integer.parseInt(request.getParameter("idProduto"));
            int qtdade = Integer.parseInt(request.getParameter("qtdade"));

            try {
                produto = produtoDAO.getById(idProduto);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProcessaItem.class.getName()).log(Level.SEVERE, null, ex);
            }

            ItemDoPedido.setProduto(produto);
            ItemDoPedido.setQtdade(qtdade);
            lstItemDoPedido.add(ItemDoPedido);
            pedido.setLstItemDoPedido(lstItemDoPedido);
            
            if (idPedido == 0) {
                pedido.setData(data);
                pedido.setId_cliente(idCliente);

                PedidoDAO.insert(pedido);
                
                ClienteDAO ClienteDAO = new ClienteDAO();
                Cliente cliente = new Cliente();
                
                 int id = Integer.parseInt(request.getParameter("idCliente"));
                
                cliente = ClienteDAO.getById(id);
                
                
                
                Cliente Cliente = new Cliente();
                

                
                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String cpf = request.getParameter("cpf");
                

                Cliente.setId(id);
                Cliente.setNome(nome);
                Cliente.setSobreNome(sobrenome);
                Cliente.setCpf(cpf);
                
           
            List<Pedido>lstpedido = new ArrayList();
            
            lstpedido= PedidoDAO.getLista(id);
            ProdutoDAO ProdutoDAO = new ProdutoDAO();
            Cliente.getPedido();
            
            Produto Produto = new Produto();
            List<Produto>lstproduto = new ArrayList();
            lstproduto= ProdutoDAO.getLista();
           
            
             
            ItemDoPedidoDAO ItemDoPedidoDAO = new ItemDoPedidoDAO();
            
            List<ItemDoPedido>lstitem = new ArrayList();
            lstitem = ItemDoPedidoDAO.getLista(id);
            pedido = PedidoDAO.getById(pedido.getId());
               

               
            request.setAttribute("dados3", lstitem);
            request.setAttribute("cliente", cliente);
            request.setAttribute("pedido", pedido);
            request.setAttribute("dados2", lstpedido);
            request.setAttribute("dados", lstproduto);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pedido.jsp");
            rd.forward(request, response);
            
                            
            } else {
                ItemDoPedidoDAO itemDAO = new ItemDoPedidoDAO();
                pedido.setId(idPedido);
                itemDAO.insert(pedido);
               ClienteDAO ClienteDAO = new ClienteDAO();
                Cliente cliente = new Cliente();
               
                int id = Integer.parseInt(request.getParameter("idCliente"));
                
                cliente = ClienteDAO.getById(id);
                
                
                
                Cliente Cliente = new Cliente();
                

                
                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String cpf = request.getParameter("cpf");
                

                Cliente.setId(id);
                Cliente.setNome(nome);
                Cliente.setSobreNome(sobrenome);
                Cliente.setCpf(cpf);
                
           
            List<Pedido>lstpedido = new ArrayList();
            
            lstpedido= PedidoDAO.getLista(id);
            ProdutoDAO ProdutoDAO = new ProdutoDAO();
            Cliente.getPedido();
            
            Produto Produto = new Produto();
            List<Produto>lstproduto = new ArrayList();
            lstproduto= ProdutoDAO.getLista();
           
            
             
            ItemDoPedidoDAO ItemDoPedidoDAO = new ItemDoPedidoDAO();
            
            List<ItemDoPedido>lstitem = new ArrayList();
            lstitem = ItemDoPedidoDAO.getLista(id);
            pedido = PedidoDAO.getById(pedido.getId());
               

               
            request.setAttribute("dados3", lstitem);
            request.setAttribute("cliente", cliente);
            request.setAttribute("pedido", pedido);
            request.setAttribute("dados2", lstpedido);
            request.setAttribute("dados", lstproduto);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pedido.jsp");
            rd.forward(request, response);
                
             
        } 
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessaItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessaItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcessaItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessaItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
