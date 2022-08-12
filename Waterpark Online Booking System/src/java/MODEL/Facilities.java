package MODEL;

import javax.servlet.http.Part;

public class Facilities {
    private int facilitiesID;
    private String facilitiesName;
    private Part facilitiesImage;
    private double facilitiesPrice;
    private int facilitiesQuantity;
    
    public Facilities(){
        
    }

    public int getFacilitiesID() {
        return facilitiesID;
    }

    public void setFacilitiesID(int facilitiesID) {
        this.facilitiesID = facilitiesID;
    }

    public String getFacilitiesName() {
        return facilitiesName;
    }

    public void setFacilitiesName(String facilitiesName) {
        this.facilitiesName = facilitiesName;
    }

    public Part getFacilitiesImage() {
        return facilitiesImage;
    }

    public void setFacilitiesImage(Part facilitiesImage) {
        this.facilitiesImage = facilitiesImage;
    }

    public double getFacilitiesPrice() {
        return facilitiesPrice;
    }

    public void setFacilitiesPrice(double facilitiesPrice) {
        this.facilitiesPrice = facilitiesPrice;
    }

    public int getFacilitiesQuantity() {
        return facilitiesQuantity;
    }

    public void setFacilitiesQuantity(int facilitiesQuantity) {
        this.facilitiesQuantity = facilitiesQuantity;
    }
    
}
