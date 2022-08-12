package CONTROLLER;

import DAO.RulesDAO;
import DAOImpl.RulesDAOImpl;
import MODEL.Rules;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RulesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String cmd = request.getParameter("command");
        
        if (cmd != null){
            switch(cmd){
                case "save":
                    saveRules(request, response);
                    break;
                
                case "update":
                    updateRules(request, response);
                    break;
                
                case "delete":
                    deleteRules(request, response);
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "ADMIN/AdminDashboard.jsp");
        }
    }
    
    private void saveRules(HttpServletRequest req, HttpServletResponse resp) 
            throws SQLException, ServletException, IOException{
        
        //get all data from signup
        String desc = req.getParameter("desc");
        
        //keep data into javabeans
        Rules newRules = new Rules();
        
        newRules.setRulesDesc(desc);

        //pass the bean to DAO
        RulesDAO staf = new RulesDAOImpl();
        staf.save(newRules);
        
        //save the bean as attribute and pass to view
        req.setAttribute("rulesDetails", newRules);
        resp.sendRedirect("ADMIN/AdminFeatures.jsp");
    }
    
    private void updateRules(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        String desc = request.getParameter("desc");
        int id = Integer.parseInt(request.getParameter("hidid"));
        
        Rules rules = new Rules();
        
        rules.setRulesDesc(desc);
        rules.setRulesID(id);

        RulesDAO rulesdao = new RulesDAOImpl();
        
        rulesdao.update(rules);

        HttpSession session = request.getSession();
        session.setAttribute("rules", rules);
        
        response.sendRedirect("ADMIN/AdminFeatures.jsp");
    }
    
    private void deleteRules(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
         
        int id = Integer.parseInt(request.getParameter("hidid"));
        
        Rules rules = new Rules();
        
        rules.setRulesID(id);
        
        RulesDAO rulesdao = new RulesDAOImpl();
        
        rulesdao.delete(rules);

        HttpSession session = request.getSession();
        session.invalidate();
        
        response.sendRedirect("ADMIN/AdminFeatures.jsp");
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
            Logger.getLogger(RulesController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RulesController.class.getName()).log(Level.SEVERE, null, ex);
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
