<%-- 
    Last Edit  : June 28, 2022, 11:05:00 PM
    Author     : Dayang Farhanim Atilia Binti Mohd Azaman
    Matric No. : S54168
--%>

<%@page import="MODEL.DailyTicket"%>
<%@page import="DAOImpl.DailyTicketDAOImpl"%>
<%@page import="DAO.DailyTicketDAO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="MODEL.CheckoutStaff"%>
<%@page import="DAOImpl.CheckoutStaffDAOImpl"%>
<%@page import="DAO.CheckoutStaffDAO"%>
<%@page import="MODEL.Checkout"%>
<%@page import="DAOImpl.CheckoutDAOImpl"%>
<%@page import="DAO.CheckoutDAO"%>
<%@page import="java.time.Month"%>
<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="MODEL.BookedTicketsStaff"%>
<%@page import="DAOImpl.BookedTicketsStaffDAOImpl"%>
<%@page import="DAO.BookedTicketsStaffDAO"%>
<%@page import="MODEL.TotalTicket"%>
<%@page import="DAOImpl.TotalTicketDAOImpl"%>
<%@page import="DAO.TotalTicketDAO"%>
<%@page import="MODEL.BookingStaff"%>
<%@page import="DAOImpl.BookingStaffDAOImpl"%>
<%@page import="DAO.BookingStaffDAO"%>
<%@page import="MODEL.Booking"%>
<%@page import="DAOImpl.BookingDAOImpl"%>
<%@page import="DAO.BookingDAO"%>
<%@page import="MODEL.BookedTickets"%>
<%@page import="DAOImpl.BookedTicketsDAOImpl"%>
<%@page import="DAO.BookedTicketsDAO"%>
<%@page import="DAO.BookedTicketsDAO"%>
<%@page import="MODEL.Tickets"%>
<%@page import="java.util.List"%>
<%@page import="DAOImpl.TicketsDAOImpl"%>
<%@page import="DAO.TicketsDAO"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en" class="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dashboard Ticket Details || Manager</title>

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
                <div class="navbar-menu" id="navbar-menu">
                    <div class="navbar-end">
                        <div class="navbar-item dropdown has-divider has-user-avatar">
                            <a class="navbar-link">
                                <div class="user-avatar">
                                    <img src="https://icons.veryicon.com/png/o/business/multi-color-financial-and-business-icons/user-139.png" alt="Profile" class="rounded-full">
                                </div>
                                <div class="is-user-name"><span>Manager</span></div>
                                <span class="icon"><i class="mdi mdi-chevron-down"></i></span>
                            </a>
                            <div class="navbar-dropdown">
                                <a href="AdminMessage.jsp" class="navbar-item">
                                    <span class="icon"><i class="mdi mdi-email"></i></span>
                                    <span>Messages</span>
                                </a>
                                <a href="AdminSettings.jsp" class="navbar-item">
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
                        MANAGER
                    </div>
                </div>
                <div class="menu is-menu-main">
                    <ul class="menu-list">
                        <li class="active">
                            <a href="AdminDashboard.jsp">
                                <span class="icon"><i class="mdi mdi-desktop-mac"></i></span>
                                <span class="menu-item-label">DASHBOARD</span>
                            </a>
                        </li>
                    </ul>
                    <ul class="menu-list">
                        <li class="--set-active-forms-html">
                            <a href="AdminFeatures.jsp">
                                <span class="icon"><i class="mdi mdi-table"></i></span>
                                <span class="menu-item-label">FEATURES</span>
                            </a>
                        </li>
                        <li class="--set-active-profile-html">
                            <a href="AdminPricing.jsp">
                                <span class="icon"><i class="mdi mdi-cash"></i></span>
                                <span class="menu-item-label">PRICING</span>
                            </a>
                        </li>
                        <li class="--set-active-profile-html">
                            <a href="AdminMessage.jsp">
                                <span class="icon"><i class="mdi mdi-email"></i></span>
                                <span class="menu-item-label">MESSAGES</span>
                            </a>
                        </li>
                        <li class="--set-active-profile-html">
                            <a href="AdminStaff.jsp">
                                <span class="icon"><i class="mdi mdi-account-multiple"></i></span>
                                <span class="menu-item-label">STAFF</span>
                            </a>
                        </li>
                        <li class="--set-active-profile-html">
                            <a href="AdminSettings.jsp">
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
                        <li>MANAGER</li>
                        <li>DASHBOARD</li>
                        <li>TICKET DETAILS</li>
                    </ul>
                </div>
            </section>
            <%
                LocalDate today = LocalDate.now();
                Month currentMonth = today.getMonth();          
                Month previousMonth = currentMonth.minus(1);    //previousmonth
                Month nextMonth = currentMonth.plus(1);         //nextmonth
            
                int currentYear = today.getYear();
            %>
            <section class="is-hero-bar">
                <div class="flex flex-col md:flex-row items-center justify-between space-y-6 md:space-y-0">
                    <h1 class="title">
                        SALES
                    </h1>
                    &nbsp; &nbsp; &nbsp; 
                    <h1 class="title">
                        -
                    </h1>
                    &nbsp; &nbsp;
                    <div class="navbar-menu" id="navbar-menu">
                        <div class="navbar-item dropdown has-divider has-user-avatar">
                            <a class="navbar-link">
                                <h1><span><%=nextMonth%></span></h1>
                                <span class="icon"><i class="mdi mdi-chevron-down"></i></span>
                            </a>
                            <div class="navbar-dropdown">
                                <a href="AdminDashboardPrevious.jsp" class="navbar-item">
                                    <h1><span><%=previousMonth%></span></h1>
                                </a>
                                <a href="AdminDashboard.jsp" class="navbar-item">
                                    <h1><span><%=currentMonth%></span></h1>
                                </a>
                                <hr class="navbar-divider">
                                <a href="AdminDashboardYear.jsp" class="navbar-item">
                                    <h1><span><%=currentYear%></span></h1>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <%              
                int ticket = 0;
                
                BookingDAO bookingdao = new BookingDAOImpl();
                List<Booking> bookinglist = bookingdao.retrieveAllBooking();
                
                BookingStaffDAO bookingstaffdao = new BookingStaffDAOImpl();
                List<BookingStaff> bookingstafflist = bookingstaffdao.retrieveAllBookingStaff();
                
                TicketsDAO ticketsdao = new TicketsDAOImpl();
                List<Tickets> ticketslist = ticketsdao.retrieveAll();
                
                TotalTicketDAO totalticketdao = new TotalTicketDAOImpl();
                TotalTicket totaltickets = null;
            %>
            <section class="section main-section">
                <div class="grid gap-6 grid-cols-1 md:grid-cols-3 mb-6">
                    <%
                        Double totalsales = 0.0;
                        
                        for (Booking booking : bookinglist){
                            LocalDate localdate = booking.getBookingDate().toLocalDate();
                            Month bookingMonth = localdate.getMonth();
                            
                            CheckoutDAO checkoutdao = new CheckoutDAOImpl();
                            Checkout checkout = checkoutdao.retrieveCheckoutByBookingId(booking.getBookingID());
                            
                            if(bookingMonth.equals(nextMonth)){
                                BookedTicketsDAO bookedticketsdao = new BookedTicketsDAOImpl();

                                for (Tickets tickets : ticketslist) {
                                    BookedTickets bookedtic = bookedticketsdao.retrieveQuantityByTicketIDnBookingId(tickets.getTicketID(), booking.getBookingID());

                                    ticket = ticket + bookedtic.getQuantity();
                                }
                                totalsales = totalsales + (checkout.getCheckoutamount());
                            }
                        }
                        
                        int total = 0;
                        
                        for (BookingStaff bookingstaff : bookingstafflist){                            
                            LocalDate localdate = bookingstaff.getBookingDate().toLocalDate();
                            Month bookingMonth = localdate.getMonth();
                            
                            CheckoutStaffDAO checkoutstaffdao = new CheckoutStaffDAOImpl();
                            CheckoutStaff checkoutstaff = checkoutstaffdao.retrieveCheckoutStaffByBookingStaffId(bookingstaff.getBookingStaffID());
                            
                            if(bookingMonth.equals(nextMonth)){
                                BookedTicketsStaffDAO bookedticketstaffdao = new BookedTicketsStaffDAOImpl();

                                for (Tickets tickets : ticketslist){
                                    BookedTicketsStaff bookedticstaff = bookedticketstaffdao.retrieveQuantityByTicketIDnBookingStaffId(tickets.getTicketID(), bookingstaff.getBookingStaffID());

                                    total = total + bookedticstaff.getQuantity();
                                }
                                totalsales = totalsales + (checkoutstaff.getCheckoutamount());
                            }
                        }
                        
                        int totalticket = total + ticket;
                        
                        totaltickets = totalticketdao.retrieveTotalTicket();
                        
                        if(totaltickets == null){
                            totaltickets.setTotalticketID(totaltickets.getTotalticketID());
                            totaltickets.setTicketDate(Date.valueOf(today));
                            totaltickets.setTicketSold(totalticket);

                            totalticketdao.update(totaltickets);
                        } else {
                            totaltickets.setTicketDate(Date.valueOf(today));
                            totaltickets.setTicketSold(totalticket);

                            totalticketdao.addTotalTicket(totaltickets);
                        }
                        
                        DecimalFormat df = new DecimalFormat();
                        df.setMaximumFractionDigits(2);
                        
                        String sales = df.format(totalsales);
                    %>
                    <div class="card">
                        <div class="card-content">
                            <div class="flex items-center justify-between">
                                <div class="widget-label">
                                    <h3>
                                        Total Sales
                                    </h3>
                                    <h1>
                                        RM <%=sales%>.00
                                    </h1>
                                </div>
                              <span class="icon widget-icon text-green-500"><i class="mdi mdi-cart-outline mdi-48px"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <div class="flex items-center justify-between">
                                <div class="widget-label">
                                    <h3>
                                        Ticket Sold
                                        <span class="icon text-blue-500">
                                            <a href="AdminDashboardNext.jsp">
                                                <i class="mdi mdi-format-list-bulleted" data-bs-toggle="tooltip" title="Click to see details.">
                                                </i>
                                            </a>
                                        </span>
                                    </h3>
                                    <h2>
                                        Online Customer - <%=ticket%>
                                    </h2>
                                    <h2>
                                        Walk-in Customer - <%=total%>
                                    </h2>
                                </div>
                                <span class="icon widget-icon text-blue-500"><i class="mdi mdi-ticket mdi-48px"></i></span>
                            </div>
                        </div>
                    </div>
                    <%
                        DailyTicketDAO dailyticketdao = new DailyTicketDAOImpl();
                        DailyTicket dailyticket = dailyticketdao.retrieveDailyTicket();
                    %>
                    <div class="card">
                        <div class="card-content">
                            <div class="flex items-center justify-between">
                                <div class="widget-label">
                                    <h3>
                                        Daily Total Ticket
                                    </h3>
                                    <h1>
                                        <%=dailyticket.getDailyticketmax()%>
                                    </h1>
                                </div>
                                <span class="icon widget-icon text-black-500"><i class="mdi mdi-ticket-percent mdi-48px"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

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
