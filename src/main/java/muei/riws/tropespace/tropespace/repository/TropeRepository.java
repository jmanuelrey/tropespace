package muei.riws.tropespace.tropespace.repository;

import muei.riws.tropespace.tropespace.model.Trope;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TropeRepository extends ElasticsearchRepository<Trope, String>{
    
    List<Trope> findByName(String name);
    
    List<Trope> findByNameOrLaconicOrContent(String keywords);
    
    List<Trope> findByMedia(List<String> media);
    
    List<Trope> findByRelatedTropes(String relatedTropes);

}