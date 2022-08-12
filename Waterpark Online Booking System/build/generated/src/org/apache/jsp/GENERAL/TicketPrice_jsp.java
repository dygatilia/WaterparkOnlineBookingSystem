package org.apache.jsp.GENERAL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import MODEL.Promotions;
import DAOImpl.PromotionsDAOImpl;
import java.util.List;
import DAO.PromotionsDAO;

public final class TicketPrice_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\"/>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>\n");
      out.write("        <meta name=\"theme-color\" content=\"#000000\"/>\n");
      out.write("        <link rel=\"shortcut icon\" href=\"./assets/img/favicon.ico\" />\n");
      out.write("        <link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"./assets/img/apple-icon.png\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/gh/creativetimofficial/tailwind-starter-kit/compiled-tailwind.min.css\"/>\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" sizes=\"32x32\" href=\"https://i.pinimg.com/originals/2a/0d/d7/2a0dd79cf1372716a4e3254e4861d9f3.png\">\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"/favicon-16x16.png\">\n");
      out.write("        <link rel=\"preload\" href=\"/fonts/Inter-roman.var.woff2?v=3.18\" as=\"font\" type=\"font/woff2\" crossorigin=\"\">\n");
      out.write("        <link href=\"https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css\" rel=\"stylesheet\">\n");
      out.write("        <title>Ticket Price</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header class=\"flex-none relative z-50 text-sm leading-6 font-medium text-gray-800 antialiased\">\n");
      out.write("            <nav class=\"z-50 w-full bg-white top-0 flex flex-wrap items-center justify-between px-2 py-3 shadow-lg\">\n");
      out.write("            <div class=\"container px-4 mx-auto flex flex-wrap items-center justify-between\">\n");
      out.write("                <div class=\"w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start\">\n");
      out.write("                    <a href=\"MainPage.jsp\" class=\"text-sm font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase text-blueGray-700\">\n");
      out.write("                        HOME\n");
      out.write("                    </a>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"lg:flex flex-grow items-center hidden\" id=\"example-navbar-danger\">\n");
      out.write("                    <ul class=\"flex flex-col lg:flex-row list-none lg:ml-auto\">\n");
      out.write("                        <li class=\"nav-item\">\n");
      out.write("                            <a class=\"px-3 py-2 flex items-center text-xs uppercase font-bold text-blueGray-700 hover:text-blueGray-500\" href=\"Features.jsp\">\n");
      out.write("                                <span class=\"ml-2\"> Features </span>\n");
      out.write("                            </a>\n");
      out.write("                        </li>\n");
      out.write("                        <li class=\"nav-item\">\n");
      out.write("                                <a class=\"download-button px-3 py-2 flex items-center text-xs uppercase font-bold text-blueGray-700 hover:text-blueGray-500\"  href=\"Facilities.jsp\" >\n");
      out.write("                                    <span class=\"ml-2\"> Facilities </span>\n");
      out.write("                                </a>\n");
      out.write("                            </li>\n");
      out.write("                        <li class=\"nav-item\">\n");
      out.write("                            <a class=\"px-3 py-2 flex items-center text-xs uppercase font-bold text-blueGray-700 hover:text-blueGray-500\" href=\"Promotions.jsp\">\n");
      out.write("                                <span class=\"ml-2\"> Promotions </span>\n");
      out.write("                            </a>\n");
      out.write("                        </li>\n");
      out.write("                        <li class=\"flex items-center\">\n");
      out.write("                            <button class=\"bg-blue-300 text-gray-800 active:bg-gray-100 text-xs font-bold uppercase px-4 py-2 rounded shadow hover:shadow-md outline-none \n");
      out.write("                                    focus:outline-none lg:mr-1 lg:mb-0 ml-3 mb-3\" type=\"button\" style=\"transition: all 0.15s ease 0s;\">\n");
      out.write("                                <a href=\"LogIn.jsp\"> LOGIN </a>\n");
      out.write("                            </button>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            </nav>\n");
      out.write("        </header>\n");
      out.write("        <div class=\"overflow-hidden bg-gray-900 -mt-32 pt-32\">\n");
      out.write("            <div class=\"container mx-auto px-4 lg:pt-24\">\n");
      out.write("                <div class=\"flex flex-wrap text-center justify-center\">\n");
      out.write("                    <div class=\"w-full lg:w-6/12 px-4\">\n");
      out.write("                        <h2 class=\"text-3xl font-semibold text-white\">Promotions</h2>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"relative bg-gray-900 mt-3 px-4 sm:px-6 lg:px-10 pb-14 lg:pb-20 xl:pb-28\">\n");
      out.write("                <div class=\"box-border h-32 w-32 p-4 border-4 ...\">\n");
      out.write("                    test\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <footer class=\"relative bg-gray-800 pt-8 pb-6\">\n");
      out.write("            <div class=\"bottom-auto top-0 left-0 right-0 w-full absolute pointer-events-none overflow-hidden -mt-20\" style=\"height: 80px;\">\n");
      out.write("                <svg class=\"absolute bottom-0 overflow-hidden\" xmlns=\"http://www.w3.org/2000/svg\" preserveAspectRatio=\"none\" version=\"1.1\" viewBox=\"0 0 2560 100\" x=\"0\" y=\"0\">\n");
      out.write("                    <polygon class=\"text-gray-800 fill-current\" points=\"2560 0 2560 100 0 100\"></polygon>\n");
      out.write("                </svg>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"container mx-auto px-4\">\n");
      out.write("                <div class=\"flex flex-wrap\">\n");
      out.write("                    <div class=\"w-full lg:w-6/12 px-4\">\n");
      out.write("                        <h4 class=\"text-3xl text-white font-semibold\">Let's keep in touch!</h4>\n");
      out.write("                        <h5 class=\"text-lg mt-0 mb-2 text-white\"> Find us on any of these platforms, we respond 1-2 business days. </h5>\n");
      out.write("                        <div class=\"mt-6\">\n");
      out.write("                            <button class=\"text-blue-400 shadow-lg font-normal h-10 w-10 items-center justify-center align-center rounded-full outline-none focus:outline-none mr-2 p-3\" type=\"button\">\n");
      out.write("                                <i class=\"flex fab fa-twitter\"></i>\n");
      out.write("                            </button>\n");
      out.write("                            <button class=\"text-blue-600 shadow-lg font-normal h-10 w-10 items-center justify-center align-center rounded-full outline-none focus:outline-none mr-2 p-3\" type=\"button\">\n");
      out.write("                                <i class=\"flex fab fa-facebook-square\"></i>\n");
      out.write("                            </button>\n");
      out.write("                            <button class=\"text-pink-400 shadow-lg font-normal h-10 w-10 items-center justify-center align-center rounded-full outline-none focus:outline-none mr-2 p-3\" type=\"button\">\n");
      out.write("                                <i class=\"flex fab fa-dribbble\"></i>\n");
      out.write("                            </button>\n");
      out.write("                            <button class=\"text-gray-900 shadow-lg font-normal h-10 w-10 items-center justify-center align-center rounded-full outline-none focus:outline-none mr-2 p-3\" type=\"button\">\n");
      out.write("                                <i class=\"flex fab fa-github\"></i>\n");
      out.write("                            </button>\n");
      out.write("                                </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"w-full lg:w-6/12 px-4\">\n");
      out.write("                        <div class=\"flex flex-wrap items-top mb-6\">\n");
      out.write("                            <div class=\"w-full lg:w-4/12 px-4 ml-auto\">\n");
      out.write("                                <span class=\"block uppercase text-white text-sm font-bold mb-2\">Useful Links</span>\n");
      out.write("                                <ul class=\"list-unstyled\">\n");
      out.write("                                    <li>\n");
      out.write("                                        <a class=\"text-white font-semibold block pb-2 text-sm\" href=\"MainPage.jsp\">Home</a>\n");
      out.write("                                    </li>\n");
      out.write("                                    <li>\n");
      out.write("                                        <a class=\"text-white font-semibold block pb-2 text-sm\" href=\"Features.jsp\">Features</a>\n");
      out.write("                                    </li>\n");
      out.write("                                    <li>\n");
      out.write("                                        <a class=\"text-white font-semibold block pb-2 text-sm\" href=\"Facilities.jsp\">Facilities</a>\n");
      out.write("                                    </li>\n");
      out.write("                                    <li>\n");
      out.write("                                        <a class=\"text-white font-semibold block pb-2 text-sm\" href=\"Promotions.jsp\">Promotions</a>\n");
      out.write("                                    </li>\n");
      out.write("                                </ul>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"w-full lg:w-4/12 px-4\">\n");
      out.write("                                <span class=\"block uppercase text-white text-sm font-bold mb-2\">Other Resources</span>\n");
      out.write("                                <ul class=\"list-unstyled\">\n");
      out.write("                                    <li>\n");
      out.write("                                        <a class=\"text-white font-semibold block pb-2 text-sm\" href=\"ContactUs.jsp\">Contact Us</a>\n");
      out.write("                                    </li>\n");
      out.write("                                    <li>\n");
      out.write("                                        <a class=\"text-white font-semibold block pb-2 text-sm\" href=\"AboutUs.jsp\">About Us</a>\n");
      out.write("                                    </li>\n");
      out.write("                                    <li>\n");
      out.write("                                        <a class=\"text-white font-semibold block pb-2 text-sm\" href=\"RulesRegulations.jsp\">Rules & Regulations</a>\n");
      out.write("                                    </li>\n");
      out.write("                                    <li>\n");
      out.write("                                        <a class=\"text-white font-semibold block pb-2 text-sm\" href=\"PrivacyPolicy.jsp\">Privacy Policy</a>\n");
      out.write("                                    </li>\n");
      out.write("                                </ul>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <hr class=\"my-6 border-gray-400\" />\n");
      out.write("                <div class=\"flex flex-wrap items-center md:justify-between justify-center\">\n");
      out.write("                    <div class=\"w-full md:w-4/12 px-4 mx-auto text-center\">\n");
      out.write("                        <div class=\"text-sm text-gray-600 font-semibold py-1\"> Copyright © 2022 Waterpark Online Booking System by Dayang Atilia</div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("    </body>\n");
      out.write("</html>");
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
