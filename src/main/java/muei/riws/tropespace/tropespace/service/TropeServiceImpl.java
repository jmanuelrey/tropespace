package muei.riws.tropespace.tropespace.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import muei.riws.tropespace.tropespace.model.Filter;
import muei.riws.tropespace.tropespace.model.Trope;
import muei.riws.tropespace.tropespace.repository.TropeRepository;

@Service
public class TropeServiceImpl implements TropeService{
    
    private TropeRepository tropeRepository;
    
    @Autowired
    public void setTropeRepository(TropeRepository tropeRepository) {
        this.tropeRepository = tropeRepository;
    }

    public List<Trope> searchTropesByName(String name, Filter filter){
    	String sortBy = "";
    	if(filter != null) {
    		sortBy = filter.getSortedBy().toString();
	    	if(sortBy == "name")
	    		sortBy = sortBy.concat(".raw");
	    	
	    	// TODO
	    	// Esto se debe realizar de esta manera porque no hay forma de pasar una wildcard reconocible por Elastic en el campo media
	    	// Idealmente se pasaría algo como "*" para que buscase sobre cualquier MediaType
	    	if(filter.getMediaType() != "")
	    		return tropeRepository.findByNameWithFilterAndOrder(name, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), sortBy);
    		return tropeRepository.findByNameWithFilterAndOrder(name, filter.getRelatedTropesNumber(), filter.getMediaNumber(), sortBy);
    	} else {
    		filter = new Filter(0, 0, "", Filter.SortBy.name);
    		return tropeRepository.findByNameWithFilterAndOrder(name, filter.getRelatedTropesNumber(), filter.getMediaNumber(), sortBy);
    	}
    }

    public List<Trope> searchTropesByKeywords(String keywords, Filter filter){
        return tropeRepository.findByNameOrLaconicOrContentOrderByName(keywords);
        
    }

    public List<Trope> searchTropesByMedia(List<String> media, Filter filter){
        return tropeRepository.findByMediaOrderByName(media);
        
    }

    public List<Trope> searchTropesByRelatedTropeName(String tropeName, Filter filter){
    	tropeName = StringUtils.capitalizeWords(tropeName);
    	tropeName = tropeName.replaceAll("\\s","");
        return tropeRepository.findByRelatedTropesOrderByName(tropeName);
    }

}