/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import models.Cart;
import models.Item;
import models.Product;


public class AddToCartControl extends HttpServlet {
   
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
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
        int num;
        try {
            num = Integer.parseInt(numString); 
            DAO dao = new DAO();
            Product product = dao.getProductById(proIdString);
            float price = product.getProPrice();
            Item item = new Item(product, num, price);
            cart.addItem(item);
            session.setAttribute("total", cart.getTotalMoney());
        } catch (NumberFormatException e) {
            num = 1;
        }
        
        List<Item> list = cart.getListItems();
        
        session.setAttribute("cart", cart);
        session.setAttribute("sizeOfCart", list.size());
        request.setAttribute("sizeOfCart", list.size());
        
        request.getRequestDispatcher("shopall").forward(request, response);
        
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
