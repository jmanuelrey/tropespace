package muei.riws.tropespace.tropespace.repository;

import muei.riws.tropespace.tropespace.model.Trope;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TropeRepository extends ElasticsearchRepository<Trope, String>{
    
    // TODO: todos los metodos deben ser ordenados, en este caso es por nombre del tropo
    // Esto es así para que búsquedas sucesivas sean consistentes a la hora de implementar paginación
    // (Page, Pageable)
    
	List<Trope> findByName(String name);
	
    List<Trope> findByNameOrderByName(String name);
    
    List<Trope> findByNameOrLaconicOrContentOrderByName(String keywords);
    
    List<Trope> findByMediaOrderByName(List<String> media);
    
    List<Trope> findByRelatedTropesOrderByName(String relatedTropes);
    
    // TODO: las queries automaticas con "greater than" lo hacen sobre el propio criterio de búsqueda (p.ej: finbByMediaUrlsCountGreaterThan)
    // Para lo que necesitamos hacer, habrá que construir el string de la query y pasarselo a la anotación "@Query" encima de la firma del metodo
    
    /*@Query("{\"bool\" "
    		+ ": {\"must\": ["
			+ "{\"match\": {\"name\" : \"?0\"} },"
			+ "{\"range\" : { \"related_tropes_count\": {\"gt\" : \"?1\"} } }" 
			+"] } }, "
		+"\"sort\": { \"name.raw\": \"asc\"}")
    List<Trope> findByNameAndRelatedTropesCountGreaterThanAndOrderByName(String name, int minCount);*/
    
    @Query("{\"bool\" "
    		+ ": {\"must\": ["
    			+ "{\"match\": {\"name\" : \"?0\"} },"
				+ "{\"range\" : { \"related_tropes_count\": {\"gt\" : \"?1\"} } }," 
				+ "{\"range\" : { \"media_urls_count\": {\"gt\" : \"?2\"} } }"
				+"] } }")
    List<Trope> findByNameWithFilterAndOrder(String name, int relatedTropesMin, int relatedMediaMin);
    

    @Query("{\"bool\" "
    		+ ": {\"must\": ["
    			+ "{\"match\": {\"name\" : \"?0\"} },"
				+ "{\"range\" : { \"related_tropes_count\": {\"gt\" : \"?1\"} } }," 
				+ "{\"range\" : { \"media_urls_count\": {\"gt\" : \"?2\"} } }"
				+"] } }, "
		+"\"sort\": { \"?3\": \"asc\"}")
    List<Trope> findByNameWithFilterAndOrder(String name, int relatedTropesMin, int relatedMediaMin, String sortBy);
    

    @Query("{\"bool\" "
    		+ ": {\"must\": ["
    			+ "{\"match\": {\"name\" : \"?0\"} },"
				+ "{\"range\" : { \"related_tropes_count\": {\"gt\" : \"?1\"} } }," 
				+ "{\"range\" : { \"media_urls_count\": {\"gt\" : \"?2\"} } }," 
				+ "{"
					+ "\"nested\":"
					+ "{"
						+ "\"path\": \"media\","
						+ "\"query\": {"
							+ "\"bool\": {"
								+ "\"must\": ["
									+ "{\"match\": {\"media.media_type\" : \"?3\"} }"
								+ "]"
							+ "}"
						+ "}"
					+ "}"
				+ "}"
				+"] } }, "
		+"\"sort\": { \"?4\": \"asc\"}")
    List<Trope> findByNameWithFilterAndOrder(String name, int relatedTropesMin, int relatedMediaMin, String mediaType, String sortBy);

}