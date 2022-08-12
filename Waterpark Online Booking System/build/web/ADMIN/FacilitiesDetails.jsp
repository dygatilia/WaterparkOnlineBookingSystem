<%-- 
    Last Edit  : June 28, 2022, 11:05:00 PM
    Author     : Dayang Farhanim Atilia Binti Mohd Azaman
    Matric No. : S54168
--%>

<%@page import="MODEL.Facilities"%>
<%@page import="DAOImpl.FacilitiesDAOImpl"%>
<%@page import="DAO.FacilitiesDAO"%>
<%@page import="MODEL.Tickets"%>
<%@page import="DAOImpl.TicketsDAOImpl"%>
<%@page import="DAO.TicketsDAO"%>
<%@page import="MODEL.Features"%>
<%@page import="DAOImpl.FeaturesDAOImpl"%>
<%@page import="DAO.FeaturesDAO"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en" class="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Facilities Details || Manager</title>

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
                        <li class="--set-active-profile-html">
                            <a href="AdminDashboard.jsp">
                                <span class="icon"><i class="mdi mdi-desktop-mac"></i></span>
                                <span class="menu-item-label">DASHBOARD</span>
                            </a>
                        </li>
                    </ul>
                    <ul class="menu-list">
                        <li class="--set-active-profile-html">
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
                        <li class="--set-active-forms-html">
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
                    <li>FACILITIES DETAILS</li>
                  </ul>
                </div>
            </section>

            <section class="section main-section">
                <div class="card mb-6">
                    <header class="card-header">
                      <p class="card-header-title">
                        <span class="icon"><i class="mdi mdi-ballot"></i></span>
                        Facilities Details
                      </p>
                    </header>
                    <%
                        String facilitiesid = request.getParameter("id");
                        FacilitiesDAO facilitiesdao = new FacilitiesDAOImpl();
                        Facilities facilities = facilitiesdao.retrieveFacilitiesByFacilitiesId(Integer.parseInt(facilitiesid));
                    %>
                    <div class="card-content">
                        <form action="../FacilitiesController" method="POST" enctype="multipart/form-data">
                            <div class="field">
                                <div class="field-body">
                                    <div class="field">
                                        <label class="label">Picture</label>
                                        <div class="field-body">
                                            <div class="field file">
                                                <div class="image w-48">
                                                    <img src="../img/imageFacilities.jsp?id=<%=facilities.getFacilitiesID()%>" alt="Facilities Image">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="field">
                                        <label class="label">Name</label>
                                        <div class="control icons-left">
                                            <input class="input" name="name" type="text" readonly placeholder="Facilities's Name" value="<%=facilities.getFacilitiesName()%>">
                                            <span class="icon left"><i class="mdi mdi-domain"></i></span>
                                        </div>
                                    </div>
                                    <div class="field">
                                        <label class="label">Price</label>
                                        <div class="control icons-left">
                                            <input class="input" name="price" id="price" type="text" readonly placeholder="0.00" value="<%=facilities.getFacilitiesPrice()%>0">
                                            <span class="icon left">RM</span>
                                        </div>
                                    </div>
                                    <div class="field">
                                        <label class="label">Quantity</label>
                                        <div class="control">
                                            <input class="input" name="quantity" type="number" readonly placeholder="0" value="<%=facilities.getFacilitiesQuantity()%>">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="field grouped">
                                <div class="buttons right nowrap">
                                    <a href="EditFacilities.jsp?id=<%=facilities.getFacilitiesID()%>" class="button blue">
                                        Edit
                                    </a>
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
