/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.RrentcarUtil;
import pojo.Tblcarlist;

/**
 *
 * @author 62857
 */
public class DAOuser {

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/rrentcar2", "root", "");
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                return connection;
            }
        } catch (Exception e) {

        }

        return connection;
    }

    public ArrayList<Tblcarlist> getById() {
        ArrayList<Tblcarlist> list = new ArrayList();
        try {
            Connection connection = getConnection();
            FacesContext context = FacesContext.getCurrentInstance();
            String username = context.getExternalContext().getSessionMap().get("username").toString();
            Statement stmtBl = connection.createStatement();
            String query = "SELECT * FROM Tblcarlist WHERE username='" + username + "'";

            ResultSet rs = stmtBl.executeQuery(query);

            while (rs.next()) {
                list.add(
                        new Tblcarlist(rs.getInt("id"), rs.getString("username"), rs.getString("carType"), rs.getString("platNumber"), rs.getString("carColor"), rs.getString("domicile"))
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Tblcarlist searchData(int id) {
        Tblcarlist model = null;
        try {
            Connection connection = getConnection();

            Statement stmtBl = connection.createStatement();
            String query = "SELECT * FROM Tblcarlist WHERE id = " + id;

            ResultSet rs = stmtBl.executeQuery(query);

            while (rs.next()) {
                model = new Tblcarlist(rs.getInt("id"), rs.getString("username"), rs.getString("carType"), rs.getString("platNumber"), rs.getString("carColor"), rs.getString("domicile")
                );

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return model;
    }

    public void updateData(Integer id, String carType, String username, String platNumber, String carColor, String domicile) {

        try {
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("UPDATE Tblcarlist SET carType = ?, username = ?, platNumber=?,carColor=? ,domicile=? WHERE id = ?");
            stmt.setString(1, carType);
            stmt.setString(2, username);
            stmt.setString(3, platNumber);
            stmt.setString(4, carColor);
            stmt.setString(5, domicile);
            stmt.setString(6, Integer.toString(id));

            int result = stmt.executeUpdate();

            if (result > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public void delete(int id) {
        try {
            Connection connection = getConnection();

            PreparedStatement stmt = connection.prepareStatement("DELETE FROM tblcarlist WHERE id = ?");
            stmt.setString(1, Integer.toString(id));

            int result = stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
