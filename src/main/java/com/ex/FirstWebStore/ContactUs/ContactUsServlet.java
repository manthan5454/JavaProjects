package com.ex.FirstWebStore.ContactUs;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "ContactUsServlet",urlPatterns = {"/contactList","/insertContact"})
public class ContactUsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ContactUsDao contactUsDao;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbc:mysql://localhost:3306/storedb?useSSL=false");
        String jdbcUsername = getServletContext().getInitParameter("root");
        String jdbcPassword = getServletContext().getInitParameter("mysql");
        contactUsDao = new ContactUsDao(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path =request.getServletPath();
        switch (path){
            case "/insertContact":
                try {
                    insertContact(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                System.out.println("Detail Parameter Is Wrong");
                break;
        }
    }

    private void insertContact(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String insertContactStatus = null;
        ContactUs contactIn = new ContactUs();
        PrintWriter out = response.getWriter();
        contactIn.setName(request.getParameter("name"));
        contactIn.setEmail(request.getParameter("email"));
        contactIn.setSubject(request.getParameter("subject"));
        contactIn.setMessage(request.getParameter("message"));
        insertContactStatus = contactUsDao.insertContactUs(contactIn);
        HttpSession session = request.getSession(true);
        if(MessageTypes.SUCCESS.toString().equals(insertContactStatus.toUpperCase())){
            session.setAttribute(MessageTypes.SUCCESS.toString(),"Successfully");
        }else{
            session.setAttribute(MessageTypes.ERROR.toString(),"Error");
        }
        request.setAttribute("insertContactStatus",insertContactStatus);
        request.getRequestDispatcher("contact.jsp").include(request, response);
    }

    private void getContactUsList(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<ContactUs> getContactUsList = contactUsDao.getContactArrayList();
            request.setAttribute("ContactUsArrayList",getContactUsList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("contactList.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}