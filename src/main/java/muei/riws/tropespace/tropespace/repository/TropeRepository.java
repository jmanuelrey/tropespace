package muei.riws.tropespace.tropespace.repository;

import muei.riws.tropespace.tropespace.model.Trope;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TropeRepository extends ElasticsearchRepository<Trope, String>{
    
    // TODO: todos los metodos deben ser ordenados, en este caso es por nombre del tropo
    // Esto es así para que búsquedas sucesivas sean consistentes a la hora de implementar paginación
    // (Page, Pageable)
    
    List<Trope> findByNameOrderByName(String name);
    
    List<Trope> findByNameOrLaconicOrContentOrderByName(String keywords);
    
    List<Trope> findByMediaOrderByName(List<String> media);
    
    List<Trope> findByRelatedTropesOrderByName(String relatedTropes);
    
    // TODO: las queries automaticas con "greater than" lo hacen sobre el propio criterio de búsqueda (p.ej: finbByMediaUrlsCountGreaterThan)
    // Para lo que necesitamos hacer, habrá que construir el string de la query y pasarselo a la anotación "@Query" encima de la firma del metodo
    
    // EJEMPLO: 
    // @Query("{\"bool\" : {\"must\" : {\"field\" : {\"name\" : \"?0\"}}}}")
    // Page<Book> findByName(String name);

}