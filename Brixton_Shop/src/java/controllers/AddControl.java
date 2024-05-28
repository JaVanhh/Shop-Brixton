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


public class AddControl extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Object o = session.getAttribute("account");
        Account acc = (Account) o;
        if (acc == null && acc.getRole()==1) {
            response.sendRedirect("error404.jsp");
        } else {
            String proName = request.getParameter("proName");
        String proImg = request.getParameter("proImg");
        float proPrice = Float.parseFloat(request.getParameter("proPrice"));
        String proDetail = request.getParameter("proDetail");
        int caId = Integer.parseInt(request.getParameter("caId"));
        int coId = Integer.parseInt(request.getParameter("coId"));
        if(proPrice>=0){
        DAO dao = new DAO();
        String newCollection = request.getParameter("addColection");
        
        if (newCollection == "") {
            dao.addProduct(proName, proImg, proPrice, proDetail, caId, coId);
        }else{
            dao.addCollection(newCollection);
            dao.addProduct(proName, proImg, proPrice, proDetail, caId, dao.getNewCoId()); 
        }
        
        response.sendRedirect("manage");
            
        }else{
            request.setAttribute("mess", "price must be >= 0");
            response.sendRedirect("manage");
        }
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
