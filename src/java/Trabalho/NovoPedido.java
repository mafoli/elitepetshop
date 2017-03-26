/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author cliente
 */
@WebServlet(name = "NovoPedido", urlPatterns = {"/NovoPedido"})
public class NovoPedido extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
         {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            Usuario Usuario = (Usuario) session.getAttribute("Usuario");

            if (Usuario != null) {
               

                PedidoDAO PedidoDAO = new PedidoDAO();
                Pedido Pedido = new Pedido();
                ProdutoDAO ProdutoDAO = new ProdutoDAO();
                Produto Produto = new Produto();
                List<Produto>lstproduto = new ArrayList();
                ClienteDAO ClienteDAO = new ClienteDAO();
                Cliente cliente = new Cliente();
                
                 int id = Integer.parseInt(request.getParameter("id"));
                
                cliente = ClienteDAO.getById(id);
                
                
                List<Pedido> lista = new ArrayList();


            
                
                try {
                    cliente = ClienteDAO.getById(id);
                    lista = cliente.getPedido();
                    lstproduto= ProdutoDAO.getLista();
                    request.setAttribute("cliente", cliente);
                    request.setAttribute("dados", lstproduto);
                } catch (SQLException ex) {
                    Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            
               RequestDispatcher rd = getServletContext().getRequestDispatcher("/pedido.jsp");
               rd.forward(request, response);
                

//                

//                
            } else {
                request.setAttribute("ERRO", "ERRO: VOCE PRECISA ESTAR LOGADO PARA ACESSAR ESSA PAGINA.");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ProcessaErro");
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
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NovoPedido.class.getName()).log(Level.SEVERE, null, ex);
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
