package se.kth.id1212.taxoptimization.model;

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
    public YearBasis(int years_from_start, int ISK_capital, int fund_account_capital){
        this.years_from_start = years_from_start;
        this.ISK_capital = ISK_capital;
        this.fund_account_capital = fund_account_capital;
        this.capital_difference = Math.abs(ISK_capital-fund_account_capital);
        System.out.println(years_from_start);

    }
    public YearBasis(int years_from_start, int yearly_average_minimum, int yearly_profit, int yearly_average_extra, int yearly_profit_extra){
        this.yearly_average_minimum = yearly_average_minimum;
        this.yearly_profit = yearly_profit;
        this.yearly_average_extra = yearly_average_extra;
        this.yearly_profit_extra = yearly_profit_extra;
        this.capital_difference = Math.abs(yearly_profit-yearly_profit_extra);
        this.years_from_start = years_from_start;
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
