package DAO;

import MODEL.DailyTicket;
import java.sql.SQLException;

public interface DailyTicketDAO {
    void addDailyTicket(DailyTicket dailyticket) throws SQLException;
    DailyTicket retrieveDailyTicket() throws SQLException;
    void update(DailyTicket dailyticket) throws SQLException;
}
