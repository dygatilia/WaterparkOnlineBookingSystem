package CONTROLLER;

import DAO.DailyTicketDAO;
import DAO.TotalTicketDAO;
import DAOImpl.DailyTicketDAOImpl;
import DAOImpl.TotalTicketDAOImpl;
import MODEL.DailyTicket;
import MODEL.TotalTicket;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DashboardController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {

        String cmd = request.getParameter("command");

        if (cmd != null) {
            switch (cmd) {
                case "save":
                    saveTotalTicket(request, response);
                    break;
                    
                case "update":
                    updateTotalTicket(request, response);
                    break;
                    
                case "updateDailyTicket":
                    updateDailyTicket(request, response);
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/ADMIN/AdminDashboard.jsp");
        }
    }
    
    private void saveTotalTicket(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, ParseException {
        
        int bookingid = Integer.parseInt(request.getParameter("bookingid"));
        int bookingstaffid = Integer.parseInt(request.getParameter("bookingstaffid"));
        String ticketdate = request.getParameter("date");
        int totalticketsold = Integer.parseInt(request.getParameter("totalticketsold"));
        
        LocalDate date = LocalDate.parse(ticketdate);
        
        TotalTicket totalticket = new TotalTicket();
        
        totalticket.setTicketDate(Date.valueOf(date));
        totalticket.setTicketSold(totalticketsold);
        
        TotalTicketDAO totalticketdao = new TotalTicketDAOImpl();
        totalticketdao.addTotalTicket(totalticket);
        
        response.sendRedirect(request.getContextPath() + "/ADMIN/AdminDashboard.jsp");
    }
    
    private void updateTotalTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        int totalticketid = Integer.parseInt(request.getParameter("totalticketid"));
        String ticketdate = request.getParameter("date");
        int totalticketsold = Integer.parseInt(request.getParameter("totalticketsold"));
        
        LocalDate date = LocalDate.parse(ticketdate);
        
        TotalTicket totalticket = new TotalTicket();
        
        totalticket.setTotalticketID(totalticketid);
        totalticket.setTicketDate(Date.valueOf(date));
        totalticket.setTicketSold(totalticketsold);
        
        TotalTicketDAO totalticketdao = new TotalTicketDAOImpl();
        totalticketdao.update(totalticket);
        
        response.sendRedirect(request.getContextPath() + "/ADMIN/AdminDashboard.jsp");
    }
    
    private void updateDailyTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        int dailyticketid = Integer.parseInt(request.getParameter("dailyticketid"));
        int dailyticketmax = Integer.parseInt(request.getParameter("dailyticketmax"));
        
        DailyTicket dailyticket = new DailyTicket();
        
        dailyticket.setDailyticketid(dailyticketid);
        dailyticket.setDailyticketmax(dailyticketmax);
        
        DailyTicketDAO dailyticketdao = new DailyTicketDAOImpl();
        dailyticketdao.update(dailyticket);
        
        response.sendRedirect(request.getContextPath() + "/ADMIN/AdminDashboard.jsp");
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
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
