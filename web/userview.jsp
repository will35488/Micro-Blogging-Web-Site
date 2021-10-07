<%-- 
    Document   : userview
    Created on : Aug 17, 2021, 1:34:39 PM
    Author     : User
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/include/header.jsp"/>

<h1>Tweets for user: ${username} | <c:choose><c:when test="${followable}">
                                                <a href="login?action=follow&username=${username}">Follow</a></c:when>
                                            <c:when test="${followable==false}">
                                                <a href="login?action=unfollow&username=${username}">Unfollow</a></c:when>
                                </c:choose></h1> 
        ${requestscope.username}
        <p></p>
        <table>
            <tr>
                <th>Name</th>
                <th>Tweet</th>
                <th>Date</th>
                <th>Likes</th>
                <th></th>
                <th></th>
                
                </tr>
            <c:forEach var="chirpdisplay" items="${displaylist}">
                <tr>
                
                <td><c:out value='${chirpdisplay.name}'/></td>
                <td><c:out value='${chirpdisplay.text}'/></td>
                <td>${chirpdisplay.date}</td>
                <td><c:out value='${chirpdisplay.likecount}'/></td>
                
                <td><c:choose>
                        <c:when test="${chirpdisplay.likeable}"><form action="login" method="post"><input type ="hidden" name="originatefrom" value="userview"><input type ="hidden" name="action" value="like"><input type ="hidden" name="chirpid" value="${chirpdisplay.chirpid}"><input type ="hidden" name="username" value="${username}"><input type="submit" value="Like"></form></c:when>
                        <c:when test="${chirpdisplay.likeable==false}"><form action="login" method="post"><input type ="hidden" name="originatefrom" value="userview"><input type ="hidden" name="action" value="unlike"><input type ="hidden" name="chirpid" value="${chirpdisplay.chirpid}"><input type ="hidden" name="username" value="${username}"><input type="submit" value="Unlike"></form></c:when>
                </c:choose></td>
                    
                
                <td><c:if test="${chirpdisplay.filename != 'blank'}">
                        <img src="getimagecontroller?chirpid=${chirpdisplay.chirpid}&${chirpdisplay.filename}" width="300" height="200"/>
                    
                    </c:if></td>
                
                
                
                    
                    
                    
                    
                </tr>
            </c:forEach>
            
        </table>    
        
    </body>
</html>
