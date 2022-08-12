<%-- 
    Last Edit  : June 28, 2022, 11:05:00 PM
    Author     : Dayang Farhanim Atilia Binti Mohd Azaman
    Matric No. : S54168
--%>

<%@page import="MODEL.Tickets"%>
<%@page import="DAOImpl.TicketsDAOImpl"%>
<%@page import="DAO.TicketsDAO"%>
<%@page import="MODEL.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta name="theme-color" content="#000000"/>
        <link rel="shortcut icon" href="./assets/img/favicon.ico" />
        <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/creativetimofficial/tailwind-starter-kit/compiled-tailwind.min.css"/>
        <link rel="icon" type="image/png" sizes="32x32" href="https://i.pinimg.com/originals/2a/0d/d7/2a0dd79cf1372716a4e3254e4861d9f3.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
        <link rel="preload" href="/fonts/Inter-roman.var.woff2?v=3.18" as="font" type="font/woff2" crossorigin="">
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
        <title>Ticket Price</title>
    </head>
    <body>
        <header class="flex-none relative z-50 text-sm leading-6 font-medium text-gray-800 antialiased">
            <nav class="z-50 w-full bg-white top-0 flex flex-wrap items-center justify-between px-2 py-3 shadow-lg">
            <div class="container px-4 mx-auto flex flex-wrap items-center justify-between">
                <div class="w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start">
                    <a href="CustMainPage.jsp" class="text-sm font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase text-blueGray-700">
                        HOME
                    </a>
                </div>
                <%
                    User user = (User) request.getSession().getAttribute("user");
                %>
                <div class="lg:flex flex-grow items-center hidden" id="example-navbar-danger">
                    <ul class="flex flex-col lg:flex-row list-none lg:ml-auto">
                        <li class="nav-item">
                            <a class="px-3 py-2 flex items-center text-xs uppercase font-bold text-blueGray-700 hover:text-blueGray-500" href="CustFeatures.jsp">
                                <span class="ml-2"> Attractions </span>
                            </a>
                        </li>
                        <li class="nav-item">
                                <a class="download-button px-3 py-2 flex items-center text-xs uppercase font-bold text-blueGray-700 hover:text-blueGray-500"  href="CustFacilities.jsp" >
                                    <span class="ml-2"> Facilities </span>
                                </a>
                            </li>
                        <li class="nav-item">
                            <a class="px-3 py-2 flex items-center text-xs uppercase font-bold text-blueGray-700 hover:text-blueGray-500" href="CustTicketPrice.jsp">
                                <span class="ml-2"> Ticket Price </span>
                            </a>
                        </li>
                        <li class="flex items-center">
                            <button class="bg-blue-300 text-gray-800 active:bg-gray-100 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-md outline-none focus:outline-none lg:mr-1 lg:mb-0 ml-3 mb-3"
                                    type="button" style="transition: all 0.15s ease 0s;">
                                <a href="CustBook.jsp?id=<%=user.getId()%>"> BOOK TICKET </a>
                            </button>
                        </li>
                    </ul>
                    <ul>
                        <div class="w-full mx-autp items-center flex justify-between md:flex-nowrap flex-wrap md:px-6 px-4">
                            <ul class="flex-col md:flex-row list-none items-center hidden md:flex">
                                <a class="text-blueGray-500 block" href="#pablo" onclick="openDropdown(event,'user-dropdown')">
                                    <div class="items-center flex">
                                        <span class="w-9 h-9 text-sm text-white bg-blueGray-200 inline-flex items-center justify-center rounded-full">
                                            <img alt="..." class="w-full rounded-full align-middle border-none shadow-lg" 
                                                src="https://icons.veryicon.com/png/o/business/multi-color-financial-and-business-icons/user-139.png"/>
                                        </span>
                                    </div>
                                </a>
                                <div class="hidden bg-gray-100 text-base z-50 float-left py-2 list-none text-left rounded shadow-lg mt-1" style="min-width: 12rem;" id="user-dropdown">
                                    <ul class="py-1 text-sm text-gray-700 dark:text-gray-400" aria-labelledby="dropdownLargeButton">
                                        <li>
                                            <a href="CustDashboard.jsp?id=<%=user.getId()%>" class="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Dashboard</a>
                                        </li>
                                        <li>
                                            <a href="CustSettings.jsp" class="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Settings</a>
                                        </li>
                                        <li>
                                            <a href="<%=request.getContextPath()%>/UserController?command=signout" class="block py-2 px-4 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-400 dark:hover:text-white">
                                                <span class="font-semibold">Sign Out</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </ul>
                        </div>
                    </ul>
                </div>
            </div>
            </nav>
        </header>
        <div class="overflow-hidden bg-gray-900 -mt-32 pt-32">
            <div class="container mx-auto px-4 lg:pt-24">
                <div class="flex flex-wrap text-center justify-center">
                    <div class="w-full lg:w-6/12 px-4">
                        <h2 class="text-3xl font-semibold text-white">Ticket Price</h2>
                    </div>
                </div>
            </div>
            <div class="relative bg-gray-900 mt-3 mx-auto px-4 sm:px-6 lg:px-10 pt-10 pb-10 lg:pb-20 xl:pb-28">
                <%
                    TicketsDAO ticketsdao = new TicketsDAOImpl();
                    List<Tickets> ticketslist = ticketsdao.retrieveAll();
                    
                    for (Tickets tickets : ticketslist) {
                %>
                <div class="box-border items-center rounded-lg bg-blue-300 opacity-90 mx-auto my-auto h-30 w-96 p-4 border-2">
                    <h1 class="text-center font-extrabold text-4xl">RM <%=tickets.getPrice()%>0</h1>
                    <h1 class="text-center font-bold text-xl uppercase mt-3"><%=tickets.getTicketName()%></h1>
                </div>
                <br>
                <%
                    }
                %>
            </div>
        </div>
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
                                        <a class="text-white font-semibold block pb-2 text-sm" href="CustMainPage.jsp">Home</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="CustFeatures.jsp">Attractions</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="CustFacilities.jsp">Facilities</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="CustTicketPrice.jsp">Ticket Price</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="w-full lg:w-4/12 px-4">
                                <span class="block uppercase text-white text-sm font-bold mb-2">Other Resources</span>
                                <ul class="list-unstyled">
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="CustContactUs.jsp">Contact Us</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="CustAboutUs.jsp">About Us</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="CustRulesRegulations.jsp">Rules & Regulations</a>
                                    </li>
                                    <li>
                                        <a class="text-white font-semibold block pb-2 text-sm" href="CustPrivacyPolicy.jsp">Privacy Policy</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <hr class="my-6 border-gray-400" />
                <div class="flex flex-wrap items-center md:justify-between justify-center">
                    <div class="w-full md:w-4/12 px-4 mx-auto text-center">
                        <div class="text-sm text-gray-600 font-semibold py-1"> Copyright © 2022 Waterpark Online Booking System by Dayang Atilia</div>
                    </div>
                </div>
            </div>
        </footer>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" charset="utf-8"></script>
        <script src="https://unpkg.com/@popperjs/core@2.9.1/dist/umd/popper.min.js" charset="utf-8"></script>
        <script type="text/javascript">
            /* Sidebar - Side navigation menu on mobile/responsive mode */
            function toggleNavbar(collapseID) {
                document.getElementById(collapseID).classList.toggle("hidden");
                document.getElementById(collapseID).classList.toggle("bg-white");
                document.getElementById(collapseID).classList.toggle("m-2");
                document.getElementById(collapseID).classList.toggle("py-3");
                document.getElementById(collapseID).classList.toggle("px-6");
            }
            /* Function for dropdowns */
            function openDropdown(event, dropdownID) {
                let element = event.target;
                while (element.nodeName !== "A") {
                    element = element.parentNode;
                }
                var popper = Popper.createPopper(element, document.getElementById(dropdownID), {
                placement: "bottom-end"
                });
                document.getElementById(dropdownID).classList.toggle("hidden");
                document.getElementById(dropdownID).classList.toggle("block");
            }


            (function() {
                /* Add current date to the footer */
                document.getElementById("javascript-date").innerHTML = new Date().getFullYear();
                /* Chart initialisations */
                /* Line Chart */
                var config = {
                        type: "line",
                    data: {
                        labels: [
                            "January",
                            "February",
                            "March",
                            "April",
                            "May",
                            "June",
                            "July"
                        ],
                        datasets: [
                        {
                        label: new Date().getFullYear(),
                        backgroundColor: "#4c51bf",
                        borderColor: "#4c51bf",
                        data: [65, 78, 66, 44, 56, 67, 75],
                        fill: false
                        },
                        {
                        label: new Date().getFullYear() - 1,
                        fill: false,
                        backgroundColor: "#ed64a6",
                        borderColor: "#ed64a6",
                        data: [40, 68, 86, 74, 56, 60, 87]
                        }
                        ]
                        },
                    options: {
                        maintainAspectRatio: false,
                        responsive: true,
                    title: {
                        display: false,
                        text: "Sales Charts",
                        fontColor: "white"
                    },
                    legend: {
                        labels: {
                            fontColor: "white"
                        },
                        align: "end",
                        position: "bottom"
                    },
                    tooltips: {
                        mode: "index",
                        intersect: false
                    },
                    hover: {
                        mode: "nearest",
                        intersect: true
                    },
                    scales: {
                        xAxes: [
                    {
                    ticks: {
                        fontColor: "rgba(255,255,255,.7)"
                    },
                        display: true,
                    scaleLabel: {
                        display: false,
                        labelString: "Month",
                        fontColor: "white"
                    },
                    gridLines: {
                        display: false,
                        borderDash: [2],
                        borderDashOffset: [2],
                        color: "rgba(33, 37, 41, 0.3)",
                        zeroLineColor: "rgba(0, 0, 0, 0)",
                        zeroLineBorderDash: [2],
                        zeroLineBorderDashOffset: [2]
                    }
                    }
                    ],
                        yAxes: [
                    {
                    ticks: {
                        fontColor: "rgba(255,255,255,.7)"
                    },
                    display: true,
                    scaleLabel: {
                        display: false,
                        labelString: "Value",
                        fontColor: "white"
                    },
                    gridLines: {
                        borderDash: [3],
                        borderDashOffset: [3],
                        drawBorder: false,
                        color: "rgba(255, 255, 255, 0.15)",
                        zeroLineColor: "rgba(33, 37, 41, 0)",
                        zeroLineBorderDash: [2],
                        zeroLineBorderDashOffset: [2]
                    }
                    }
                    ]
                    }
                    }
                };
                var ctx = document.getElementById("line-chart").getContext("2d");
        window.myLine = new Chart(ctx, config);

        /* Bar Chart */
        config = {
          type: "bar",
          data: {
            labels: [
              "January",
              "February",
              "March",
              "April",
              "May",
              "June",
              "July"
            ],
            datasets: [
              {
                label: new Date().getFullYear(),
                backgroundColor: "#ed64a6",
                borderColor: "#ed64a6",
                data: [30, 78, 56, 34, 100, 45, 13],
                fill: false,
                barThickness: 8
              },
              {
                label: new Date().getFullYear() - 1,
                fill: false,
                backgroundColor: "#4c51bf",
                borderColor: "#4c51bf",
                data: [27, 68, 86, 74, 10, 4, 87],
                barThickness: 8
              }
            ]
          },
          options: {
            maintainAspectRatio: false,
            responsive: true,
            title: {
              display: false,
              text: "Orders Chart"
            },
            tooltips: {
              mode: "index",
              intersect: false
            },
            hover: {
              mode: "nearest",
              intersect: true
            },
            legend: {
              labels: {
                fontColor: "rgba(0,0,0,.4)"
              },
              align: "end",
              position: "bottom"
            },
            scales: {
              xAxes: [
                {
                  display: false,
                  scaleLabel: {
                    display: true,
                    labelString: "Month"
                  },
                  gridLines: {
                    borderDash: [2],
                    borderDashOffset: [2],
                    color: "rgba(33, 37, 41, 0.3)",
                    zeroLineColor: "rgba(33, 37, 41, 0.3)",
                    zeroLineBorderDash: [2],
                    zeroLineBorderDashOffset: [2]
                  }
                }
              ],
              yAxes: [
                {
                  display: true,
                  scaleLabel: {
                    display: false,
                    labelString: "Value"
                  },
                  gridLines: {
                    borderDash: [2],
                    drawBorder: false,
                    borderDashOffset: [2],
                    color: "rgba(33, 37, 41, 0.2)",
                    zeroLineColor: "rgba(33, 37, 41, 0.15)",
                    zeroLineBorderDash: [2],
                    zeroLineBorderDashOffset: [2]
                  }
                }
              ]
            }
          }
        };
            ctx = document.getElementById("bar-chart").getContext("2d");
            window.myBar = new Chart(ctx, config);
        })();
        </script>
    </body>
</html>