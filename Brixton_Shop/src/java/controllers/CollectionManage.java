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
import models.Collections;
import models.Product;


public class CollectionManage extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        PagingProductDAO paginDao = new PagingProductDAO();

        List<Collections> list = dao.getAllCollections();
        request.setAttribute("listCollection", list);
        try {
            int coId = Integer.parseInt(request.getParameter("coId"));
            request.setAttribute("checkActive", coId);
            
            int total = paginDao.getTotalAllProductBycoId(coId);
            int endPage = total / 3;
            
            if (total%3 != 0 ) {
                endPage ++;
            }
            String indexPage = request.getParameter("indexCo");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            if (index <= 0) {
                index = 1;              
            }
            if (index >= endPage) {
                index = endPage;              
            }
            request.setAttribute("endPageCo", endPage);
 
            List<Product> listP = paginDao.pagingAllProductBycoId(index, coId );
            request.setAttribute("listProduct", listP);
            request.setAttribute("indexNowCo", index);
            request.setAttribute("coId", coId);
        } catch (NumberFormatException e) {
        }

        request.getRequestDispatcher("collection.jsp").forward(request, response);

//        List<Collections> list = dao.getAllCollections();
//        request.setAttribute("listCollection", list);
//        try {
//            int coId = Integer.parseInt(request.getParameter("coId"));
//             request.setAttribute("checkActive", coId);
//             List<Product> listP =dao.getAllProductBycoId(request.getParameter("coId"));
//             request.setAttribute("listProduct", listP);
//             
//        } catch (NumberFormatException e) {
//        }
//        
//        
//       
//        request.getRequestDispatcher("collection.jsp").forward(request, response);
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
