package se.kth.id1212.taxoptimization.model;

import se.kth.id1212.taxoptimization.data_access.CSNData;

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
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        String id = valueOf(rand.nextInt(100000));
        sessions.add(new Session(id, valueOf(LocalDateTime.now()), "City"));
    }
    public User(String firstname, String password, String email, String lastname, String country, String city, int phone, String gender, String subscribe) {
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
    public void updateUser(String firstname, String password, String email, String lastname, String country, String city, int phone, String gender, String subscribe) {
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
    public void createInput(int start_capital, int profit_capital, int interest_rate, int years, double[][] yearly_value){
        this.sessions.get(sessions.size()-1).updateInput(start_capital, profit_capital, interest_rate, years,yearly_value);
    }

    /**
     * Creates a new CSN input
     * @param total_loan The total loan
     * @param interest_rate The interest rate
     * @param desired_payments How much over the minimum the user wants to pay
     */
    public void createCSNInput(int total_loan, int interest_rate, int desired_payments ){
        this.sessions.get(sessions.size()-1).updateCSNInput(total_loan, interest_rate, desired_payments);
    }

    public boolean userExists() throws Exception {
        try {
            if (CSNData.selectUser(this.email, this.password) != null) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
