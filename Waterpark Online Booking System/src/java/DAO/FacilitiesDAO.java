package DAO;

import MODEL.Facilities;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface FacilitiesDAO {
    
    void save(Facilities facilities) throws SQLException;
    List<Facilities> retrieveAll() throws SQLException;
    Facilities retrieveFacilitiesByFacilitiesId(int facilitiesid) throws SQLException;
    void update(Facilities facilities) throws SQLException, IOException;
    void delete(Facilities facilities) throws SQLException;
    
}
