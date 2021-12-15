package se.kth.id1212.taxoptimization.model;

import se.kth.id1212.taxoptimization.data_access.SECRETS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Handles data related to the sessions and input.
 *
 *  @author William Axbrink
 *  @author Charlotta Bodén
 */
public class Session {
    String session_id;
    String time;
    String location;
    List<Input> inputs = new ArrayList<>();
    public Session(String session_id, String time, String location){
        this.session_id = session_id;
        this.time = time;
        this.location = location;
    }
    public void updateInput(int start_capital, int profit_capital, int interest_rate, int years, double yearly_value[][]){
        this.inputs.add(new Input(start_capital, profit_capital, interest_rate, years, yearly_value));
    }
    public void updateCSNInput(int total_loan, int interest_rate, int desired_payments){

        double start = (total_loan*(0.04))/12;
        double other_start = (total_loan*(0.04))/12;
        int yearly_value[][] = new int[4][25];
        int sum = 0;
        double profit = 0;
        int years = 0;
        yearly_value[0][0] = (int)start*12;
        yearly_value[1][0] = (int)profit;
        while(sum < total_loan){
            years++;
            sum += start*12;
            start = start*1.02;
            profit = profit*(interest_rate/100.0f+1)+desired_payments*12;
            yearly_value[0][years] = (int)start*12;
            yearly_value[1][years] = (int)profit;
        }
        int other_years = 0;
        int other_sum = 0;
        yearly_value[2][0] = (int)(other_start+desired_payments)*12;
        yearly_value[3][0] = 0;
        while(other_sum < total_loan){
            other_years++;
            other_sum += (other_start+desired_payments)*12;
            other_start = other_start*1.02;
            yearly_value[2][other_years] = (int)(other_start+desired_payments)*12;
            yearly_value[3][other_years] = 0;

        }
        int i = other_years;
        double other_profit = 0;
        while(i < years){
            i++;
            other_profit = other_profit*(interest_rate/100.0f+1)+desired_payments*12+other_start*12;
            yearly_value[2][i] = 0;
            yearly_value[3][i] = (int)other_profit;
        }
        this.inputs.add(new Input(total_loan, other_years, 25, years, interest_rate, desired_payments, yearly_value));
    }

    /**
     * Returns the fund account value from the latest input.
     * To be adjusted or removed.
     * @return The fund account value.
     */
    public double getValue(){
        return this.inputs.get(inputs.size()-1).getValue();
    }

    /**
     * Gets two lists of the yearly capital of savings from ISK and Fund Account to compare them
     * @return Two lists.
     */
    public int[][] getYearlyCapital(){
        return this.inputs.get(inputs.size()-1).getYearlyCapital();
    }
    /**
     * Returns the two different payment rates, and two different investment rates.
     * @return Four lists.
     */
    public int[][] getYearlyCSNCapital(){
        return this.inputs.get(inputs.size()-1).getYearlyCSNCapital();
    }

}
