package DAOImpl;

import DAO.PromotionsDAO;
import DATABASE.DBConnection;
import MODEL.Promotions;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

public class PromotionsDAOImpl implements PromotionsDAO {
    
    private final Connection con;

    public PromotionsDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }
    
    @Override
    public void save(Promotions promo) throws SQLException {
        try {
            String mySQLQuery = "insert into promotions(promotionsimage, promotionscode, promotionsdiscount) values(?, ?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            Part filePart = promo.getPromotionsImage();
            InputStream inputStream = null;
            
            if (filePart != null){
                try {
                    // obtains input stream of the upload file
                    inputStream = filePart.getInputStream();
                } catch (IOException ex) {
                    Logger.getLogger(PromotionsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                myPS.setBlob(1, inputStream);
            }
            
            myPS.setString(2, promo.getPromotionsCode());
            myPS.setInt(3, promo.getPromotionsDiscount());
            
            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            con.close();
        }
    }
    
    @Override
    public List<Promotions> retrieveAll() throws SQLException {
        List<Promotions> promolist = new ArrayList<>();

        Promotions promo;
        ResultSet rs = null;

        try {
            String sqlQuery = "select * from promotions";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                promo = new Promotions();

                promo.setPromotionsID(rs.getInt(1));
                promo.setPromotionsCode(rs.getString(3));
                promo.setPromotionsDiscount(rs.getInt(4));
                
                promolist.add(promo);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return promolist;
    }
    
    @Override
    public Promotions retrievePromotionsByPromotionsID(int promotionsid) throws SQLException {
        Promotions promo = new Promotions();
        
        try {
            String sqlQuery = "select * from promotions where promotionsid=" +promotionsid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                promo.setPromotionsID(rs.getInt(1));
                promo.setPromotionsCode(rs.getString(3));
                promo.setPromotionsDiscount(rs.getInt(4));
    
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return promo;
    }
    
    @Override
    public void update(Promotions promo) throws SQLException, IOException {
        try {
            String myQ = "update promotions SET promotionsimage=?, promotionscode=?, promotionsdiscount=? where promotionsid=?";
     
            PreparedStatement myPS = con.prepareStatement(myQ);
            
            Part filePart = promo.getPromotionsImage();
            InputStream inputStream = null;
            
            inputStream = filePart.getInputStream();
            
            // fetches input stream of the upload file for the blob column
            myPS.setBlob(1, inputStream);
            myPS.setString(2, promo.getPromotionsCode());
            myPS.setInt(3, promo.getPromotionsDiscount());
            myPS.setInt(4, promo.getPromotionsID());

            myPS.executeUpdate();

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            con.close();
        }
    }
    
    @Override
    public void delete(Promotions promo) throws SQLException {
        
        String myQ = "delete from promotions where promotionsid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, promo.getPromotionsID());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Promotions verifyPromoCode(String promocode) throws SQLException {
        ResultSet rs = null;
        Promotions promotions = null;

        try {
            String mySQLQuery = "select * from promotions where promotionscode=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setString(1, promocode);

            rs = ps.executeQuery();

            while (rs.next()) {
                promotions = new Promotions();

                promotions.setPromotionsCode(promocode);
                promotions.setPromotionsID(rs.getInt(1));
            }
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }
            con.close();
        }

        return promotions;
    }
}
