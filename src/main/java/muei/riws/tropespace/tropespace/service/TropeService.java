package muei.riws.tropespace.tropespace.service;

import muei.riws.tropespace.tropespace.model.Filter;
import muei.riws.tropespace.tropespace.model.Trope;
import java.util.List;

public interface TropeService {

    List<Trope> searchTropesByName(String name, Filter filter);
    
    List<Trope> searchTropesByKeywords(String keywords, Filter filter);
    
    List<Trope> searchTropesByMedia(List<String> media, Filter filter); // Por qué es una List?
    
    List<Trope> searchTropesByRelatedTropeName(String tropeName, Filter filter);

}