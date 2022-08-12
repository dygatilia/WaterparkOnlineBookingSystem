<%-- 
    Last Edit  : June 28, 2022, 11:05:00 PM
    Author     : Dayang Farhanim Atilia Binti Mohd Azaman
    Matric No. : S54168
--%>

<%@page import="MODEL.Promotions"%>
<%@page import="DAOImpl.PromotionsDAOImpl"%>
<%@page import="DAO.PromotionsDAO"%>
<%@page import="MODEL.Facilities"%>
<%@page import="DAOImpl.FacilitiesDAOImpl"%>
<%@page import="DAO.FacilitiesDAO"%>
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
        <title>Pricing || Manager</title>

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
                    <li class="--set-active-index-html">
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
                    <li class="active">
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
                    <li>PRICING</li>
                </ul>
            </div>
        </section>

        <section class="is-title-bar">
            <a href="AdminAddTickets.jsp" class="button blue">
                <span class="icon"><i class="mdi mdi-ticket"></i></span>
                <span>ADD TICKETS</span>
            </a>
            <a href="AdminAddFacilities.jsp" class="button blue">
                <span class="icon"><i class="mdi mdi-domain"></i></span>
                <span>ADD FACILITIES</span>
            </a>
            <%--<a href="AdminAddPromotions.jsp" class="button blue">
                <span class="icon"><i class="mdi mdi-ticket-percent"></i></span>
                <span>ADD PROMOTIONS</span>
            </a>--%>
        </section>

        <section class="section main-section">
            <div class="card has-table">
                <header class="card-header">
                    <p class="card-header-title">
                        <span class="mdi mdi-format-list-bulleted"></span>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; TICKETS
                    </p>
                </header>
                <div class="card-content">
                    <table>
                        <thead>
                            <tr>
                                <th class="image-cell"></th>
                                <th>Ticket Name</th>
                                <th>Normal Price</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <%
                            TicketsDAO ticketsdao = new TicketsDAOImpl();
                            List<Tickets> ticketslist = ticketsdao.retrieveAll();
                            for (Tickets tickets : ticketslist) {
                        %>
                        <tbody>
                            <tr>
                                <td class="image-cell">
                                    <div class="image"></div>
                                </td>
                                <td data-label="Name"><%=tickets.getTicketName()%></td>
                                <td data-label="Price">RM <%=tickets.getPrice()%>0</td>
                                <td data-label=""></td>
                                <td class="actions-cell">
                                    <div class="buttons right nowrap">
                                        <a href="TicketsDetails.jsp?id=<%=tickets.getTicketID()%>" class="button small blue">
                                            <span class="icon"><i class="mdi mdi-pencil"></i></span>
                                        </a>
                                        <form action="<%=request.getContextPath()%>/TicketsController" method="POST">
                                            <input type="hidden" name="hidid" value="<%=tickets.getTicketID()%>">
                                            <button  onclick="return confirm('Are you sure to delete?')" type="submit" name="command" value="delete" class="button small red">
                                                <span class="icon"><i class="mdi mdi-trash-can"></i></span>
                                            </button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                        <%
                            }
                        %>
                    </table>
                </div>
            </div>
        </section>

        <section class="section main-section">
            <div class="card has-table">
                <header class="card-header">
                    <p class="card-header-title">
                        <span class="mdi mdi-format-list-bulleted"></span>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; FACILITIES
                    </p>
                </header>
                <div class="card-content">
                    <table>
                        <thead>
                            <tr>
                                <th class="image-cell"></th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                            </tr>
                        </thead>
                        <%
                            FacilitiesDAO facilitiesdao = new FacilitiesDAOImpl();
                            List<Facilities> facilitieslist = facilitiesdao.retrieveAll();
                            for (Facilities facilities : facilitieslist) {
                        %>
                        <tbody>
                            <tr>
                                <td class="image-cell">
                                  <div class="image">
                                      <img src="../img/imageFacilities.jsp?id=<%=facilities.getFacilitiesID()%>" alt="Facilities Image" class="rounded-full">
                                  </div>
                                </td>
                                <td data-label="name">
                                    <a class="hover:text-blue-500 hover:underline" href="FacilitiesDetails.jsp?id=<%=facilities.getFacilitiesID()%>"><%=facilities.getFacilitiesName()%></a>
                                </td>
                                <td data-label="price">RM <%=facilities.getFacilitiesPrice()%>0</td>
                                <td data-label="quantity"><%=facilities.getFacilitiesQuantity()%></td>
                                <td class="actions-cell">
                                    <div class="buttons right nowrap">
                                        <a href="FacilitiesDetails.jsp?id=<%=facilities.getFacilitiesID()%>" class="button small green">
                                            <span class="icon"><i class="mdi mdi-eye"></i></span>
                                        </a>
                                        <a href="EditFacilities.jsp?id=<%=facilities.getFacilitiesID()%>" class="button small blue">
                                            <span class="icon"><i class="mdi mdi-pencil"></i></span>
                                        </a>
                                        <form action="<%=request.getContextPath()%>/FacilitiesController" method="POST">
                                            <input type="hidden" name="hidid" value="<%=facilities.getFacilitiesID()%>">
                                            <button  onclick="return confirm('Are you sure to delete?')" type="submit" name="command" value="delete" class="button small red">
                                                <span class="icon"><i class="mdi mdi-trash-can"></i></span>
                                            </button>
                                        </form>
                                    </div>
                                </td>
                              </tr>
                        </tbody>
                        <%
                            }
                        %>
                    </table>
                </div>
            </div>
        </section>

        <%--<section class="section main-section">
            <div class="card has-table">
                <header class="card-header">
                  <p class="card-header-title">
                    <span class="mdi mdi-format-list-bulleted"></span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; PROMOTIONS
                  </p>
                  <a href="#" class="card-header-icon">
                    <span class="icon"><i class="mdi mdi-reload"></i></span>
                  </a>
                </header>
                <div class="card-content">
                    <table>
                        <thead>
                        <tr>
                            <th class="image-cell"></th>
                            <th>Code</th>
                            <th>Discount Off</th>
                        </tr>
                        </thead>
                        <%
                            PromotionsDAO promodao = new PromotionsDAOImpl();
                            List<Promotions> promolist = promodao.retrieveAll();
                            for (Promotions promo : promolist) {
                        %>
                        <tbody>
                            <tr>
                                <td class="image-cell">
                                    <div class="image">
                                        <img src="../img/imagePromotions.jsp?id=<%=promo.getPromotionsID()%>" alt="Promotions Image" class="rounded-full">
                                    </div>
                                </td>
                                <td data-label="code">
                                    <a class="hover:text-blue-500 hover:underline" href="PromotionsDetails.jsp?id=<%=promo.getPromotionsID()%>"><%=promo.getPromotionsCode()%></a>
                                </td>
                                <td data-label="discount">
                                    <a class="hover:text-blue-500 hover:underline"><%=promo.getPromotionsDiscount()%></a>
                                </td>
                                <td class="actions-cell">
                                    <div class="buttons right nowrap">
                                        <a href="PromotionsDetails.jsp?id=<%=promo.getPromotionsID()%>" class="button small green">
                                            <span class="icon"><i class="mdi mdi-eye"></i></span>
                                        </a>
                                        <a href="EditPromotions.jsp?id=<%=promo.getPromotionsID()%>" class="button small blue">
                                            <span class="icon"><i class="mdi mdi-pencil"></i></span>
                                        </a>
                                        <form action="<%=request.getContextPath()%>/PromotionsController" method="POST">
                                            <input type="hidden" name="hidid" value="<%=promo.getPromotionsID()%>">
                                            <button  onclick="return confirm('Are you sure to delete?')" type="submit" name="command" value="delete" class="button small red">
                                                <span class="icon"><i class="mdi mdi-trash-can"></i></span>
                                            </button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                        <%
                            }
                        %>
                    </table>
                </div>
            </div>
        </section>--%>

        <footer class="footer">
            <div class="flex flex-col md:flex-row items-center justify-between space-y-3 md:space-y-0">
                <div class="flex items-center justify-start space-x-3">
                    <div>
                        © 2022 Dayang Atilia
                    </div>
                </div>
            </div>
        </footer>

        <!-- Scripts below are for demo only -->
        <script type="text/javascript" src="JS/main.min.js?v=1628755089081"></script>
        <script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>

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
