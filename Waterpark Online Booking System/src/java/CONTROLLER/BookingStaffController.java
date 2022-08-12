package CONTROLLER;

import DAO.BookedFacilitiesStaffDAO;
import DAO.BookedTicketsDAO;
import DAO.BookedTicketsStaffDAO;
import DAO.BookingDAO;
import DAO.BookingStaffDAO;
import DAO.CheckoutStaffDAO;
import DAO.OrderStaffDAO;
import DAO.TicketsDAO;
import DAOImpl.BookedFacilitiesStaffDAOImpl;
import DAOImpl.BookedTicketsDAOImpl;
import DAOImpl.BookedTicketsStaffDAOImpl;
import DAOImpl.BookingDAOImpl;
import DAOImpl.BookingStaffDAOImpl;
import DAOImpl.CheckoutStaffDAOImpl;
import DAOImpl.OrderStaffDAOImpl;
import DAOImpl.TicketsDAOImpl;
import MODEL.BookedFacilitiesStaff;
import MODEL.BookedTickets;
import MODEL.BookedTicketsStaff;
import MODEL.Booking;
import MODEL.BookingStaff;
import MODEL.CheckoutStaff;
import MODEL.OrderStaff;
import MODEL.Tickets;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookingStaffController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {

        String cmd = request.getParameter("command");

        if (cmd != null) {
            switch (cmd) {
                case "saveBooking":
                    saveBooking(request, response);
                    break;
                
                case "saveBookedTic":
                    saveBookedTic(request, response);
                    break;
                    
                case "saveBookedFac":
                    saveBookedFac(request, response);
                    break;
                
                case "confirmBook":
                    confirmBook(request, response);
                    break;
                
                case "saveCheckout":
                    saveCheckout(request, response);
                    break;
                    
                case "savePayment":
                    savePayment(request, response);
                    break;
                    
                case "updateStatus":
                    updateStatus(request, response);
                    break;
                    
                case "cancelBooking":
                    deleteBooking(request, response);
                    break;
                    
                case "cancelBookedTic":
                    deleteBookedTic(request, response);
                    break;
                    
                case "cancelBookedFac":
                    deleteBookedFac(request, response);
                    break;
                    
                case "cancelCheckout":
                    deleteCheckout(request, response);
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/STAFF/StaffDashboard.jsp");
        }
    }
    
    private void saveBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, ParseException {
        
        int totalticket = 0;
        int staffid = Integer.parseInt(request.getParameter("staffid"));
        int total = Integer.parseInt(request.getParameter("total"));
        String datee = request.getParameter("date");
        String name = request.getParameter("name");
        String contactnum = request.getParameter("contactnum");
        
        LocalDate myDate = LocalDate.parse(datee);
        
        BookingDAO bookingdao = new BookingDAOImpl();
        List<Booking> bookinglist = bookingdao.retrieveAllBooking();

        BookingStaffDAO bookingstaffdao = new BookingStaffDAOImpl();
        List<BookingStaff> bookingstafflist = bookingstaffdao.retrieveAllBookingStaff();

        TicketsDAO ticketsdao = new TicketsDAOImpl();
        List<Tickets> ticketslist = ticketsdao.retrieveAll();
        
        for (Booking booking : bookinglist){

            if(booking.getBookingDate().equals(Date.valueOf(myDate))){
                BookedTicketsDAO bookedticketsdao = new BookedTicketsDAOImpl();

                for (Tickets tickets : ticketslist) {
                    BookedTickets bookedtic = bookedticketsdao.retrieveQuantityByTicketIDnBookingId(tickets.getTicketID(), booking.getBookingID());

                    totalticket = totalticket + bookedtic.getQuantity();
                }
            }
        }

        for (BookingStaff bookingstaff : bookingstafflist){

            if(bookingstaff.getBookingDate().equals(Date.valueOf(myDate))){
                BookedTicketsStaffDAO bookedticketstaffdao = new BookedTicketsStaffDAOImpl();

                for (Tickets tickets : ticketslist){
                    BookedTicketsStaff bookedticstaff = bookedticketstaffdao.retrieveQuantityByTicketIDnBookingStaffId(tickets.getTicketID(), bookingstaff.getBookingStaffID());

                    totalticket = totalticket + bookedticstaff.getQuantity();
                }
            }
        }
        
        if (totalticket < total){
            BookingStaff bookingstaff = new BookingStaff();
        
            bookingstaff.setStaffid(staffid);
            bookingstaff.setBookingName(name);
            bookingstaff.setBookingDate(Date.valueOf(myDate));
            bookingstaff.setContactNum(contactnum);

            BookingStaffDAO bookingstaffDAO = new BookingStaffDAOImpl();
            bookingstaffDAO.addBookingStaff(bookingstaff);

            response.sendRedirect(request.getContextPath() + "/STAFF/StaffBookTicket.jsp?staffid=" + staffid + "&date=" + Date.valueOf(myDate));
        } else {
            //request.setAttribute("errorticExceed", "Total tickets ordered on the date entered has exceed the limit. Please try again on the other date.");
            //RequestDispatcher rd = request.getRequestDispatcher("STAFF/StaffBook.jsp?staffid=" + staffid);
            //rd.forward(request,response);
            
            HttpSession session = request.getSession();
            session.setAttribute("errorticExceed", "Total tickets ordered on the date entered has exceed the limit. Please try again on the other date.");
            response.sendRedirect("STAFF/StaffBook.jsp?staffid=" + staffid);
        }
        
    }
    
    private void saveBookedTic(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        int counter = Integer.parseInt(request.getParameter("counter"));
        int bookstaffid = Integer.parseInt(request.getParameter("bookingstaffid"));
        int totaltics = Integer.parseInt(request.getParameter("totaltics"));
        String datee = request.getParameter("date");
        int staffid = Integer.parseInt(request.getParameter("staffid"));
        
        LocalDate myDate = LocalDate.parse(datee);
        Date date = Date.valueOf(myDate);
        
        int bookedTicID[] = new int[counter];
        int bookedTic[] = new int[counter];
          
        for (int i = 0; i < counter; i++){
            bookedTicID[i] = Integer.parseInt(request.getParameter("hidticketid" + (i+1)));
            bookedTic[i] = Integer.parseInt(request.getParameter("qty" + (i+1)));
        }
        
        int totaltic = 0;
        
        for (int i = 0; i < counter; i++){
            totaltic = totaltic + bookedTic[i];
        }
        
        if (totaltic != 0){
            if (totaltic < totaltics){

                for (int i = 0; i < counter; i++){
                    BookedTicketsStaff bookedticketsstaff = new BookedTicketsStaff();

                    bookedticketsstaff.setTicketid(bookedTicID[i]);
                    bookedticketsstaff.setBookid(bookstaffid);
                    bookedticketsstaff.setQuantity(bookedTic[i]);

                    BookedTicketsStaffDAO bookedticketstaffDAO = new BookedTicketsStaffDAOImpl();
                    bookedticketstaffDAO.addBookedTicketsStaff(bookedticketsstaff);
                }

                response.sendRedirect(request.getContextPath() + "/STAFF/StaffBookFacilities.jsp?id=" + bookstaffid + "&date=" + date);
            } else {
                //request.setAttribute("errorticExceed", "Total ticket that you have ordered exceeds the available tickets. Please enter tickets' quantity below " + totaltics + " or same.");
                //RequestDispatcher rd = request.getRequestDispatcher("STAFF/StaffBookTicket.jsp?id=" + bookstaffid + "&date=" + date + "&staffid=" + staffid);
                //rd.include(request,response);

                HttpSession session = request.getSession();
                session.setAttribute("errorticExceed", "Total ticket that you have ordered exceeds the available tickets. Please enter tickets' quantity below " + totaltics + " or same.");
                response.sendRedirect("STAFF/StaffBookTicket.jsp?id=" + bookstaffid + "&date=" + date + "&staffid=" + staffid);
            }
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("errorticExceed", "Please enter at least 1 ticket.");
            response.sendRedirect("STAFF/StaffBookTicket.jsp?id=" + bookstaffid + "&date=" + date + "&staffid=" + staffid);
        }
        
    }
    
    private void saveBookedFac(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        int count = Integer.parseInt(request.getParameter("count"));
        int bookstaffid = Integer.parseInt(request.getParameter("bookingstaffid"));
        String datee = request.getParameter("date");
        
        LocalDate myDate = LocalDate.parse(datee);
        Date date = Date.valueOf(myDate);
        
        int bookedFacID[] = new int[count];
        int bookedFac[] = new int[count];
        int totalAvailable[] = new int[count];
        int cnt = 0;
        
        for (int i = 0; i < count; i++){
            bookedFacID[i] = Integer.parseInt(request.getParameter("hidfacilitiesid" + (i+1)));
            bookedFac[i] = Integer.parseInt(request.getParameter("qty" + (i+1)));
            totalAvailable[i] = Integer.parseInt(request.getParameter("totalAvailable" + (i+1)));
            
            if (bookedFac[i] <= totalAvailable[i]){
                cnt = cnt + 1;
            }
        }
        
        if(cnt != count){
            HttpSession session = request.getSession();
            session.setAttribute("errorfacExceed", "Total facilities that you have ordered exceeds the available facilities. Please enter facilities' quantity below or same as displayed.");
            response.sendRedirect("STAFF/StaffBookFacilities.jsp?id=" + bookstaffid + "&date=" + date);
        } else {        
            for (int i = 0; i < count; i++){
                BookedFacilitiesStaff bookedfacilitiesstaff = new BookedFacilitiesStaff();

                bookedfacilitiesstaff.setFacilitiesid(bookedFacID[i]);
                bookedfacilitiesstaff.setBookid(bookstaffid);
                bookedfacilitiesstaff.setQuantity(bookedFac[i]);
                
                BookedFacilitiesStaffDAO bookedfacilitiesstaffdao = new BookedFacilitiesStaffDAOImpl();
                bookedfacilitiesstaffdao.addBookedFacilitiesStaff(bookedfacilitiesstaff);
            }

            response.sendRedirect(request.getContextPath() + "/STAFF/StaffConfirmBook.jsp?id=" + bookstaffid);
        }    
    }

    private void confirmBook(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException, ParseException {
        
        int bookstaffid = Integer.parseInt(request.getParameter("bookstaffid"));
        String paymenttype = request.getParameter("paymenttype");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        
        CheckoutStaff checkoutstaff = new CheckoutStaff();
        
        checkoutstaff.setBookid(bookstaffid);
        checkoutstaff.setPaymenttype(paymenttype);
        checkoutstaff.setCheckoutamount(amount);
        
        CheckoutStaffDAO checkoutstaffdao = new CheckoutStaffDAOImpl();
        checkoutstaffdao.saveCheckoutStaff(checkoutstaff);
        
        response.sendRedirect(request.getContextPath() + "/STAFF/StaffCheckout.jsp?id=" + bookstaffid);
    }
    
    private void saveCheckout(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        int checkoutid = Integer.parseInt(request.getParameter("checkoutid"));
        int bookstaffid = Integer.parseInt(request.getParameter("bookstaffid"));
        String paymenttype = request.getParameter("paymenttype");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        
        CheckoutStaff checkoutstaff = new CheckoutStaff();
        
        checkoutstaff.setCheckoutid(checkoutid);
        checkoutstaff.setBookid(bookstaffid);
        checkoutstaff.setPaymenttype(paymenttype);
        checkoutstaff.setCheckoutamount(amount);
        
        CheckoutStaffDAO checkoutdao = new CheckoutStaffDAOImpl();
        
        checkoutdao.update(checkoutstaff);
        
        HttpSession session = request.getSession();
        session.setAttribute("checkoutstaff", checkoutstaff);
        
        response.sendRedirect(request.getContextPath() + "/STAFF/StaffPaymentPage.jsp?id=" + checkoutid);
    }
    
    private void savePayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        int checkoutid = Integer.parseInt(request.getParameter("checkoutid"));
        int bookstaffid = Integer.parseInt(request.getParameter("bookstaffid"));
        String paymenttype = request.getParameter("paymenttype");
        String orderstaffstatus = request.getParameter("orderstaffstatus");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        
        CheckoutStaff checkoutstaff = new CheckoutStaff();
        OrderStaff orderstaff = new OrderStaff();
        
        checkoutstaff.setCheckoutid(checkoutid);
        checkoutstaff.setBookid(bookstaffid);
        checkoutstaff.setPaymenttype(paymenttype);
        checkoutstaff.setCheckoutamount(amount);
        
        orderstaff.setBookingStaffID(bookstaffid);
        orderstaff.setOrderStaffStatus(orderstaffstatus);
        
        CheckoutStaffDAO checkoutdao = new CheckoutStaffDAOImpl();
        OrderStaffDAO orderstaffdao = new OrderStaffDAOImpl();
        
        checkoutdao.update(checkoutstaff);
        orderstaffdao.addOrderStatus(orderstaff);
        
        HttpSession session = request.getSession();
        session.setAttribute("checkoutstaff", checkoutstaff);
        
        response.sendRedirect(request.getContextPath() + "/STAFF/StaffPaymentResult.jsp?id=" + checkoutid);
    }
    
    private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        int orderstaffid = Integer.parseInt(request.getParameter("orderstaffid"));
        int bookstaffid = Integer.parseInt(request.getParameter("bookstaffid"));
        String orderstaffstatus = request.getParameter("orderstaffstatus");
        
        OrderStaff orderstaff = new OrderStaff();
        
        orderstaff.setOrderStaffID(orderstaffid);
        orderstaff.setBookingStaffID(bookstaffid);
        orderstaff.setOrderStaffStatus(orderstaffstatus);
        
        OrderStaffDAO orderstaffdao = new OrderStaffDAOImpl();
        
        orderstaffdao.update(orderstaff);
        
        response.sendRedirect(request.getContextPath() + "/STAFF/StaffBooked.jsp");
    }
    
    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, SQLException {
        
        int bookstaffid = Integer.parseInt(request.getParameter("bookingstaffid"));
        
        BookingStaff bookingstaff = new BookingStaff();
        
        bookingstaff.setBookingStaffID(bookstaffid);
        
        BookingStaffDAO bookingstaffDAO = new BookingStaffDAOImpl();
        
        bookingstaffDAO.delete(bookingstaff);
        
        response.sendRedirect(request.getContextPath() + "/STAFF/StaffDashboard.jsp");
    }
    
    private void deleteBookedTic(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, SQLException {
        
        int bookstaffid = Integer.parseInt(request.getParameter("bookingstaffid"));
        int bookedticid = Integer.parseInt(request.getParameter("bookedticketid"));
        
        BookingStaff bookingstaff = new BookingStaff();
        BookedTicketsStaff bookedtic = new BookedTicketsStaff();
        
        bookingstaff.setBookingStaffID(bookstaffid);
        bookedtic.setBookedticid(bookstaffid);
        
        BookingStaffDAO bookingstaffDAO = new BookingStaffDAOImpl();
        BookedTicketsStaffDAO bookedticketsdao = new BookedTicketsStaffDAOImpl();
        
        bookedticketsdao.delete(bookedtic);
        bookingstaffDAO.delete(bookingstaff);
        
        response.sendRedirect(request.getContextPath() + "/STAFF/StaffDashboard.jsp");
    }
    
    private void deleteBookedFac(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, SQLException {
        
        int bookstaffid = Integer.parseInt(request.getParameter("bookingstaffid"));
        
        BookingStaff bookingstaff = new BookingStaff();
        BookedTicketsStaff bookedtic = new BookedTicketsStaff();
        BookedFacilitiesStaff bookedfac = new BookedFacilitiesStaff();
        
        bookingstaff.setBookingStaffID(bookstaffid);
        bookedtic.setBookedticid(bookstaffid);
        bookedfac.setBookedfacid(bookstaffid);
        
        BookingStaffDAO bookingDAO = new BookingStaffDAOImpl();
        BookedTicketsStaffDAO bookedticketsdao = new BookedTicketsStaffDAOImpl();
        BookedFacilitiesStaffDAO bookedfacilitiesdao = new BookedFacilitiesStaffDAOImpl();
        
        bookedfacilitiesdao.delete(bookedfac);
        bookedticketsdao.delete(bookedtic);
        bookingDAO.delete(bookingstaff);
        
        response.sendRedirect(request.getContextPath() + "/STAFF/StaffDashboard.jsp");
    }
    
    private void deleteCheckout(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, SQLException {
        
        int bookstaffid = Integer.parseInt(request.getParameter("bookingstaffid"));
        
        BookingStaff bookingstaff = new BookingStaff();
        BookedTicketsStaff bookedtic = new BookedTicketsStaff();
        BookedFacilitiesStaff bookedfac = new BookedFacilitiesStaff();
        CheckoutStaff checkoutstaff = new CheckoutStaff();
        
        bookingstaff.setBookingStaffID(bookstaffid);
        bookedtic.setBookedticid(bookstaffid);
        bookedfac.setBookedfacid(bookstaffid);
        checkoutstaff.setCheckoutid(bookstaffid);
        
        BookingStaffDAO bookingDAO = new BookingStaffDAOImpl();
        BookedTicketsStaffDAO bookedticketsdao = new BookedTicketsStaffDAOImpl();
        BookedFacilitiesStaffDAO bookedfacilitiesdao = new BookedFacilitiesStaffDAOImpl();
        CheckoutStaffDAO checkoutstaffdao = new CheckoutStaffDAOImpl();
        
        bookedfacilitiesdao.delete(bookedfac);
        bookedticketsdao.delete(bookedtic);
        bookingDAO.delete(bookingstaff);
        
        response.sendRedirect(request.getContextPath() + "/STAFF/StaffDashboard.jsp");
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
            Logger.getLogger(BookingStaffController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BookingStaffController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BookingStaffController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BookingStaffController.class.getName()).log(Level.SEVERE, null, ex);
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
