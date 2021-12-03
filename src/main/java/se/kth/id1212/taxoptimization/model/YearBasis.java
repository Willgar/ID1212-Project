package se.kth.id1212.taxoptimization.model;

public class YearBasis {
    int years_from_start;
    int ISK_capital;
    int fund_account_capital;
    int capital_difference;
    public YearBasis(int years_from_start, int ISK_capital, int fund_account_capital){
        this.years_from_start = years_from_start;
        this.ISK_capital = ISK_capital;
        this.fund_account_capital = fund_account_capital;
        this.capital_difference = Math.abs(ISK_capital-fund_account_capital);
    }
    public int[] getCapital(){
        int item[] = new int[]{ISK_capital, fund_account_capital};
        return item;
    }
}
