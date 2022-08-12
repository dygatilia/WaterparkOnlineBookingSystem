package DAO;

import MODEL.Features;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface FeaturesDAO {
    
    void save(Features features) throws SQLException;
    List<Features> retrieveAll() throws SQLException;
    Features retrieveFeaturesByFeaturesId(int featuresid) throws SQLException;
    void update(Features features) throws SQLException, IOException;
    void delete(Features features) throws SQLException;
    
}
