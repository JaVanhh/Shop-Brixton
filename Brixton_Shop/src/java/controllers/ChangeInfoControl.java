/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;


public class ChangeInfoControl extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangeInfoControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeInfoControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
//        HttpSession session = request.getSession();
//        DAO dao = new DAO();
//        Object o = session.getAttribute("account");
//        request.setAttribute("none1", "block");
//        Account acc = (Account) o;
//        Account acc2 = dao.getAccount(acc.getAccId(), acc.getAccPassword());
//        
//        request.setAttribute("userName", acc2.getName());
//        request.setAttribute("userPhone", acc2.getPhone());
//        request.setAttribute("userAddress", acc2.getAddress());
//        request.getRequestDispatcher("cart.jsp").forward(request, response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();

        String name = request.getParameter("userName");
        String phone = request.getParameter("userPhone");
        String address = request.getParameter("userAddress");
        String accId = request.getParameter("accId");
        String accPass = request.getParameter("accPass");
        dao.ChangeInfoInCartPage(name, phone, address, accId);
        HttpSession session = request.getSession();
        Account acc = dao.getAccount(accId, accPass);
        session.setAttribute("account", acc);
//        session.setAttribute("name", name);
//        session.setAttribute("phone", phone);
//        session.setAttribute("address", address);
//        request.setAttribute("block", "block");
        request.setAttribute("mess", "Change Info Success ");
        request.getRequestDispatcher("cart.jsp").forward(request, response);

    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
