<%-- 
    Last Edit  : June 28, 2022, 11:05:00 PM
    Author     : Dayang Farhanim Atilia Binti Mohd Azaman
    Matric No. : S54168
--%>

<%@page import="MODEL.Promotions"%>
<%@page import="DAOImpl.PromotionsDAOImpl"%>
<%@page import="DAO.PromotionsDAO"%>
<%@page import="MODEL.BookedFacilitiesStaff"%>
<%@page import="DAOImpl.BookedFacilitiesStaffDAOImpl"%>
<%@page import="DAO.BookedFacilitiesStaffDAO"%>
<%@page import="MODEL.BookedFacilities"%>
<%@page import="DAO.BookedFacilitiesDAO"%>
<%@page import="DAOImpl.BookedFacilitiesDAOImpl"%>
<%@page import="MODEL.Facilities"%>
<%@page import="DAOImpl.FacilitiesDAOImpl"%>
<%@page import="DAO.FacilitiesDAO"%>
<%@page import="MODEL.BookedTicketsStaff"%>
<%@page import="MODEL.BookedTicketsStaff"%>
<%@page import="DAOImpl.BookedTicketsStaffDAOImpl"%>
<%@page import="DAO.BookedTicketsStaffDAO"%>
<%@page import="MODEL.Tickets"%>
<%@page import="DAOImpl.TicketsDAOImpl"%>
<%@page import="java.util.List"%>
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
        <title>Book Facilities || Staff</title>

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
                        <li>TICKET CONFIRMATION</li>
                    </ul>
                </div>
            </section>

            <section class="section main-section">
                <div class="card">
                    <header class="card-header">
                        <p class="card-header-title">
                            <span class="icon"><i class="mdi mdi-ballot"></i></span>
                            Book Ticket Confirmation
                        </p>
                    </header>
                    <%
                        int bookstaffid = Integer.parseInt(request.getParameter("id"));
                        BookingStaffDAO bookingstaffdao = new BookingStaffDAOImpl();
                        BookingStaff bookingstaff = bookingstaffdao.retrieveBookingStaffByBookingStaffId(bookstaffid);
                    %>
                    <div class="card-content">
                        <form action="<%=request.getContextPath()%>/BookingStaffController" method="POST">
                            <div class="field">
                                <label class="label">Date</label>
                                <div class="field-body">
                                    <div class="field">
                                        <div class="control is-expanded icons-left">
                                            <input class="input" type="text" name="date" value="<%=bookingstaff.getBookingDate()%>" readonly>
                                            <span class="icon is-small left"><i class="mdi mdi-calendar"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Full Name</label>
                                <div class="field-body">
                                    <div class="field">
                                        <div class="control is-expanded icons-left">
                                            <input class="input" type="text" name="name" value="<%=bookingstaff.getBookingName()%>" readonly>
                                            <span class="icon is-small left"><i class="mdi mdi-account"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Contact Number</label>
                                <div class="field-body">
                                    <div class="field">
                                        <div class="control is-expanded icons-left">
                                            <input class="input" type="text" name="contactnum" value="<%=bookingstaff.getContactNum()%>" readonly>
                                            <span class="icon is-small left"><i class="mdi mdi-cellphone-iphone"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>  
                            <hr>
                            <div class="field">
                                <div class="control">
                                    <table>
                                        <tr>
                                            <th>Name</th>
                                            <th></th>
                                            <th>Price</th>
                                            <th></th>
                                            <th>Quantity</th>
                                        </tr>
                                    </table>
                                </div>
                            </div>                
                            <div class="field">
                                <label class="label">Ticket Type</label>
                                <div class="control">
                                    <table>
                                        <%
                                            Double amount = 0.0;

                                            TicketsDAO ticketsdao = new TicketsDAOImpl();
                                            List<Tickets> ticketslist = ticketsdao.retrieveAll();

                                            BookedTicketsStaffDAO bookedticketsstaffdao = new BookedTicketsStaffDAOImpl();
                                            BookedTicketsStaff bookedticket = bookedticketsstaffdao.retrieveBookedTicketsStaffByBookingStaffId(bookstaffid);

                                            for (Tickets tickets : ticketslist) {
                                                BookedTicketsStaff bookedtic = bookedticketsstaffdao.retrieveQuantityByTicketIDnBookingStaffId(tickets.getTicketID(), bookingstaff.getBookingStaffID());
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
                                                <input class="input is-danger text-right" type="text" value="<%=bookedtic.getQuantity()%>" readonly>
                                            </td>
                                        </tr>
                                        <%
                                            amount = amount + (tickets.getPrice() * bookedtic.getQuantity());

                                            }
                                        %>
                                    </table>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Facilities</label>
                                <div class="control">
                                    <table>
                                        <%
                                            FacilitiesDAO facilitiesdao = new FacilitiesDAOImpl();
                                            List<Facilities> facilitieslist = facilitiesdao.retrieveAll();

                                            BookedFacilitiesStaffDAO bookedfacilitiesstaffdao = new BookedFacilitiesStaffDAOImpl();
                                            BookedFacilitiesStaff bookedfacilities = bookedfacilitiesstaffdao.retrieveBookedFacilitiesStaffByBookingStaffId(bookstaffid);

                                            for (Facilities facilities : facilitieslist) {
                                                BookedFacilitiesStaff bookedfac = bookedfacilitiesstaffdao.retrieveQuantityByFacilitiesIDnBookingStaffID(facilities.getFacilitiesID(), bookingstaff.getBookingStaffID());
                                        %>
                                        <tr>
                                            <td>
                                                <label><%=facilities.getFacilitiesName()%></label>
                                            </td>
                                            <td><label class="label"></label></td>
                                            <td>
                                                <label>RM <%=facilities.getFacilitiesPrice()%>0</label>
                                            </td>
                                            <td>
                                                <input class="input is-danger text-right" type="text" value="<%=bookedfac.getQuantity()%>" readonly>
                                            </td>
                                        </tr>
                                        <%
                                            amount = amount + (facilities.getFacilitiesPrice() * bookedfac.getQuantity());

                                            }
                                        %>
                                    </table>
                                </div>
                            </div>
                            <hr>
                            <%--<div class="field">
                                <label class="label">Promotions Code</label>
                                <div class="control">
                                    <div class="select is-fullwidth">
                                        <select name="promoid">
                                        <%
                                            PromotionsDAO promotionsdao = new PromotionsDAOImpl();
                                            int c = 0;

                                            List<Promotions> promotionslist = promotionsdao.retrieveAll();
                                            for (Promotions promotions : promotionslist) {
                                                if (c == 0){
                                                    %>
                                                    <option selected value="<%=promotions.getPromotionsID()%>"><%=promotions.getPromotionsCode()%></option>
                                                    <%
                                                    c++;
                                                } else {
                                                    %>
                                                    <option value="<%=promotions.getPromotionsID()%>"><%=promotions.getPromotionsCode()%></option>
                                                    <%
                                                }
                                            }
                                        %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <hr>--%>
                            <input type="hidden" name="paymenttype" value="Credit/Debit Card"/>
                            <input type="hidden" name="bookstaffid" value="<%=bookingstaff.getBookingStaffID()%>"/>
                            <input type="hidden" name="bookedticketid" value="<%=bookedticket.getBookedticid()%>">
                            <input type="hidden" name="bookedfacilitiesid" value="<%=bookedfacilities.getBookedfacid()%>">
                            <input type="hidden" name="amount" value="<%=amount%>"/>
                            <div class="field grouped">
                                <div class="control">
                                    <button type="submit" name="command" value="cancelBookedFac" class="button red">
                                        Reset
                                    </button>
                                </div>
                                <div class="control">
                                    <button type="submit" name="command" value="confirmBook" class="button green">
                                        Next
                                    </button>
                                </div>
                            </div>
                        </form>
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
