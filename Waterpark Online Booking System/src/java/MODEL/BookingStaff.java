package MODEL;

import java.sql.Date;

public class BookingStaff {
    private int bookingStaffID;
    private int staffid;
    private String bookingName;
    private Date bookingDate;
    private String contactNum;

    public int getBookingStaffID() {
        return bookingStaffID;
    }

    public void setBookingStaffID(int bookingStaffID) {
        this.bookingStaffID = bookingStaffID;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
}
