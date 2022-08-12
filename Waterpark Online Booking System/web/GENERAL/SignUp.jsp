<%-- 
    Last Edit  : June 28, 2022, 11:05:00 PM
    Author     : Dayang Farhanim Atilia Binti Mohd Azaman
    Matric No. : S54168
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Sign Up || Waterpark</title>
        
        <script>
            function validate(){
                var password = document.form.password.value;
                var repassword = document.form.repassword.value;
                
                if(password.length < 8){
                    alert("Password must be at least 8 characters long.");
                    return false;
                } else if (password != repassword){
                    alert("Confirm Password should match with the Password.");
                    return false;
                }
            }
        </script>
    </head>
    <body class="text-gray-800 antialiased">
        <nav class="top-0 absolute z-50 w-full flex flex-wrap items-center justify-between px-2 py-3 ">
            <div class="container px-4 mx-auto flex flex-wrap items-center justify-between">
                <div class="w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start">
                    <a class="text-sm font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase text-white" href="../index.jsp">
                        HOME
                    </a>
         
                </div>
                <div class="lg:flex flex-grow items-center bg-white lg:bg-transparent lg:shadow-none hidden" id="example-collapse-navbar">
                    <ul class="flex flex-col lg:flex-row list-none lg:ml-auto">
                        <li class="nav-item">
                            <a class="px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500" href="Features.jsp">
                                <span class="ml-2"> Attractions </span>
                            </a>
                        </li>
                        <li class="nav-item">
                                <a class="download-button px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500"  href="Facilities.jsp" >
                                    <span class="ml-2"> Facilities </span>
                                </a>
                            </li>
                        <li class="nav-item">
                            <a class="px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500" href="TicketPrice.jsp">
                                <span class="ml-2"> Ticket Price </span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- component -->
        <div class="bg-gray-800 min-h-screen flex flex-col"> <br><br>
            <div class="absolute top-0 w-full h-full bg-center bg-cover z-0" style='background-image: url("https://imgcy.trivago.com/c_limit,d_dummy.jpeg,f_auto,h_1300,q_auto,w_2000/itemimages/55/55/55556_v5.jpeg");'>
                <span id="blackOverlay" class="w-full h-full absolute opacity-40 bg-black z-0"></span>
            </div>
            <div class="container max-w-sm mx-auto flex-1 flex flex-col items-center justify-center px-1">
                <div class="bg-white px-6 py-3 rounded shadow-md text-black w-full z-20">
                    <div>
                        <img class="mx-auto h-14 w-auto" src="../img/Logo.png" alt="Waterpark">
                        <h2 class="mt-2 text-center text-3xl font-extrabold text-gray-900">
                            Sign up for 
                            <span class="font-extrabold text-indigo-600">Free</span>
                        </h2>
                    </div>
                    <form name = "form" class="mt-1 space-y-4" action="../UserController" method="POST" onsubmit = "return validate()">
                        <input type="hidden" name="remember" value="true">
                        <div class="rounded-md shadow-sm -space-y-px">
                            <div>
                                <label class="block uppercase text-gray-700 text-xs font-bold mb-1" for="name">Name</label>
                                <input id="name" name="name" type="text" required class="appearance-none rounded-none relative block w-full px-3 border border-gray-300 placeholder-gray-500 text-gray-900 rounded focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Full Name">
                            </div>
                            <br>
                            <div>
                                <label class="block uppercase text-gray-700 text-xs font-bold mb-1" for="email">Email Address</label>
                                <input id="email-address" name="email" type="email" autocomplete="email" required class="appearance-none rounded-none relative block w-full mb-5 px-3 border border-gray-300 placeholder-gray-500 text-gray-900 rounded focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Email Address">
                            </div>
                            <div class="mt-6">
                                <table>
                                    <tr>
                                        <td>
                                            <label class="uppercase text-gray-700 text-xs font-bold mt-5 mb-2 mr-1" for="password">Password</label>
                                        </td>
                                        <td>
                                            <i class="fa fa-info-circle w-2 h-2 text-blue-600 hover:text-blue-700 transition duration-150 ease-in-out mb-2" data-bs-toggle="tooltip" data-bs-placement="top" title="Your password must be at least 8 characters."></i>
                                        </td>
                                    </tr>
                                </table>
                                <input id="password" name="password" type="password" autocomplete="current-password" required class="appearance-none rounded-none relative block w-full mt-1 px-3 border border-gray-300 placeholder-gray-500 text-gray-900 rounded focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Password">
                            </div>
                            <div>
                                <label class="block uppercase text-gray-700 text-xs font-bold mt-5 mb-1" for="repassword">Confirm Password</label>
                                <input id="repassword" name="repassword" type="password" autocomplete="current-password" required class="appearance-none rounded-none relative block w-full px-3 border border-gray-300 placeholder-gray-500 text-gray-900 rounded focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Confirm Password">
                            </div>
                        </div>            
                        <div>
                            <button type="submit" name="command" value ="verify" class="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                                <span class="absolute left-0 inset-y-0 flex items-center pl-3">
                                    <!-- Heroicon name: solid/lock-closed -->
                                    <svg class="h-5 w-5 text-indigo-500 group-hover:text-indigo-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                                    <path fill-rule="evenodd" d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z" clip-rule="evenodd" />
                                    </svg>
                                </span>
                                    Sign up
                            </button>
                        </div>
                        <div class="text-center text-md">
                            <span style="color:red">${erroremail}</span>
                        </div>
                        <div>
                            <p class="mt-1 text-center text-sm text-gray-600">
                                Signing up signifies you've read and agree to our 
                            </p>
                            <p class="mt-1 text-center text-sm text-gray-600">
                                <a href="RulesRegulations.jsp" class="font-medium text-indigo-600 hover:text-indigo-500">
                                    Rules & Regulations
                                </a> 
                                and
                                <a href="PrivacyPolicy.jsp" class="font-medium text-indigo-600 hover:text-indigo-500">
                                    Privacy Policy
                                </a> 
                            </p>
                        </div>
                    </form>
                </div>

                <div class="text-white mt-4 z-20">
                    Already have an account? 
                    <a class="font-medium no-underline border-blue text-white hover:text-indigo-500" href="LogIn.jsp">
                        Log in
                    </a>
                </div>
            </div>
        </div>
        <script src="https://unpkg.com/@popperjs/core@2.9.1/dist/umd/popper.min.js" charset="utf-8"></script>
        <script type="text/javascript">
            var tooltipTriggerList = [].slice.call(
                document.querySelectorAll('[data-bs-toggle="tooltip"]')
            );
            var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
                return new Tooltip(tooltipTriggerEl);
            });
        </script>
    </body>    
</html>
