/*******************************
 * Tool Rental Demo            *
 * Author: Mariam Ben-Neticha  *
 * Date: 2/14/2019             *
 *******************************/

public class Tool {

    /************************************
     *  A Tool HAS A:                   *
     *       - identifier --> toolCode  *
     *       - property --> brand       *
     *       - property --> toolType    *
     ************************************/

    private String toolType;
    private String toolCode;
    private String brand;


    //Set and get tool properties
    public void setToolAttributes(String toolCode){
        if ("LADW".equals(toolCode)) {
            this.toolType = "Ladder";
            this.brand = "Werner";
            this.toolCode = toolCode;
        }
        else if ("CHNS".equals(toolCode)) {
            this.toolType = "Chainsaw";
            this.brand = "Stihl";
            this.toolCode = toolCode;
        }
        else if ("JAKR".equals(toolCode)) {
            this.toolType = "Jackhammer";
            this.brand = "Ridgid";
            this.toolCode = toolCode;
        }
        else if ("JAKD".equals(toolCode)) {
            this.toolType = "Jackhammer";
            this.brand = "DeWalt";
            this.toolCode = toolCode;
        }
    }


    public String getToolType(){
        return toolType;
    }


    public String getToolCode(){
        return toolCode;
    }


    public String getBrand(){
        return brand;
    }


    public double getDailyCharge(String toolType) {
        if (toolType == "Ladder"){
            return 1.99;
        }
        else if (toolType == "Chainsaw"){
            return 1.49;
        }
        else if (toolType == "Jackhammer"){
            return 2.99;
        }
        else {
            return  0;
        }
    }

}
