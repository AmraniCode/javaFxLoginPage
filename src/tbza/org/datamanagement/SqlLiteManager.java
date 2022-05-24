/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbza.org.datamanagement;

import tbza.org.models.User;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Amine Amrani
 */
public class SqlLiteManager implements IDataManager {
    private final String connectionString ="jdbc:sqlite:C:\\Users\\PC-DEV-CRI\\Documents\\NetBeansProjects\\LoginPage\\sqllite\\db.db";
    private Connection connection = null;
    
    private Connection getConnection() {  
        // SQLite connection string  
        try {  
            connection = DriverManager.getConnection(connectionString);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return connection;  
    }  
    
    @Override
    public boolean connectToData(String connectionString) {
       Connection conn = null;
        try {
            // db parameters
            String url = this.connectionString;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            //Creating user table 
            String sql = "CREATE TABLE IF NOT EXISTS users (\n"  
                + " userId integer PRIMARY KEY AUTOINCREMENT,\n"  
                + " username text NOT NULL,\n"
                + " password text NOT NULL\n"
                + ");";
            
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();  
                    System.out.println("The driver name is " + meta.getDriverName());  
                    System.out.println("A new database has been created.");  
                    conn.close();
                    return true;
                }
                return false;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }
    }

    @Override
    public boolean checkUserExist(User user) {
        selectAll();
        return true;
    }

    @Override
    public User getUserInformation(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertUser(User user) {
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";  
   
        try{  
            Connection conn = this.getConnection();  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, user.getUserName());  
            pstmt.setString(2, user.getPassword());  
            pstmt.executeUpdate(); 
            
            return true;
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
            return false;
        }  
    }
    
    public void selectAll(){  
        String sql = "SELECT * FROM users";  
          
        try {  
            Connection conn = this.getConnection();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
              
            // loop through the result set  
            while (rs.next()) {  
                System.out.println(rs.getInt("userId") +  "\t" +   
                                   rs.getString("userName") + "\t" +  
                                   rs.getString("password"));  
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    
}
