package DAO;

import MODEL.OrderStaff;
import java.sql.SQLException;

public interface OrderStaffDAO {
    
    void addOrderStatus(OrderStaff orderstaff) throws SQLException;
    OrderStaff retrieveOrderStatusByBookingStaffID(int bookingstaffid) throws SQLException;
    OrderStaff retrieveOrderStatusByOrderID(int orderstaffid) throws SQLException;
    void update(OrderStaff orderstaff) throws SQLException;
    
}
