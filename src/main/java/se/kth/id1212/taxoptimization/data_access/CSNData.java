package se.kth.id1212.taxoptimization.data_access;

import se.kth.id1212.taxoptimization.model.Input;
import se.kth.id1212.taxoptimization.model.Session;
import se.kth.id1212.taxoptimization.model.User;

import java.sql.*;

/**
 * @author William Axbrink
 */
public class CSNData {

    /**
     * TODO: SQL Insert queries for all tables
     * TODO: SQL Update queries for all tables
     * TODO: SQL Select queries for all tables
     * TODO: Connect DA Layer with model
     */

    /* Sample structure for Select Query
    private void name(String query) throws Exception{
        //Initialize the class
        Class.forName("com.mysql.jdbc.Driver");
        //Create connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
        //Create a statement with the connection to then send the query
        Statement stmt=con.createStatement();
        //Executes the query and gets the result in rs.
        ResultSet rs = stmt.executeQuery(query);
        //Foreach resultset
        //TODO: Standardize the input?, for example a loop that keeps adding each value until it hits an error/out of bounds and goes to next item
        while(rs.next()){
            String firstRow = rs.getString(1);
            int secondRow = rs.getInt(2);
            int thirdRow = rs.getInt(3);
            int fourthRow = rs.getInt(4);
            //add to the model or return the data
            //inputs.add(new Input(start_capital, profit_capital,interest_rate, years));
        }
    }
    */


    /* Sample structure for Insert Query
    //TODO: Standardize for different inserts
    private void name(String query[]) throws Exception{
        //Initialize the class
        Class.forName("com.mysql.jdbc.Driver");
        //Create connection
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
        String newquery = " INSERT INTO "+Table+" ("+query[0]+", "+query[1]+", "+query[2]+") values (? ? ?)";
        //String query = " INSERT INTO User (email, name, password)" + " values (?,?,?)";
        PreparedStatement prep = con.prepareStatement(newquery);
        prep.setString(0, query[0]);
        prep.setString(1, query[1]);
        prep.setString(2, query[2]);
        prep.execute();
        con.close();
    }

     */

    /**
     * Inserts data into the database table User.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public void insertUser(String[] query) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
        String newquery = " INSERT INTO user (email, first_name, last_name, password, country, city, mobile_number, gender, subscribe_newsletter) values (? ? ? ? ? ? ? ? ?)";
        PreparedStatement prep = con.prepareStatement(newquery);
        prep.setString(0, query[0]);
        prep.setString(1, query[1]);
        prep.setString(2, query[2]);
        prep.setString(3, query[3]);
        prep.setString(4, query[4]);
        prep.setString(5, query[5]);
        prep.setString(6, query[6]);
        prep.setString(7, query[7]);
        prep.setBoolean(8, Boolean.parseBoolean(query[8]));
        prep.execute();
        con.close();
    }
    /**
     * Inserts data into the database table User.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public void insertCSNInput(int[] query) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
        String newquery = " INSERT INTO csn_input (input_id, total_loan, estimated_years, max_years, average_payment, interest_rate, desired_payment, session_id) values (? ? ? ? ? ? ? ?)";
        PreparedStatement prep = con.prepareStatement(newquery);
        prep.setInt(0, query[0]);
        prep.setInt(1, query[1]);
        prep.setInt(2, query[2]);
        prep.setInt(3, query[3]);
        prep.setInt(4, query[4]);
        prep.setInt(5, query[5]);
        prep.setInt(6, query[6]);
        prep.setInt(7, query[7]);
        prep.execute();
        con.close();
    }
    /**
     * Inserts data into the database table Payments.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public void insertPayments(int[] query) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
        String newquery = " INSERT INTO payments (payment_id, yearly_average_minimum, yearly_profit, yearly_average_extra, yearly_profit_extra, capital_difference, years_from_start, input_id) values (? ? ? ? ? ? ? ?)";
        PreparedStatement prep = con.prepareStatement(newquery);
        prep.setInt(0, query[0]);
        prep.setInt(1, query[1]);
        prep.setInt(2, query[2]);
        prep.setInt(3, query[3]);
        prep.setInt(4, query[4]);
        prep.setInt(5, query[5]);
        prep.setInt(6, query[6]);
        prep.setInt(7, query[7]);
        prep.execute();
        con.close();
    }
    /**
     * Inserts data into the database table TaxSession.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public void insertTaxSession(String[] query) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
        String newquery = " INSERT INTO session (session_id, time, location, browser, email) values (? ? ? ? ?)";
        PreparedStatement prep = con.prepareStatement(newquery);
        prep.setInt(0, Integer.parseInt(query[0]));
        prep.setInt(1, Integer.parseInt(query[1]));
        prep.setString(2, query[2]);
        prep.setString(3, query[3]);
        prep.setString(4, query[4]);
        prep.execute();
        con.close();
    }
    /**
     * Inserts data into the database table TaxInput.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public void insertTaxInput(int[] query) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
        String newquery = " INSERT INTO input (input_id, start_capital, profit_capital, interest_rate, fund_account_capital, isk_account_capital, account_difference, session_id) values (? ? ? ? ? ? ? ?)";
        PreparedStatement prep = con.prepareStatement(newquery);
        prep.setInt(0, query[0]);
        prep.setInt(1, query[1]);
        prep.setInt(2, query[2]);
        prep.setInt(3, query[3]);
        prep.setInt(4, query[4]);
        prep.setInt(5, query[5]);
        prep.setInt(6, query[6]);
        prep.setInt(7, query[7]);
        prep.execute();
        con.close();
    }

    /**
     * Inserts data into the database table YearBasis.
     * @param query The data to be inserted.
     * @throws Exception If something goes wrong.
     */
    public void insertYearBasis(int[] query) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
        String newquery = " INSERT INTO year_basis (years_from_start, isk_account_capital, fund_account_capital, capital_difference, input_id) values (? ? ? ? ?)";
        PreparedStatement prep = con.prepareStatement(newquery);
        prep.setInt(0, query[0]);
        prep.setInt(1, query[1]);
        prep.setInt(2, query[2]);
        prep.setInt(3, query[3]);
        prep.setInt(4, query[4]);
        prep.execute();
        con.close();
    }

    /**
     * Selects data from the table User based on the Primary Key email.
     * @param email The primary key which the data is gathered from.
     * @return Return all data in the table in form of a User object.
     * @throws Exception If something goes wrong.
     */
    public static User selectUser(String email, String password) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
        Statement stmt=con.createStatement();
        String query = "SELECT * FROM User WHERE email='"+email+"' AND password='"+ password+"';";
        ResultSet rs = stmt.executeQuery(query);
        User user = null;
        try {
            rs.next();
            String user_email = rs.getString(1);
            String first_name = rs.getString(2);
            String last_name = rs.getString(3);
            String user_password = rs.getString(4);
            String country = rs.getString(5);
            String city = rs.getString(6);
            String mobile_number = rs.getString(7);
            String gender = rs.getString(8);
            String subscribe_newsletter = String.valueOf(rs.getBoolean(9));
            user = new User(first_name, user_password, user_email, last_name, country, city, Integer.parseInt(mobile_number), gender, subscribe_newsletter);
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
        return user;
    }
    /**
     * Selects data from the table CSNInput based on the Primary Key input_id.
     * @param id The primary key which the data is gathered from.
     * @return Return all data in the table in form of a User object.
     * @throws Exception If something goes wrong.
     */
    private Input selectCSNInput(String id) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", SECRETS.DATABASEUSER, SECRETS.DATABASEPASS);
        Statement stmt=con.createStatement();
        String query = "SELECT * FROM CSNInput WHERE input_id='"+id+"';";
        ResultSet rs = stmt.executeQuery(query);
        Input input = null;
        try {
            rs.next();
            int input_id = rs.getInt(1);
            int total_loan = rs.getInt(2);
            int estimated_years = rs.getInt(3);
            int max_years = rs.getInt(4);
            int average_payment = rs.getInt(5);
            int interest_rate = rs.getInt(6);
            int desired_payment = rs.getInt(7);
            input = new Input(total_loan, estimated_years, max_years, interest_rate, desired_payment);
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
        return input;
    }
    /*private void SQLQueryGet2(String query) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test" , SECRETS.DATABASEUSER,  SECRETS.DATABASEPASS);
        Statement stmt=con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            String input_id = rs.getString(1);
            int start_capital = rs.getInt(2);
            int profit_capital = rs.getInt(3);
            int interest_rate = rs.getInt(4);
            int years = rs.getInt(5);
            //inputs.add(new Input(start_capital, profit_capital,interest_rate, years));
        }
        con.close();
    }*/
    /**
     *  Compares the email and password combo with the database to see if the user already exists, and if not it
     *  creates a new user.
     * @return True if user exists, false if it did not.
     */
    /*public boolean userExists() throws SQLException, ClassNotFoundException {
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
    }*/
}
