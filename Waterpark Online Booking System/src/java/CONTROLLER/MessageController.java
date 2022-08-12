package CONTROLLER;

import DAO.MessageDAO;
import DAOImpl.MessageDAOImpl;
import MODEL.Message;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MessageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String cmd = request.getParameter("command");
        
        if (cmd != null){
            switch(cmd){
                case "send":
                    saveMessage(request, response);
                    break;
                    
                case "delete":
                    deleteMessage(request, response);
                    break;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "GENERAL/ContactUs.jsp");
        }
    }
    
    private void saveMessage(HttpServletRequest req, HttpServletResponse resp) 
            throws SQLException, ServletException, IOException{
        
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String subject = req.getParameter("subject");
        String description = req.getParameter("description");
        
        Message newMessage = new Message();
        
        newMessage.setMessageName(name);
        newMessage.setMessageEmail(email);
        newMessage.setMessageSubject(subject);
        newMessage.setMessageDescription(description);

        MessageDAO msg = new MessageDAOImpl();
        msg.save(newMessage);
        
        req.setAttribute("messageDetails", newMessage);
        resp.sendRedirect("GENERAL/ContactUs.jsp");
    }
    
    private void deleteMessage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("hidid"));
        
        Message message = new Message();
        
        message.setMessageID(id);
        
        MessageDAO messagedao = new MessageDAOImpl();
        
        messagedao.delete(message);

        HttpSession session = request.getSession();
        session.invalidate();
        
        response.sendRedirect("ADMIN/AdminMessage.jsp");
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
