package muei.riws.tropespace.tropespace.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Media {
	@JsonProperty("media_type")
	private String mediaType;
	@JsonProperty("media_urls")
	private List<String> mediaUrls;
	
	public Media() {
		
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public List<String> getMediaUrls() {
		return mediaUrls;
	}

	public void setMediaUrls(List<String> mediaUrls) {
		this.mediaUrls = mediaUrls;
	}
	
}
