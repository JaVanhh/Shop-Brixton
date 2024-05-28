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
import java.util.ArrayList;
import java.util.List;
import models.Account;
import models.Order;


public class DeleteAccountControl extends HttpServlet {
   
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        
    } 

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account acc = (Account)session.getAttribute("account");
        if(acc.getRole()==0){
            String accId = request.getParameter("accId");
        DAO dao = new DAO();
            List<Order> list = new ArrayList<>();
            
        list = dao.getListOrderByAccid(accId);
            for (int i = 0; i < list.size(); i++) {
                
                dao.deleteAOrderDetail(list.get(i).getOrId());
            }
        
//       
        dao.deleteOrder(accId);
        dao.deleteAccountById(accId);
        response.sendRedirect("usermanage");
        }else{
            response.sendRedirect("error404.jsp");
        }
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
