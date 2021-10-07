/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author User
 */

@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class ChirpController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    // https://www.codejava.net/coding/upload-files-to-database-servlet-jsp-mysql
    
    //get filepart, make input stream, make byte array
        InputStream inputStream = null; // input stream of the upload file
        Part filePart = request.getPart("file");
        
        byte[] targetArray= null;
        String filename = null;
        
        if (filePart!=null) {
            filename = extractFileName(filePart);
        
            inputStream = filePart.getInputStream();

            targetArray = new byte[inputStream.available()];

            inputStream.read(targetArray);
            inputStream.close();
        }
        
        String action = request.getParameter("action");
        String url="/Chirpsuccess.jsp";
        
        Chirp chirp;
        
        HttpSession session = request.getSession();
        String text = (String)request.getParameter("chirptext");
        User user = (User)session.getAttribute("user");
        if (user!=null && action.equals("newchirp")) {
            request.setAttribute("action", "homepage");
            
            if (!filename.equals("")) {
                chirp=new Chirp(user, text,targetArray, filename);
                ChirpDB.insert(chirp);
                request.setAttribute("message", "New chirp added with image."+text);
                
                //url="/login";
                //url="/Chirpsuccess.jsp";
            }
            else{
                chirp=new Chirp(user, text, "blank");
                ChirpDB.insert(chirp);
                request.setAttribute("message", "New chirp added."+text);
                //request.setAttribute("action", "homepage");
                //url="/login";
                //url="/Chirpsuccess.jsp";
            }
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
        
        
//        if (user==null) {
//            url="/login.jsp";
//            session.setAttribute("message", "Please login.");
//            return url;
//        }
        
        
        
        
        
        
        
        
        
        
    }
    
    private void newChirp(HttpServletRequest request, HttpServletResponse response){
        
        
        
        
        
        
        
        
        
        
    
    }
    //https://www.codejava.net/java-ee/servlet/java-file-upload-example-with-servlet-30-api
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
