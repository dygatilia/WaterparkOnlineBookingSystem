package CONTROLLER;

import DAO.FeaturesDAO;
import DAOImpl.FeaturesDAOImpl;
import MODEL.Features;
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
public class FeaturesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, IllegalStateException, ClassNotFoundException {
        
        String cmd = request.getParameter("command");
        
        if (cmd != null){
            switch(cmd){
                case "save":
                    saveFeatures(request, response);
                    break;
                
                case "update":
                    updateFeatures(request, response);
                    break;
                
                case "delete":
                    deleteFeatures(request, response);
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "ADMIN/AdminDashboard.jsp");
        }
    }
    
    private void saveFeatures(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, IllegalStateException, ServletException, SQLException, ClassNotFoundException {

        //get all data from form
        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        Part image = req.getPart("image");

        //keep data into javabeans
        Features newfeatures = new Features();

        newfeatures.setFeaturesName(name);
        newfeatures.setFeaturesDesc(desc);
        newfeatures.setFeaturesImage(image);

        //pass the bean to DAO
        FeaturesDAO featuresdao = new FeaturesDAOImpl();
        featuresdao.save(newfeatures);

        req.setAttribute("featuresDetails", newfeatures);
        resp.sendRedirect("ADMIN/AdminFeatures.jsp");
    }
    
    private void updateFeatures(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, IllegalStateException, ServletException, SQLException, ClassNotFoundException {

        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        Part image = req.getPart("image");
        int id = Integer.parseInt(req.getParameter("hidid"));

        Features features = new Features();
        
        features.setFeaturesName(name);
        features.setFeaturesDesc(desc);
        features.setFeaturesImage(image);
        features.setFeaturesID(id);
        
        FeaturesDAO featuresdao = new FeaturesDAOImpl();
        featuresdao.update(features);
        
        req.setAttribute("features", features);

        resp.sendRedirect("ADMIN/AdminFeatures.jsp");
    }

    private void deleteFeatures(HttpServletRequest req, HttpServletResponse resp) 
            throws SQLException, IOException, ClassNotFoundException {
        
        int id = Integer.parseInt(req.getParameter("hidid"));
        
        Features features = new Features();
        
        features.setFeaturesID(id);
        
        FeaturesDAO featuresdao = new FeaturesDAOImpl();
        featuresdao.delete(features);

        resp.sendRedirect("ADMIN/AdminFeatures.jsp");
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
            Logger.getLogger(FeaturesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(FeaturesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FeaturesController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FeaturesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(FeaturesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FeaturesController.class.getName()).log(Level.SEVERE, null, ex);
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
