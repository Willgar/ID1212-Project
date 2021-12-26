package se.kth.id1212.taxoptimization.model;

import se.kth.id1212.taxoptimization.data_access.DBHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.String.valueOf;
import static org.thymeleaf.util.ArrayUtils.length;

/**
 *  Handles data input and numbers.
 *
 *  @author William Axbrink
 *  @author Charlotta Bodén
 */

public class Input {
    Random rand = new Random();
    int input_id = rand.nextInt(100000);
    int start_capital;
    int profit_capital;
    int interest_rate;
    int years;
    int fund_account_capital = 0;
    int ISK_account_capital = 0;
    int account_difference;
    int session_id;

    int total_loan; int estimated_years; int max_years; int average_years; int desired_payments;
    List<YearBasis> yearBasis = new ArrayList<>();

    /**
     * The input from when the user uses the Tax Calculator function.
     * @param start_capital How much capital the fund started at
     * @param profit_capital How much profit the fund has.
     * @param interest_rate The expected rate of growth.
     * @param years How many years the user wants to see for.
     * @param yearly_value The yearly values.
     * @param session_id The ID of the session, relevant for the database.
     * @throws Exception If something goes wrong.
     */
    public Input(int start_capital, int profit_capital, int interest_rate, int years, double yearly_value[][], int session_id) throws Exception {
        this.start_capital = start_capital;
        this.profit_capital = profit_capital;
        this.interest_rate = interest_rate;
        this.years = years;
        this.ISK_account_capital = (int)yearly_value[length(yearly_value)-1][0];
        this.fund_account_capital = (int)yearly_value[length(yearly_value)-1][1];
        this.account_difference = Math.abs(this.ISK_account_capital - this.fund_account_capital);

        int[] query = {input_id, start_capital, profit_capital, interest_rate, fund_account_capital, ISK_account_capital, account_difference, session_id};
        try{
            DBHandler.insertTaxInput(query);
        }catch (Exception e){
            e.printStackTrace();
        }

        for(int i = 0; i < years; i++){
            yearBasis.add(new YearBasis(i, (int)yearly_value[0][i], (int)yearly_value[1][i], input_id));
        }

    }

    /**
     * The input for when the user uses the CSN calculator function.
     * @param total_loan The total loan
     * @param estimated_years How many years that is estimated using our calculator
     * @param max_years The maximum years CSN allows for payments.
     * @param average_years The average amount of years to pay of the CSN.
     * @param interest_rate The estimated rate of which the funds are expected to grow.
     * @param desired_payments How much above the minimum the user wishes to pay each month
     * @param yearly_value The yearly values.
     * @param session_id The ID of the session, relevant for the database.
     * @throws Exception If something goes wrong.
     */
    public Input(int total_loan, int estimated_years, int max_years, int average_years, int interest_rate, int desired_payments, int yearly_value[][], int session_id) throws Exception {
        this.total_loan = total_loan;
        this.estimated_years = estimated_years;
        this.max_years = max_years;
        this.average_years = average_years;
        this.interest_rate = interest_rate;
        this.desired_payments = desired_payments;
        this.session_id = session_id;

        int[] query = {input_id, total_loan, estimated_years, max_years, yearly_value[0][0], interest_rate, desired_payments, session_id};
        try{
            DBHandler.insertCSNInput(query);
        } catch (Exception e){
            e.printStackTrace();
        }

        for(int i = 0; i < max_years; i++){
            yearBasis.add(new YearBasis(i, yearly_value[0][i], yearly_value[1][i],yearly_value[2][i], yearly_value[3][i], input_id));
        }

    }

    /**
     * Gets the value of the Fund account
     * To be adjusted or removed
     * @return Returns Fund Account Capital
     */
    public double getValue(){
        return fund_account_capital;
    }

    /**
     * Gets two lists of the yearly capital of savings from ISK and Fund Account to compare them
     * @return Two lists.
     */
    public int[][] getYearlyCapital(){
        int[][] yearlyCapital = new int[2][yearBasis.size()];
        int i = 0;
        for (YearBasis n : yearBasis) {
            int[] value = n.getCapital();
            yearlyCapital[0][i] = value[0];
            yearlyCapital[1][i] = value[1];
            i++;
        }
        return yearlyCapital;
    }

    /**
     * Returns the two different payment rates, and two different investment rates.
     * @return Four lists.
     */
    public int[][] getYearlyCSNCapital(){
        int[][] yearlyCapital = new int[4][yearBasis.size()+1];
        int i = 0;
        for (YearBasis n : yearBasis) {
            int[] value = n.getCSNCapital();
            yearlyCapital[0][i] = value[0];
            yearlyCapital[1][i] = value[1];
            yearlyCapital[2][i] = value[2];
            yearlyCapital[3][i] = value[3];
            i++;
        }
        return yearlyCapital;
    }
}
