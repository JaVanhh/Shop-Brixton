/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import models.Cart;


@WebServlet(name = "CheckOutControl", urlPatterns = {"/checkOut"})
public class CheckOutControl extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Cart cart = null;

        Object o = session.getAttribute("cart");

        if (o != null) {
            //cos roi
            cart = (Cart) o;

        } else {
            cart = new Cart();

        }
        Account account = null;
        Object user = session.getAttribute("account");
        if (user != null) {
            account = (Account) user;
            DAO dao = new DAO();
            dao.AddOrder(account, cart);
            session.removeAttribute("cart");
            session.setAttribute("sizeOfCart", 0);
            session.removeAttribute("total");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            
        }else{
            response.sendRedirect("login");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
