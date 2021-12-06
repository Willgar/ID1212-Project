package se.kth.id1212.taxoptimization.model;

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
    String input_id = valueOf(rand.nextInt(10000));
    int start_capital;
    int profit_capital;
    int interest_rate;
    int years;
    int fund_account_capital = 0;
    int ISK_account_capital = 0;
    int account_difference;

    int total_loan; int estimated_years; int max_years; int average_years; int desired_payments;
    List<YearBasis> yearBasis = new ArrayList<>();
 /*   public Input(int start_capital, int profit_capital, int interest_rate, int years,int fund_account_capital, double yearly_value[][],boolean flag){
        this.start_capital = start_capital;
        this.profit_capital = profit_capital;
        this.interest_rate = interest_rate;
        this.years = years;
        this.fund_account_capital = fund_account_capital;
        if(this.ISK_account_capital != 0){
            this.account_difference = Math.abs(this.ISK_account_capital - this.fund_account_capital);
        }
    }
    public Input(int start_capital, int profit_capital, int interest_rate, int years, int ISK_account_capital){
        this.start_capital = start_capital;
        this.profit_capital = profit_capital;
        this.interest_rate = interest_rate;
        this.years = years;
        this.ISK_account_capital = ISK_account_capital;
        if(this.fund_account_capital != 0){
            this.account_difference = Math.abs(this.ISK_account_capital - this.fund_account_capital);
        }
    }*/
    public Input(int start_capital, int profit_capital, int interest_rate, int years, double yearly_value[][]){
        this.start_capital = start_capital;
        this.profit_capital = profit_capital;
        this.interest_rate = interest_rate;
        this.years = years;
        this.ISK_account_capital = (int)yearly_value[length(yearly_value)-1][0];
        this.fund_account_capital = (int)yearly_value[length(yearly_value)-1][1];
        this.account_difference = Math.abs(this.ISK_account_capital - this.fund_account_capital);

        for(int i = 0; i < years; i++){
            yearBasis.add(new YearBasis(i, (int)yearly_value[i][0], (int)yearly_value[i][1]));
        }
    }

    public Input(int total_loan, int estimated_years, int max_years, int average_years, int interest_rate, int desired_payments, int yearly_value[][]){
        this.total_loan = total_loan;
        this.estimated_years = estimated_years;
        this.max_years = max_years;
        this.average_years = average_years;
        this.interest_rate = interest_rate;
        this.desired_payments = desired_payments;
        for(int i = 0; i < years; i++){
            yearBasis.add(new YearBasis(i, yearly_value[i][0], yearly_value[i][1]));
        }

    }
    public double getValue(){
        return fund_account_capital;
    }
    public int[][] getYearlyCapital(){
        int[][] yearlyCapital = new int[yearBasis.size()][2];
        int i = 0;
        for (YearBasis n : yearBasis) {
            int[] value = n.getCapital();
            yearlyCapital[i][0] = value[0];
            yearlyCapital[i][1] = value[1];
            i++;
        }
        return yearlyCapital;
    }
}
