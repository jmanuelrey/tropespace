package muei.riws.tropespace.tropespace.model;

public class Filter {
    
    private Integer mediaNumber;
    private Integer relatedTropesNumber;
    
    public Filter(Integer mediaNumber, Integer relatedTropesNumber) {
        this.mediaNumber = mediaNumber;
        this.relatedTropesNumber = relatedTropesNumber;
        
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

}
