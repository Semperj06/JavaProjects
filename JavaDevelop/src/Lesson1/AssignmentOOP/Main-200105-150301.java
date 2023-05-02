package StaticClassesEnumsExceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) {

        double result = Calculator.calculate(2, 3, Calculator.Type.MULTIPLICATION);
        logger.info("result: " + result);


//        try {
//            result = Calculator.calculate(22, 3, null);
//            System.out.println("result: " + result);
//        }

//        catch (Exception err){
       //     System.out.println("Something bad has happened ");

//        }

        result = Calculator.calculate(244, 3, Calculator.Type.SUBTRACTION);
        logger.info("result: " + result);

        result = Calculator.calculate(244, 3, Calculator.Type.DIVISION);
        logger.info("result: " + result);
    }
}
