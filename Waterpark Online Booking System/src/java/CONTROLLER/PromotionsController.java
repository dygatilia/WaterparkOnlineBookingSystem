package CONTROLLER;

import DAO.PromotionsDAO;
import DAOImpl.PromotionsDAOImpl;
import MODEL.Promotions;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215)
public class PromotionsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, IllegalStateException, ClassNotFoundException {
        
        String cmd = request.getParameter("command");
        
        if (cmd != null){
            switch(cmd){
                case "save":
                    savePromotions(request, response);
                    break;
                
                case "update":
                    updatePromotions(request, response);
                    break;
                
                case "delete":
                    deletePromotions(request, response);
                    break;
                    
                case "verify":
                    verifyPromotions(request, response);
                    break;    
            }
        } else {
            response.sendRedirect(request.getContextPath() + "ADMIN/AdminDashboard.jsp");
        }
    }
    
    private void savePromotions(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, IllegalStateException, ServletException, SQLException, ClassNotFoundException {

        //get all data from form
        Part image = req.getPart("image");
        String code = req.getParameter("code");
        int discount = Integer.parseInt(req.getParameter("discount"));

        //keep data into javabeans
        Promotions newpromo = new Promotions();

        newpromo.setPromotionsImage(image);
        newpromo.setPromotionsCode(code);
        newpromo.setPromotionsDiscount(discount);

        //pass the bean to DAO
        PromotionsDAO promodao = new PromotionsDAOImpl();
        promodao.save(newpromo);

        req.setAttribute("promoDetails", newpromo);
        resp.sendRedirect("ADMIN/AdminPricing.jsp");
    }
    
    private void updatePromotions(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, IllegalStateException, ServletException, SQLException, ClassNotFoundException {

        Part image = req.getPart("image");
        String code = req.getParameter("code");
        int discount = Integer.parseInt(req.getParameter("discount"));
        int id = Integer.parseInt(req.getParameter("hidid"));

        Promotions promo = new Promotions();

        promo.setPromotionsImage(image);
        promo.setPromotionsCode(code);
        promo.setPromotionsDiscount(discount);
        promo.setPromotionsID(id);
        
        PromotionsDAO promodao = new PromotionsDAOImpl();
        promodao.update(promo);
        
        req.setAttribute("promo", promo);
        resp.sendRedirect("ADMIN/AdminPricing.jsp");
    }

    private void deletePromotions(HttpServletRequest req, HttpServletResponse resp) 
            throws SQLException, IOException, ClassNotFoundException {
        
        int id = Integer.parseInt(req.getParameter("hidid"));
        
        Promotions promo = new Promotions();
        
        promo.setPromotionsID(id);
        
        PromotionsDAO promodao = new PromotionsDAOImpl();
        promodao.delete(promo);

        resp.sendRedirect("ADMIN/AdminPricing.jsp");
    }
    
    private void verifyPromotions(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException{
        
        if(req.getParameter("command") != null){
            
            String promocode = req.getParameter("promocode");
            
            PromotionsDAO promotionsdao = new PromotionsDAOImpl();
            
            try {
                Promotions promotions = promotionsdao.verifyPromoCode(promocode);
                
                if(promotions != null){
                    HttpSession session = req.getSession();
                    session.setAttribute("successPromo", "Valid Promotions Code!");                    
                    resp.sendRedirect("CUSTOMER/CustBookTicket.jsp");
                    
                } else {
                    req.setAttribute("errorPromo", "Invalid Promotions Code!");
                    RequestDispatcher rd = req.getRequestDispatcher("CUSTOMER/CustBookTicket.jsp");
                    rd.forward(req,resp);
                }
                
            } catch (SQLException e){
                throw new ServletException(e);
            }
        }    
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
            Logger.getLogger(PromotionsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(PromotionsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PromotionsController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PromotionsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(PromotionsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PromotionsController.class.getName()).log(Level.SEVERE, null, ex);
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
