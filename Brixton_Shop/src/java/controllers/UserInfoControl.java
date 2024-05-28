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
import java.util.List;
import models.Account;
import models.OrderToManage;


public class UserInfoControl extends HttpServlet {
   
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        DAO dao = new DAO();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc.getRole()==1) {
        List<OrderToManage> list2 = dao.getListOrderOfUser(request.getParameter("accId"));
        request.setAttribute("listOrderOfUser", list2);
        request.getRequestDispatcher("userInfo.jsp").forward(request, response);            
        }else{
        List<OrderToManage> list2 = dao.getListOrderOfUser(request.getParameter("accId"));
        request.setAttribute("listOrderOfUser", list2);
        request.setAttribute("noneok", "none");
        List<Account> list = dao.getAllUser();
        request.setAttribute("listUser", list);
        request.getRequestDispatcher("userManage.jsp").forward(request, response);
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
    }

    
}
