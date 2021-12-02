package se.kth.id1212.taxoptimization.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 *  Handles data related to the sessions and input.
 *
 *  @author William Axbrink
 *  @author Charlotta Bodén
 */
public class Session {
    String session_id;
    Time time;
    String location;
    List<Input> inputs;
    public Session(String session_id, Time time, String location){
        this.session_id = session_id;
        this.time = time;
        this.location = location;
    }

    private void SQLQueryGet(String query) throws Exception{
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
            inputs.add(new Input(input_id, start_capital, profit_capital,interest_rate, years));
        }
    }
}
