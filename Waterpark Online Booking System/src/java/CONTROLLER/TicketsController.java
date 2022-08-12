package CONTROLLER;

import DAO.TicketsDAO;
import DAOImpl.TicketsDAOImpl;
import MODEL.Tickets;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TicketsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, IllegalStateException, ClassNotFoundException {
        
        String cmd = request.getParameter("command");
        
        if (cmd != null){
            switch(cmd){
                case "save":
                    saveTickets(request, response);
                    break;
                
                case "update":
                    updateTickets(request, response);
                    break;
                
                case "delete":
                    deleteTickets(request, response);
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "ADMIN/AdminDashboard.jsp");
        }
    }
    
    private void saveTickets(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException, SQLException {

        //get all data from form
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        //keep data into javabeans
        Tickets newtickets = new Tickets();

        newtickets.setTicketName(name);
        newtickets.setPrice(price);

        //pass the bean to DAO
        TicketsDAO ticketsdao = new TicketsDAOImpl();
        ticketsdao.save(newtickets);

        req.setAttribute("ticketsDetails", newtickets);
        resp.sendRedirect("ADMIN/AdminPricing.jsp");
    }
    
    private void updateTickets(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, IllegalStateException, ServletException, SQLException, ClassNotFoundException {

        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int id = Integer.parseInt(req.getParameter("hidid"));

        Tickets tickets = new Tickets();
        
        tickets.setTicketName(name);
        tickets.setPrice(price);
        tickets.setTicketID(id);
        
        TicketsDAO ticketsdao = new TicketsDAOImpl();
        ticketsdao.update(tickets);
        
        req.setAttribute("tickets", tickets);

        resp.sendRedirect("ADMIN/AdminPricing.jsp");
    }

    private void deleteTickets(HttpServletRequest req, HttpServletResponse resp) 
            throws SQLException, IOException, ClassNotFoundException {
        
        int id = Integer.parseInt(req.getParameter("hidid"));
        
        Tickets tickets = new Tickets();
        
        tickets.setTicketID(id);
        
        TicketsDAO ticketsdao = new TicketsDAOImpl();
        ticketsdao.delete(tickets);

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
            Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
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
