<%-- 
    Last Edit  : June 28, 2022, 11:05:00 PM
    Author     : Dayang Farhanim Atilia Binti Mohd Azaman
    Matric No. : S54168
--%>

<%@page import="MODEL.Admin"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en" class="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Settings || Manager</title>

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
                    <li class="--set-active-profile-html">
                        <a href="AdminFeatures.jsp">
                            <span class="icon"><i class="mdi mdi-table"></i></span>
                            <span class="menu-item-label">FEATURES</span>
                        </a>
                    </li>
                    <li class="--set-active-forms-html">
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
                    <li class="active">
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
                    <li>SETTINGS</li>
                    <li>EDIT PROFILE</li>
                </ul>
            </div>
        </section>

        <section class="section main-section">
            <div class="mb-6">
                <div class="card">
                    <header class="card-header">
                        <p class="card-header-title">
                            <span class="icon"><i class="mdi mdi-account-circle"></i></span>
                            Edit Profile
                        </p>
                    </header>
                    <div class="card-content">
                        <form action="../UserController">
                            <div class="image w-48 h-48 mx-auto">
                                <img src="https://icons.veryicon.com/png/o/business/multi-color-financial-and-business-icons/user-139.png" alt="Profile" class="rounded-full">
                            </div>
                            <div class="field">
                                <div class="buttons right nowrap">
                                    <button class="button green" onclick="alert('Your password has been successfully changed!');" type="submit" name="command" value="updateAdmin">
                                        Update
                                    </button>
                                </div>
                            </div>
                            <%
                                Admin admin = (Admin) request.getSession().getAttribute("admin");
                            %>
                            <hr>
                            <div>
                                <div>
                                    <input class="w-full border rounded" type="hidden" name="hidid" value="<%=admin.getAdminID()%>">
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">E-mail</label>
                                <div class="field-body">
                                    <div class="field">
                                        <div class="control">
                                            <input type="email" autocomplete="on" name="email" readonly value="<%=admin.getAdEmail()%>" class="input is-static">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Password</label>
                                <div class="field-body">
                                    <div class="field">
                                        <div class="control">
                                            <input type="text" autocomplete="on" name="password" value="<%=admin.getAdPassword()%>" class="input" required>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
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
