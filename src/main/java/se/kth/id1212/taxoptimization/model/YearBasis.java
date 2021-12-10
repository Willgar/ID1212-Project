package se.kth.id1212.taxoptimization.model;

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
    public int[] getCapital(){
        int item[] = new int[]{ISK_capital, fund_account_capital};
        return item;
    }
    public int[] getCSNCapital(){
        int item[] = new int[]{yearly_average_minimum, yearly_profit, yearly_average_extra, yearly_profit_extra};
        return item;
    }

}
