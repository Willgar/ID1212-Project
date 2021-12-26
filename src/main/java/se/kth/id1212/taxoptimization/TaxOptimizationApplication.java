package se.kth.id1212.taxoptimization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kth.id1212.taxoptimization.model.User;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

/**
 * The main function for Spring to start the application and the Controller.
 *
 * @author William Axbrink
 * @author Charlotta Bodén
 */
@Controller
@SpringBootApplication
public class TaxOptimizationApplication {
    User user;
    /**
     * The front page of the website, so that the user can log in.
     * If a user goes straight to the calculator without logging in, they will be redirected to the login page.
     * If a user gets exposed to an error page, they will be redirected to the login page.
     * @param model The model for the HTML pages.
     * @return Returns the login.html page
     */
    @RequestMapping({"/", "/calculator"})
    public String login(Model model){
        return "login";
    }

    /**
     * When the user has entered their information in the login page they will be redirected to the
     * calculator with their user info stored.
     * @param password The users password
     * @param email The users email
     * @param model The model for the HTML pages
     * @return Returns the calculator.html page
     */
    @PostMapping("/calculator")
    public String calculator(@RequestParam() String password,
                             @RequestParam() String email,
                             Model model) {
        try{
            this.user = new User(email, password);
            if(user.userExists())
                return "calculator";
            else {
                //return "calculator";
                return "signup";       //Change to this return when database is up and running
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");
            return "calculator";
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
    @PostMapping("/signedup")
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
            user = new User(firstname, password, email, lastname, country, city, phone, gender, subscribe);
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
     */
    @PostMapping("/answer")
    public String answer(@RequestParam() int start_capital,
                         @RequestParam() int profit_capital,
                         @RequestParam() int interest_rate,
                         @RequestParam() int years,Model model){
        try { //Connects to a local API server to make the calculations
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://fundaccounttoiskcalculatorapi.herokuapp.com/tax/?start_capital=" + start_capital + "&profit_capital=" + profit_capital + "&years=" + years + "&interest_rate=+" + interest_rate))
                    .build();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            //Parses the JSON response with Regex
            String[] regex = response.body().split("\\[");
            String[] isk = regex[1].split(",");
            isk = Arrays.copyOf(isk, isk.length - 1);
            String[] fund = regex[2].split(",");
            double[][] yearly_value = new double[2][years];
            int i = 0;
            for (String s : isk) {
                s = s.replace("]", "");
                yearly_value[0][i] = Double.parseDouble(s);
                i++;
            }
            i = 0;
            for (String s : fund) {
                s = s.replace("]}", "");
                yearly_value[1][i] = Double.parseDouble(s);
                i++;
            }
            this.user.createInput(start_capital, profit_capital, interest_rate, years, yearly_value);
            System.out.println("Input created with API server");
            model.addAttribute("amount", user.getValue());
            model.addAttribute("yearly_value", user.getYearlyCapital());

            return "answer";
        } catch(Exception e){ //If it fails, then we use the backup method ( Overly complicated but for grading reasons )
            e.printStackTrace();
            try {
                calculateFundToISK(start_capital, profit_capital, interest_rate, years);
                System.out.println("Input created locally");
                model.addAttribute("amount", user.getValue());
                model.addAttribute("yearly_value", user.getYearlyCapital());

                return "answer";
            } catch(Exception e2){
                e2.printStackTrace();
                return "login";
            }
        }
    }

    /**
     * The server retrieves from the post request the users input to calculate the differences between CSN payments.
     * @param total_loan The total amount that is loaned.
     * @param csn_interest_rate The estimated yearly interest rate for investments
     * @param desired_payment How much above the minimum the users wants to pay towards the loan.
     * @param model The model for the HTML pages.
     * @return Returns the csnanswer.html page with the calculated values.
     */
    @PostMapping("/csnanswer")
    public String answer(@RequestParam() int total_loan,
                         @RequestParam() int csn_interest_rate,
                         @RequestParam() int desired_payment,Model model){
        try {
            user.createCSNInput(total_loan, csn_interest_rate, desired_payment);
            model.addAttribute("yearly_value", user.getYearlyCSNCapital());
            int[][] yearly_value = user.getYearlyCSNCapital();
            for (int j = 0; j < yearly_value[0].length; j++) {
                System.out.println(yearly_value[0][j] + " " + yearly_value[1][j] + " " + yearly_value[2][j] + " " + yearly_value[3][j]);
            }
            return "csnanswer";
        } catch(Exception e){
            e.printStackTrace();
            return "login";
        }
    }

    /**
     * The page for the user to sign up.
     * @param model The model for the HTML pages.
     * @return Returns the signup.html page.
     */
    @GetMapping("/signup")
    public String signup(Model model){
        return "signup";
    }

    /**
     * Redirects the user to the login page if they receieve an error.
     * @param model The model for the HTML pages.
     * @return Returns the login.html page.
     */
    /*@PostMapping("/error")
    public String error(Model model){
        return "login";
    }
*/

    /**
     * Calculates the difference.
     * Deprecated and kept as backup.
     */
    private void calculateFundToISK(int start_capital, int profit_capital, int interest_rate, int years) throws Exception {
        double total_capital_ISK = (start_capital+profit_capital*0.7);
        double total_capital_fund = (start_capital+profit_capital);
        double[][] yearly_value = new double[2][years];
        yearly_value[0][0] = total_capital_ISK;
        yearly_value[1][0] = (total_capital_fund-start_capital)*0.7+start_capital;
        for(int i = 1; i < years; i++){
            total_capital_ISK = total_capital_ISK*((interest_rate/100.0f)+1)-(total_capital_ISK*0.00375);
            yearly_value[0][i] = total_capital_ISK;
            total_capital_fund = total_capital_fund*((interest_rate/100.0f)+1);
            yearly_value[1][i] = (total_capital_fund-start_capital)*0.7+start_capital;
        }
        this.user.createInput(start_capital, profit_capital, interest_rate, years, yearly_value);
    }



    /**
     * Starts the application
     *
     * @param args Empty
     */
    public static void main(String[] args) {
        SpringApplication.run(TaxOptimizationApplication.class, args);
    }
}
