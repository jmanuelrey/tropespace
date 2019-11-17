package muei.riws.tropespace.tropespace.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "tropes", type = "_doc")
public class Trope {

    private String name;
    private String content;
    private String laconic;
    private List<String> relatedTropes;
    private Map<String, List<String>> media;

    public Trope() {

    }

    public Trope(String name, String content, String laconic, List<String> relatedTropes,
            Map<String, List<String>> media) {
        this.name = name;
        this.content = content;
        this.laconic = laconic;
        this.relatedTropes = relatedTropes;
        this.media = media;
    }

    @Id
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

    public Map<String, List<String>> getMedia() {
        return media;
    }

    public void setMedia(Map<String, List<String>> media) {
        this.media = media;
    }

}
