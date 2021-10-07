<%-- 
    Document   : homepage
    Created on : Aug 17, 2021, 11:27:16 AM
    Author     : User
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/include/header.jsp"/>
        <h1>Home screen for ${user.name}</h1>
<!--        <p>List length: ${listlength}</p>-->
        <form action="ChirpController" method="post" enctype="multipart/form-data">
            <label>Tweet:</label>
            <input type="text" name="chirptext"><br>
            <input type="file" accept="image/*" name="file">
            <input type ="hidden" name="action" value="newchirp">
            <input type="submit" value="Submit">
        </form>
        
        
          
            
        
        
        
        ${message}
        <br>
        <br>
        <br>
        <table >
            <tr>
                <th>Name</th>
                <th>Tweet</th>
                <th>Date</th>
                <th>Likes</th>
                <th></th>
<!--                <th>Followable</th>-->
                <th></th>
                </tr>
            <c:forEach var="chirpdisplay" items="${displaylist}">
                <tr>
                
                <td><c:out value='${chirpdisplay.name}'/></td>
                <td><c:out value='${chirpdisplay.text}'/></td>
                <td>${chirpdisplay.date}</td>
                <td><c:out value='${chirpdisplay.likecount}'/></td>
                
                <td><c:choose>
                        <c:when test="${chirpdisplay.likeable}"><form action="login" method="post"><input type ="hidden" name="originatefrom" value="homepage"><input type ="hidden" name="action" value="like"><input type ="hidden" name="chirpid" value="${chirpdisplay.chirpid}"><input type="submit" value="Like"></form></c:when>
                        <c:when test="${chirpdisplay.likeable==false}"><form action="login" method="post"><input type ="hidden" name="originatefrom" value="homepage"><input type ="hidden" name="action" value="unlike"><input type ="hidden" name="chirpid" value="${chirpdisplay.chirpid}"><input type="submit" value="Unlike"></form></c:when>
                </c:choose></td>
                    
                
                <td><c:if test="${chirpdisplay.filename != 'blank'}">
                        <img src="getimagecontroller?chirpid=${chirpdisplay.chirpid}&${chirpdisplay.filename}" width="300" height="200"/>
                    
                    </c:if></td>
                
                
                
                    
                    
                    
                    
                </tr>
            </c:forEach>
            
        </table>    

        
        
        
        
    </body>
</html>
