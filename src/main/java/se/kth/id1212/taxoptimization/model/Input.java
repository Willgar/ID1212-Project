package se.kth.id1212.taxoptimization.model;
/**
 *  Handles data input and numbers.
 *
 *  @author William Axbrink
 *  @author Charlotta Bodén
 */

public class Input {
    String input_id;
    int start_capital;
    int profit_capital;
    int interest_rate;
    public Input(String input_id, int start_capital, int profit_capital, int interest_rate){
        this.input_id = input_id;
        this.start_capital = start_capital;
        this.profit_capital = profit_capital;
        this.interest_rate = interest_rate;
    }
}
