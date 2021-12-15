package se.kth.id1212.taxoptimization.data_access;

import se.kth.id1212.taxoptimization.model.Session;

import java.sql.*;

/**
 * @Author William Axbrink
 */
public class csndata {

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
