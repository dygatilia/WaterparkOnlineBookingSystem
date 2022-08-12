package MODEL;

public class CheckoutStaff {
    private int checkoutid;
    private int bookid;
    private String paymenttype;
    private Double checkoutamount;

    public int getCheckoutid() {
        return checkoutid;
    }

    public void setCheckoutid(int checkoutid) {
        this.checkoutid = checkoutid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }
    
    public Double getCheckoutamount() {
        return checkoutamount;
    }

    public void setCheckoutamount(Double checkoutamount) {
        this.checkoutamount = checkoutamount;
    }
}
