package MODEL;

import javax.servlet.http.Part;

public class Payment {
    private int paymentid;
    private int checkoutid;
    private Part paymentfile;
    
    public Payment (){
        
    }
    
    public int getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }

    public int getCheckoutid() {
        return checkoutid;
    }

    public void setCheckoutid(int checkoutid) {
        this.checkoutid = checkoutid;
    }

    public Part getPaymentfile() {
        return paymentfile;
    }

    public void setPaymentfile(Part paymentfile) {
        this.paymentfile = paymentfile;
    }

}
