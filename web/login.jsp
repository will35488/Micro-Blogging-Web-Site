<%-- 
    Document   : login
    Created on : Aug 17, 2021, 9:56:30 AM
    Author     : User
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/include/header.jsp"/>
        <h1>Please login.</h1>
        
        <form action="login" method="post">
            <label>Name:</label>
            <input type="text" name="name"><br>
            <label>Password:</label>
            <input type="text" name="password"><br>
            <input type ="hidden" name="action" value="login">
            <input type="submit" value="Submit">
        </form>
    ${message}<br>
    <a href="/WDFinal/createuser.jsp"> Create User </a>
    </body>
</html>
