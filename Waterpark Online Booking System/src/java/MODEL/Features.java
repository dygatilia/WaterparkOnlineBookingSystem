package MODEL;

import javax.servlet.http.Part;

public class Features {
    private int featuresID;
    private String featuresName;
    private String featuresDesc;
    private Part featuresImage;
    
    public Features(){
        
    }

    public int getFeaturesID() {
        return featuresID;
    }

    public void setFeaturesID(int featuresID) {
        this.featuresID = featuresID;
    }

    public String getFeaturesName() {
        return featuresName;
    }

    public void setFeaturesName(String featuresName) {
        this.featuresName = featuresName;
    }

    public String getFeaturesDesc() {
        return featuresDesc;
    }

    public void setFeaturesDesc(String featuresDesc) {
        this.featuresDesc = featuresDesc;
    }

    public Part getFeaturesImage() {
        return featuresImage;
    }

    public void setFeaturesImage(Part featuresImage) {
        this.featuresImage = featuresImage;
    }
    
}
