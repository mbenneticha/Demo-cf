/*******************************
 * Tool Rental Demo            *
 * Author: Mariam Ben-Neticha  *
 * Date: 2/14/2019             *
 *******************************/

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class RentalAgreement {


    /*********************************
     * Output/                       *
     * Rental Agreement Properties:  *
     *      - Tool Code              *
     *      - Tool type              *
     *      - Tool brand             *
     *      - Rental days            *
     *      - Check out date         *
     *      - Due date               *
     *      - Daily Rental Charge    *
     *      - Charge days            *
     *      - Pre-discount charge    *
     *      - Discount percent       *
     *      - Discount amount        *
     *      - Final charge           *
     *********************************/

    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private Calendar checkoutDate;
    private Date dueDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private double prediscountCharge;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;



    /***********************************
     * Method creates Rental Agreement *
     * IN:   - Tool Code               *
     *       - Rental Day Count        *
     *       - Discount Percent        *
     *       - Check Out Date          *
     * OUT:  - RentalAgreement Object  *
     ***********************************/
    public RentalAgreement checkout(String tCode, int rentDayCount, int discPercent, Calendar checkOutDate) throws Exception{

        //Throw exception if Rental Day Count IS NOT 1 or greater
        //Throw exception if Discount Percent IS NOT in range [0-100]
        try {
            if (rentDayCount < 1){
                throw new Exception("Rental day count must be greater than 0.");
            }

            if (discPercent < 0 || discPercent > 100){
                throw new Exception("Discount percent is out of range [0-100].");
            }
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }

        //checkout instantiates user-defined tool
        Tool myTool = new Tool();
        myTool.setToolAttributes(tCode);

        //checkout generates rental agreement with the following info
        //info is being added to the rental agreement via set methods
        setToolAttributes(myTool, myTool.getDailyCharge(myTool.getToolType()));

        setRentalDays(rentDayCount);

        setCheckoutDate(checkOutDate);

        setDueDate(calcDueDate(checkoutDate, rentalDays));

        setChargeDays(calcChargeDays(rentalDays, checkoutDate, myTool));

        setPrediscountCharge(calcPrediscountCharge(chargeDays, dailyRentalCharge));

        setDiscountPercent(discPercent);

        setDiscountAmount(calcDiscountAmount(prediscountCharge, discountPercent));

        setFinalCharge(calcFinalCharge(prediscountCharge, discountAmount));

        //return generated rental agreement
        return this;
    }



    /*********************************
     * Method prints Rental Agreement*
     * IN:   - Tool Code             *
     *       - Rental Day Count      *
     *       - Discount Percent      *
     *       - Check Out Date        *
     * OUT:  - Void                  *
     *********************************/
    public void printAgreement(RentalAgreement myRental){
        //Print the Agreement
        System.out.println(
                "Checkout terms: \n"
                        + "Tool code: " + myRental.getToolCode() + "\n"
                        + "Tool type: " + myRental.getToolType() + "\n"
                        + "Tool brand: " + myRental.getToolBrand() + "\n"
                        + "Rental days: " + myRental.getRentalDays() + "\n"
                        + "Check out date: " + myRental.getCheckoutDate() + "\n"
                        + "Due date: " + myRental.getDueDate() + "\n"
                        + "Daily rental charge: " + myRental.getDailyRentalCharge() + "\n"
                        + "Charge days: " + myRental.getChargeDays() + "\n"
                        + "Pre-discount charge: " + myRental.getPrediscountCharge() + "\n"
                        + "Discount percent: " + myRental.getDiscountPercent() + "\n"
                        + "Discount Amount: " + myRental.getDiscountAmount() + "\n"
                        + "Final charge: " + myRental.getFinalCharge() + "\n"
        );
    }


    /*********************************
     * Method calculates dueDate     *
     * IN:   - Check Out Date        *
     *       - Rental Day Count      *
     * OUT:  - Due Date              *
     *********************************/
    private Date calcDueDate(Calendar checkOutDate, int rentalDayCount){
        Calendar myCal = (Calendar) checkOutDate.clone();
        myCal.add(Calendar.DAY_OF_MONTH, rentalDayCount);
        Date dueDate = myCal.getTime();
        return dueDate;
    }


    /**************************************
     * Method checks if date of Calendar  *
     * instance is  a  Holiday.           *
     * Holiday = July 4th OR              *
     * Labor Day (1st Monday in September)*
     * IN:   - Calendar instance          *
     * OUT:  - boolean True/False         *
     **************************************/
    private boolean isHoliday(Calendar calendar){

        //find date of labor day
        Calendar laborDay = Calendar.getInstance();
        laborDay.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        laborDay.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        laborDay.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        laborDay.set(Calendar.YEAR, calendar.get(Calendar.YEAR));

        //zero out the time components
        laborDay.set(Calendar.HOUR_OF_DAY, 0);
        laborDay.set(Calendar.MINUTE, 0);
        laborDay.set(Calendar.SECOND, 0);
        laborDay.set(Calendar.MILLISECOND, 0);

        //set date of July fourth
        Calendar julyFourth = Calendar.getInstance();
        julyFourth.set(Calendar.MONTH, Calendar.JULY);
        julyFourth.set(Calendar.DAY_OF_MONTH, 4);
        julyFourth.set(Calendar.YEAR, calendar.get(Calendar.YEAR));

        //zero out the time components
        julyFourth.set(Calendar.HOUR_OF_DAY, 0);
        julyFourth.set(Calendar.MINUTE, 0);
        julyFourth.set(Calendar.SECOND, 0);
        julyFourth.set(Calendar.MILLISECOND, 0);


        //zero out the time components of calendar parameter
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if (calendar.equals(laborDay) || calendar.equals(julyFourth)){
            return true;
        }
        else return false;
    }



    /**************************************
     * Method checks if date of Calendar  *
     * instance is  a  WEEKEND.           *
     * Weekend = SATURDAY OR SUNDAY       *
     * IN:   - Calendar instance          *
     * OUT:  - boolean True/False         *
     **************************************/
    private boolean isWeekend(Calendar calendar){
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY  || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return true;
        }
        else return false;
    }


    /*****************************************
     * Method calculates number of days to   *
     * charge for rental based on Tool Type  *
     * Ladder = No Charge on Holidays        *
     * Chainsaw = No Charge on Weekends      *
     * Jackhammer = No Charge on Holidays OR *
     *              Weekends                 *
     * IN:   - RentalDayCount                *
     *       - CheckoutDate                  *
     *       - Tool                          *
     * OUT:  - int numberChargeDays          *
     *****************************************/
    private int calcChargeDays(int rentalDayCount, Calendar checkoutDate, Tool myTool){
        boolean holiday;
        boolean weekend;
        int numberChargeDays = rentalDayCount;
        Calendar cal = (Calendar) checkoutDate.clone();

        //Check which tool is being rented
        if ("Ladder".equals(myTool.getToolType())) {
            //For every day tool is rented
            //if a date is a holiday, subtract that day from the number of chargeable days
            //then increment to the next date
            for (int i = 0; i < rentalDayCount; i++) {
                holiday = isHoliday(cal);
                if (holiday) {
                    numberChargeDays--;
                }
                cal.add(Calendar.DATE, 1);
            }
        } else if ("Chainsaw".equals(myTool.getToolType())) {
            //For every day tool is rented
            //if a date is a weekend, subtract that day from the number of chargeable days
            //then increment to the next date
            for (int i = 0; i < rentalDayCount; i++) {
                weekend = isWeekend(cal);
                if (weekend) {
                    numberChargeDays--;
                }
                cal.add(Calendar.DATE, 1);
            }
        } else if ("Jackhammer".equals(myTool.getToolType())) {
            //For every day tool is rented
            //if a date is a Weekend OR Holiday, subtract that day from the number of chargeable days
            //if date is Holiday and Weekend, decrement twice since Holiday is observed on nearest weekday
            //then increment to the next date
            for (int i = 0; i < rentalDayCount; i++) {
                holiday = isHoliday(cal);
                weekend = isWeekend(cal);
                //check is it's a holiday first
                if (holiday) {
                    if (weekend) {
                        numberChargeDays -= 2;
                    } else {
                        numberChargeDays--;
                    }
                } else {
                    if (weekend) {
                        numberChargeDays--;
                    }
                }
                cal.add(Calendar.DATE, 1);
            }
        }
        return numberChargeDays;
    }



    /*****************************************
     * Method calculates Prediscount Charge  *
     * Amount                                *
     * IN:   - NumberChargeDays              *
     *       - DailyCharge amount            *
     * OUT:  - PrediscountCharge             *
     *****************************************/
    private double calcPrediscountCharge(int numberChargeDays, double dailyCharge){
        return numberChargeDays * dailyCharge;
    }

    /**************************************
     * Method calculates Discount Amount  *
     * IN:   - PrediscountCharge          *
     *       - DiscountPercent            *
     * OUT:  - DiscountAmount             *
     **************************************/
    private BigDecimal calcDiscountAmount(double prediscountTotal, int discountPercent){
        BigDecimal discountPrcnt = new BigDecimal(discountPercent);
        BigDecimal discountAmt = BigDecimal.valueOf(prediscountTotal).multiply(discountPrcnt.divide(BigDecimal.valueOf(100)));
        return discountAmt.setScale(2, RoundingMode.HALF_UP);
    }

    /*****************************************
     * Method calculates Final Charge Amount *
     * IN:   - PrediscountCharge             *
     *       - DiscountAmount                *
     * OUT:  - FinalCharge                   *
     *****************************************/
    private BigDecimal calcFinalCharge(double prediscountCharge, BigDecimal discountAmount){
        return BigDecimal.valueOf(prediscountCharge).subtract(discountAmount);
    }


    /** Setters and Getters **/
    private void setToolAttributes(Tool myTool, double dailyRentalCharge) {
        this.toolCode = myTool.getToolCode();
        this.toolType = myTool.getToolType();
        this.toolBrand = myTool.getBrand();
        this.dailyRentalCharge = dailyRentalCharge;
    }

    private void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    private void setCheckoutDate(Calendar checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    private void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    private void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    private void setPrediscountCharge(double prediscountCharge) {
        this.prediscountCharge = prediscountCharge;
    }

    private void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    private void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    private void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }


    /*****************************
     * Formats should be:        *
     *  - Currency: $9,999.99    *
     *  - Dates: mm/dd/yy        *
     *  - Percents: 99%          *
     *****************************/
    public String getToolCode(){ return this.toolCode;}

    public String getToolType() { return this.toolType;}

    public String getToolBrand() { return this.toolBrand; }

    public int getRentalDays(){ return this.rentalDays; }

    public String getCheckoutDate() {
        SimpleDateFormat df = new SimpleDateFormat("M/d/yy");
        return df.format(this.checkoutDate.getTime());
    }

    public String getDueDate() {
        SimpleDateFormat df = new SimpleDateFormat("M/d/yy");
        return df.format(this.dueDate);
    }

    public String getDailyRentalCharge() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(this.dailyRentalCharge);
    }

    public int getChargeDays() { return this.chargeDays; }

    public String getPrediscountCharge() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(this.prediscountCharge);
    }

    public String getDiscountPercent() {
        return this.discountPercent + "%";
    }

    public String getDiscountAmount() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(this.discountAmount);
    }

    public String getFinalCharge() {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US"))
                .format(this.finalCharge);
    }


}
