import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ScenarioTest {
    // You should provide JUnit tests for the following scenarios.
    // Each test configures a tool instance as specified, invokes
    // checkout with the specified args, and asserts all values in
    // the returned Rental Agreement instance are correct.

    @org.junit.Before
    public void setUp() throws Exception {
    }



    @Test (expected = Exception.class)
    public void testScenario1() throws Exception {
        Tool myTool = new Tool();
        myTool.setToolAttributes("JAKR");
        String date = "9/3/15";
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        Calendar checkoutDate = Calendar.getInstance();
        checkoutDate.setTime(df.parse(date));
        RentalAgreement myRental = new RentalAgreement();

        try{
            myRental.checkout("JAKR",  5, 101, checkoutDate);
        }
        catch (Exception e){
            assertEquals(Exception.class, e.getClass());
            assertEquals("Discount percent is out of range [0-100].", e.getMessage());
            throw e;
        }
    }



    @Test
    public void testScenario2() throws Exception {
        Tool myTool = new Tool();
        myTool.setToolAttributes("LADW");
        String date = "7/2/20";
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        Calendar checkoutDate = Calendar.getInstance();
        checkoutDate.setTime(df.parse(date));
        RentalAgreement myRental = new RentalAgreement();
        myRental.checkout("LADW",  3, 10, checkoutDate);

        assertEquals("LADW", myRental.getToolCode());
        assertEquals("Ladder", myRental.getToolType());
        assertEquals("Werner", myRental.getToolBrand());
        assertEquals(3, myRental.getRentalDays());
        assertEquals("7/2/20", myRental.getCheckoutDate());
        assertEquals("7/5/20", myRental.getDueDate());
        assertEquals("$1.99", myRental.getDailyRentalCharge());
        assertEquals(2, myRental.getChargeDays());
        assertEquals("$3.98", myRental.getPrediscountCharge());
        assertEquals("10%", myRental.getDiscountPercent());
        assertEquals("$0.40", myRental.getDiscountAmount());
        assertEquals("$3.58", myRental.getFinalCharge());
    }


    @Test
    public void testScenario3() throws Exception {
        String date = "7/2/15";
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        Calendar checkoutDate = Calendar.getInstance();
        checkoutDate.setTime(df.parse(date));
        RentalAgreement myRental = new RentalAgreement();
        myRental = myRental.checkout("CHNS",  5, 25, checkoutDate);

        assertEquals("CHNS", myRental.getToolCode());
        assertEquals("Chainsaw", myRental.getToolType());
        assertEquals("Stihl", myRental.getToolBrand());
        assertEquals(5, myRental.getRentalDays());
        assertEquals("7/2/15", myRental.getCheckoutDate());
        assertEquals("7/7/15", myRental.getDueDate());
        assertEquals("$1.49", myRental.getDailyRentalCharge());
        assertEquals(3, myRental.getChargeDays());
        assertEquals("$4.47", myRental.getPrediscountCharge());
        assertEquals("25%", myRental.getDiscountPercent());
        assertEquals("$1.12", myRental.getDiscountAmount());
        assertEquals("$3.35", myRental.getFinalCharge());
    }


    @Test
    public void testScenario4() throws Exception {
        String date = "9/3/15";
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        Calendar checkoutDate = Calendar.getInstance();
        checkoutDate.setTime(df.parse(date));
        RentalAgreement myRental = new RentalAgreement();
        myRental = myRental.checkout("JAKD",  6, 0, checkoutDate);

        assertEquals("JAKD", myRental.getToolCode());
        assertEquals("Jackhammer", myRental.getToolType());
        assertEquals("DeWalt", myRental.getToolBrand());
        assertEquals(6, myRental.getRentalDays());
        assertEquals("9/3/15", myRental.getCheckoutDate());
        assertEquals("9/9/15", myRental.getDueDate());
        assertEquals("$2.99", myRental.getDailyRentalCharge());
        assertEquals(3, myRental.getChargeDays());
        assertEquals("$8.97", myRental.getPrediscountCharge());
        assertEquals("0%", myRental.getDiscountPercent());
        assertEquals("$0.00", myRental.getDiscountAmount());
        assertEquals("$8.97", myRental.getFinalCharge());
    }


    @Test
    public void testScenario5() throws Exception {
        String date = "7/2/15";
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        Calendar checkoutDate = Calendar.getInstance();
        checkoutDate.setTime(df.parse(date));
        RentalAgreement myRental = new RentalAgreement();
        myRental = myRental.checkout("JAKR",  9, 0, checkoutDate);

        assertEquals("JAKR", myRental.getToolCode());
        assertEquals("Jackhammer", myRental.getToolType());
        assertEquals("Ridgid", myRental.getToolBrand());
        assertEquals(9, myRental.getRentalDays());
        assertEquals("7/2/15", myRental.getCheckoutDate());
        assertEquals("7/11/15", myRental.getDueDate());
        assertEquals("$2.99", myRental.getDailyRentalCharge());
        assertEquals(5, myRental.getChargeDays());
        assertEquals("$14.95", myRental.getPrediscountCharge());
        assertEquals("0%", myRental.getDiscountPercent());
        assertEquals("$0.00", myRental.getDiscountAmount());
        assertEquals("$14.95", myRental.getFinalCharge());
    }



    @Test
    public void testScenario6() throws Exception {
        String date = "7/2/20";
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        Calendar checkoutDate = Calendar.getInstance();
        checkoutDate.setTime(df.parse(date));
        RentalAgreement myRental = new RentalAgreement();
        myRental = myRental.checkout("JAKR",  4, 50, checkoutDate);

        assertEquals("JAKR", myRental.getToolCode());
        assertEquals("Jackhammer", myRental.getToolType());
        assertEquals("Ridgid", myRental.getToolBrand());
        assertEquals(4, myRental.getRentalDays());
        assertEquals("7/2/20", myRental.getCheckoutDate());
        assertEquals("7/6/20", myRental.getDueDate());
        assertEquals("$2.99", myRental.getDailyRentalCharge());
        assertEquals(1, myRental.getChargeDays());
        assertEquals("$2.99", myRental.getPrediscountCharge());
        assertEquals("50%", myRental.getDiscountPercent());
        assertEquals("$1.50", myRental.getDiscountAmount());
        assertEquals("$1.49", myRental.getFinalCharge());
    }



    @org.junit.After
    public void tearDown() throws Exception {
    }
}
