<%-- 
    Document   : displayimage
    Created on : Aug 18, 2021, 5:20:59 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <img src="data:image/jpg;base64,${base64Image.image}" width="240" height="300"/>
    </body>
</html>
