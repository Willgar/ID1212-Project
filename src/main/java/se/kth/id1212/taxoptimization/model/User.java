package se.kth.id1212.taxoptimization.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.String.valueOf;

/**
 *  Handles data related to the user info and sessions.
 *
 *  @author William Axbrink
 *  @author Charlotta Bodén
 */
public class User {
    String email;
    String firstname;
    String password;
    String lastname;
    String country;
    String city;
    int phone;
    String gender;
    String subscribe;
    List<Session> sessions = new ArrayList<>();
    Random rand = new Random();
    public User(String email, String password) throws SQLException, ClassNotFoundException {
        this.email = email;
        this.password = password;
        String id = valueOf(rand.nextInt(100000));
        sessions.add(new Session(id, valueOf(LocalDateTime.now()), "City"));
    }
    public User(String firstname, String password, String email, String lastname, String country, String city, int phone, String gender, String subscribe) throws Exception {
        this.email = email;
        this.firstname = firstname;
        this.password = password;
        this.lastname = lastname;
        this.country = country;
        this.city = city;
        this.phone = phone;
        this.gender = gender;
        this.subscribe = subscribe;
    }
    public void updateUser(String firstname, String password, String email, String lastname, String country, String city, int phone, String gender, String subscribe) throws Exception {
        this.email = email;
        this.firstname = firstname;
        this.password = password;
        this.lastname = lastname;
        this.country = country;
        this.city = city;
        this.phone = phone;
        this.gender = gender;
        this.subscribe = subscribe;
    }
    public double getValue(){
        return this.sessions.get(sessions.size()-1).getValue();
    }
    public int[][] getYearlyCapital(){
        return this.sessions.get(sessions.size()-1).getYearlyCapital();
    }
    public void createInput(int start_capital, int profit_capital, int interest_rate, int years, double yearly_value[][]){
        this.sessions.get(sessions.size()-1).updateInput(start_capital, profit_capital, interest_rate, years,yearly_value);
    }
    public void createCSNInput(int total_loan, int interest_rate, int desired_payments ){
        this.sessions.get(sessions.size()-1).updateCSNInput(total_loan, interest_rate, desired_payments);
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
            try { //Redo when DB is finished
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
                String query = " INSERT INTO User (email, name, password)" + " values (?,?,?)";
                PreparedStatement prep = con.prepareStatement(query);
                prep.setString(0, this.email);
                prep.setString(1, this.firstname);
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
            String time = rs.getString(2);
            String location = rs.getString(3);
            sessions.add(new Session(session_id, time, location));
        }
        con.close();
    }
}
