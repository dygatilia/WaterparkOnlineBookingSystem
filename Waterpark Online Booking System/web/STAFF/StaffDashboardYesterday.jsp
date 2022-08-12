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
<%@page import="java.time.Month"%>
<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="MODEL.Tickets"%>
<%@page import="DAOImpl.TicketsDAOImpl"%>
<%@page import="DAO.TicketsDAO"%>
<%@page import="DAOImpl.TotalTicketDAOImpl"%>
<%@page import="DAO.TotalTicketDAO"%>
<%@page import="MODEL.BookingStaff"%>
<%@page import="MODEL.BookingStaff"%>
<%@page import="DAOImpl.BookingStaffDAOImpl"%>
<%@page import="DAO.BookingStaffDAO"%>
<%@page import="MODEL.Booking"%>
<%@page import="java.util.List"%>
<%@page import="DAOImpl.BookingDAOImpl"%>
<%@page import="DAO.BookingDAO"%>
<%@page import="MODEL.Staff"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en" class="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dashboard || Staff</title>

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
                                <a href="StaffSettings.jsp" class="navbar-item">
                                    <span class="icon"><i class="mdi mdi-settings"></i></span>
                                    <span>Settings</span>
                                </a>
                                <hr class="navbar-divider">
                                <a href="<%=request.getContextPath()%>/UserController?command=signout" class="navbar-item">
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
                        <li class="active">
                            <a href="StaffDashboard.jsp">
                                <span class="icon"><i class="mdi mdi-desktop-mac"></i></span>
                                <span class="menu-item-label">DASHBOARD</span>
                            </a>
                        </li>
                    </ul>
                    <ul class="menu-list">
                        <li class="--set-active-profile-html">
                            <a href="StaffBooked.jsp">
                                <span class="icon"><i class="mdi mdi-table"></i></span>
                                <span class="menu-item-label">BOOKED</span>
                            </a>
                        </li>
                        <li class="--set-active-forms-html">
                            <a href="StaffHistory.jsp">
                                <span class="icon"><i class="mdi mdi-history"></i></span>
                                <span class="menu-item-label">HISTORY</span>
                            </a>
                        </li>
                        <li class="--set-active-index-html">
                            <a href="StaffSettings.jsp">
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
                        <li>DASHBOARD</li>
                    </ul>
                    <a href="StaffBook.jsp?id=<%=staff.getStaffID()%>" class="button blue">
                        <span class="icon"><i class="mdi mdi-ticket"></i></span>
                        <span>Book Ticket</span>
                    </a>
                </div>
            </section>
            <%
                LocalDate today = LocalDate.now();          //today
                LocalDate earlier = today.minusDays(1);     //yesterday
                LocalDate later = today.plusDays(1);        //tomorrow
                
                Date currentDay = Date.valueOf(today);
                Date yesterday = Date.valueOf(earlier);
                Date tomorrow = Date.valueOf(later);
            %>
            <section class="is-hero-bar">
                <div class="flex flex-col md:flex-row items-center justify-between space-y-6 md:space-y-0">
                    <h1 class="title">
                        TICKETS
                    </h1>
                    &nbsp; &nbsp; &nbsp; 
                    <h1 class="title">
                        -
                    </h1>
                    &nbsp; &nbsp;
                    <div class="navbar-menu" id="navbar-menu">
                        <div class="navbar-item dropdown has-divider has-user-avatar">
                            <a class="navbar-link">
                                <h1><span>Yesterday (<%=yesterday%>)</span></h1>
                                <span class="icon"><i class="mdi mdi-chevron-down"></i></span>
                            </a>
                            <div class="navbar-dropdown">
                                <a href="StaffDashboard.jsp" class="navbar-item">
                                    <h1><span>Today</span></h1>
                                </a>
                                <a href="StaffDashboardTomorrow.jsp" class="navbar-item">
                                    <h1><span>Tomorrow</span></h1>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <%              
                int totalticket = 0;
                
                BookingDAO bookingdao = new BookingDAOImpl();
                List<Booking> bookinglist = bookingdao.retrieveAllBooking();
                
                BookingStaffDAO bookingstaffdao = new BookingStaffDAOImpl();
                List<BookingStaff> bookingstafflist = bookingstaffdao.retrieveAllBookingStaff();
                
                TicketsDAO ticketsdao = new TicketsDAOImpl();
                List<Tickets> ticketslist = ticketsdao.retrieveAll();
                
                DailyTicketDAO dailyticketdao = new DailyTicketDAOImpl();
                DailyTicket dailyticket = dailyticketdao.retrieveDailyTicket();
            %>
            <section class="section main-section">
                <div class="grid gap-6 grid-cols-1 md:grid-cols-3 mb-6">
                    <%
                        for (Booking booking : bookinglist){
                            
                            if(booking.getBookingDate().equals(yesterday)){
                                BookedTicketsDAO bookedticketsdao = new BookedTicketsDAOImpl();

                                for (Tickets tickets : ticketslist) {
                                    BookedTickets bookedtic = bookedticketsdao.retrieveQuantityByTicketIDnBookingId(tickets.getTicketID(), booking.getBookingID());

                                    totalticket = totalticket + bookedtic.getQuantity();
                                }
                            }
                        }
                        
                        for (BookingStaff bookingstaff : bookingstafflist){
                            
                            if(bookingstaff.getBookingDate().equals(yesterday)){
                                BookedTicketsStaffDAO bookedticketstaffdao = new BookedTicketsStaffDAOImpl();

                                for (Tickets tickets : ticketslist){
                                    BookedTicketsStaff bookedticstaff = bookedticketstaffdao.retrieveQuantityByTicketIDnBookingStaffId(tickets.getTicketID(), bookingstaff.getBookingStaffID());

                                    totalticket = totalticket + bookedticstaff.getQuantity();
                                }
                            }
                        }
                    %>
                    <div class="card">
                        <div class="card-content">
                            <div class="flex items-center justify-between">
                                <div class="widget-label">
                                    <h3>
                                        Ticket Sold 
                                        <span class="icon text-blue-500">
                                            <a href="StaffDashboardYesterdayTicket.jsp">
                                                <i class="mdi mdi-format-list-bulleted" data-bs-toggle="tooltip" title="Click to see details.">
                                                </i>
                                            </a>
                                        </span>
                                    </h3>
                                    <h1>
                                        <%=totalticket%>
                                    </h1>
                                </div>
                                <span class="icon widget-icon text-blue-500"><i class="mdi mdi-cart-outline mdi-48px"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <div class="flex items-center justify-between">
                                <div class="widget-label">
                                    <h3>
                                        Ticket Available
                                    </h3>
                                    <h1>
                                        <%=dailyticket.getDailyticketmax() - totalticket%>
                                    </h1>
                                </div>
                              <span class="icon widget-icon text-green-500"><i class="mdi mdi-ticket mdi-48px"></i></span>
                            </div>
                        </div>
                    </div>                
                </div>
            </section>
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
