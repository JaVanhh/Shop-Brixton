/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dal.DAO;
import dal.PagingProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Categories;
import models.Product;


public class ShopAllControl extends HttpServlet {
   
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        PagingProductDAO paginDao = new PagingProductDAO();
        
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
       
        int index = Integer.parseInt(indexPage);
        if (index == 0) {
            index = 1;
        }
        
        int total = paginDao.getTotalAllProduct();
        int endPage = total/6;
        if(endPage%6!=0){
            endPage++;
            
        }
        
        if (index >= endPage) {
            index = endPage;              
        }
        
        request.setAttribute("endPage", endPage);
        
        List<Product> list = paginDao.pagingAllProduct(index);
        request.setAttribute("listProduct", list);
        
        request.setAttribute("indexNow", index);
        
        List<Categories> listca = dao.getAllCategories();
        request.setAttribute("listCategories", listca);
        request.setAttribute("none2", "none");
        request.setAttribute("none3", "none");
        request.getRequestDispatcher("shopAll.jsp").forward(request, response);
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
