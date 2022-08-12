package org.apache.jsp.GENERAL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class LogIn_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\"/>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>\n");
      out.write("        <meta name=\"theme-color\" content=\"#000000\"/>\n");
      out.write("        <link rel=\"shortcut icon\" href=\"./assets/img/favicon.ico\"/>\n");
      out.write("        <link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"./assets/img/apple-icon.png\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/gh/creativetimofficial/tailwind-starter-kit/compiled-tailwind.min.css\"/>\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" sizes=\"32x32\" href=\"https://i.pinimg.com/originals/2a/0d/d7/2a0dd79cf1372716a4e3254e4861d9f3.png\">\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"/favicon-16x16.png\">\n");
      out.write("        <link rel=\"preload\" href=\"/fonts/Inter-roman.var.woff2?v=3.18\" as=\"font\" type=\"font/woff2\" crossorigin=\"\">\n");
      out.write("        <link href=\"https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css\" rel=\"stylesheet\">\n");
      out.write("        <title>Log In || Waterpark</title>\n");
      out.write("    </head>\n");
      out.write("    <body class=\"text-gray-800 antialiased\">\n");
      out.write("        <nav class=\"top-0 absolute z-50 w-full flex flex-wrap items-center justify-between px-2 py-3 \">\n");
      out.write("            <div class=\"container px-4 mx-auto flex flex-wrap items-center justify-between\">\n");
      out.write("                <div class=\"w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start\">\n");
      out.write("                    <a class=\"text-sm font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase text-white\" href=\"../index.jsp\">\n");
      out.write("                        HOME\n");
      out.write("                    </a>\n");
      out.write("         \n");
      out.write("                </div>\n");
      out.write("                <div class=\"lg:flex flex-grow items-center bg-white lg:bg-transparent lg:shadow-none hidden\" id=\"example-collapse-navbar\">\n");
      out.write("                    <ul class=\"flex flex-col lg:flex-row list-none lg:ml-auto\">\n");
      out.write("                        <li class=\"nav-item\">\n");
      out.write("                            <a class=\"px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500\" href=\"Features.jsp\">\n");
      out.write("                                <span class=\"ml-2\"> Attractions </span>\n");
      out.write("                            </a>\n");
      out.write("                        </li>\n");
      out.write("                        <li class=\"nav-item\">\n");
      out.write("                                <a class=\"download-button px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500\"  href=\"Facilities.jsp\" >\n");
      out.write("                                    <span class=\"ml-2\"> Facilities </span>\n");
      out.write("                                </a>\n");
      out.write("                            </li>\n");
      out.write("                        <li class=\"nav-item\">\n");
      out.write("                            <a class=\"px-3 py-2 flex items-center text-xs uppercase font-bold text-white hover:text-white-500\" href=\"TicketPrice.jsp\">\n");
      out.write("                                <span class=\"ml-2\"> Ticket Price </span>\n");
      out.write("                            </a>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("        <!-- component -->\n");
      out.write("        <div class=\"bg-gray-500 min-h-screen flex flex-col\">\n");
      out.write("            <div class=\"absolute top-0 w-full h-full bg-center bg-cover z-0\" style='background-image: url(\"https://imgcy.trivago.com/c_limit,d_dummy.jpeg,f_auto,h_1300,q_auto,w_2000/itemimages/55/55/55556_v5.jpeg\");'>\n");
      out.write("                <span id=\"blackOverlay\" class=\"w-full h-full absolute opacity-40 bg-black z-0\"></span>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"container max-w-sm mx-auto flex-1 flex flex-col items-center justify-center px-2\">\n");
      out.write("                <div class=\"bg-white px-6 py-8 rounded shadow-md text-black w-full z-20\">\n");
      out.write("                    <div>\n");
      out.write("                        <img class=\"mx-auto h-14 w-auto\" src=\"../img/Logo.png\" alt=\"waterpark\">\n");
      out.write("                        <h2 class=\"mt-6 text-center text-3xl font-extrabold text-gray-900\">\n");
      out.write("                            Sign into \n");
      out.write("                            <span class=\"font-extrabold text-indigo-600\">Account</span>\n");
      out.write("                        </h2>\n");
      out.write("                    </div>\n");
      out.write("                    <form class=\"mt-8 space-y-6\" action=\"");
      out.print(request.getContextPath());
      out.write("/UserController\" method=\"POST\">\n");
      out.write("                        <input type=\"hidden\" name=\"remember\" value=\"true\">\n");
      out.write("                        <div class=\"rounded-md shadow-sm -space-y-px\">\n");
      out.write("                            <div>\n");
      out.write("                                <label class=\"block uppercase text-gray-700 text-xs font-bold mb-2\" for=\"email-address\">Email Address</label>\n");
      out.write("                                <input id=\"email-address\" name=\"email\" type=\"email\" autocomplete=\"email\" required class=\"appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm\" placeholder=\"Email Address\">\n");
      out.write("                            </div>\n");
      out.write("                            <br>\n");
      out.write("                            <div>\n");
      out.write("                                <label class=\"block uppercase text-gray-700 text-xs font-bold mb-2\" for=\"password\">Password</label>\n");
      out.write("                                <input id=\"password\" name=\"password\" type=\"password\" autocomplete=\"current-password\" required class=\"appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm\" placeholder=\"Password\">\n");
      out.write("                            </div>\n");
      out.write("                        </div>            \n");
      out.write("                        <div class=\"flex items-center justify-between\">\n");
      out.write("                            <div class=\"flex items-center\">\n");
      out.write("                                <input id=\"remember_me\" name=\"remember_me\" type=\"checkbox\" class=\"h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded\">\n");
      out.write("                                <label for=\"remember_me\" class=\"ml-2 block text-sm text-gray-900\">\n");
      out.write("                                    Remember me\n");
      out.write("                                </label>\n");
      out.write("                            </div>\n");
      out.write("                    \n");
      out.write("                            <div class=\"text-sm\">\n");
      out.write("                                <a href=\"#\" class=\"font-medium text-indigo-600 hover:text-indigo-500\">\n");
      out.write("                                    Forgot your password?\n");
      out.write("                                </a>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div>\n");
      out.write("                            <button type=\"submit\" name = \"command\" value = \"signin\" class=\"group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500\">\n");
      out.write("                                <span class=\"absolute left-0 inset-y-0 flex items-center pl-3\">\n");
      out.write("                                    <!-- Heroicon name: solid/lock-closed -->\n");
      out.write("                                    <svg class=\"h-5 w-5 text-indigo-500 group-hover:text-indigo-400\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 20 20\" fill=\"currentColor\" aria-hidden=\"true\">\n");
      out.write("                                    <path fill-rule=\"evenodd\" d=\"M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z\" clip-rule=\"evenodd\" />\n");
      out.write("                                    </svg>\n");
      out.write("                                </span>\n");
      out.write("                                    Sign in\n");
      out.write("                            </button>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"text-center text-md\">\n");
      out.write("                            <span style=\"color:red\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${error}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\n");
      out.write("                        </div>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"text-white mt-2 z-20\">\n");
      out.write("                    Not yet have an account? \n");
      out.write("                    <a class=\"font-medium no-underline border-blue text-white hover:text-indigo-500\" href=\"SignUp.jsp\">\n");
      out.write("                        Create one for FREE \n");
      out.write("                    </a>!\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>       \n");
      out.write("    </body>    \n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
