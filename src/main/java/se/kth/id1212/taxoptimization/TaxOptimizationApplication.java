package se.kth.id1212.taxoptimization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main function for Spring to start the application
 *
 * @author William Axbrink
 * @author Charlotta Bodén
 */
@SpringBootApplication
public class TaxOptimizationApplication {
    /**
     * Starts the application
     *
     * @param args Empty
     */
    public static void main(String[] args) {
        SpringApplication.run(TaxOptimizationApplication.class, args);
    }
}
