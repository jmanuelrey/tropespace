package muei.riws.tropespace.tropespace.service;

import muei.riws.tropespace.tropespace.model.Trope;
import java.util.List;

public interface TropeService {
    
    List<Trope> searchTropesByName(String name);
    
    List<Trope> searchTropesByKeywords(String keywords);
    
    List<Trope> searchTropesByMedia(List<String> media);
    
    List<Trope> searchTropesByTropeName(String tropeName);   

}
