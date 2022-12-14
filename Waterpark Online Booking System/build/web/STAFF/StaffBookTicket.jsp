<%-- 
    Last Edit  : June 28, 2022, 11:05:00 PM
    Author     : Dayang Farhanim Atilia Binti Mohd Azaman
    Matric No. : S54168
--%>

<%@page import="MODEL.DailyTicket"%>
<%@page import="DAOImpl.DailyTicketDAOImpl"%>
<%@page import="DAO.DailyTicketDAO"%>
<%@page import="MODEL.BookedTicketsStaff"%>
<%@page import="DAOImpl.BookedTicketsStaffDAOImpl"%>
<%@page import="DAO.BookedTicketsStaffDAO"%>
<%@page import="MODEL.BookedTickets"%>
<%@page import="DAOImpl.BookedTicketsDAOImpl"%>
<%@page import="DAO.BookedTicketsDAO"%>
<%@page import="MODEL.Booking"%>
<%@page import="DAOImpl.BookingDAOImpl"%>
<%@page import="DAO.BookingDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.LocalDate"%>
<%@page import="MODEL.Tickets"%>
<%@page import="java.util.List"%>
<%@page import="DAOImpl.TicketsDAOImpl"%>
<%@page import="DAO.TicketsDAO"%>
<%@page import="MODEL.BookingStaff"%>
<%@page import="DAOImpl.BookingStaffDAOImpl"%>
<%@page import="DAO.BookingStaffDAO"%>
<%@page import="MODEL.Staff"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en" class="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Book Ticket || Staff</title>

        <!-- Tailwind is included -->
        <link rel="stylesheet" href="CSS/main.css?v=1628755089081">
        <link rel="icon" type="image/png" sizes="32x32" href="https://i.pinimg.com/originals/2a/0d/d7/2a0dd79cf1372716a4e3254e4861d9f3.png">

        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-130795909-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag(){dataLayer.push(arguments);}
            gtag('js', new Date());
            gtag('config', 'UA-130795909-1');
        </script>
    </head>
    <body>
        <div id="app">
            <nav id="navbar-main" class="navbar is-fixed-top">
                <div class="navbar-brand is-right">
                    <a class="navbar-item --jb-navbar-menu-toggle" data-target="navbar-menu">
                        <span class="icon"><i class="mdi mdi-dots-vertical mdi-24px"></i></span>
                    </a>
                </div>
                <%
                    Staff staff = (Staff) request.getSession().getAttribute("staff");
                %>
                <div class="navbar-menu" id="navbar-menu">
                    <div class="navbar-end">
                        <div class="navbar-item dropdown has-divider has-user-avatar">
                            <a class="navbar-link">
                                <div class="user-avatar">
                                    <img src="https://www.eastriverdental.com/wp-content/uploads/2021/03/Avatar-PNG-High-Quality-Image.png" alt="Profile" class="rounded-full">
                                </div>
                                <div class="is-user-name"><span>Counter Staff</span></div>
                                <span class="icon"><i class="mdi mdi-chevron-down"></i></span>
                            </a>
                            <div class="navbar-dropdown">
                                <a href="" class="navbar-item">
                                    <span class="icon"><i class="mdi mdi-settings"></i></span>
                                    <span>Settings</span>
                                </a>
                                <hr class="navbar-divider">
                                <a href="" class="navbar-item">
                                    <span class="icon"><i class="mdi mdi-logout"></i></span>
                                    <span>Log Out</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>

            <aside class="aside is-placed-left is-expanded">
                <div class="aside-tools">
                  <div>
                    COUNTER STAFF
                  </div>
                </div>
                <div class="menu is-menu-main">
                    <ul class="menu-list">
                        <li class="--set-active-profile-html">
                            <a href="">
                                <span class="icon"><i class="mdi mdi-desktop-mac"></i></span>
                                <span class="menu-item-label">DASHBOARD</span>
                            </a>
                        </li>
                    </ul>
                    <ul class="menu-list">
                        <li class="--set-active-profile-html">
                            <a href="">
                                <span class="icon"><i class="mdi mdi-table"></i></span>
                                <span class="menu-item-label">BOOKED</span>
                            </a>
                        </li>
                        <li class="--set-active-forms-html">
                            <a href="">
                                <span class="icon"><i class="mdi mdi-history"></i></span>
                                <span class="menu-item-label">HISTORY</span>
                            </a>
                        </li>
                        <li class="--set-active-index-html">
                            <a href="">
                                <span class="icon"><i class="mdi mdi-settings"></i></span>
                                <span class="menu-item-label">SETTINGS</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </aside>

            <section class="is-title-bar">
                <div class="flex flex-col md:flex-row items-center justify-between space-y-6 md:space-y-0">
                    <ul>
                        <li>COUNTER STAFF</li>
                        <li>BOOK TICKET</li>
                    </ul>
                </div>
            </section>

            <section class="section main-section">
                <div class="card">
                    <header class="card-header">
                        <p class="card-header-title">
                            <span class="icon"><i class="mdi mdi-ballot"></i></span>
                            Book Ticket Form (Choose Ticket Type!)
                        </p>
                    </header>
                    <div class="card-content">
                        <%              
                            int totalticket = 0;
                            String date = request.getParameter("date");
                            LocalDate myDate = LocalDate.parse(date);
                            Date datee = Date.valueOf(myDate);

                            BookingDAO bookingdao = new BookingDAOImpl();
                            List<Booking> bookinglist = bookingdao.retrieveAllBooking();

                            BookingStaffDAO bookingstaffdao = new BookingStaffDAOImpl();
                            List<BookingStaff> bookingstafflist = bookingstaffdao.retrieveAllBookingStaff();

                            TicketsDAO ticketsdao = new TicketsDAOImpl();
                            List<Tickets> ticketslist = ticketsdao.retrieveAll();

                            for (Booking booking : bookinglist){

                                if(booking.getBookingDate().equals(datee)){
                                    BookedTicketsDAO bookedticketsdao = new BookedTicketsDAOImpl();

                                    for (Tickets tickets : ticketslist) {
                                        BookedTickets bookedtic = bookedticketsdao.retrieveQuantityByTicketIDnBookingId(tickets.getTicketID(), booking.getBookingID());

                                        totalticket = totalticket + bookedtic.getQuantity();
                                    }
                                }
                            }

                            for (BookingStaff bookingstaff : bookingstafflist){

                                if(bookingstaff.getBookingDate().equals(datee)){
                                    BookedTicketsStaffDAO bookedticketstaffdao = new BookedTicketsStaffDAOImpl();

                                    for (Tickets tickets : ticketslist){
                                        BookedTicketsStaff bookedticstaff = bookedticketstaffdao.retrieveQuantityByTicketIDnBookingStaffId(tickets.getTicketID(), bookingstaff.getBookingStaffID());

                                        totalticket = totalticket + bookedticstaff.getQuantity();
                                    }
                                }
                            }
                        %>
                        <form action="<%=request.getContextPath()%>/BookingStaffController" method="post">
                            <%
                                String staffid = request.getParameter("staffid");
                                int staff_id = Integer.parseInt(staffid);
                                
                                BookingStaffDAO bookingstaffdaoo = new BookingStaffDAOImpl();
                                BookingStaff bookingstafff = bookingstaffdaoo.retrieveBookingStaffByStaffId(staff_id);
                                
                                DailyTicketDAO dailyticketdao = new DailyTicketDAOImpl();
                                DailyTicket dailyticket = dailyticketdao.retrieveDailyTicket();
                            %>
                            <div class="field">
                                <div class="relative flex flex-row justify-between">
                                    <label class="label">Ticket Type</label>
                                    <label class="label">Tickets Available: <%=dailyticket.getDailyticketmax() - totalticket%></label>
                                </div>
                                <div class="control">
                                    <table>
                                        <%
                                            int counter = 1;

                                            for (Tickets tickets : ticketslist) {
                                        %>
                                        <tr>
                                            <td>
                                                <label><%=tickets.getTicketName()%></label>
                                            </td>
                                            <td><label class="label"></label></td>
                                            <td><label class="label"></label></td>
                                            <td><label class="label"></label></td>
                                            <td>
                                                <label>RM <%=tickets.getPrice()%>0</label>
                                            </td>
                                            <td>
                                                <input type="hidden" name="hidticketid<%=counter%>" value="<%=tickets.getTicketID()%>">
                                                <input class="input is-danger" type="number" name="qty<%=counter%>" value="0" min="0">
                                            </td>
                                        </tr>
                                        <%
                                            counter++;
                                            }
                                        %>
                                    </table>
                                </div>
                            </div>
                            <hr>
                            <input type="hidden" name="counter" value="<%=counter-1%>">
                            <input type="hidden" name="bookingstaffid" value="<%=bookingstafff.getBookingStaffID()%>">
                            <input type="hidden" name="totaltics" value="<%=dailyticket.getDailyticketmax() - totalticket%>">
                            <input type="hidden" name="date" value="<%=datee%>">
                            <input type="hidden" name="staffid" value="<%=staff_id%>">
                            <div class="field grouped">
                                <div class="control">
                                    <button type="submit" name = "command" value = "cancelBooking" class="button red">
                                        Cancel
                                    </button>
                                </div>
                                <div class="control">
                                    <button type="submit" name = "command" value = "saveBookedTic" class="button green">
                                        Next
                                    </button>
                                </div>
                            </div>
                            <div class="text-center text-xs mt-5">
                                <span style="color:red">${errorticExceed}</span>
                            </div>
                        </form>
                    </div>
                </div>
            </section>

            <footer class="footer">
                <div class="flex flex-col md:flex-row items-center justify-between space-y-3 md:space-y-0">
                    <div class="flex items-center justify-start space-x-3">
                        <div>
                            ?? 2022 Dayang Atilia
                        </div>
                    </div>
                </div>
            </footer>

            <div id="sample-modal" class="modal">
                <div class="modal-background --jb-modal-close"></div>
                <div class="modal-card">
                    <header class="modal-card-head">
                        <p class="modal-card-title">Sample modal</p>
                      </header>
                    <section class="modal-card-body">
                        <p>Lorem ipsum dolor sit amet <b>adipiscing elit</b></p>
                        <p>This is sample modal</p>
                    </section>
                    <footer class="modal-card-foot">
                        <button class="button --jb-modal-close">Cancel</button>
                        <button class="button red --jb-modal-close">Confirm</button>
                    </footer>
                </div>
            </div>

            <div id="sample-modal-2" class="modal">
                <div class="modal-background --jb-modal-close"></div>
                <div class="modal-card">
                    <header class="modal-card-head">
                        <p class="modal-card-title">Sample modal</p>
                    </header>
                    <section class="modal-card-body">
                        <p>Lorem ipsum dolor sit amet <b>adipiscing elit</b></p>
                        <p>This is sample modal</p>
                    </section>
                    <footer class="modal-card-foot">
                        <button class="button --jb-modal-close">Cancel</button>
                        <button class="button blue --jb-modal-close">Confirm</button>
                    </footer>
                </div>
            </div>
        </div>

        <!-- Scripts below are for demo only -->
        <script type="text/javascript" src="JS/main.min.js?v=1628755089081"></script>

        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
        <script type="text/javascript" src="JS/chart.sample.min.js"></script>


        <script>
            !function(f,b,e,v,n,t,s)
            {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
              n.callMethod.apply(n,arguments):n.queue.push(arguments)};
              if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
              n.queue=[];t=b.createElement(e);t.async=!0;
              t.src=v;s=b.getElementsByTagName(e)[0];
              s.parentNode.insertBefore(t,s)}(window, document,'script',
              'https://connect.facebook.net/en_US/fbevents.js');
            fbq('init', '658339141622648');
            fbq('track', 'PageView');
        </script>
        <noscript><img height="1" width="1" style="display:none" src="https://www.facebook.com/tr?id=658339141622648&ev=PageView&noscript=1"/></noscript>

        <!-- Icons below are for demo only. Feel free to use any icon pack. Docs: https://bulma.io/documentation/elements/icon/ -->
        <link rel="stylesheet" href="https://cdn.materialdesignicons.com/4.9.95/css/materialdesignicons.min.css">

    </body>
</html>
