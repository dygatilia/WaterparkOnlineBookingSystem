package MODEL;

import java.sql.Date;

public class TotalTicket {
    private int totalticketID;
    private Date ticketDate;
    private int ticketSold;

    public int getTotalticketID() {
        return totalticketID;
    }

    public void setTotalticketID(int totalticketID) {
        this.totalticketID = totalticketID;
    }

    public Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    public int getTicketSold() {
        return ticketSold;
    }

    public void setTicketSold(int ticketSold) {
        this.ticketSold = ticketSold;
    }
}
