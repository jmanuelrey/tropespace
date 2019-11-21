package muei.riws.tropespace.tropespace.service;

import muei.riws.tropespace.tropespace.model.Filter;
import muei.riws.tropespace.tropespace.model.Trope;
import java.util.List;

public interface TropeService {

    List<Trope> searchTropes(String keywords, Filter filter);

}
