package DAO;

import MODEL.Promotions;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PromotionsDAO {
    
    void save(Promotions promotions) throws SQLException;
    List<Promotions> retrieveAll() throws SQLException;
    Promotions retrievePromotionsByPromotionsID(int promotionsid) throws SQLException;
    void update(Promotions promotions) throws SQLException, IOException;
    void delete(Promotions promotions) throws SQLException;
    Promotions verifyPromoCode (String promocode) throws SQLException;
}
