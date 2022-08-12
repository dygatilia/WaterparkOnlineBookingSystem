<%-- 
    Last Edit  : June 28, 2022, 11:05:00 PM
    Author     : Dayang Farhanim Atilia Binti Mohd Azaman
    Matric No. : S54168
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="MODEL.Promotions"%>
<%@page import="DAOImpl.PromotionsDAOImpl"%>
<%@page import="DAO.PromotionsDAO"%>
<%@page import="DAOImpl.FacilitiesDAOImpl"%>
<%@page import="MODEL.BookedFacilities"%>
<%@page import="MODEL.BookedFacilities"%>
<%@page import="DAOImpl.BookedFacilitiesDAOImpl"%>
<%@page import="DAO.BookedFacilitiesDAO"%>
<%@page import="MODEL.Facilities"%>
<%@page import="DAO.FacilitiesDAO"%>
<%@page import="DAOImpl.BookedTicketsDAOImpl"%>
<%@page import="MODEL.BookedTickets"%>
<%@page import="DAO.BookedTicketsDAO"%>
<%@page import="MODEL.Tickets"%>
<%@page import="DAOImpl.TicketsDAOImpl"%>
<%@page import="DAO.TicketsDAO"%>
<%@page import="MODEL.Checkout"%>
<%@page import="DAOImpl.CheckoutDAOImpl"%>
<%@page import="DAO.CheckoutDAO"%>
<%@page import="MODEL.Booking"%>
<%@page import="java.util.List"%>
<%@page import="DAOImpl.BookingDAOImpl"%>
<%@page import="DAO.BookingDAO"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Booking Invoice</title>
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
        <link rel="icon" type="image/png" sizes="32x32" href="https://i.pinimg.com/originals/2a/0d/d7/2a0dd79cf1372716a4e3254e4861d9f3.png">

    </head>
    <%
        String bookid = request.getParameter("id");

        if(bookid.equals("all")){
            String datee = request.getParameter("date");
        
            LocalDate myDate = LocalDate.parse(datee);
            
            BookingDAO bookingdao = new BookingDAOImpl();
            List<Booking> booklist = bookingdao.retrieveAllBooking();

            for (Booking bookinglist : booklist){
                if(bookinglist.getBookingDate().equals(Date.valueOf(myDate))){
                    int i = 1;
    %>
    <body>
        <div class="flex items-center justify-center min-h-screen bg-gray-100">
            <div class="w-3/5 bg-white shadow-lg">
                <div class="flex justify-between p-4">
                    <div class="p-4">
                        <h1 class="text-3xl italic font-extrabold tracking-widest text-blue-500">Jengka Wonderland</h1>
                    </div>
                    <div class="p-2">
                        <ul class="flex">
                            <li class="flex flex-col p-2 border-l-2 border-indigo-200">
                                <svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 text-blue-600" fill="none"
                                    viewBox="0 0 24 24" stroke="currentColor">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                                </svg>
                                <span class="text-sm">
                                    PT18047/1, Jalan Kilang, Persekitaran JPJ, 26400 Bandar Tun Razak, Pahang
                                </span>
                                <span class="text-sm">
                                    09-4663775
                                </span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="w-full h-0.5 bg-blue-500"></div>
                <%
                    CheckoutDAO checkoutdao = new CheckoutDAOImpl();
                    Checkout checkout = checkoutdao.retrieveCheckoutByBookingId(bookinglist.getBookingID());
                    Checkout checkoutt = checkoutdao.retrieveCheckoutByCheckoutId(checkout.getCheckoutid());
                %>
                <div class="flex justify-between p-4">
                    <div>
                        <h6 class="font-bold">Booking Name : <span class="text-sm font-medium"><%=bookinglist.getBookingName()%></span></h6>
                        <h6 class="font-bold">Contact Number : <span class="text-sm font-medium"><%=bookinglist.getContactNum()%></span></h6>
                        <h6 class="mt-10 font-bold">Payment Method: <span class="text-sm font-medium"><%=checkout.getPaymenttype()%></span></h6>
                    </div>
                    <div class="w-100">
                        <h6 class="font-bold">Booking Date : <span class="text-sm font-medium"><%=bookinglist.getBookingDate()%></span></h6>
                        <h6 class="font-bold">Booking ID : <span class="text-sm font-medium"><%=bookinglist.getBookingID()%></span></h6>
                    </div>
                    <div></div>
                </div>
                <div class="flex justify-center p-4">
                    <div class="border-b border-gray-200 shadow">
                        <h6 class="font-bold">Booking Details :</h6>
                        <table class="">
                            <thead class="bg-gray-50">
                                <tr>
                                    <th class="px-4 py-2 text-xs text-gray-500 ">
                                        #
                                    </th>
                                    <th class="px-4 py-2 text-xs text-gray-500 ">
                                        Tickets / Facilities Name
                                    </th>
                                    <th class="px-4 py-2 text-xs text-gray-500 ">
                                        Quantity
                                    </th>
                                    <th class="px-4 py-2 text-xs text-gray-500 ">
                                        Price
                                    </th>
                                    <th class="px-4 py-2 text-xs text-gray-500 ">
                                        Subtotal
                                    </th>
                                </tr>
                            </thead>
                            <tbody class="bg-white">
                                <%
                                    double total = 0.0;
                                    double subtotal = 0.0;
                                    TicketsDAO ticketsdao = new TicketsDAOImpl();
                                    List<Tickets> ticketslist = ticketsdao.retrieveAll();

                                    BookedTicketsDAO bookedticketsdao = new BookedTicketsDAOImpl();
                                    BookedTickets bookedticket = bookedticketsdao.retrieveBookedTicketsByBookingId(bookinglist.getBookingID());

                                    for (Tickets tickets : ticketslist) {
                                        BookedTickets bookedtic = bookedticketsdao.retrieveQuantityByTicketIDnBookingId(tickets.getTicketID(), bookinglist.getBookingID());
                                        if (bookedtic.getQuantity() > 0){
                                            subtotal = (bookedtic.getQuantity() * tickets.getPrice());
                                %>
                                <tr class="border-b-2 whitespace-nowrap">
                                    <td class="px-6 py-4 text-sm text-gray-500">
                                        <%=i%>
                                    </td>
                                    <td class="px-6 py-4">
                                        <div class="text-sm text-gray-900">
                                            <%=tickets.getTicketName()%>
                                        </div>
                                    </td>
                                    <td class="px-6 py-4">
                                        <div class="text-sm text-gray-500"><%=bookedtic.getQuantity()%></div>
                                    </td>
                                    <td class="px-6 py-4 text-sm text-gray-500">
                                        RM <%=tickets.getPrice()%>0
                                    </td>
                                    <td class="px-6 py-4">
                                        RM <%=subtotal%>0
                                    </td>
                                </tr>
                                <%      
                                        i++;
                                        total = total + subtotal;
                                        }
                                    }
                                %>
                                <%
                                    FacilitiesDAO facilitiesdao = new FacilitiesDAOImpl();
                                    List<Facilities> facilitieslist = facilitiesdao.retrieveAll();

                                    BookedFacilitiesDAO bookedfacilitiesdao = new BookedFacilitiesDAOImpl();
                                    BookedFacilities bookedfacilities = bookedfacilitiesdao.retrieveBookedFacilitiesByBookingId(bookinglist.getBookingID());

                                    for (Facilities facilities : facilitieslist) {
                                        BookedFacilities bookedfac = bookedfacilitiesdao.retrieveQuantityByFacilitiesIDnBookingID(facilities.getFacilitiesID(), bookinglist.getBookingID());
                                        if (bookedfac.getQuantity()> 0){
                                            double amt = 0.0;
                                            amt = (bookedfac.getQuantity() * facilities.getFacilitiesPrice());
                                %>
                                <tr class="border-b-2 whitespace-nowrap">
                                    <td class="px-6 py-4 text-sm text-gray-500">
                                        <%=i%>
                                    </td>
                                    <td class="px-6 py-4">
                                        <div class="text-sm text-gray-900">
                                            <%=facilities.getFacilitiesName()%>
                                        </div>
                                    </td>
                                    <td class="px-6 py-4">
                                        <div class="text-sm text-gray-500"><%=bookedfac.getQuantity()%></div>
                                    </td>
                                    <td class="px-6 py-4 text-sm text-gray-500">
                                        RM <%=facilities.getFacilitiesPrice()%>0
                                    </td>
                                    <td class="px-6 py-4">
                                        RM <%=amt%>0
                                    </td>
                                </tr>
                                <%      
                                        i++;
                                        total = total + amt;
                                        }
                                    } 

                                    
                                    //Double discount = 0.0;
                                    //DecimalFormat df = new DecimalFormat();
                                    //df.setMaximumFractionDigits(2);

                                    //discount = (total * ((Double.valueOf(promo.getPromotionsDiscount()) / 100)));
                                    //String disc = df.format(discount);
                                %>
                                <%--<tr class="">
                                    <td colspan="3"></td>
                                    <td class="text-sm font-bold">Sub Total</td>
                                    <td class="text-sm font-bold tracking-wider pl-5"><b>RM <%=total%>0</b></td>
                                </tr>
                                <tr>
                                    <th colspan="3"></th>
                                    <td class="text-sm font-bold"><b>Discount Off</b></td>
                                    <td class="text-sm font-bold pl-5"><b>-RM <%=disc %>0</b></td>
                                </tr>--%>
                                <tr class="text-white bg-gray-800">
                                    <th colspan="3"></th>
                                    <td class="text-sm font-bold"><b>Grand Total</b></td>
                                    <td class="text-sm font-bold pl-5"><b>RM <%=checkout.getCheckoutamount()%>0</b></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="w-full h-0.5 bg-blue-500"></div>

                <div class="p-4">
                    <div class="flex items-center justify-center">
                        Thank you very much for doing business with us.
                    </div>
                </div>
            </div>
        </div>
                                
        <% 
            }
                }
            } else {
                int c = 1;
                int bookingid = Integer.parseInt(request.getParameter("id"));
        %>                        
                                
        <div class="flex items-center justify-center min-h-screen bg-gray-100">
            <div class="w-3/5 bg-white shadow-lg">
                <div class="flex justify-between p-4">
                    <div class="p-4">
                        <h1 class="text-3xl italic font-extrabold tracking-widest text-blue-500">Jengka Wonderland</h1>
                    </div>
                    <div class="p-2">
                        <ul class="flex">
                            <li class="flex flex-col p-2 border-l-2 border-indigo-200">
                                <svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 text-blue-600" fill="none"
                                    viewBox="0 0 24 24" stroke="currentColor">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                                </svg>
                                <span class="text-sm">
                                    PT18047/1, Jalan Kilang, Persekitaran JPJ, 26400 Bandar Tun Razak, Pahang
                                </span>
                                <span class="text-sm">
                                    09-4663775
                                </span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="w-full h-0.5 bg-blue-500"></div>
                <%
                    BookingDAO bookingdao = new BookingDAOImpl();
                    Booking booking = bookingdao.retrieveBookingByBookingId(bookingid);
                    
                    CheckoutDAO checkoutdao = new CheckoutDAOImpl();
                    Checkout checkout = checkoutdao.retrieveCheckoutByBookingId(bookingid);
                    Checkout checkoutt = checkoutdao.retrieveCheckoutByCheckoutId(checkout.getCheckoutid());
                    
                    //PromotionsDAO promodao = new PromotionsDAOImpl();
                    //Promotions promo = promodao.retrievePromotionsByPromotionsID(checkout.getPromoid());
                %>
                <div class="flex justify-between p-4">
                    <div>
                        <h6 class="font-bold">Booking Name : <span class="text-sm font-medium"><%=booking.getBookingName()%></span></h6>
                        <h6 class="font-bold">Contact Number : <span class="text-sm font-medium"><%=booking.getContactNum()%></span></h6>
                        <h6 class="mt-10 font-bold">Payment Method: <span class="text-sm font-medium"><%=checkout.getPaymenttype()%></span></h6>
                    </div>
                    <div class="w-100">
                        <h6 class="font-bold">Booking Date : <span class="text-sm font-medium"><%=booking.getBookingDate()%></span></h6>
                        <h6 class="font-bold">Booking ID : <span class="text-sm font-medium"><%=booking.getBookingID()%></span></h6>
                    </div>
                    <div></div>
                </div>
                <div class="flex justify-center p-4">
                    <div class="border-b border-gray-200 shadow">
                        <h6 class="font-bold">Booking Details :</h6>
                        <table class="">
                            <thead class="bg-gray-50">
                                <tr>
                                    <th class="px-4 py-2 text-xs text-gray-500 ">
                                        #
                                    </th>
                                    <th class="px-4 py-2 text-xs text-gray-500 ">
                                        Tickets / Facilities Name
                                    </th>
                                    <th class="px-4 py-2 text-xs text-gray-500 ">
                                        Quantity
                                    </th>
                                    <th class="px-4 py-2 text-xs text-gray-500 ">
                                        Price
                                    </th>
                                    <th class="px-4 py-2 text-xs text-gray-500 ">
                                        Subtotal
                                    </th>
                                </tr>
                            </thead>
                            <tbody class="bg-white">
                                <%
                                    double total = 0.0;
                                    double subtotal = 0.0;
                                    TicketsDAO ticketsdao = new TicketsDAOImpl();
                                    List<Tickets> ticketslist = ticketsdao.retrieveAll();

                                    BookedTicketsDAO bookedticketsdao = new BookedTicketsDAOImpl();
                                    BookedTickets bookedticket = bookedticketsdao.retrieveBookedTicketsByBookingId(bookingid);

                                    for (Tickets tickets : ticketslist) {
                                        BookedTickets bookedtic = bookedticketsdao.retrieveQuantityByTicketIDnBookingId(tickets.getTicketID(), booking.getBookingID());
                                        if (bookedtic.getQuantity() > 0){
                                            subtotal = (bookedtic.getQuantity() * tickets.getPrice());
                                %>
                                <tr class="border-b-2 whitespace-nowrap">
                                    <td class="px-6 py-4 text-sm text-gray-500">
                                        <%=c%>
                                    </td>
                                    <td class="px-6 py-4">
                                        <div class="text-sm text-gray-900">
                                            <%=tickets.getTicketName()%>
                                        </div>
                                    </td>
                                    <td class="px-6 py-4">
                                        <div class="text-sm text-gray-500"><%=bookedtic.getQuantity()%></div>
                                    </td>
                                    <td class="px-6 py-4 text-sm text-gray-500">
                                        RM <%=tickets.getPrice()%>0
                                    </td>
                                    <td class="px-6 py-4">
                                        RM <%=subtotal%>0
                                    </td>
                                </tr>
                                <%      
                                        c++;
                                        total = total + subtotal;
                                        }
                                    }
                                %>
                                <%
                                    FacilitiesDAO facilitiesdao = new FacilitiesDAOImpl();
                                    List<Facilities> facilitieslist = facilitiesdao.retrieveAll();

                                    BookedFacilitiesDAO bookedfacilitiesdao = new BookedFacilitiesDAOImpl();
                                    BookedFacilities bookedfacilities = bookedfacilitiesdao.retrieveBookedFacilitiesByBookingId(bookingid);

                                    for (Facilities facilities : facilitieslist) {
                                        BookedFacilities bookedfac = bookedfacilitiesdao.retrieveQuantityByFacilitiesIDnBookingID(facilities.getFacilitiesID(), booking.getBookingID());
                                        if (bookedfac.getQuantity()> 0){
                                            double amt = 0.0;
                                            amt = (bookedfac.getQuantity() * facilities.getFacilitiesPrice());
                                %>
                                <tr class="border-b-2 whitespace-nowrap">
                                    <td class="px-6 py-4 text-sm text-gray-500">
                                        <%=c%>
                                    </td>
                                    <td class="px-6 py-4">
                                        <div class="text-sm text-gray-900">
                                            <%=facilities.getFacilitiesName()%>
                                        </div>
                                    </td>
                                    <td class="px-6 py-4">
                                        <div class="text-sm text-gray-500"><%=bookedfac.getQuantity()%></div>
                                    </td>
                                    <td class="px-6 py-4 text-sm text-gray-500">
                                        RM <%=facilities.getFacilitiesPrice()%>0
                                    </td>
                                    <td class="px-6 py-4">
                                        RM <%=amt%>0
                                    </td>
                                </tr>
                                <%      
                                        c++;
                                        total = total + amt;
                                        }
                                    } 

                                    
                                    //Double discount = 0.0;
                                    //DecimalFormat df = new DecimalFormat();
                                    //df.setMaximumFractionDigits(2);

                                    //discount = (total * ((Double.valueOf(promo.getPromotionsDiscount()) / 100)));
                                    //String disc = df.format(discount);
                                %>
                                <%--<tr class="">
                                    <td colspan="3"></td>
                                    <td class="text-sm font-bold">Sub Total</td>
                                    <td class="text-sm font-bold tracking-wider pl-5"><b>RM <%=total%>0</b></td>
                                </tr>
                                <tr>
                                    <th colspan="3"></th>
                                    <td class="text-sm font-bold"><b>Discount Off</b></td>
                                    <td class="text-sm font-bold pl-5"><b>-RM <%=disc %>0</b></td>
                                </tr>--%>
                                <tr class="text-white bg-gray-800">
                                    <th colspan="3"></th>
                                    <td class="text-sm font-bold"><b>Grand Total</b></td>
                                    <td class="text-sm font-bold pl-5"><b>RM <%=checkout.getCheckoutamount()%>0</b></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="w-full h-0.5 bg-blue-500"></div>

                <div class="p-4">
                    <div class="flex items-center justify-center">
                        Thank you very much for doing business with us.
                    </div>
                </div>
            </div>
        </div>
    </body>
    <%
        }
    %>
</html>