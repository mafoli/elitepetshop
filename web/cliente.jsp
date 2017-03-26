<%-- 
    Document   : Portal
    Created on : 27/09/2016, 16:40:26
    Author     : cliente
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                  <h3 class="h3">Cadastro de Cliente</h3>
                </div>
                <div class="container">
      <!-- Example row of columns -->
                
                <div class="col-md-4">
                <a href="NovoCliente" class="btn btn-info"> Novo Cliente </a><br><br>
                 <form method="post"   action="ProcessaCliente">
                   <div class="form-group">
                   <div style="display:none;">
                   <input type="text" value="${cliente.id}" name="id">
                   </div>
               
                <div class="edita">
                    <label>Nome:</label><br>
                    <input class="form-control" type="text" value="${cliente.nome}"   name="nome"><br><br>
                    <label>Sobrenome:</label><br>
                    <input class="form-control" type="text" value="${cliente.sobreNome}" name="sobrenome"><br><br>
                    <label>CPF:</label><br><input class="form-control" type="text" value="${cliente.cpf}" name="cpf"><br><br>
                    <input type="submit" class=" btn btn-success" value="Salvar" />
                    
                  </div>
                  </div>
                 </form>
                </div>
                    <div class="col-md-8"> <p> .</p></div>
                 
     
                    <div class="col-md-8">
                    <form method="post" action="BuscarCliente">
                        <div class=" col-xs-5">
                    <input type="text" class="form-control"  name="filtroCpf">
                        </div>
                    <input type="submit" class=" btn btn-primary" value="buscar"> 
                   
                </form>
                 
               <br>
                                
                <table class = "table table-striped">
                    <tr>
                        <td>Codigo</td>
                        <td>Nome</td>
                        <td>Sobrenome</td>
                        <td>CPF</td>
                        
                        <td>Ações</td>
                    </tr>
                    <c:forEach items="${dados}" var="x">
                        <tr>
                            <td>${x.id}</td>
                            <td>${x.nome}</td>
                            <td>${x.sobreNome}</td>
                            <td>${x.cpf}</td>
                            
                            <td>
                                <a href="BuscarPedido?id=${x.id}" class="btn btn-default btn-xs" >Pedido</a>
                                <a href="ProcessaCliente?id=${x.id}" class="btn btn-warning btn-xs" >Editar</a>
                                <a href="ExcluiCliente?id=${x.id}" class="btn btn-danger btn-xs" >Excluir</a>
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
