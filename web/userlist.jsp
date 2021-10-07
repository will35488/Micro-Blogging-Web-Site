<%-- 
    Document   : userlist
    Created on : Aug 19, 2021, 12:34:16 PM
    Author     : User
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/include/header.jsp"/>
        <h1>Registered users</h1>
        
        
        <table>
            <tr><th>Users</th></tr>
            <c:forEach var="user" items="${userlist}">
                <tr><td><a href="login?action=userview&username=<c:out value='${user.name}'/>"> <c:out value='${user.name}'/> </a></td></tr>
                
                
            </c:forEach>
            
            
            
            
            
        </table>
    </body>
</html>
