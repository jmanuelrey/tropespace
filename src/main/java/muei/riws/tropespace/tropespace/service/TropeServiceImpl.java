package muei.riws.tropespace.tropespace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Trope> searchTropes(String keywords, Filter f){

    	Filter filter = f;
    	if(filter == null) {
    		filter = new Filter(Filter.SearchBy.name, 0, 0, "", Filter.SortBy.name);
    	}

		String sortBy = filter.getSortedBy().toString();
    	if(sortBy == "name")
    		sortBy = sortBy.concat(".raw");

    	switch(filter.getSearchBy()) {
	    	case keywords:
    	    	return searchTropesByKeywords(keywords, filter, sortBy);
	    	case relatedMedia:
    	    	return searchTropesByMedia(keywords, filter, sortBy);
	    	case relatedTropes:
    	    	return searchTropesByRelatedTrope(keywords, filter, sortBy);
	    	case name:
    		default:
    	    	return searchTropesByName(keywords, filter, sortBy);		
    	}
    }
    
    private List<Trope> searchTropesByName(String tropeName, Filter filter, String sortBy) {
    	if(filter.getMediaType() != "" && !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByNameWithFilterAndOrder(tropeName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), sortBy);
    	}
		return tropeRepository.findByNameWithFilterAndOrder(tropeName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), sortBy);
    }
    
    private List<Trope> searchTropesByKeywords(String keywords, Filter filter, String sortBy) {
    	if(filter.getMediaType() != "" && !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByContentWithFilterAndOrder(keywords, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), sortBy);
    	}
		return tropeRepository.findByContentWithFilterAndOrder(keywords, filter.getRelatedTropesNumber(), filter.getMediaNumber(), sortBy);
    	
    }
    
    private List<Trope> searchTropesByMedia(String mediaName, Filter filter, String sortBy) {
    	if(filter.getMediaType() != "" && !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByNameWithFilterAndOrder(mediaName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), sortBy);
    	}
		return tropeRepository.findByNameWithFilterAndOrder(mediaName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), sortBy);
    	
    }
    
    private List<Trope> searchTropesByRelatedTrope(String mediaName, Filter filter, String sortBy) {
    	if(filter.getMediaType() != "" && !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByNameWithFilterAndOrder(mediaName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), sortBy);
    	}
		return tropeRepository.findByNameWithFilterAndOrder(mediaName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), sortBy);
    	
    }

}
