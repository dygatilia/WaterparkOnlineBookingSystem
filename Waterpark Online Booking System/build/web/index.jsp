<%@page import="MODEL.Features"%>
<%@page import="java.util.List"%>
<%@page import="DAOImpl.FeaturesDAOImpl"%>
<%@page import="DAO.FeaturesDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta name="theme-color" content="#000000"/>
        <link rel="shortcut icon" href="./assets/img/favicon.ico"/>
        <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/creativetimofficial/tailwind-starter-kit/compiled-tailwind.min.css"/>
        <link rel="icon" type="image/png" sizes="32x32" href="https://i.pinimg.com/originals/2a/0d/d7/2a0dd79cf1372716a4e3254e4861d9f3.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
        <link rel="preload" href="/fonts/Inter-roman.var.woff2?v=3.18" as="font" type="font/woff2" crossorigin="">
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
        <title>Waterpark Online Booking System</title>
    </head>
    <body class="text-gray-800 antialiased">
        <nav class="top-0 absolute z-50 w-full flex flex-wrap items-center justify-between px-2 py-3 ">
            <div class="container px-4 mx-auto flex flex-wrap items-center justify-between">
                <div class="w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start">
                    <a class="text-sm font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase text-white" href="index.jsp">
                        HOME
                    </a>
         
                </div>
                <div class="lg:flex flex-grow items-center bg-white lg:bg-transparent lg:shadow-none hidden" id="example-collapse-navbar">
                    <ul class="flex flex-col lg:flex-row list-none lg:ml-auto">
                        <li class="nav-item">
                            <a class="px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500" href="GENERAL/Features.jsp">
                                <span class="ml-2"> Attractions </span>
                            </a>
                        </li>
                        <li class="nav-item">
                                <a class="download-button px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500"  href="GENERAL/Facilities.jsp" >
                                    <span class="ml-2"> Facilities </span>
                                </a>
                            </li>
                        <li class="nav-item">
                            <a class="px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500" href="GENERAL/TicketPrice.jsp">
                                <span class="ml-2"> Ticket Price </span>
                            </a>
                        </li>
                        <li class="flex items-center">
                            <button class="bg-blue-300 text-gray-800 active:bg-gray-100 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-md outline-none focus:outline-none lg:mr-1 lg:mb-0 ml-3 mb-3"
                                    type="button" style="transition: all 0.15s ease 0s;">
                                <a href="GENERAL/LogIn.jsp"> LOGIN </a>
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <main>
            <div class="relative pt-16 pb-32 flex content-center items-center justify-center" style="min-height: 75vh;">
                <div class="absolute top-0 w-full h-full bg-center bg-cover" style='background-image: url("https://imgcy.trivago.com/c_limit,d_dummy.jpeg,f_auto,h_1300,q_auto,w_2000/itemimages/55/55/55556_v5.jpeg");'>
                    <span id="blackOverlay" class="w-full h-full absolute opacity-40 bg-black"></span>
                </div>
                <div class="container relative mx-auto">
                    <div class="items-center flex flex-wrap">
                        <div class="w-full lg:w-6/12 px-4 ml-auto mr-auto text-center">
                            <div class="pr-12">
                                <h1 class="text-white font-semibold text-6xl">
                                    JENGKA WONDERLAND
                                </h1><br>
                                <p class="text-white">Book Tickets Now!</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="top-auto bottom-0 left-0 right-0 w-full absolute pointer-events-none overflow-hidden" style="height: 70px;">
                    <svg class="absolute bottom-0 overflow-hidden" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none" version="1.1" viewBox="0 0 2560 100" x="0" y="0">
                        <polygon class="text-gray-300 fill-current" points="2560 0 2560 100 0 100"></polygon>
                    </svg>
                </div>
            </div>
            <section class="pb-20 bg-gray-300 -mt-24">
                <div class="container mx-auto px-4">
                    <div class="flex flex-wrap">
                        <div class="lg:pt-12 pt-6 w-full md:w-4/12 px-4 text-center">
                            <div class="relative flex flex-col min-w-0 break-words bg-white w-full mb-8 shadow-lg rounded-lg">
                                <div class="px-4 py-5 flex-auto">
                                    <div class="text-white p-3 text-center inline-flex items-center justify-center w-12 h-12 mb-5 shadow-lg rounded-full bg-red-400">
                                        <i class="fas fa-child"></i>
                                    </div>
                                    <h6 class="text-xl font-semibold">KIDS</h6>
                                    <p class="mt-2 mb-4 text-gray-600">
                                        Kids will love the adventures that are just their size in this waterpark. 
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="w-full md:w-4/12 px-4 text-center">
                            <div class="relative flex flex-col min-w-0 break-words bg-white w-full mb-8 shadow-lg rounded-lg">
                                <div class="px-4 py-5 flex-auto">
                                    <div class="text-white p-3 text-center inline-flex items-center justify-center w-12 h-12 mb-5 shadow-lg rounded-full bg-blue-400">
                                        <i class="fas fa-globe"></i>
                                    </div>
                                    <h6 class="text-xl font-semibold">ADVENTURE</h6>
                                    <p class="mt-2 mb-4 text-gray-600">
                                        There are many adventures await you!
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="pt-6 w-full md:w-4/12 px-4 text-center">
                            <div class="relative flex flex-col min-w-0 break-words bg-white w-full mb-8 shadow-lg rounded-lg">
                                <div class="px-4 py-5 flex-auto">
                                    <div class="text-white p-3 text-center inline-flex items-center justify-center w-12 h-12 mb-5 shadow-lg rounded-full bg-green-400">
                                        <i class="fas fa-users"></i>
                                    </div>
                                    <h6 class="text-xl font-semibold">FAMILY</h6>
                                    <p class="mt-2 mb-4 text-gray-600">
                                        Rides are more fun when you get to experience them with friends and family.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section class="relative pb-32 bg-gray-300">
                <div class="bottom-auto top-0 left-0 right-0 w-full absolute pointer-events-none overflow-hidden -mt-20" style="height: 80px;">
                    <svg class="absolute bottom-0 overflow-hidden" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none" version="1.1" viewBox="0 0 2560 100" x="0" y="0">
                        <polygon class="text-gray-300 fill-current" points="2560 0 2560 100 0 100"></polygon>
                    </svg>
                </div>
                <%
                    FeaturesDAO featuresdao = new FeaturesDAOImpl();
                    List<Features> featureslist = featuresdao.retrieveAll();
                    for (Features feature : featureslist) {
                %>
                <div class="container mx-auto px-4">
                    <div class="items-center flex flex-wrap">
                        <div class="w-full md:w-4/12 ml-auto mr-auto px-4">
                            <img src="img/image.jsp?id=<%=feature.getFeaturesID()%>" alt="Features Image"
                                 class="max-w-full rounded-lg shadow-lg"/>
                        </div>
                        <div class="w-full md:w-5/12 ml-auto mr-auto px-4">
                            <div class="md:pr-12">
                                <div class="text-pink-600 p-3 text-center inline-flex items-center justify-center w-16 h-16 mb-6 shadow-lg rounded-full bg-pink-300">
                                    <i class="fas fa-rocket text-xl"></i>
                                </div>
                                <h3 class="text-3xl font-semibold"><%=feature.getFeaturesName()%></h3>
                                <p class="mt-4 text-lg leading-relaxed text-gray-600">
                                    <%=feature.getFeaturesDesc()%>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <br><br><br>
                <%
                    }
                %>
                <!--<div class="container mx-auto px-4">
                    <br><br><br>
                    <div class="container mx-auto px-4">
                        <div class="items-center flex flex-wrap">
                            <div class="w-full md:w-4/12 ml-auto mr-auto px-4">
                                <img alt="..." src="https://martinaquatic.com/wp-content/uploads/2018/09/IMG_4394-For-Web.jpg"
                                     class="max-w-full rounded-lg shadow-lg"/>
                            </div>
                            <div class="w-full md:w-5/12 ml-auto mr-auto px-4">
                                <div class="md:pr-12">
                                    <div class="text-pink-600 p-3 text-center inline-flex items-center justify-center w-16 h-16 mb-6 shadow-lg rounded-full bg-pink-300">
                                        <i class="fas fa-puzzle-piece text-xl"></i>
                                    </div>
                                    <h3 class="text-3xl font-semibold">Kids Pool</h3>
                                    <p class="mt-4 text-lg leading-relaxed text-gray-600">
                                        When the little ones are keen to take to the water, it is time 
                                        to let them make a splash at kids pool inspired water playground 
                                        with slides designed for the young.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>-->
            </section>
        </main>
        <footer class="relative bg-gray-800 pt-8 pb-6">
            <div class="bottom-auto top-0 left-0 right-0 w-full absolute pointer-events-none overflow-hidden -mt-20" style="height: 80px;">
                <svg class="absolute bottom-0 overflow-hidden" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none" version="1.1" viewBox="0 0 2560 100" x="0" y="0">
                    <polygon class="text-gray-800 fill-current" points="2560 0 2560 100 0 100"></polygon>
                </svg>
            </div>
            <div class="container mx-auto px-4">
                <div class="flex flex-wrap">
                    <div class="w-full lg:w-6/12 px-4">
                        <h4 class="text-3xl text-white font-semibold">Let's keep in touch!</h4>
                        <h5 class="text-lg mt-0 mb-2 text-white"> Find us on any of these platforms, we respond 1-2 business days. </h5>
                        <div class="mt-6">
                            <button class="text-blue-400 shadow-lg font-normal h-10 w-10 items-center justify-center align-center rounded-full outline-none focus:outline-none mr-2 p-3" type="button">
                                <i class="flex fab fa-twitter"></i>
                            </button>
                            <button class="text-blue-600 shadow-lg font-normal h-10 w-10 items-center justify-center align-center rounded-full outline-none focus:outline-none mr-2 p-3" type="button">
                                <i class="flex fab fa-facebook-square"></i>
                            </button>
                            <button class="text-pink-400 shadow-lg font-normal h-10 w-10 items-center justify-center align-center rounded-full outline-none focus:outline-none mr-2 p-3" type="button">
                                <i class="flex fab fa-dribbble"></i>
                            </button>
                            <button class="text-gray-900 shadow-lg font-normal h-10 w-10 items-center justify-center align-center rounded-full outline-none focus:outline-none mr-2 p-3" type="button">
                                <i class="flex fab fa-github"></i>
                            </button>
                                </div>
                    </div>
                    <div class="w-full lg:w-6/12 px-4">
                        <div class="flex flex-wrap items-top mb-6">
                            <div class="w-full lg:w-4/12 px-4 ml-auto">
                                <span class="block uppercase text-white text-sm font-bold mb-2">Useful Links</span>
                                <ul class="list-unstyled">
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="index.jsp">Home</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="GENERAL/Features.jsp">Attractions</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="GENERAL/Facilities.jsp">Facilities</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="GENERAL/TicketPrice.jsp">Ticket Price</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="w-full lg:w-4/12 px-4">
                                <span class="block uppercase text-white text-sm font-bold mb-2">Other Resources</span>
                                <ul class="list-unstyled">
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="GENERAL/ContactUs.jsp">Contact Us</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="GENERAL/AboutUs.jsp">About Us</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="GENERAL/RulesRegulations.jsp">Rules & Regulations</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="GENERAL/PrivacyPolicy.jsp">Privacy Policy</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <hr class="my-6 border-gray-400" />
                <div class="flex flex-wrap items-center md:justify-between justify-center">
                    <div class="w-full md:w-4/12 px-4 mx-auto text-center">
                        <div class="text-sm text-gray-600 font-semibold py-1"> Copyright � 2022 Waterpark Online Booking System by Dayang Atilia</div>
                    </div>
                </div>
            </div>
        </footer>
    </body>
    <script>
        function toggleNavbar(collapseID) {
            document.getElementById(collapseID).classList.toggle("hidden");
            document.getElementById(collapseID).classList.toggle("block");
        }
    </script>
</html>
