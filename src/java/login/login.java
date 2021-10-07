/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String url  = "/login.jsp";
        String action;
        //see if authenticated
        Boolean auth = (Boolean) session.getAttribute("auth");
        action = request.getParameter("action");
        String username = request.getParameter("username");
        String originatefrom = request.getParameter("originatefrom");
        
        if (auth == null) {
            action="login";
            auth = false;
        }
        
        
        if (auth == false) {
            action="login";
        }
        
        
        if (action == null) {
            action = "homepage";
        }
        
        if (action.equals("userlist")) {
            url = userList(request, response);
        }
        
        if (action.equals("like")) {
            newLike(request, response);
            if (originatefrom.equals("homepage")) {
                action = "homepage";
            }
            else action = "userview";
            
            
            //response.sendRedirect("homepage.jsp");
        }
        
        if (action.equals("unlike")) {
            unlike(request, response);
            if (originatefrom.equals("homepage")) {
                action = "homepage";
            }
            else action = "userview";
        }
        
        if (action.equals("logout")) {
            //session.removeAttribute("user");
            //session.setAttribute("auth", false);
            session.invalidate();
            url = "/login.jsp";
        }
        
        
        if (action.equals("login")) {
            url = checkLogin(request, response);
            if (url.equals("/homepage.jsp")) {
                action="homepage";
            }
        }
        
        if (action.equals("newchirp")) {
            url = newChirp(request, response);
        }
        
        if (action.equals("homepage")) {
            url = homePageView(request, response);
        }
        
        if (action.equals("follow")) {
            url = follow(request, response);
        }
        
        if (action.equals("unfollow")) {
            url = unfollow(request, response);
        }
        
        if (username!=null || action.equals("userview")) {
            if (auth) {
                url= userView(request, response);
            }
            //else url="/login.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
        
        
        
//        if (auth == null || auth == false) {
//            url="/login.jsp";
//            getServletContext().getRequestDispatcher(url).forward(request, response);
//        }
        
        
        //url="/login.jsp";
        //getServletContext().getRequestDispatcher(url).forward(request, response);
        
        
        
        
        
        
        
        
        
        
        
       
        
        
        
        
        
        //String action = request.getParameter("action");
        if (action == null){
            action = "homePage";
        
        }
        
        
        
    }
    
    private String checkLogin(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String url = "/login.jsp";
        
        String name = request.getParameter("name");
        String password =request.getParameter("password");
        User user  = userDB.selectUser(name);
        if (user == null) {
            request.setAttribute("message", "User not found.");
            return url;
        }
        
        if (user.getPassWord().equals(password)) {
            url="/homepage.jsp";
            session.setAttribute("user", user);
            session.setAttribute("auth", true);
            request.setAttribute("message", "");
            return url;
        }
        else  
            session.setAttribute("message", "Password is incorrect.");
        return url;
    }
    
    private String newChirp(HttpServletRequest request, HttpServletResponse response){
        String url="/homepage.jsp";
        Chirp chirp;
        
        HttpSession session = request.getSession();
        String text = (String)request.getParameter("chirptext");
        User user = (User)session.getAttribute("user");
        if (user==null) {
            url="/login.jsp";
            session.setAttribute("message", "Please login.");
            return url;
        }
        chirp=new Chirp(user, text);
        ChirpDB.insert(chirp);
        
        session.setAttribute("message", "New chirp added."+text);
        return url;
    
    }
    
    private String userView(HttpServletRequest request, HttpServletResponse response){
        String url="/userview.jsp";
        String username=request.getParameter("username");
        User user = userDB.selectUser(username);
        Long userid= null;
        if (user!=null) {
            userid = user.getUserId();
        }
        
        
        
        HttpSession session = request.getSession();
        User currentuser = (User)session.getAttribute("user");
        
        
        
        List<ChirpDisplay> mainlist= null;
        if (currentuser!=null && userid!=null) {
            Boolean followable = FollowDB.checkFollowable(currentuser, user);
            mainlist = ChirpDisplay.getDisplayListForUserView(userid,currentuser);
            if (mainlist!=null) {
                session.setAttribute("listlength", mainlist.size());
            } 
            
            session.setAttribute("displaylist", mainlist);
            request.setAttribute("username", username);
            request.setAttribute("followable", followable);
            return url;
        }else url="/userdoesnotexist.jsp";
        return url;
    
    
    }
    
    private String homePageView(HttpServletRequest request, HttpServletResponse response){
        String url="/homepage.jsp";
        HttpSession session = request.getSession();
        User currentuser = (User)session.getAttribute("user");
        List<ChirpDisplay> mainlist= null;
        if (currentuser!=null) {
            mainlist = ChirpDisplay.getDisplayListForFollowed(currentuser);
            if (mainlist!=null) {
                session.setAttribute("listlength", mainlist.size());
            } 
            
            session.setAttribute("displaylist", mainlist);
            return url;
        }
        return url;
        
    }
    
    private void newLike(HttpServletRequest request, HttpServletResponse response){
        String url="/homepage.jsp";
        String originatefrom = (String)request.getParameter("originatefrom");
        
        HttpSession session = request.getSession();
        User currentuser = (User)session.getAttribute("user");
        String chirpId = request.getParameter("chirpid");
        Long chirpIdlong = Long.valueOf(chirpId);
        List<Chirp> chirplist = ChirpDB.selectById(chirpIdlong);
        if (!chirplist.isEmpty()) {
            Chirp likedchirp = chirplist.get(0);
            LikeDB.insert(new Lke(currentuser, likedchirp));
        }
        
        
        if (originatefrom.equals("homepage")) {
            request.setAttribute("action", "homepage");
        }else request.setAttribute("action", "userview");
        //return url;
    }
    
    
    private void unlike(HttpServletRequest request, HttpServletResponse response){
        String url="/homepage.jsp";
        String originatefrom = (String)request.getParameter("originatefrom");
        
        HttpSession session = request.getSession();
        User currentuser = (User)session.getAttribute("user");
        String chirpId = request.getParameter("chirpid");
        Long chirpIdlong = Long.valueOf(chirpId);
        Chirp chirp = ChirpDB.getChirpById(chirpIdlong);
        Lke like = LikeDB.selectSingleLike(currentuser, chirp);
        
        
        LikeDB.unlike(like);
        //return url;

    }
    
    private String follow(HttpServletRequest request, HttpServletResponse response){
        String url = "/userview.jsp";
        String followedusername = request.getParameter("username");
        HttpSession session = request.getSession();
        
        User currentuser = (User)session.getAttribute("user");
        User followeduser = userDB.selectUser(followedusername);
        
        Follow newfollow = new Follow(currentuser, followeduser);
        FollowDB.insert(newfollow);
        
        //update followable boolean
        Boolean followable = FollowDB.checkFollowable(currentuser,followeduser );
        request.setAttribute("followable", followable);
        
        return url;
    
    
    
    }
    
    
    private String unfollow(HttpServletRequest request, HttpServletResponse response){
        String url = "/userview.jsp";
        String followedusername = request.getParameter("username");
        HttpSession session = request.getSession();
        
        User currentuser = (User)session.getAttribute("user");
        User followeduser = userDB.selectUser(followedusername);
        Follow follow = FollowDB.selectSingleFollow(currentuser, followeduser);
        FollowDB.unfollow(follow);
        //update followable boolean
        Boolean followable = FollowDB.checkFollowable(currentuser,followeduser );
        request.setAttribute("followable", followable);
        return url;
    }
    
    private String userList(HttpServletRequest request, HttpServletResponse response){
        List<User> userlist = userDB.getUserList();
        request.setAttribute("userlist", userlist);
        String url = "/userlist.jsp";
        return url;
    
    
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
