package se.kth.id1212.taxoptimization;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kth.id1212.taxoptimization.model.User;

import java.sql.SQLException;
import java.util.Random;

import static org.thymeleaf.util.ArrayUtils.length;

/**
 *  The controller which handles all connections and logic.
 *
 *  @author William Axbrink
 *  @author Charlotta Bodén
 */

@org.springframework.stereotype.Controller
public class TaxOptimizationController {
    User user;
    /**
     * The front page of the website, so that the user can log in.
     * If a user goes straight to the calculator without loggin in, they will be redirected to the login page.
     * If a user gets exposed to an error page, they will be redirected to login.
     * @param model The model for the HTML pages.
     * @return Returns the login.html page
     * @throws Exception If something goes wrong.
     */
    @RequestMapping({"/", "/error", "/calculator"})
    public String login(Model model) throws Exception {
        return "login";
    }

    /**
     * When the user has entered their information in the login page they will be redirected to the
     * calculator with their user info stored.
     * @param password The users password
     * @param email The users email
     * @param model The model for the HTML pages
     * @return Returns the calculator.html page
     * @throws Exception If something goes wrong.
     */
    @PostMapping("/calculator")
    public String calculator(@RequestParam() String password,
                        @RequestParam() String email,
                        Model model) throws Exception {
        try{
            this.user = new User(email, password);
            boolean test = user.userExists();
            //System.out.println(test + " " + email + " " + password + " " + name);
        /*
            Takes user, pass, etc
            if(valid)
                create user object from mysql database
                return calculator
            else if(email exists in database but wrong password)
                return wrong password page
            else if(email does not exist)
                create new user with user/pass/mail
                return calculator

         */
            return "calculator";
        }catch (Exception e){
            System.out.println(e);
            return "login";
        }
    }

    /**
     * Retrieves all the information about the user and creates a profile.
     * @param firstname The users first name
     * @param password The users password
     * @param email The users email
     * @param lastname The users lastname
     * @param country The users country they reside in
     * @param city The users city that they reside in
     * @param phone The users phone number
     * @param gender The users gender
     * @param subscribe The users preference of subscription to a news letter
     * @param model The model for the HTML pages
     * @return Returns the calculator HTML page
     * @throws Exception If something goes wrong
     */
    @PostMapping("/calculator/signedup")
    public String signedUp(@RequestParam() String firstname,
                           @RequestParam() String password,
                           @RequestParam() String email,
                           @RequestParam() String lastname,
                           @RequestParam() String country,
                           @RequestParam() String city,
                           @RequestParam() int phone,
                           @RequestParam() String gender,
                           @RequestParam() String subscribe,
                           Model model) throws Exception{
        //Create a new user which is added to the DB.
        if(user != null){
            user.updateUser(firstname, password, email, lastname, country, city, phone, gender, subscribe);
        } else{
            user = new User(firstname, password, email, lastname, country, city, phone, gender, subscribe);
        }
        return "calculator";
    }

    /**
     * The server retrieves from the post request the users input to calculate the new valuation of their funds.
     * @param start_capital How much capital the user spent at their fund/stock
     * @param profit_capital The current growth since purchase.
     * @param interest_rate The estimated rate which the fund is expected to grow.
     * @param years How many years ahead the user wants to let it grow.
     * @param model The model for the HTML pages
     * @return Returns the answer.html page with the calculated value.
     * @throws Exception If something goes wrong.
     */
    @PostMapping("/answer")
    public String answer(@RequestParam() int start_capital,
                         @RequestParam() int profit_capital,
                         @RequestParam() int interest_rate,
                         @RequestParam() int years,Model model) throws Exception {
        calculateFundToISK(start_capital, profit_capital, interest_rate, years);
        model.addAttribute("amount", user.getValue());
        model.addAttribute("yearly_value", user.getYearlyCapital());
        return "answer";
    }
    @PostMapping("/csnanswer")
    public String answer(@RequestParam() int total_loan,
                         @RequestParam() int interest_rate,
                         @RequestParam() int desired_payments,Model model) throws Exception { ;

        return "csnanswer";
    }
    @GetMapping("/signup")
    public String signup(Model model){
        return "signup";
    }
    @PostMapping("/error")
    public String error(Model model){
        return "login";
    }
    private double calculateGrowths(int start_capital, int profit_capital, int interest_rate, int years){
        double first = (start_capital + profit_capital)+((start_capital + profit_capital)*interest_rate*years)/100;
        double afterTax = (first-start_capital)*0.7+start_capital;
        double second = (start_capital + profit_capital*0.7)+((start_capital + profit_capital*0.7)*interest_rate*years)/100;
        return (start_capital + profit_capital)+((start_capital + profit_capital)*interest_rate*years)/100;
    }


    private void calculateFundToISK(int start_capital, int profit_capital, int interest_rate, int years){

        double total_capital_ISK = (start_capital+profit_capital*0.7);
        double total_capital_fund = (start_capital+profit_capital);
        double yearly_value[][] = new double[years][2];
        yearly_value[0][0] = total_capital_ISK;
        yearly_value[0][1] = total_capital_fund;
        for(int i = 1; i < years; i++){
            total_capital_ISK = total_capital_ISK*((interest_rate/100.0f)+1)-(total_capital_ISK*0.00375);
            yearly_value[i][0] = total_capital_ISK;
            total_capital_fund = total_capital_fund*((interest_rate/100.0f)+1);
            yearly_value[i][1] = total_capital_fund;
        }
        yearly_value[length(yearly_value)-1][1] = (total_capital_fund-start_capital)*0.7+start_capital;
        this.user.createInput(start_capital, profit_capital, interest_rate, years, yearly_value);
    }
}
