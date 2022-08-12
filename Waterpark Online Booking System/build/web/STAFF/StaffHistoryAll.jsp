<%-- 
    Last Edit  : June 28, 2022, 11:05:00 PM
    Author     : Dayang Farhanim Atilia Binti Mohd Azaman
    Matric No. : S54168
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="MODEL.OrderStaff"%>
<%@page import="DAOImpl.OrderStaffDAOImpl"%>
<%@page import="DAO.OrderStaffDAO"%>
<%@page import="MODEL.Order"%>
<%@page import="DAOImpl.OrderDAOImpl"%>
<%@page import="DAO.OrderDAO"%>
<%@page import="MODEL.Booking"%>
<%@page import="DAOImpl.BookingDAOImpl"%>
<%@page import="DAO.BookingDAO"%>
<%@page import="MODEL.BookingStaff"%>
<%@page import="DAOImpl.BookingStaffDAOImpl"%>
<%@page import="java.util.List"%>
<%@page import="DAO.BookingStaffDAO"%>
<%@page import="MODEL.Staff"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en" class="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>History || Staff</title>

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
                        <li class="--set-active-profile-html">
                            <a href="StaffDashboard.jsp">
                                <span class="icon"><i class="mdi mdi-desktop-mac"></i></span>
                                <span class="menu-item-label">DASHBOARD</span>
                            </a>
                        </li>
                    </ul>
                    <ul class="menu-list">
                        <li class="--set-active-forms-html">
                            <a href="StaffBooked.jsp">
                                <span class="icon"><i class="mdi mdi-table"></i></span>
                                <span class="menu-item-label">BOOKED</span>
                            </a>
                        </li>
                        <li class="active">
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
                        <li>HISTORY</li>
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
                        Booking
                    </h1>
                    &nbsp; &nbsp;
                    <h1 class="title">
                        History
                    </h1>
                    &nbsp; &nbsp; &nbsp; 
                    <h1 class="title">
                        -
                    </h1>
                    &nbsp; &nbsp;
                    <div class="navbar-menu" id="navbar-menu">
                        <div class="navbar-item dropdown has-divider has-user-avatar">
                            <a class="navbar-link">
                                <h1><span>All Day</span></h1>
                                <span class="icon"><i class="mdi mdi-chevron-down"></i></span>
                            </a>
                            <div class="navbar-dropdown">
                                <a href="StaffHistory.jsp" class="navbar-item">
                                    <h1><span>Today</span></h1>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="section main-section">
                <div class="card has-table">
                    <header class="card-header">
                        <p class="card-header-title">
                            <span class="icon"><i class="mdi mdi-account-multiple"></i></span>
                            Booking By Customer
                        </p>
                    </header>
                    <div class="card-content">
                        <table>
                            <thead>
                                <tr>
                                  <th>No</th>
                                  <th>Date</th>
                                  <th>Booking ID</th>
                                  <th>Ticket Status</th>
                                  <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int i = 1;
                                    
                                    BookingDAO bookingdao = new BookingDAOImpl();
                                    List<Booking> bookinglist = bookingdao.retrieveAllBooking();
                                    
                                    OrderDAO orderdao = new OrderDAOImpl();
                                    
                                    for (Booking booking : bookinglist){
                                        Order order = orderdao.retrieveOrderStatusByBookingID(booking.getBookingID());
                                        
                                        if (order.getOrderStatus().equals("Invalid")){
                                %>
                                <tr>
                                    <td data-label="No"><%=i%></td>
                                    <td data-label="Date"><%=booking.getBookingDate()%></td>
                                    <td data-label="BookingID"><%=booking.getBookingID()%></td>
                                    <td data-label="BookingStatus"><%=order.getOrderStatus()%></td>
                                    <td class="actions-cell">
                                        <div class="buttons right nowrap">
                                            <button class="button small blue" type="button">
                                                <a href="../CUSTOMER/CustInvoice.jsp?id=<%=booking.getBookingID()%>"><span class="icon"><i class="mdi mdi-eye"></i></span>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <%
                                    i++;
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <br>
                <div class="card has-table">
                    <header class="card-header">
                        <p class="card-header-title">
                            <span class="icon"><i class="mdi mdi-account-multiple"></i></span>
                            Booking By Counter Staff
                        </p>
                        <a href="#" class="card-header-icon">
                            <span class="icon"><i class="mdi mdi-reload"></i></span>
                        </a>
                    </header>
                    <div class="card-content">
                        <table>
                            <thead>
                                <tr>
                                  <th>No</th>
                                  <th>Date</th>
                                  <th>Booking ID</th>
                                  <th>Ticket Status</th>
                                  <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int c = 1;
                                    BookingStaffDAO bookingstaffdao = new BookingStaffDAOImpl();
                                    List<BookingStaff> bookingstafflist = bookingstaffdao.retrieveAllBookingStaff();
                                    
                                    OrderStaffDAO orderstaffdao = new OrderStaffDAOImpl();
                                    
                                    for (BookingStaff bookingstaff : bookingstafflist){
                                        OrderStaff orderstaff = orderstaffdao.retrieveOrderStatusByBookingStaffID(bookingstaff.getBookingStaffID());
                                        
                                        if (orderstaff.getOrderStaffStatus().equals("Invalid")){
                                %>
                                <tr>
                                    <td data-label="No"><%=c%></td>
                                    <td data-label="Date"><%=bookingstaff.getBookingDate() %></td>
                                    <td data-label="BookingID"><%=bookingstaff.getBookingStaffID()%></td>
                                    <td data-label="BookingStatus">Invalid</td>
                                    <td class="actions-cell">
                                        <div class="buttons right nowrap">
                                            <button class="button small blue" type="button">
                                                <a href="StaffInvoice.jsp?id=<%=bookingstaff.getBookingStaffID()%>"><span class="icon"><i class="mdi mdi-eye"></i></span>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <%
                                    c++;
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>

            <footer class="footer">
                <div class="flex flex-col md:flex-row items-center justify-between space-y-3 md:space-y-0">
                    <div class="flex items-center justify-start space-x-3">
                        <div>
                            © 2022 Dayang Atilia
                        </div>
                    </div>
                </div>
            </footer>
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
