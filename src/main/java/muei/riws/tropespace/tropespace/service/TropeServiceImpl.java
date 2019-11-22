package muei.riws.tropespace.tropespace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import muei.riws.tropespace.tropespace.model.Filter;
import muei.riws.tropespace.tropespace.model.Trope;
import muei.riws.tropespace.tropespace.repository.TropeRepository;

@Service
public class TropeServiceImpl implements TropeService{
    
    private static final int PAGE_COUNT = 10;
    private TropeRepository tropeRepository;
    
    @Autowired
    public void setTropeRepository(TropeRepository tropeRepository) {
        this.tropeRepository = tropeRepository;
    }

    public Page<Trope> searchTropes(String keywords, Filter f, int startIndex){

    	Filter filter = f;
    	if(filter == null) {
    		filter = new Filter(Filter.SearchBy.name, 0, 0, "", Filter.SortBy.name);
    	}

		String sortBy = "";
		
		switch(filter.getSortedBy()) {
			case relatedMedia:
				sortBy = "media_urls_count";
				break;
			case relatedTropes:
				sortBy = "related_tropes_count";
				break;
			case name:
				sortBy = "name.raw";
			default:
				break;
				
		}
    	
    	// Pagination
    	Pageable pageable = PageRequest.of(startIndex, PAGE_COUNT, Sort.by(sortBy).ascending());

    	switch(filter.getSearchBy()) {
	    	case keywords:
    	    	return searchTropesByKeywords(keywords, filter, sortBy, pageable);
	    	case relatedMedia:
    	    	return searchTropesByMedia(keywords, filter, sortBy, pageable);
	    	case relatedTropes:
    	    	return searchTropesByRelatedTrope(keywords, filter, sortBy, pageable);
	    	case name:
    		default:
    	    	return searchTropesByName(keywords, filter, sortBy, pageable);		
    	}
    }
    
    private Page<Trope> searchTropesByName(String tropeName, Filter filter, String sortBy, Pageable pageable) {
    	if(filter.getMediaType() != "" && !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByNameWithFilterAndOrder(tropeName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), sortBy, pageable);
    	}
		return tropeRepository.findByNameWithFilterAndOrder(tropeName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), sortBy, pageable);
    }
    
    private Page<Trope> searchTropesByKeywords(String keywords, Filter filter, String sortBy, Pageable pageable) {
    	if(filter.getMediaType() != "" && !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByContentWithFilterAndOrder(keywords, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), sortBy, pageable);
    	}
		return tropeRepository.findByContentWithFilterAndOrder(keywords, filter.getRelatedTropesNumber(), filter.getMediaNumber(), sortBy, pageable);
    	
    }
    
    private Page<Trope> searchTropesByMedia(String mediaName, Filter filter, String sortBy, Pageable pageable) {
    	if(filter.getMediaType() != "" && !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByMediaWithFilterAndOrder(mediaName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), sortBy, pageable);
    	}
		return tropeRepository.findByMediaWithFilterAndOrder(mediaName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), sortBy, pageable);
    	
    }
    
    private Page<Trope> searchTropesByRelatedTrope(String relatedTropeName, Filter filter, String sortBy, Pageable pageable) {
    	if(filter.getMediaType() != "" && !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByRelatedTropeWithFilterAndOrder(relatedTropeName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), sortBy, pageable);
    	}
		return tropeRepository.findByRelatedTropeWithFilterAndOrder(relatedTropeName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), sortBy, pageable);
    	
    }

}
