package CONTROLLER;

import DAO.BookedFacilitiesDAO;
import DAO.BookedTicketsDAO;
import DAO.BookedTicketsStaffDAO;
import DAO.BookingDAO;
import DAO.BookingStaffDAO;
import DAO.CheckoutDAO;
import DAO.OrderDAO;
import DAO.PaymentDAO;
import DAO.TicketsDAO;
import DAOImpl.BookedFacilitiesDAOImpl;
import DAOImpl.BookedTicketsDAOImpl;
import DAOImpl.BookedTicketsStaffDAOImpl;
import DAOImpl.BookingDAOImpl;
import DAOImpl.BookingStaffDAOImpl;
import DAOImpl.CheckoutDAOImpl;
import DAOImpl.OrderDAOImpl;
import DAOImpl.PaymentDAOImpl;
import DAOImpl.TicketsDAOImpl;
import MODEL.BookedFacilities;
import MODEL.BookedTickets;
import MODEL.BookedTicketsStaff;
import MODEL.Booking;
import MODEL.BookingStaff;
import MODEL.Checkout;
import MODEL.Order;
import MODEL.Payment;
import MODEL.Tickets;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
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

public class BookingController extends HttpServlet {

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
                    
                case "saveCheckoutTransfer":
                    saveCheckoutTransfer(request, response);
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
            response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustMainPage.jsp");
        }
    }
    
    private void saveBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, ParseException {
        
        int totalticket = 0;
        int customerid = Integer.parseInt(request.getParameter("customerid"));
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
            Booking booking = new Booking();

            booking.setCustomerid(customerid);
            booking.setBookingName(name);
            booking.setBookingDate(Date.valueOf(myDate));
            booking.setContactNum(contactnum);

            BookingDAO bookingDAO = new BookingDAOImpl();
            bookingDAO.addBooking(booking);

            response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustBookTicket.jsp?customerid=" + customerid + "&date=" + Date.valueOf(myDate));
        } else {
            request.setAttribute("errorticExceed", "Total tickets ordered on the date entered has exceed the limit. Please try again on the other date.");
            RequestDispatcher rd = request.getRequestDispatcher("CUSTOMER/CustBook.jsp?customerid=" + customerid + "&date=" + Date.valueOf(myDate));
            rd.forward(request,response);
        }
    }
    
    private void saveBookedTic(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        int counter = Integer.parseInt(request.getParameter("counter"));
        int bookid = Integer.parseInt(request.getParameter("bookingid"));
        int totaltics = Integer.parseInt(request.getParameter("totaltics"));
        String datee = request.getParameter("date");
        int customerid = Integer.parseInt(request.getParameter("customerid"));
        
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
                    BookedTickets bookedtickets = new BookedTickets();

                    bookedtickets.setTicketid(bookedTicID[i]);
                    bookedtickets.setBookid(bookid);
                    bookedtickets.setQuantity(bookedTic[i]);
                    
                    BookedTicketsDAO bookedticketDAO = new BookedTicketsDAOImpl(); 
                    bookedticketDAO.addBookedTickets(bookedtickets);
                }

                response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustBookFacilities.jsp?id=" + bookid + "&date=" + date + "&customerid=" + customerid);

            }  else {

                request.setAttribute("errorticExceed", "Total ticket that you have ordered exceeds the available tickets. Please enter tickets' quantity below " + totaltics + " or same.");
                RequestDispatcher rd = request.getRequestDispatcher("CUSTOMER/CustBookTicket.jsp?id=" + bookid + "&date=" + date + "&customerid=" + customerid);
                rd.forward(request,response);

            }
        } else {
            
            request.setAttribute("errorticExceed", "Please enter at least 1 ticket.");
            RequestDispatcher rd = request.getRequestDispatcher("CUSTOMER/CustBookTicket.jsp?id=" + bookid + "&date=" + date + "&customerid=" + customerid);
            rd.forward(request,response);
            
        }
    }
    
    private void saveBookedFac(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        int count = Integer.parseInt(request.getParameter("count"));
        int bookid = Integer.parseInt(request.getParameter("bookingid"));
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
            request.setAttribute("errorfacExceed", "Total facilities that you have ordered exceeds the available facilities. Please enter facilities' quantity below or same as displayed.");
            RequestDispatcher rd = request.getRequestDispatcher("CUSTOMER/CustBookFacilities.jsp?id=" + bookid + "&date=" + date);
            rd.forward(request,response);
        } else {
            for (int i = 0; i < count; i++){
                BookedFacilities bookedfacilities = new BookedFacilities();

                bookedfacilities.setFacilitiesid(bookedFacID[i]);
                bookedfacilities.setBookid(bookid);
                bookedfacilities.setQuantity(bookedFac[i]);

                BookedFacilitiesDAO bookedfacilitiesdao = new BookedFacilitiesDAOImpl();
                bookedfacilitiesdao.addBookedFacilities(bookedfacilities);
            }
            
            response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustConfirmBook.jsp?id=" + bookid);
        } 
    }

    private void confirmBook(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException, ParseException {
        
        int bookid = Integer.parseInt(request.getParameter("bookid"));
        String paymenttype = request.getParameter("paymenttype");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        
        Checkout checkout = new Checkout();
        
        checkout.setBookid(bookid);
        checkout.setPaymenttype(paymenttype);
        checkout.setCheckoutamount(amount);
        
        CheckoutDAO checkoutdao = new CheckoutDAOImpl();
        checkoutdao.saveCheckout(checkout);
        
        response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustCheckout.jsp?id=" + bookid);
    }
    
    private void saveCheckout(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        int checkoutid = Integer.parseInt(request.getParameter("checkoutid"));
        int bookid = Integer.parseInt(request.getParameter("bookid"));
        String paymenttype = request.getParameter("paymenttype");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        
        if(paymenttype.equals("Transfer")){
            Checkout checkout = new Checkout();

            checkout.setCheckoutid(checkoutid);
            checkout.setBookid(bookid);
            checkout.setPaymenttype(paymenttype);
            checkout.setCheckoutamount(amount);

            CheckoutDAO checkoutdao = new CheckoutDAOImpl();

            checkoutdao.update(checkout);

            HttpSession session = request.getSession();
            session.setAttribute("checkout", checkout);
            
            response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustCheckoutTransfer.jsp?id=" + checkoutid + "&bookid=" + bookid);
        } else {
            Checkout checkout = new Checkout();

            checkout.setCheckoutid(checkoutid);
            checkout.setBookid(bookid);
            checkout.setPaymenttype(paymenttype);
            checkout.setCheckoutamount(amount);

            CheckoutDAO checkoutdao = new CheckoutDAOImpl();

            checkoutdao.update(checkout);

            HttpSession session = request.getSession();
            session.setAttribute("checkout", checkout);

            response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustPaymentPage.jsp?id=" + checkoutid);
        }
    }
    
    private void saveCheckoutTransfer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        int checkoutid = Integer.parseInt(request.getParameter("id"));
        Part receipt = request.getPart("receipt");
        int bookid = Integer.parseInt(request.getParameter("bookid"));
        String paymenttype = request.getParameter("paymenttype");
        String orderstatus = request.getParameter("orderstatus");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        
        Payment payment = new Payment();
        Checkout checkout = new Checkout();
        Order order = new Order();
        
        payment.setCheckoutid(checkoutid);
        payment.setPaymentfile(receipt);
        
        checkout.setCheckoutid(checkoutid);
        checkout.setBookid(bookid);
        checkout.setPaymenttype(paymenttype);
        checkout.setCheckoutamount(amount);
        
        order.setBookingID(bookid);
        order.setOrderStatus(orderstatus);
        
        PaymentDAO paymentdao = new PaymentDAOImpl();
        CheckoutDAO checkoutdao = new CheckoutDAOImpl();
        OrderDAO orderdao = new OrderDAOImpl();
        
        paymentdao.save(payment);
        checkoutdao.update(checkout);
        orderdao.addOrderStatus(order);
        
        HttpSession session = request.getSession();
        session.setAttribute("checkout", checkout);
        
        response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustPaymentResult.jsp?id=" + checkoutid);
    }
    
    private void savePayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        int checkoutid = Integer.parseInt(request.getParameter("checkoutid"));
        int bookid = Integer.parseInt(request.getParameter("bookid"));
        String paymenttype = request.getParameter("paymenttype");
        String orderstatus = request.getParameter("orderstatus");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        
        Checkout checkout = new Checkout();
        Order order = new Order();
        
        checkout.setCheckoutid(checkoutid);
        checkout.setBookid(bookid);
        checkout.setPaymenttype(paymenttype);
        checkout.setCheckoutamount(amount);
        
        order.setBookingID(bookid);
        order.setOrderStatus(orderstatus);
        
        CheckoutDAO checkoutdao = new CheckoutDAOImpl();
        OrderDAO orderdao = new OrderDAOImpl();
        
        checkoutdao.update(checkout);
        orderdao.addOrderStatus(order);
        
        HttpSession session = request.getSession();
        session.setAttribute("checkout", checkout);
        
        response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustPaymentResult.jsp?id=" + checkoutid);
    }
    
    private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        int bookid = Integer.parseInt(request.getParameter("bookid"));
        String orderstatus = request.getParameter("orderstatus");
        
        Order order = new Order();
        
        order.setOrderID(orderid);
        order.setBookingID(bookid);
        order.setOrderStatus(orderstatus);
        
        OrderDAO orderdao = new OrderDAOImpl();
        
        orderdao.update(order);
        
        response.sendRedirect(request.getContextPath() + "/STAFF/StaffBooked.jsp");
    }
    
    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, SQLException {
        
        int bookid = Integer.parseInt(request.getParameter("bookingid"));
        int custid = Integer.parseInt(request.getParameter("customerid"));
        
        Booking booking = new Booking();
        
        booking.setBookingID(bookid);
        
        BookingDAO bookingDAO = new BookingDAOImpl();
        
        bookingDAO.delete(booking);
        
        response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustDashboard.jsp?id=" + custid);
    }
    
    private void deleteBookedTic(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, SQLException {
        
        int bookid = Integer.parseInt(request.getParameter("bookingid"));
        int bookedticid = Integer.parseInt(request.getParameter("bookedticketid"));
        
        Booking booking = new Booking();
        BookedTickets bookedtic = new BookedTickets();
        
        booking.setBookingID(bookid);
        bookedtic.setBookedticid(bookid);
        
        BookingDAO bookingDAO = new BookingDAOImpl();
        BookedTicketsDAO bookedticketsdao = new BookedTicketsDAOImpl();
        
        bookedticketsdao.delete(bookedtic);
        bookingDAO.delete(booking);
        
        response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustMainPage.jsp");
    }
    
    private void deleteBookedFac(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, SQLException {
        
        int bookid = Integer.parseInt(request.getParameter("bookid"));
        
        Booking booking = new Booking();
        BookedTickets bookedtic = new BookedTickets();
        BookedFacilities bookedfac = new BookedFacilities();
        
        booking.setBookingID(bookid);
        bookedtic.setBookedticid(bookid);
        bookedfac.setBookedfacid(bookid);
        
        BookingDAO bookingDAO = new BookingDAOImpl();
        BookedTicketsDAO bookedticketsdao = new BookedTicketsDAOImpl();
        BookedFacilitiesDAO bookedfacilitiesdao = new BookedFacilitiesDAOImpl();
        
        bookedfacilitiesdao.delete(bookedfac);
        bookedticketsdao.delete(bookedtic);
        bookingDAO.delete(booking);
        
        response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustMainPage.jsp");
    }
    
    private void deleteCheckout(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, SQLException {
        
        int bookid = Integer.parseInt(request.getParameter("bookid"));
        
        Booking booking = new Booking();
        BookedTickets bookedtic = new BookedTickets();
        BookedFacilities bookedfac = new BookedFacilities();
        Checkout checkout = new Checkout();
        
        booking.setBookingID(bookid);
        bookedtic.setBookedticid(bookid);
        bookedfac.setBookedfacid(bookid);
        checkout.setCheckoutid(bookid);
        
        BookingDAO bookingDAO = new BookingDAOImpl();
        BookedTicketsDAO bookedticketsdao = new BookedTicketsDAOImpl();
        BookedFacilitiesDAO bookedfacilitiesdao = new BookedFacilitiesDAOImpl();
        CheckoutDAO checkoutdao = new CheckoutDAOImpl();
        
        checkoutdao.delete(checkout);
        bookedfacilitiesdao.delete(bookedfac);
        bookedticketsdao.delete(bookedtic);
        bookingDAO.delete(booking);
        
        response.sendRedirect(request.getContextPath() + "/CUSTOMER/CustMainPage.jsp");
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
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
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
