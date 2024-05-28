/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Account;
import models.Cart;
import models.Categories;
import models.Collections;
import models.Item;
import models.Order;
import models.OrderToManage;
import models.Product;
import models.SecurityQuestion;


public class DAO {

    Connection conn = null;        // keets noois sql
    PreparedStatement ps = null;  // nems cau lenh query
    ResultSet rs = null;    // keet qua tra ve

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7))
                );
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> get3NewProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select top (3) * from Product order by ProId desc";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7))
                );
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Categories> getAllCategories() {
        List<Categories> list = new ArrayList<>();
        String query = "select * from Categories";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Categories(rs.getInt(1),
                        rs.getString(2)
                )
                );
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Collections> getAllCollections() {
        List<Collections> list = new ArrayList<>();
        String query = "select * from Collections";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Collections(rs.getInt(1),
                        rs.getString(2)
                )
                );
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void addCollection(String coName) {
        String query = "INSERT [dbo].[Collections]([CoName]) values (?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, coName);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public int getNewCoId(){
        String query  ="select top (1) CoId from Collections order by CoId desc";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
              return rs.getInt(1);              
            }
        } catch (Exception e) {
        }
        return 1;     
    }
        

    public List<SecurityQuestion> getAllQuestion() {
        List<SecurityQuestion> list = new ArrayList<>();
        String query = "select * from SecurityQuestion";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SecurityQuestion(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProductBycaId(String caId) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where CaId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, caId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7))
                );
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProductBycoId(String coId) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where CoId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, coId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7))
                );
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProductByName(String name) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where ProName like ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7))
                );
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductById(String proId) {
        String query = "select * from Product where ProId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, proId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7)
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account login(String id, String pass) {
        String query = "select * from Account where AccId = ? and AccPass= ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8));
            }
        } catch (Exception e) {

        }
        return null;

    }

    public Account searchAccByID(String id) {
        String query = "select * from Account where AccId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8));
            }
        } catch (Exception e) {

        }
        return null;

    }

    public void addAccount(String accId, String accPass, String name, String address, String phone, int quesId, String answer) {
        String query = "insert into Account values(?,?,1,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, accId);
            ps.setString(2, accPass);
            ps.setString(3, name);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setInt(6, quesId);
            ps.setString(7, answer);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteProductById(String proId) {
        String query = "delete from OrderDetail where ProId = ?;"
                + "delete from Product where ProId = ? ";
        
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, proId);
            ps.setString(2, proId);
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void addProduct(String proName, String proImg, float proPrice, String proDetail, int caId, int coId) {
        String query = "INSERT [dbo].[Product]([ProName],[ProImg], [ProPrice], [ProDetail], [CaId], [CoId] ) values (?,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, proName);
            ps.setString(2, "img/" + proImg);
            ps.setFloat(3, proPrice);
            ps.setString(4, proDetail);
            ps.setInt(5, caId);
            ps.setInt(6, coId);
            rs = ps.executeQuery();
        } catch (Exception e) {

        }

    }
    
    
    public void updateProduct1(String proName, String proImg, float proPrice, String proDetail, int caId, int coId, int proId) {
        String query = "update Product set [ProName] = ?, [ProImg]= ? , [ProPrice] = ?, [ProDetail]= ?, [CaId]= ?, [CoId]= ? where ProId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, proName);
            ps.setString(2,  proImg);
            ps.setFloat(3, proPrice);
            ps.setString(4, proDetail);
            ps.setInt(5, caId);
            ps.setInt(6, coId);
            ps.setInt(7, proId);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }

    }
    
    public void updateProduct(String proName, String proImg, float proPrice, String proDetail, int caId, int coId, int proId) {
        String query = "update Product set [ProName] = ?, [ProImg]= ? , [ProPrice] = ?, [ProDetail]= ?, [CaId]= ?, [CoId]= ? where ProId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, proName);
            ps.setString(2, "img/"+ proImg);
            ps.setFloat(3, proPrice);
            ps.setString(4, proDetail);
            ps.setInt(5, caId);
            ps.setInt(6, coId);
            ps.setInt(7, proId);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }

    }

    public List<Account> getAllUser() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account where Role = 1";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    
    public void deleteOrder(String accId) {
        String query = "delete from Order where AccId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, accId);
            
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }
    public void deleteAccountById(String accId) {
        String query = "delete from Account where AccId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, accId);
            
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }
    

    public Account getAccount(String accId, String accPass) {
        String query = "select * from Account where AccId = ? and AccPass = ? ";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, accId);
            ps.setString(2, accPass);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8));
            }

        } catch (Exception e) {
        }
        return null;
    }

    public void AddOrder(Account acc, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            //add zo order
            String query = "INSERT [dbo].[Order]([OrDate],[AccId], [TotalMoney] ) values (?,?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, date);
            ps.setString(2, acc.getAccId());
            ps.setFloat(3, cart.getTotalMoney());
            ps.executeUpdate();

            //lay id cua order vua add
            String query2 = "select top 1 OrId from [Order] order by OrId desc";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query2);
            rs = ps.executeQuery();
            //add vao bang OrderDetail
            if (rs.next()) {
                int orId = rs.getInt(1);
                for (Item i : cart.getListItems()) {
                    String query3 = "INSERT [dbo].[OrderDetail]([OrId],[ProId], [Quantity], [Price]) values (?,?,?, ?)";
                    conn = new DBContext().getConnection();
                    ps = conn.prepareStatement(query3);
                    ps.setInt(1, orId);
                    ps.setInt(2, i.getProduct().getProId());
                    ps.setInt(3, i.getQuantity());
                    ps.setFloat(4, i.getPrice());
                    ps.executeUpdate();
                }
            }

        } catch (Exception e) {
        }
    }

    public List<OrderToManage> getListOrderToManage() {
        List<OrderToManage> list = new ArrayList<>();
        String query = "SELECT orr.OrId, orr.OrDate, o.ProId, p.ProName, o.Quantity,o.Price , acc.UserName, acc.UserAddress, acc.UserPhone ,orr.TotalMoney\n"
                + "FROM OrderDetail As o , Product AS p, Account as acc, [Order] as orr\n"
                + "WHERE o.ProId=p.ProId and orr.OrId = o.OrId and acc.AccId = orr.AccId order by orr.OrId desc";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderToManage(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10))
                );

            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<OrderToManage> getListOrderOfUser(String accId) {
        List<OrderToManage> list = new ArrayList<>();
        String query = "SELECT orr.OrId, orr.OrDate, o.ProId, p.ProName, o.Quantity,o.Price , acc.UserName, acc.UserAddress, acc.UserPhone ,orr.TotalMoney\n"
                + "FROM OrderDetail As o , Product AS p, Account as acc, [Order] as orr\n"
                + "WHERE o.ProId=p.ProId and orr.OrId = o.OrId and acc.AccId = orr.AccId and acc.AccId = ? order by orr.OrId desc";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, accId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderToManage(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10))
                );

            }
        } catch (Exception e) {
        }
        return list;
    }

    public void ChangeInfoInCartPage(String name, String phone, String address, String accId) {
        String query = "UPDATE Account\n"
                + "SET UserName = ?, UserPhone = ? , UserAddress = ? \n"
                + "WHERE AccId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setString(4, accId);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }

    }
    
    public void ChangeSQId(String accId ,int quesId, String answer) {
        String query = "UPDATE Account \n"
                + "SET QuesId = ?, Answer = ?"
                + " where AccId = ?";
              
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, quesId);
            ps.setString(2, answer);
            ps.setString(3, accId);
            
            rs = ps.executeQuery();
        } catch (Exception e) {
        }

    }
    
    public void ChangeInfoInUserPage(String name, String phone, String address, String accId) {
        String query = "UPDATE Account\n"
                + "SET UserName = ?, UserPhone = ? , UserAddress = ? \n"
                + "WHERE AccId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setString(4, accId);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }

    }

    public void updatePassByaccId(String accId, String accPass) {
        String query = "UPDATE Account\n"
                + "SET AccPass = ? \n"
                + "WHERE AccId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, accPass);
            ps.setString(2, accId);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }

    }
    
    public List<Order> getListOrderByAccid(String accId){
        String sql = "select * from Order where OrId=?";
        List<Order> list = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, accId);
            rs = ps.executeQuery();
            while (rs.next()) {
                 list.add(new Order(rs.getInt(1), 
                         rs.getString(2), 
                         rs.getString(3),
                 rs.getFloat(4)));
                
            }
        } catch (Exception e) {
        }
        return list;
        
    }
    
    public void deleteAOrderDetail(int orId){
        String sql = "delete from OrderDetail where OrId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orId);
            ps.executeUpdate(); 
        } catch (Exception e) {
        }
        
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();
        List<Categories> listc = dao.getAllCategories();
        List<OrderToManage> listp = dao.getListOrderOfUser("maytroi");
        List<SecurityQuestion> listq = dao.getAllQuestion();
        Account a = dao.login("vietanh", "123456");
//        System.out.println(a);
        for (OrderToManage o : listp) {
            System.out.println(o.getName());

        }
    }
}
