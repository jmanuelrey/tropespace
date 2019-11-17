package muei.riws.tropespace.tropespace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import muei.riws.tropespace.tropespace.model.Trope;
import muei.riws.tropespace.tropespace.repository.TropeRepository;

@Service
public class TropeServiceImpl implements TropeService{
    
    private TropeRepository tropeRepository;
    
    @Autowired
    public void setTropeRepository(TropeRepository tropeRepository) {
        this.tropeRepository = tropeRepository;
    }

    public List<Trope> searchTropesByName(String name){
        return tropeRepository.findByName(name);
    }

    public List<Trope> searchTropesByKeywords(String keywords){
        return tropeRepository.findByNameOrLaconicOrContent(keywords);
        
    }

    public List<Trope> searchTropesByMedia(List<String> media){
        return tropeRepository.findByMedia(media);
        
    }

    public List<Trope> searchTropesByTropeName(String tropeName){
        return tropeRepository.findByRelatedTropes(tropeName);
        
    }

}
