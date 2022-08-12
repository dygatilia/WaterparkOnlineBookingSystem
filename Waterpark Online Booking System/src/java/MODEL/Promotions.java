package MODEL;

import javax.servlet.http.Part;

public class Promotions {
    private int promotionsID;
    private Part promotionsImage;
    private String promotionsCode;
    private int promotionsDiscount;
    
    public Promotions(){
        
    }

    public int getPromotionsID() {
        return promotionsID;
    }

    public void setPromotionsID(int promotionsID) {
        this.promotionsID = promotionsID;
    }

    public Part getPromotionsImage() {
        return promotionsImage;
    }

    public void setPromotionsImage(Part promotionsImage) {
        this.promotionsImage = promotionsImage;
    }

    public String getPromotionsCode() {
        return promotionsCode;
    }

    public void setPromotionsCode(String promotionsCode) {
        this.promotionsCode = promotionsCode;
    }
    
    public int getPromotionsDiscount() {
        return promotionsDiscount;
    }

    public void setPromotionsDiscount(int promotionsDiscount) {
        this.promotionsDiscount = promotionsDiscount;
    }
}
