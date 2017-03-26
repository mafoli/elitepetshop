<%-- 
    Document   : Portal
    Created on : 27/09/2016, 16:40:26
    Author     : cliente
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
   
    <jsp:useBean id="Usuario" scope="session" class="Trabalho.Usuario" />
    <c:choose>
        <c:when test="${Usuario.id > 0}">
            <head>
                <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link href="bootstrap/css/main.css" rel="stylesheet" type="text/css"/>
                <title>Cadastro Cliente</title>
            </head>
            <body>
                <jsp:useBean id="cliente" scope="request" class="Trabalho.Cliente" />
                <jsp:useBean id="pedido" scope="request" class="Trabalho.Pedido" />
                 <nav id="mainNav" class="   azul navbar navbar-dark navbar-fixed-top navbar-custom">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="marca" >PORTAL UFPR</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="azul  navbar-collapse " id="bs-example-navbar-collapse-1">
                <ul class=" azul  nav  navbar-nav  navbar-right ">
                    <li class="hidden"> 
                        <a href="#page-top"></a>
                    </li>
                    
                    <li class="page-scroll">
                        
                        <a  class="marca" href="cliente.jsp"> Cliente </a>
                    </li>
                    <li class="page-scroll"> 
                        <a href="produto.jsp" class="marca" >Produto </a>
                    </li>
                    
                    <li class="page-scroll">
                         <a href="ProcessaLogout" class="marca">Logout</a>
                    </li>
                    
                    
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
                <div class = "jumbotron text-center" >
                <h3 class="h3">Bem vindo ao Portal, ${Usuario.login}</h3>
                <h3 class="h3">Pedido</h3>
                </div>
                <div class="container">
      <!-- Example row of columns -->
                
                <div class="col-md-4">
                
                 <form method="post"   action="ProcessaItem">
                   <div class="form-group">
                   <div style="display:none;">
                   
                   <input type="text" value="${pedido.id}" name="idPedido">
                   <input type="text" value="${cliente.id}" name="idCliente">
                   </div>
               
                <div class="edita">
                    <label>Data:</label><br>
                    <fmt:formatDate var='fmtDate' value='${pedido.data}' pattern='dd/MM/yyyy' />
                     <input class="data"  value="${fmtDate}"  name="data"><br><br>
                    <label>Produto:</label><br> 
                    <select id="lstproduto" class="required" name="idProduto">
                     <option value="0"></option>
                    <c:forEach items="${dados}" var="x">
                    <option value="${x.id}" name="idproduto">${x.descricao}</option>
                    </c:forEach>
                     </select><br><br>
                    
                    <label>Quantidade:</label><br>
                    <input class="form-control" type="text" value="${produto.quantidade}" name="qtdade"><br><br>
                   
                    <input type="submit" class=" btn btn-success" value="Salvar" />
                    
                  </div>
                  </div>
                 </form>
                </div>
                
                 
     
      <div class="col-md-8">
                    
                 
               <br>
                <table class = "table table-striped">
                    <tr>
                        <td>Codigo</td>
                        <td>Data</td>
                       
                        
                        <td>Ações</td>
                    </tr>
                    <c:forEach items="${dados2}" var="x">
                        <tr>
                            <td>${x.id}</td>
                            <td>${x.data}</td>
                            
                            
                            <td>
                                <a href="EditaPedido?id=${x.id}" class="btn btn-primary btn-xs" >Itens</a>
                                
                            </td>
                        </tr>
                    </c:forEach>
                  </table>    
               
                <table class = "table table-striped">
                    <tr>
                        <td>Codigo</td>
                        <td>Produto</td>
                        <td>Quantidade</td>
                        
                        
                        <td>Ações</td>
                    </tr>
                    <c:forEach items="${dados3}" var="y">
                        <tr>
                            <td>${y.id_pedido}</td>
                            <td>${y.produto.descricao}</td>
                            <td>${y.qtdade}</td>
                            
                            
                            <td>
                                
                                
                                <a href="ExcluiItem?idPedido=${y.id_pedido}&idProduto=${y.produto.id}&idCliente=${cliente.id}" class="btn btn-danger btn-xs" >Excluir</a>
                            </td>
                        </tr>
                        
                        
                    </c:forEach>
                  </table>
                 </div>
                 
             
                   
                     </div>
            </body>
        </c:when>
        <c:otherwise>
            <c:out value="Precisa estar logado." />
        </c:otherwise>
    </c:choose>    
</html>
