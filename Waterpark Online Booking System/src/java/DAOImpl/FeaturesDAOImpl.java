package DAOImpl;

import DAO.FeaturesDAO;
import DATABASE.DBConnection;
import MODEL.Features;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

public class FeaturesDAOImpl implements FeaturesDAO {
    
    private final Connection con;

    public FeaturesDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }
    
    @Override
    public void save(Features features) throws SQLException {
        try {
            String mySQLQuery = "insert into features(featuresname, featuresdesc, featuresimage) values(?, ?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, features.getFeaturesName());
            myPS.setString(2, features.getFeaturesDesc());
            
            Part filePart = features.getFeaturesImage();
            InputStream inputStream = null;
            
            if (filePart != null){
                try {
                    // obtains input stream of the upload file
                    inputStream = filePart.getInputStream();
                } catch (IOException ex) {
                    Logger.getLogger(FeaturesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                myPS.setBlob(3, inputStream);
            }
            
            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            con.close();
        }
    }
    
    @Override
    public List<Features> retrieveAll() throws SQLException {
        List<Features> featureslist = new ArrayList<>();

        Features features;
        ResultSet rs = null;

        try {
            String sqlQuery = "select * from features";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                features = new Features();

                features.setFeaturesID(rs.getInt(1));
                features.setFeaturesName(rs.getString(2));
                features.setFeaturesDesc(rs.getString(3));
                
                featureslist.add(features);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return featureslist;
    }
    
    @Override
    public Features retrieveFeaturesByFeaturesId(int featuresid) throws SQLException {
        Features features = new Features();
        
        try {
            String sqlQuery = "select * from features where featuresid=" +featuresid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                features.setFeaturesID(rs.getInt(1));
                features.setFeaturesName(rs.getString(2));
                features.setFeaturesDesc(rs.getString(3));
    
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return features;
    }
    
    
    @Override
    public void update(Features features) throws SQLException, IOException {
        try {
            String myQ = "update features SET featuresname=?, featuresdesc=?, featuresimage=? where featuresid=?";
     
            PreparedStatement myPS = con.prepareStatement(myQ);
            
            myPS.setString(1, features.getFeaturesName());
            myPS.setString(2, features.getFeaturesDesc());
            
            Part filePart = features.getFeaturesImage();
            InputStream inputStream = null;
            
            inputStream = filePart.getInputStream();
            
            // fetches input stream of the upload file for the blob column
            myPS.setBlob(3, inputStream);
            myPS.setInt(4, features.getFeaturesID());

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
    public void delete(Features features) throws SQLException {
        
        String myQ = "delete from features where featuresid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, features.getFeaturesID());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeaturesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
