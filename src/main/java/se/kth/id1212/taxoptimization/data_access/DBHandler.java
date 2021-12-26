package se.kth.id1212.taxoptimization.data_access;

import se.kth.id1212.taxoptimization.model.Input;
import se.kth.id1212.taxoptimization.model.Session;
import se.kth.id1212.taxoptimization.model.User;

import java.sql.*;

/**
 * The Database Layer file, responsible for handling all interactions with the database.
 * @author William Axbrink
 */
public class DBHandler {
    /**
     * Inserts data into the database table User.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public static void insertUser(String[] query) throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
            String newquery = "INSERT INTO user (email, first_name, last_name, password, country, city, mobile_number, gender, subscribe_newsletter) values ('" + query[0] + "', '" + query[1] + "','" + query[2] + "','" + query[3] + "','" + query[4] + "','" + query[5] + "','" + query[6] + "','" + query[7] + "','" + (query[8] == "on" ? 1 : 0) + "');";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(newquery);
            con.close();
        }catch(Exception e){
            if(!e.toString().contains("Communications link failure"))
                e.printStackTrace();
        }
    }

    /**
     * Inserts data into the database table User.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public static void insertCSNInput(int[] query) throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String newquery = "INSERT INTO csn_input (input_id, total_loan, estimated_years, max_years, average_payment, interest_rate, desired_payment, session_id) values ('" + query[0] + "', '" + query[1] + "','" + query[2] + "','" + query[3] + "','" + query[4] + "','" + query[5] + "','" + query[6] + "','" + query[7] + "')";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(newquery);
            con.close();
        }catch(Exception e){
            if(!e.toString().contains("Communications link failure"))
                e.printStackTrace();
        }
    }

    /**
     * Inserts data into the database table Payments.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public static void insertPayments(int[] query) throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
            String newquery = " INSERT INTO payments (payment_id, yearly_average_minimum, yearly_profit, yearly_average_extra, yearly_profit_extra, capital_difference, years_from_start, input_id) values ('" + query[0] + "', '" + query[1] + "','" + query[2] + "','" + query[3] + "','" + query[4] + "','" + query[5] + "','" + query[6] + "','" + query[7] + "')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(newquery);
            con.close();
        }catch(Exception e){
            if(!e.toString().contains("Communications link failure"))
                e.printStackTrace();
        }
    }

    /**
     * Inserts data into the database table TaxSession.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public static void insertSession(String[] query) throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
            String newquery = "INSERT INTO session (session_id, time, location, browser, email) values ('" + Integer.parseInt(query[0]) + "', '" + query[1] + "','" + query[2] + "','" + query[3] + "','" + query[4] + "')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(newquery);
            con.close();
        }catch(Exception e){
            if(!e.toString().contains("Communications link failure"))
                e.printStackTrace();
        }
    }

    /**
     * Inserts data into the database table TaxInput.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public static void insertTaxInput(int[] query) throws Exception{
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
        String newquery = "INSERT INTO input (input_id, start_capital, profit_capital, interest_rate, fund_account_capital, isk_account_capital, account_difference, session_id) values ('"+query[0]+"', '"+query[1]+"','"+query[2]+"','"+query[3]+"','"+query[4]+"','"+query[5]+"','"+query[6]+"','"+query[7]+"')";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(newquery);
        con.close();
    }catch(Exception e){
            if(!e.toString().contains("Communications link failure"))
                e.printStackTrace();
        }
}

    /**
     * Inserts data into the database table YearBasis.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public static void insertYearBasis(int[] query) throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
            String newquery = "INSERT INTO year_basis (years_from_start, isk_account_capital, fund_account_capital, capital_difference, input_id) values ('" + query[0] + "', '" + query[1] + "','" + query[2] + "','" + query[3] + "','" + query[4] + "')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(newquery);
            con.close();
        }catch(Exception e){
            if(!e.toString().contains("Communications link failure"))
                e.printStackTrace();
        }
    }

    /**
     * Selects data from the table User based on the Primary Key email.
     * @param email The primary key which the data is gathered from.
     * @return Return all data in the table in form of a User object.
     * @throws Exception If something goes wrong.
     */
    public static User selectUser(String email, String password) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
            Statement stmt=con.createStatement();
            String query = "SELECT * FROM User WHERE email='"+email+"' AND password='"+ password+"';";
            ResultSet rs = stmt.executeQuery(query);
            User user = null;
            rs.next();
            String user_email = rs.getString(1);
            String user_password = rs.getString(4);
            try {
                user = new User(user_email, user_password);
            }catch(Exception e){
                System.out.println("Already exists");
            }
            return user;
        } catch(Exception e){
            if(e.toString().contains("Communications link failure")) {      //If the user can't connect to the database it will enter offline mode.
                System.out.println("Offline mode");
                return new User("temp", "temp");
            }
            e.printStackTrace();
            return null;
        }
    }

}
