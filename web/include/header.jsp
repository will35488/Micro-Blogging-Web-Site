<%-- 
    Document   : newjsp
    Created on : Aug 17, 2021, 5:41:01 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="test.css">
        <style>
/*            Taken from: https://www.w3schools.com/css/tryit.asp?filename=trycss_table_striped*/
            table {
                
            }
            table {
                border-collapse: collapse;
                width: 100%;
              }

              th, td {
                text-align: left;
                padding: 8px;
              }

              tr:nth-child(even) {background-color: #f8f9fa;}
            
        </style>
        
        
    </head>
    <body>
        <p><a href="login"> Home | </a><a href="login?action=logout"> Logout | </a> <a href="login?action=userlist"> User List | </a> <c:if test="${user!=null}">Current user: ${user.name} |</c:if> 
        Current session: ${pageContext.session.id}</p>
    
