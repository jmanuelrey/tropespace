package muei.riws.tropespace.tropespace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import muei.riws.tropespace.tropespace.model.Trope;
import muei.riws.tropespace.tropespace.service.TropeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EsConfig.class)
public class TropeServiceTest {
    
    @Autowired
    private TropeService tropeService;
    
    @Test
    public void testSearchTropesByName() {
        String name = "Necromancer";
        
        List<Trope> tropes = tropeService.searchTropesByName(name);
        
        String testName = tropes.get(0).getName();
        
        assertNotNull(testName);
        assertEquals(testName, "Necromancer");
        
    }

}