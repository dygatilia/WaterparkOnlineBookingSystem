package DAOImpl;

import DAO.FacilitiesDAO;
import DATABASE.DBConnection;
import MODEL.Facilities;
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

public class FacilitiesDAOImpl implements FacilitiesDAO {
    
    private final Connection con;

    public FacilitiesDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }
    
    @Override
    public void save(Facilities facilities) throws SQLException {
        try {
            String mySQLQuery = "insert into facilities(facilitiesname, facilitiesimage, facilitiesprice, facilitiesquantity) values(?, ?, ?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, facilities.getFacilitiesName());
            
            Part filePart = facilities.getFacilitiesImage();
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
                myPS.setBlob(2, inputStream);
            }
            
            myPS.setDouble(3, facilities.getFacilitiesPrice());
            myPS.setInt(4, facilities.getFacilitiesQuantity());
            
            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            con.close();
        }
    }
    
    @Override
    public List<Facilities> retrieveAll() throws SQLException {
        List<Facilities> facilitieslist = new ArrayList<>();

        Facilities facilities;
        ResultSet rs = null;

        try {
            String sqlQuery = "select * from facilities";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                facilities = new Facilities();

                facilities.setFacilitiesID(rs.getInt(1));
                facilities.setFacilitiesName(rs.getString(2));
                facilities.setFacilitiesPrice(rs.getDouble(4));
                facilities.setFacilitiesQuantity(rs.getInt(5));
                
                facilitieslist.add(facilities);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return facilitieslist;
    }
    
    @Override
    public Facilities retrieveFacilitiesByFacilitiesId(int facilitiesid) throws SQLException {
        Facilities facilities = new Facilities();
        
        try {
            String sqlQuery = "select * from facilities where facilitiesid=" +facilitiesid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                facilities.setFacilitiesID(rs.getInt(1));
                facilities.setFacilitiesName(rs.getString(2));
                facilities.setFacilitiesPrice(rs.getDouble(4));
                facilities.setFacilitiesQuantity(rs.getInt(5));
    
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return facilities;
    }
    
    @Override
    public void update(Facilities facilities) throws SQLException, IOException {
        try {
            String myQ = "update facilities SET facilitiesname=?, facilitiesimage=?, facilitiesprice=?, facilitiesquantity=? where facilitiesid=?";
     
            PreparedStatement myPS = con.prepareStatement(myQ);
            
            myPS.setString(1, facilities.getFacilitiesName());
            
            Part filePart = facilities.getFacilitiesImage();
            InputStream inputStream = null;
            
            inputStream = filePart.getInputStream();
            
            // fetches input stream of the upload file for the blob column
            myPS.setBlob(2, inputStream);
            myPS.setDouble(3, facilities.getFacilitiesPrice());
            myPS.setInt(4, facilities.getFacilitiesQuantity());
            myPS.setInt(5, facilities.getFacilitiesID());

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
    public void delete(Facilities facilities) throws SQLException {
        
        String myQ = "delete from facilities where facilitiesid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, facilities.getFacilitiesID());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FacilitiesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
