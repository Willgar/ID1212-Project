package se.kth.id1212.taxoptimization.model;

import se.kth.id1212.taxoptimization.data_access.DBHandler;

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

    /**
     * The bare minimum of the User object.
     * @param email The email of the user.
     * @param password The password of the user.
     * @throws Exception If something goes wrong.
     */
    public User(String email, String password) throws Exception {
        this.email = email;
        this.password = password;

    }

    /**
     * The full User object.
     * @param firstname The users first name.
     * @param password The users password.
     * @param email The users email.
     * @param lastname The users last name.
     * @param country The users country.
     * @param city The users city.
     * @param phone The users phone.
     * @param gender The users gender.
     * @param subscribe The users choice of subscription setting.
     * @throws Exception If something goes wrong.
     */
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
        String[] query = {email, firstname, lastname, password, country, city, Integer.toString(phone), gender, subscribe};
        try {
            DBHandler.insertUser(query);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("User already exists");
        }
        String id = valueOf(rand.nextInt(100000));
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        sessions.add(new Session(id, currentTime, city, email));
    }

    /**
     * Gets the value of the Fund account
     * To be adjusted or removed
     * @return Returns Fund Account Capital
     */
    public double getValue(){
        return this.sessions.get(sessions.size()-1).getValue();
    }

    /**
     * Gets two lists of the yearly capital of savings from ISK and Fund Account to compare them
     * @return Two lists.
     */
    public int[][] getYearlyCapital(){
        return this.sessions.get(sessions.size()-1).getYearlyCapital();
    }
    /**
     * Returns the two different payment rates, and two different investment rates.
     * @return Four lists.
     */
    public int[][] getYearlyCSNCapital() {
        return this.sessions.get(sessions.size() - 1).getYearlyCSNCapital();
    }

    /**
     * Creates a new input with the data
     * @param start_capital The start capital
     * @param profit_capital The profit capital
     * @param interest_rate The interest rate
     * @param years The years to be saved
     * @param yearly_value The yearly values
     */
    public void createInput(int start_capital, int profit_capital, int interest_rate, int years, double[][] yearly_value) throws Exception {
        this.sessions.get(sessions.size()-1).updateInput(start_capital, profit_capital, interest_rate, years,yearly_value);
    }

    /**
     * Creates a new CSN input
     * @param total_loan The total loan
     * @param interest_rate The interest rate
     * @param desired_payments How much over the minimum the user wants to pay
     */
    public void createCSNInput(int total_loan, int interest_rate, int desired_payments ) throws Exception {
        this.sessions.get(sessions.size()-1).updateCSNInput(total_loan, interest_rate, desired_payments);
    }

    /**
     * Compares to the database to see if the current user already exists.
     * @return Returns true if the user exists or the offline mode is started, else false.
     * @throws Exception Communication Failure is thrown if it can't connect and will let the user use in offline mode.
     */
    public boolean userExists() throws Exception {
        try {
            if (DBHandler.selectUser(this.email, this.password) != null) {
                String id = valueOf(rand.nextInt(100000));
                java.util.Date dt = new java.util.Date();
                java.text.SimpleDateFormat sdf =
                        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(dt);
                sessions.add(new Session(id, currentTime, "City", email));
                return true;
            } else {
                System.out.println("Returning false here");
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();

            return true;
        }
    }
    
}
