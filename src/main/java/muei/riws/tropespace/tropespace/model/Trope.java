package muei.riws.tropespace.tropespace.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "tropes", type = "_doc")
public class Trope {
	
	private String id;
    private String name;
    private String content;
    private String url;
    private String laconic;
    @JsonProperty("related_tropes")
    private List<String> relatedTropes;
    private List<Media> media;
    private Long mediaTypeCount;
    private Long mediaUrlsCount;
    private Integer relatedTropesCount;

    public Trope() {

    }

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    public String getLaconic() {
        return laconic;
    }

    public void setLaconic(String laconic) {
        this.laconic = laconic;
    }

    public List<String> getRelatedTropes() {
        return relatedTropes;
    }

    public void setRelatedTropes(List<String> relatedTropes) {
        this.relatedTropes = relatedTropes;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public Long getMediaTypeCount() {
        return mediaTypeCount;
    }

    public void setMediaTypeCount(Long mediaTypeCount) {
        this.mediaTypeCount = mediaTypeCount;
    }

    public Long getMediaUrlsCount() {
        return mediaUrlsCount;
    }

    public void setMediaUrlsCount(Long mediaUrlsCount) {
        this.mediaUrlsCount = mediaUrlsCount;
    }

    public Integer getRelatedTropesCount() {
        return relatedTropesCount;
    }

    public void setRelatedTropesCount(Integer relatedTropesCount) {
        this.relatedTropesCount = relatedTropesCount;
    }

}
