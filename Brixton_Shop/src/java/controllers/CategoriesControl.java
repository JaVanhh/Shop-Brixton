/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.DAO;
import dal.PagingProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Categories;
import models.Product;


public class CategoriesControl extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        PagingProductDAO paginDao = new PagingProductDAO();
        List<Categories> listca = dao.getAllCategories();
        request.setAttribute("listCategories", listca);

        String caIdPage = request.getParameter("caId");
        request.setAttribute("caId", caIdPage);
        
        try {
            int caid = Integer.parseInt(caIdPage);
            request.setAttribute("checkActive", caid);
            int total = paginDao.getTotalAllProductBycaId(caid);
            int endPage = total / 6;
            
            if (total%6 != 0 ) {
                endPage +=1;
            }
            String indexPage = request.getParameter("indexCa");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            if (index == 0) {
                index = 1;              
            }
            if (index >= endPage) {
                index = endPage;              
            }
            
            request.setAttribute("endPageCa", endPage);
            List<Product> listP = paginDao.pagingAllProductBycaId(index, caid);
            request.setAttribute("listProduct", listP);
             request.setAttribute("none1", "none");
             request.setAttribute("none", "block");
             request.setAttribute("none3", "none");
            request.setAttribute("indexNowCa", index);
            
        } catch (NumberFormatException e) {
            System.out.println("Loi");

        }
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
