package CONTROLLER;

import DAO.FacilitiesDAO;
import DAOImpl.FacilitiesDAOImpl;
import MODEL.Facilities;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215)
public class FacilitiesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, IllegalStateException, ClassNotFoundException {
        
        String cmd = request.getParameter("command");
        
        if (cmd != null){
            switch(cmd){
                case "save":
                    saveFacilities(request, response);
                    break;
                
                case "update":
                    updateFacilities(request, response);
                    break;
                
                case "delete":
                    deleteFacilities(request, response);
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "ADMIN/AdminDashboard.jsp");
        }
    }
    
    private void saveFacilities(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, IllegalStateException, ServletException, SQLException, ClassNotFoundException {

        //get all data from form
        String name = req.getParameter("name");
        Part image = req.getPart("image");
        Double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        //keep data into javabeans
        Facilities newfacilities = new Facilities();

        newfacilities.setFacilitiesName(name);
        newfacilities.setFacilitiesImage(image);
        newfacilities.setFacilitiesPrice(price);
        newfacilities.setFacilitiesQuantity(quantity);

        //pass the bean to DAO
        FacilitiesDAO facilitiesdao = new FacilitiesDAOImpl();
        facilitiesdao.save(newfacilities);

        req.setAttribute("facilitiesDetails", newfacilities);
        resp.sendRedirect("ADMIN/AdminPricing.jsp");
    }
    
    private void updateFacilities(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, IllegalStateException, ServletException, SQLException, ClassNotFoundException {

        String name = req.getParameter("name");
        Part image = req.getPart("image");
        Double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int id = Integer.parseInt(req.getParameter("hidid"));

        Facilities facilities = new Facilities();

        facilities.setFacilitiesName(name);
        facilities.setFacilitiesImage(image);
        facilities.setFacilitiesPrice(price);
        facilities.setFacilitiesQuantity(quantity);
        facilities.setFacilitiesID(id);
        
        FacilitiesDAO facilitiesdao = new FacilitiesDAOImpl();
        facilitiesdao.update(facilities);
        
        req.setAttribute("facilities", facilities);
        resp.sendRedirect("ADMIN/AdminPricing.jsp");
    }

    private void deleteFacilities(HttpServletRequest req, HttpServletResponse resp) 
            throws SQLException, IOException, ClassNotFoundException {
        
        int id = Integer.parseInt(req.getParameter("hidid"));
        
        Facilities facilities = new Facilities();
        
        facilities.setFacilitiesID(id);
        
        FacilitiesDAO facilitiesdao = new FacilitiesDAOImpl();
        facilitiesdao.delete(facilities);

        resp.sendRedirect("ADMIN/AdminPricing.jsp");
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
            Logger.getLogger(FacilitiesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(FacilitiesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacilitiesController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FacilitiesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(FacilitiesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacilitiesController.class.getName()).log(Level.SEVERE, null, ex);
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
