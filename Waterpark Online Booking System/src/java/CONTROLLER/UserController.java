package CONTROLLER;

import DAO.AdminDAO;
import DAO.StaffDAO;
import DAO.UserDAO;
import DAOImpl.AdminDAOImpl;
import DAOImpl.StaffDAOImpl;
import DAOImpl.UserDAOImpl;
import MODEL.Admin;
import MODEL.Staff;
import MODEL.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String cmd = request.getParameter("command");
        
        if (cmd != null){
            switch(cmd){
                case "verify":
                    verifyEmail(request, response);
                    break;
                  
                case "signup":
                    saveUser(request, response);
                    break;
                
                case "addStaff":
                    saveStaff(request, response);
                    break;
                
                case "signin":
                    retrieveUser(request, response);
                    break;
                    
                case "updateUser":
                    updateUser(request, response);
                    break;
                
                case "updateAdmin":
                    updateAdmin(request, response);
                    break;
                
                case "updateStaff":
                    updateStaff(request, response);
                    break;
                    
                case "delete":
                    deleteUser(request, response);
                    break;
                
                case "deleteStaff":
                    deleteStaff(request, response);
                    break;
                    
                case "signout":
                    signOut(request, response);
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "index.jsp");
        }
    }
    
    // to check whether email is existed or not
    // customer
    private void verifyEmail(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException{
        
        if(req.getParameter("command") != null){
            
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            
            UserDAO userdao = new UserDAOImpl();
            
            try {
                User user = userdao.verifyEmail(email);
                
                if(user == null){
                    User newUser = new User();
        
                    newUser.setName(name);
                    newUser.setEmail(email);
                    newUser.setPassword(password);

                    UserDAO cust = new UserDAOImpl();
                    cust.save(newUser);
        
                    req.setAttribute("userDetails", newUser);
                    resp.sendRedirect("GENERAL/LogIn.jsp");
                    
                } else {
                    HttpSession session = req.getSession();
                    session.setAttribute("erroremail", "Email is already taken.");
                    resp.sendRedirect("GENERAL/SignUp.jsp");
                }
                
            } catch (SQLException e){
                throw new ServletException(e);
            }
        }    
    }
    
    // sign up - add user
    // customer
    private void saveUser(HttpServletRequest req, HttpServletResponse resp) 
            throws SQLException, ServletException, IOException{
        
        //get all data from signup
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        //keep data into javabeans
        User newUser = new User();
        
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        //pass the bean to DAO
        UserDAO cust = new UserDAOImpl();
        cust.save(newUser);
        
        //save the bean as attribute and pass to view
        req.setAttribute("userDetails", newUser);
        resp.sendRedirect("GENERAL/LogIn.jsp");
    }
    
    private void saveStaff(HttpServletRequest req, HttpServletResponse resp) 
            throws SQLException, ServletException, IOException{
        
        //get all data from signup
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        //keep data into javabeans
        Staff newStaff = new Staff();
        
        newStaff.setStaffName(name);
        newStaff.setStaffEmail(email);
        newStaff.setStaffPassword(password);

        //pass the bean to DAO
        StaffDAO staf = new StaffDAOImpl();
        staf.save(newStaff);
        
        //save the bean as attribute and pass to view
        req.setAttribute("staffDetails", newStaff);
        resp.sendRedirect("ADMIN/AdminStaff.jsp");
    }
    
    // log in - direct to the dashboard
    // all
    private void retrieveUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException{
        
        if(req.getParameter("command") != null){
            
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            
            AdminDAO admindao = new AdminDAOImpl();
            StaffDAO staffdao = new StaffDAOImpl();
            UserDAO userdao = new UserDAOImpl();
            
            try {
                Admin admin = admindao.authentication(email, password);
                Staff staff = staffdao.authentication(email, password);
                User user = userdao.authentication(email, password);
                
                if(admin != null){
                    HttpSession session = req.getSession();
                    session.setAttribute("admin", admin);
                    resp.sendRedirect("ADMIN/AdminDashboard.jsp");
                    
                } else if(staff != null){
                    HttpSession session = req.getSession();
                    session.setAttribute("staff", staff);
                    resp.sendRedirect("STAFF/StaffDashboard.jsp");
                    
                } else if(user != null){
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    resp.sendRedirect("CUSTOMER/CustMainPage.jsp");
                    
                } else {
                    req.setAttribute("error", "Invalid username or password!");
                    RequestDispatcher rd = req.getRequestDispatcher("GENERAL/LogIn.jsp");
                    rd.forward(req,resp);
                    
                }
                
            } catch (SQLException e){
                throw new ServletException(e);
            }
        }
        
    }
    
    // update admin
    // admin
    private void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("hidid"));
        
        Admin admin = new Admin();
        
        admin.setAdEmail(email);
        admin.setAdPassword(password);
        admin.setAdminID(id);
       
        AdminDAO admindao = new AdminDAOImpl();
         
        admindao.update(admin);
        
        HttpSession session = request.getSession();
        session.setAttribute("admin", admin);
        
        response.sendRedirect("ADMIN/AdminSettings.jsp");
    }
    
    // update customer
    //customer
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("hidid"));
        
        User user = new User();
        
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setId(id);
        
        UserDAO userdao = new UserDAOImpl();
        
        userdao.update(user);
        
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        response.sendRedirect("CUSTOMER/CustSettings.jsp");
    }
    
    private void updateStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("hidid"));
        
        Staff staff = new Staff();
        
        staff.setStaffName(name);
        staff.setStaffEmail(email);
        staff.setStaffPassword(password);
        staff.setStaffID(id);

        StaffDAO staffdao = new StaffDAOImpl();
        
        staffdao.update(staff);

        HttpSession session = request.getSession();
        session.setAttribute("staff", staff);
        
        response.sendRedirect("ADMIN/AdminStaff.jsp");
    }
    
    // delete account
    // customer
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("hidid"));
        
        User user = new User();
        
        user.setId(id);
        
        UserDAO userdao = new UserDAOImpl();
        
        userdao.delete(user);

        HttpSession session = request.getSession();
        session.invalidate();
        
        response.sendRedirect("index.jsp");
        
    }
    
    private void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
         
        int id = Integer.parseInt(request.getParameter("hidid"));
        
        Staff staff = new Staff();
        
        staff.setStaffID(id);
        
        StaffDAO staffdao = new StaffDAOImpl();
        
        staffdao.delete(staff);

        HttpSession session = request.getSession();
        session.invalidate();
        
        response.sendRedirect("ADMIN/AdminStaff.jsp");
    }
    
    // all
    private void signOut(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        request.getSession().removeAttribute("vmuser");
        
        response.sendRedirect("index.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
