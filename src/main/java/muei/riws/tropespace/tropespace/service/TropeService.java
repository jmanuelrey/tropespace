package muei.riws.tropespace.tropespace.service;

import muei.riws.tropespace.tropespace.model.Filter;
import muei.riws.tropespace.tropespace.model.Trope;
import java.util.List;

import org.springframework.data.domain.Page;

public interface TropeService {

    Page<Trope> searchTropes(String keywords, Filter filter, int startIndex);

}
