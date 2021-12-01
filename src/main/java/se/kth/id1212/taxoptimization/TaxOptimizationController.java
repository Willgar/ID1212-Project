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

/**
 *  The controller which handles all connections and logic.
 *
 *  @author William Axbrink
 *  @author Charlotta Bodén
 */

@org.springframework.stereotype.Controller
public class TaxOptimizationController {
    /**
     * The front page of the website, so that the user can log in.
     * @param model The model for the HTML pages.
     * @return Returns the login.html page
     * @throws Exception If something goes wrong.
     */
    @GetMapping("/")
    public String login(Model model) throws Exception {
        return "login";
    }

    /**
     * If a user goes straight to the calculator without loggin in, they will be redirected to the login page.
     * @param model The model for the HTML pages.
     * @return Returns the login.html page
     * @throws Exception If something goes wrong.
     */
    @GetMapping("/calculator")
    public String calculatorError(Model model) throws Exception {
        return "login";
    }

    /**
     * When the user has entered their information in the login page they will be redirected to the
     * calculator with their user info stored.
     * @param name The users name
     * @param password The users password
     * @param email The users email
     * @param model The model for the HTML pages
     * @return Returns the calculator.html page
     * @throws Exception If something goes wrong.
     */
    @PostMapping("/calculator")
    public String calculator(@RequestParam() String name,
                        @RequestParam() String password,
                        @RequestParam() String email,
                        Model model) throws Exception {
        try{
            User user = new User(email, name, password);
            boolean test = user.userExists();
            System.out.println(test + " " + email + " " + password + " " + name);
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
}
