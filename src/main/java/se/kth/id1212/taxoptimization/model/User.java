package se.kth.id1212.taxoptimization.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 *  Handles data related to the user info and sessions.
 *
 *  @author William Axbrink
 *  @author Charlotta Bodén
 */
public class User {
    String email;
    String name;
    String password;
    List<Session> sessions;
    public User(String email, String name, String password) throws Exception {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    /**
     *  Compares the email and password combo with the database to see if the user already exists, and if not it
     *  creates a new user.
     * @return True if user exists, false if it did not.
     */
    public boolean userExists(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project" , SECRETS.DATABASEUSER,  SECRETS.DATABASEPASS);
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT email, password FROM User WHERE email='"+this.email+"' AND password='"+this.password +"';");
            if(rs.next()) {
                return true;
            }
            else {
                String query = " INSERT INTO User (email, name, password)" + " values (?,?,?)";
                PreparedStatement prep = con.prepareStatement(query);
                prep.setString(1, this.email);
                prep.setString(2, this.name);
                prep.setString(3, this.password);
                prep.execute();
                con.close();
                return false;
            }
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    private void SQLQueryGet(String query) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project" , SECRETS.DATABASEUSER,  SECRETS.DATABASEPASS);
        Statement stmt=con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            String session_id = rs.getString(1);
            Time time = rs.getTime(2);
            String location = rs.getString(3);
            sessions.add(new Session(session_id, time, location));
        }
        con.close();
    }
}
