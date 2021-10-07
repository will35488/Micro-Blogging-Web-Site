<%-- 
    Document   : chirpdisplay
    Created on : Aug 18, 2021, 10:27:47 PM
    Author     : User
--%>


        
        <table>
            <tr>
                <th>Name</th>
                <th>Chirp</th>
                <th>Date</th>
                <th>Likes</th>
                <th>Likeable</th>
                <th>Followable</th>
                <th>Image</th>
            </tr>
            <c:forEach var="chirpdisplay" items="${displaylist}">
            <tr>
                
                <td><c:out value='${chirpdisplay.name}'/></td>
                <td><c:out value='${chirpdisplay.text}'/></td>
                <td>${chirpdisplay.date}</td>
                <td><c:out value='${chirpdisplay.likecount}'/></td>
                
                <td><c:if test="${chirpdisplay.likeable}"><form action="login" method="post"><input type ="hidden" name="originatefrom" value="homepage"><input type ="hidden" name="action" value="like"><input type ="hidden" name="chirpid" value="${chirpdisplay.chirpid}"><input type="submit" value="Like"></form></c:if></td>
                    
                   <td> <c:out value='${chirpdisplay.followable}'/></td>
<td><img src="getimagecontroller?chirpid=${chirpdisplay.chirpid}&AMP;${chirpdisplay.filename}" width="240" height="300"/></td>
            </tr>
            </c:forEach>
            
        </table>
        
        
        

