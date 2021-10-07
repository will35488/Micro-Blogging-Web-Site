<%-- 
    Document   : createuser
    Created on : Aug 17, 2021, 9:55:42 AM
    Author     : User
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/include/header.jsp"/>
        <h1>Create User</h1>
        
        <form action="createusercontroller" method="post">
            <label>Name:</label>
            <input type="text" name="name"><br>
            <label>Password:</label>
            <input type="text" name="password"><br>
            <input type ="hidden" name="action" value="">
            <input type="submit" value="Submit">
        </form>
        ${message1}
    </body>
</html>
