<%-- 
    Document   : index
    Created on : 27/09/2016, 15:52:38
    Author     : cliente
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Login</title>
    </head>
    <body>
        <div class = "jumbotron text-center" >
            <div class="col-lg-4 col-lg-offset-4">
            <form action="ProcessaLogin" method="POST">
               <div class ="form-group" >
            <span>Usuario:</span>
            <input type="text"  class="form-control" name="login"/><br />
            <span>Senha:</span>
            <input type="password" class="form-control" name="senha"/>
               </div>
            <input type="submit"  class=" btn btn-success" value="Ok" />
            
             </form>
            </div>
        </div>    
    </body>
</html>

