/*******************************
 * Tool Rental Demo            *
 * Author: Mariam Ben-Neticha  *
 * Date: 2/14/2019             *
 *******************************/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static javafx.application.Platform.exit;
import static javafx.application.Platform.setImplicitExit;


public class Main {

    public static void main(String[] args) throws Exception {

        //Receiving checkout parameters
        String toolCode = args[0];
        int rentalDayCount = Integer.parseInt(args[1]);
        int discountPercent = Integer.parseInt(args[2]);


        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        Calendar checkoutDate = Calendar.getInstance();
        checkoutDate.setTime(df.parse(args[3]));


        RentalAgreement myRental = new RentalAgreement();
        myRental = myRental.checkout(toolCode, rentalDayCount, discountPercent, checkoutDate);

        myRental.printAgreement(myRental);
    }
}
