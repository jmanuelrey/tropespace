package muei.riws.tropespace.tropespace.service;

import java.util.List;

import org.elasticsearch.search.sort.SortOrder;
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
    		filter = new Filter(Filter.SearchBy.name, 0, 0, "", Filter.SortBy._score, Filter.SortByOrder.asc);
    	}

		String sortBy = "";
		
		switch(filter.getSortedBy()) {
			case name:
				sortBy = "name.raw";
				break;
			case relatedMedia:
				sortBy = "media_urls_count";
				break;
			case relatedTropes:
				sortBy = "related_tropes_count";
				break;
			case _score:
			default:
				sortBy = "_score";
				break;		
		}
		Sort.Direction sortOrder;
		switch(filter.getSortedByOrder()) {
			case asc:
				sortOrder = Sort.Direction.ASC;
				break;
			case desc:
			default:
				sortOrder = Sort.Direction.DESC;
				break;
		}
    	
    	// Pagination
    	Pageable pageable = PageRequest.of(startIndex, PAGE_COUNT, Sort.by(sortOrder, sortBy));

    	keywords = keywords.replace("\"", "");
    	
    	switch(filter.getSearchBy()) {
	    	case keywords:
    	    	return searchTropesByKeywords(keywords, filter, pageable);
	    	case relatedMedia:
    	    	return searchTropesByMedia(keywords, filter, pageable);
	    	case relatedTropes:
    	    	return searchTropesByRelatedTrope(keywords, filter, pageable);
	    	case name:
    		default:
    	    	return searchTropesByName(keywords, filter, pageable);		
    	}
    }
    
    private Page<Trope> searchTropesByName(String tropeName, Filter filter, Pageable pageable) {
    	if(!filter.getMediaType().contentEquals("") && !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByNameWithFilterAndOrder(tropeName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), pageable);
    	}
		return tropeRepository.findByNameWithFilterAndOrder(tropeName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), pageable);
    }
    
    private Page<Trope> searchTropesByKeywords(String keywords, Filter filter, Pageable pageable) {
    	if(!filter.getMediaType().contentEquals("")&& !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByContentWithFilterAndOrder(keywords, filter.getRelatedTropesNumber(), filter.getMediaNumber(), pageable);
    	}
		return tropeRepository.findByContentWithFilterAndOrder(keywords, filter.getRelatedTropesNumber(), filter.getMediaNumber(), pageable);
    	
    }
    
    private Page<Trope> searchTropesByMedia(String mediaName, Filter filter, Pageable pageable) {
    	if(!filter.getMediaType().contentEquals("") && !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByMediaWithFilterAndOrder(mediaName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), pageable);
    	}
		return tropeRepository.findByMediaWithFilterAndOrder(mediaName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), pageable);
    	
    }
    
    private Page<Trope> searchTropesByRelatedTrope(String relatedTropeName, Filter filter, Pageable pageable) {
    	if(!filter.getMediaType().contentEquals("") && !filter.getMediaType().contentEquals("*")) {
    		return tropeRepository.findByRelatedTropeWithFilterAndOrder(relatedTropeName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), filter.getMediaType(), pageable);
    	}
		return tropeRepository.findByRelatedTropeWithFilterAndOrder(relatedTropeName, filter.getRelatedTropesNumber(), filter.getMediaNumber(), pageable);
    	
    }

}
