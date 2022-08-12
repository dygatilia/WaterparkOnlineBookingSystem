package DAO;

import MODEL.Rules;
import java.sql.SQLException;
import java.util.List;

public interface RulesDAO {
    
    void save(Rules rules) throws SQLException;
    List<Rules> retrieveAll() throws SQLException;
    Rules retrieveRulesByRulesId(int rulesid) throws SQLException;
    void update(Rules rules) throws SQLException;
    void delete(Rules rules) throws SQLException;
    
}
