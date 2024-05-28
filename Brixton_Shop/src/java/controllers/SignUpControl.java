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
import java.util.List;
import models.SecurityQuestion;


public class SignUpControl extends HttpServlet {
   
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        DAO dao = new DAO();
        String accId = request.getParameter("id");
        String accPass = request.getParameter("psw");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String accPassRepeat = request.getParameter("pswrepeat");
        int quesId = Integer.parseInt(request.getParameter("quesId"));
        String answer = request.getParameter("answer");
        if (!accPass.equals(accPassRepeat)) {
            request.setAttribute("messErrorPass", "Password and Re-Password must be the same");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }else{
            if (dao.searchAccByID(accId)== null) {
                dao.addAccount(accId, accPass, name,address ,phone,quesId,answer );
                request.getRequestDispatcher("login").forward(request, response);
            }else{
                request.setAttribute("messErrorID", "Username is exist");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
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
        DAO dao = new DAO();
        List<SecurityQuestion> list = dao.getAllQuestion();
        request.setAttribute("listQues", list);
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
