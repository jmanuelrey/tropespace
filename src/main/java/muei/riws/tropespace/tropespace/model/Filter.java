package muei.riws.tropespace.tropespace.model;

public class Filter {
	
	public enum SearchBy {
		name,
		keywords,
		relatedMedia,
		relatedTropes
	}
	
    public enum SortBy {
    	_score,
    	name,
    	relatedMedia,
    	relatedTropes
    }
    
    public enum SortByOrder {
    	asc,
    	desc
    }
    
    private SearchBy searchBy;
    private Integer mediaNumber = 0;
    private Integer relatedTropesNumber = 0;
    private String mediaType = "";
	private SortBy sortedBy = SortBy.name;
	private SortByOrder sortedByOrder = SortByOrder.asc;
    
    public Filter(SearchBy searchBy, Integer mediaNumber, Integer relatedTropesNumber, String mediaType, SortBy sortedBy, SortByOrder sortedByOrder) {
    	this.searchBy = searchBy;
        this.mediaNumber = mediaNumber;
        this.relatedTropesNumber = relatedTropesNumber;
        this.mediaType = mediaType;
        this.sortedBy = sortedBy;
        this.sortedByOrder = sortedByOrder;
    }

    public SearchBy getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(SearchBy searchBy) {
		this.searchBy = searchBy;
	}

	public Integer getMediaNumber() {
        return mediaNumber;
    }

    public void setMediaNumber(Integer mediaNumber) {
        this.mediaNumber = mediaNumber;
    }

    public Integer getRelatedTropesNumber() {
        return relatedTropesNumber;
    }

    public void setRelatedTropesNumber(Integer relatedTropesNumber) {
        this.relatedTropesNumber = relatedTropesNumber;
    }
    
    public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public SortBy getSortedBy() {
		return sortedBy;
	}

	public void setSortedBy(SortBy sortedBy) {
		this.sortedBy = sortedBy;
	}

	public SortByOrder getSortedByOrder() {
		return sortedByOrder;
	}

	public void setSortedByOrder(SortByOrder sortedByOrder) {
		this.sortedByOrder = sortedByOrder;
	}
    
}
