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
    public boolean userExists() throws SQLException, ClassNotFoundException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project" , SECRETS.DATABASEUSER,  SECRETS.DATABASEPASS);
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT email, password FROM User WHERE email='"+this.email+"';");
            rs.next();
            String password = rs.getString(2);
            System.out.println(password + " and " + this.password);
            if(password.equals(this.password)) {
                System.out.println("hello");
                return true;
            }
            else {
                System.out.println("hello2");

                return false;   //Email exists but password did not match
            }
        } catch (Exception e){
            System.out.println(e);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
                String query = " INSERT INTO User (email, name, password)" + " values (?,?,?)";
                PreparedStatement prep = con.prepareStatement(query);
                prep.setString(0, this.email);
                prep.setString(1, this.name);
                prep.setString(2, this.password);
                prep.execute();
                con.close();
            } catch(Exception e2){return false;}
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
