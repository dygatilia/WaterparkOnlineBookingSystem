package CONTROLLER;

import DAO.StaffDAO;
import DAOImpl.StaffDAOImpl;
import MODEL.Staff;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StaffController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String cmd = request.getParameter("command");
        
        if (cmd != null){
            switch(cmd){
                case "addStaff":
                    saveStaff(request, response);
                    break;
                
                case "signin":
                    retrieveStaff(request, response);
                    break;
                
                case "updateStaff":
                    updateStaff(request, response);
                    break;
                
                case "deleteStaff":
                    deleteStaff(request, response);
                    break;
                    
                case "signout":
                    signOut(request, response);
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "ADMIN/AdminDashboard.jsp");
        }
    }
    
    //to check whether email is existed or not
    //private void verifyEmail(HttpServletRequest req, HttpServletResponse resp)
      //      throws SQLException, ServletException, IOException{
        
        //if(req.getParameter("command") != null){
            
          //  String name = req.getParameter("name");
            //String email = req.getParameter("email");
            //String password = req.getParameter("password");
            
    //        StaffDAO staffdao = new StaffDAOImpl();
            
      //      try {
        //        Staff staff = staffdao.verifyEmail(email);
                
          //      if(staff == null){
            //        Staff newStaff = new Staff();
        
              //      newStaff.setStaffName(name);
                //    newStaff.setStaffEmail(email);
                  //  newStaff.setStaffPassword(password);

    //                StaffDAO staf = new StaffDAOImpl();
      //              staf.save(newStaff);
        
        //            req.setAttribute("staffDetails", newStaff);
          //          resp.sendRedirect("ADMIN/AdminStaff.jsp");
                    
            //    } else {
              //      HttpSession session = req.getSession();
                //    session.setAttribute("erroremail", "Email is already taken.");
                  //  resp.sendRedirect("ADMIN/AdminAddStaff.jsp");
                //}
                
//            } catch (SQLException e){
  //              throw new ServletException(e);
    //        }
      //  }    
    //}
    
    //sign up - add user
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
    
    //log in - direct to the dashboard
    private void retrieveStaff(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException{
        
        if(req.getParameter("command") != null){
            
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            
            StaffDAO staffdao = new StaffDAOImpl();
            
            try {
                Staff staff = staffdao.authentication(email, password);
                
                if(staff != null){
                    
                    HttpSession session = req.getSession();
                    session.setAttribute("staff", staff);
                    resp.sendRedirect("ADMIN/AdminMessage.jsp");
                    
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
    
    //retrieve all staff
    private void listStaff(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException{
        
        StaffDAO staffdao = new StaffDAOImpl();
        List <Staff> stafflist = staffdao.retrieveAll();
        
        request.setAttribute("stafflist",stafflist);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ADMIN/AdminStaff.jsp");
        dispatcher.forward(request, response);
    }
    
    //update staff
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
    
    //delete account
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
    
    private void signOut(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        request.getSession().removeAttribute("vmstaff");
        
        response.sendRedirect("ADMIN/AdminStaff.jsp");
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
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
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
