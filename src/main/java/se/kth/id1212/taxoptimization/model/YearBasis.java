package se.kth.id1212.taxoptimization.model;

import se.kth.id1212.taxoptimization.data_access.CSNData;

/**
 * A year by year object to compare all the growth
 * @author William Axbrink
 */
public class YearBasis {
    int years_from_start;
    int ISK_capital;
    int fund_account_capital;
    int capital_difference;

    int yearly_average_minimum;
    int yearly_profit;
    int yearly_average_extra;
    int yearly_profit_extra;

    /**
     * The yearly values for the Tax Calculator
     * @param years_from_start The years from start
     * @param ISK_capital The current capital of the ISK
     * @param fund_account_capital The current capital of the fund account
     * @param input_id The ID of the input, relevant for the database.
     * @throws Exception If something goes wrong.
     */
    public YearBasis(int years_from_start, int ISK_capital, int fund_account_capital, int input_id) throws Exception {
        this.years_from_start = years_from_start;
        this.ISK_capital = ISK_capital;
        this.fund_account_capital = fund_account_capital;
        this.capital_difference = Math.abs(ISK_capital-fund_account_capital);
        int[] query = {years_from_start, ISK_capital, fund_account_capital, capital_difference, input_id};
        try{
            CSNData.insertYearBasis(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * The yearly values for the CSN calculator.
     * @param years_from_start The years from the start.
     * @param yearly_average_minimum The current average minimum to pay.
     * @param yearly_profit The current profit from investments.
     * @param yearly_average_extra The current average minimum plus monthly extra to pay.
     * @param yearly_profit_extra The current profit from investments.
     * @param input_id The ID for the input, relevant for the database.
     * @throws Exception If something goes wrong.
     */
    public YearBasis(int years_from_start, int yearly_average_minimum, int yearly_profit, int yearly_average_extra, int yearly_profit_extra, int input_id) throws Exception {
        this.yearly_average_minimum = yearly_average_minimum;
        this.yearly_profit = yearly_profit;
        this.yearly_average_extra = yearly_average_extra;
        this.yearly_profit_extra = yearly_profit_extra;
        this.capital_difference = Math.abs(yearly_profit-yearly_profit_extra);
        this.years_from_start = years_from_start;
        int[] query = {years_from_start, yearly_average_minimum, yearly_profit, yearly_average_extra, yearly_profit_extra, capital_difference, years_from_start, input_id};
        try{
            CSNData.insertPayments(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returns the yearly ISK capital and the yearly Fund Account capital
     * @return two items
     */
    public int[] getCapital(){
        return new int[]{ISK_capital, fund_account_capital};
    }

    /**
     * Four lists of lists where the
     * first list contains the yearly average minimum payment,
     * the second list contains the yearly profit if the user invested instead of paying more,
     * the third is the payment rate if the user paid minimum plus extra towards the loan, and
     * the fourth is the investments of minimum + extra from the years after the CSN is paid off until the comparison is also paid off.
     * @return Four items
     */
    public int[] getCSNCapital(){
        return new int[]{yearly_average_minimum, yearly_profit, yearly_average_extra, yearly_profit_extra};
    }

}
