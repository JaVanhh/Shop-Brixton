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
import models.Cart;
import models.Item;
import models.Product;


public class ProcessCartControl extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    } 

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = null;
        
        Object o  = session.getAttribute("cart");

        if (o!= null){
            //cos roi
            cart = (Cart)o;
            
        }else{
            cart = new Cart();
            
        }
        String numString = request.getParameter("num");
        String proIdString = request.getParameter("proId");
        int num, proId;
        try {
            num = Integer.parseInt(numString);
            proId =Integer.parseInt(proIdString);
            if((num==-1) &&(cart.getQuantityById(proId)<=1)){
                cart.removeItem(proId); 
            }else{
            DAO dao = new DAO();
            Product product = dao.getProductById(proIdString);
            float price = product.getProPrice();
            Item item = new Item(product, num, price);
            cart.addItem(item);
            }
            float totalMoney = cart.getTotalMoney();
            session.setAttribute("total", totalMoney);
        } catch (NumberFormatException e) {
            num = 1;
        }
        
        List<Item> list = cart.getListItems();
        session.setAttribute("cart", cart);
        session.setAttribute("sizeOfCart", list.size());
        
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = null;
        
        Object o  = session.getAttribute("cart");

        if (o!= null){
            //cos roi
            cart = (Cart)o;  
        }else{
            cart = new Cart();      
        }
        
        int proId = Integer.parseInt(request.getParameter("proId"));
        cart.removeItem(proId);
        float totalMoney = cart.getTotalMoney();
        session.setAttribute("total", totalMoney);

        List<Item> list = cart.getListItems();
        session.setAttribute("cart", cart);
        session.setAttribute("sizeOfCart", list.size());
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
