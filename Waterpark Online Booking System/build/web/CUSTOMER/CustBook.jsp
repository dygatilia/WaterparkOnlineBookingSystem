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
<%@page import="MODEL.BookingStaff"%>
<%@page import="DAOImpl.BookingStaffDAOImpl"%>
<%@page import="DAO.BookingStaffDAO"%>
<%@page import="MODEL.Booking"%>
<%@page import="DAOImpl.BookingDAOImpl"%>
<%@page import="DAO.BookingDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="MODEL.User"%>
<%@page import="MODEL.Payment"%>
<%@page import="DAOImpl.PaymentDAOImpl"%>
<%@page import="DAO.PaymentDAO"%>
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta name="theme-color" content="#000000"/>
        <link rel="shortcut icon" href="./assets/img/favicon.ico"/>
        <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/creativetimofficial/tailwind-starter-kit/compiled-tailwind.min.css"/>
        <link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.1/dist/flowbite.min.css"/>
        <link rel="icon" type="image/png" sizes="32x32" href="https://i.pinimg.com/originals/2a/0d/d7/2a0dd79cf1372716a4e3254e4861d9f3.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
        <link rel="preload" href="/fonts/Inter-roman.var.woff2?v=3.18" as="font" type="font/woff2" crossorigin="">
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tw-elements/dist/css/index.min.css" />
        <script src="https://cdn.tailwindcss.com"></script>
        <script>
            tailwind.config = {
                theme: {
                extend: {
                fontFamily: {
                    sans: ['Inter', 'sans-serif'],
                    },
                }
                }
            }
        </script>
        <title>Customer Book</title>
        
    </head>
    <body class="text-gray-800 antialiased">
        <nav class="top-0 absolute z-50 w-full flex flex-wrap items-center justify-between px-2 py-3 ">
            <div class="container px-4 mx-auto flex flex-wrap items-center justify-between">
                <div class="w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start">
                    <a class="text-sm font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase text-white" href="CustMainPage.jsp">
                        HOME
                    </a>
                </div>
                <%
                    User user = (User) request.getSession().getAttribute("user");
                %>
                <div class="lg:flex flex-grow items-center bg-white lg:bg-transparent lg:shadow-none hidden" id="example-collapse-navbar">
                    <ul class="flex flex-col lg:flex-row list-none lg:ml-auto">
                        <li class="nav-item">
                            <a class="px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500" href="CustFeatures.jsp">
                                <span class="ml-2"> Attractions </span>
                            </a>
                        </li>
                        <li class="nav-item">
                                <a class="download-button px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500"  href="CustFacilities.jsp" >
                                    <span class="ml-2"> Facilities </span>
                                </a>
                            </li>
                        <li class="nav-item">
                            <a class="px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500" href="CustTicketPrice.jsp">
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
                                <div class="hidden bg-white text-base z-50 float-left py-2 list-none text-left rounded shadow-lg mt-1" style="min-width: 12rem;" id="user-dropdown">
                                    <ul class="py-1 text-sm text-black dark:text-gray-900" aria-labelledby="dropdownLargeButton">
                                        <li>
                                            <a href="CustDashboard.jsp?id=<%=user.getId()%>" class="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Dashboard</a>
                                        </li>
                                        <li>
                                            <a href="CustSettings.jsp" class="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Settings</a>
                                        </li>
                                        <li>
                                            <a href="<%=request.getContextPath()%>/UserController?command=signout" class="block py-2 px-4 text-sm text-black hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-900 dark:hover:text-white">
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
        
        <main class="profile-page">
            <section class="absolute w-full h-full bg-gray-900">
                <div class="absolute container mx-auto px-4 h-full">
                    <div class="flex content-center items-center justify-center h-full">
                        <div class="w-full lg:w-6/12 px-4">
                            <div class="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded-lg bg-gray-300 border-0">
                                <div class="rounded-t mb-0 px-6 py-6">
                                    <div class="text-center mb-3">
                                        <h2 class="text-black text-2xl font-bold">
                                            TICKET FORM
                                        </h2>
                                    </div>
                                    <hr class="mt-6 border-b-1 border-gray-400" />
                                </div>
                                <div class="flex-auto px-4 lg:px-10 py-10 pt-0">
                                    <form action="<%=request.getContextPath()%>/BookingController" method="POST">
                                        <%
                                            BookingDAO bookingdaoo = new BookingDAOImpl();
                                            Booking bookingg = bookingdaoo.retrieveBookingByCustomerId(user.getId());
                                            
                                            DailyTicketDAO dailyticketdao = new DailyTicketDAOImpl();
                                            DailyTicket dailyticket = dailyticketdao.retrieveDailyTicket();
                                        %>
                                        <input type="hidden" name="remember" value="true">
                                        <div class="relative w-1/4 mb-3">
                                            <label class="block uppercase text-gray-700 text-sm font-bold mb-2" for="grid-password">Date</label>
                                            <input name="date" type="date" placeholder="Select date"
                                                class="required bg-gray-50 border border-gray-300 text-gray-700 sm:text-sm rounded-lg focus:ring-blue-500 shadow focus:outline-none focus:ring w-full focus:border-blue-500 block w-full p-2.5">
                                        </div>
                                        <div class="relative w-full mb-3">
                                            <label class="block uppercase text-gray-700 text-sm font-bold mb-2" for="grid-password">Full Name</label>
                                            <input type="text" name="name" class="required border-0 px-3 py-3 placeholder-gray-400 text-gray-700 bg-white rounded-lg text-sm shadow focus:outline-none focus:ring w-full"
                                                   placeholder="Full Name" style="transition: all 0.15s ease 0s;"/>
                                        </div>
                                        <div class="relative w-full mb-3">
                                            <label class="block uppercase text-gray-700 text-sm font-bold mb-2" for="grid-password">Contact Number</label>
                                            <input type="text" name="contactnum" class="required border-0 px-3 py-3 placeholder-gray-400 text-gray-700 bg-white rounded-lg text-sm shadow focus:outline-none focus:ring w-full"
                                                   placeholder="Your Phone Number" onkeypress="return onlyNumberKey(event)" style="transition: all 0.15s ease 0s;"/>
                                        </div>
                                        <div class="btn-wrapper text-center mt-10">
                                            <button class="bg-gray-800 active:bg-blue-100 text-white font-bold px-4 py-2 rounded outline-none focus:outline-none mr-2 mb-1 uppercase shadow hover:shadow-md inline-flex items-center font-bold text-xs"
                                                    type="reset" style="transition: all 0.15s ease 0s;">
                                                RESET
                                            </button>
                                            <input type="hidden" name="customerid" value="<%=user.getId()%>">
                                            <input type="hidden" name="bookingid" value="<%=bookingg.getBookingID()%>">
                                            <input type="hidden" name="total" value="<%=dailyticket.getDailyticketmax()%>">
                                            <button class="bg-gray-800 active:bg-blue-100 text-white font-bold px-4 py-2 rounded outline-none focus:outline-none mr-1 mb-1 uppercase shadow hover:shadow-md inline-flex items-center font-bold text-xs"
                                                    type="submit" name = "command" value = "saveBooking" style="transition: all 0.15s ease 0s;">
                                                NEXT
                                            </button>
                                        </div>
                                        <div class="text-center text-xs mt-5">
                                            <span style="color:red">${errorticExceed}</span>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <footer class="absolute w-full bottom-0 bg-gray-900 pb-6">
                    <div class="container mx-auto px-4">
                        <hr class="mb-6 border-b-1 border-gray-700" />
                        <div class="flex flex-wrap items-center md:justify-between justify-center">
                            <div class="w-full md:w-4/12 px-4">
                                <div class="text-sm text-white font-semibold py-1"> 
                                    Copyright © 2022
                                    <a href="" class="text-white hover:text-gray-400 text-sm font-semibold py-1">
                                        Dayang Atilia
                                    </a>
                                </div>
                            </div>
                            <div class="w-full md:w-8/12 px-4">
                                <ul class="flex flex-wrap list-none md:justify-end  justify-center">
                                    <li>
                                        <a href="CustContactUs.jsp" class="text-white hover:text-gray-400 text-sm font-semibold block py-1 px-3">
                                            Contact Us
                                        </a>
                                    </li>
                                    <li>
                                        <a href="CustAboutUs.jsp" class="text-white hover:text-gray-400 text-sm font-semibold block py-1 px-3">
                                            About Us
                                        </a>
                                    </li>
                                    <li>
                                        <a href="CustRulesRegulations.jsp" class="text-white hover:text-gray-400 text-sm font-semibold block py-1 px-3">
                                            Rules & Regulations
                                        </a>
                                    </li>
                                    <li>
                                        <a href="CustPrivacyPolicy.jsp" class="text-white hover:text-gray-400 text-sm font-semibold block py-1 px-3">
                                            Privacy Policy
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </footer>
            </section>
        </main>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" charset="utf-8"></script>
        <script src="https://unpkg.com/@popperjs/core@2.9.1/dist/umd/popper.min.js" charset="utf-8"></script>
        <script src="https://unpkg.com/flowbite@1.4.1/dist/flowbite.js"></script>
        <script src="https://unpkg.com/flowbite@1.4.1/dist/datepicker.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/tw-elements/dist/js/index.min.js"></script>
        <script>
            function onlyNumberKey(evt) {
          
                // Only ASCII character in that range allowed
                var ASCIICode = (evt.which) ? evt.which : evt.keyCode
                if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
                    return false;
                return true;
            }
            
            var today = new Date().toISOString().split('T')[0];
            document.getElementsByName("date")[0].setAttribute('min', today);
        </script>
        <script>
            function checkTicketOnDate(date){
                if 
            }
        </script>
        <script type="text/javascript">
            import 'tw-elements';
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

            const datepickerEl = document.getElementById('datepickerId');
            new Datepicker(datepickerEl, {
            // options
            }); 
            
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
    <script>
        function toggleNavbar(collapseID) {
            document.getElementById(collapseID).classList.toggle("hidden");
            document.getElementById(collapseID).classList.toggle("block");
        }
    </script>
</html>