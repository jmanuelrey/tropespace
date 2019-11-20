package muei.riws.tropespace.tropespace.model;

public class Filter {
	
    public enum SortBy {
    	name,
    	relatedMedia,
    	relatedTropes
    }
    
    private Integer mediaNumber;
    private Integer relatedTropesNumber;
    private String mediaType;
	private SortBy sortedBy;
    
    public Filter(Integer mediaNumber, Integer relatedTropesNumber, String mediaType, SortBy sortedBy) {
        this.mediaNumber = mediaNumber;
        this.relatedTropesNumber = relatedTropesNumber;
        this.mediaType = mediaType;
        this.sortedBy = sortedBy;
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
    
}
