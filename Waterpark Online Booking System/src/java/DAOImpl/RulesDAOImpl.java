package DAOImpl;

import DAO.RulesDAO;
import DATABASE.DBConnection;
import MODEL.Rules;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RulesDAOImpl implements RulesDAO {
    
    private final Connection con;

    public RulesDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }

    @Override
    public void save(Rules rules) throws SQLException {
        try {
            String mySQLQuery = "insert into rulesregulations(rulesdesc) values(?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, rules.getRulesDesc());

            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            con.close();
        }
    }
    
    @Override
    public List<Rules> retrieveAll() throws SQLException {
        List<Rules> ruleslist = new ArrayList<>();

        Rules rules;
        ResultSet rs = null;

        try {
            String sqlQuery = "select * from rulesregulations";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                rules = new Rules();

                rules.setRulesID(rs.getInt(1));
                rules.setRulesDesc(rs.getString(2));

                ruleslist.add(rules);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return ruleslist;
    }
    
    @Override
    public Rules retrieveRulesByRulesId(int rulesid) throws SQLException {
       Rules rules = new Rules();
        
        try {
            String sqlQuery = "select * from rulesregulations where rulesid =" +rulesid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){                
                rules.setRulesID(rs.getInt(1));
                rules.setRulesDesc(rs.getString(2));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return rules;
    }
    
    @Override
    public void update(Rules rules) throws SQLException {

        try {
            String myQ = "UPDATE rulesregulations SET rulesdesc=? WHERE rulesid=?";

            PreparedStatement myPS = con.prepareStatement(myQ);

            myPS.setString(1, rules.getRulesDesc());
            myPS.setInt(2, rules.getRulesID());

            myPS.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RulesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void delete(Rules rules) throws SQLException {

        String myQ = "DELETE FROM rulesregulations WHERE rulesid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, rules.getRulesID());

            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RulesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
