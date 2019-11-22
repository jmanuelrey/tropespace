package muei.riws.tropespace.tropespace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import muei.riws.tropespace.tropespace.model.Filter;
import muei.riws.tropespace.tropespace.model.Media;
import muei.riws.tropespace.tropespace.model.Trope;
import muei.riws.tropespace.tropespace.service.TropeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EsConfig.class)
public class TropeServiceTest {

	@Autowired
	private TropeService tropeService;

	@Test
	public void testSearchTropesByName() {

		String tropeName = "...And That";
		String resultName = "";
		Filter filter = new Filter(Filter.SearchBy.name, 0, 0, "", Filter.SortBy.name);

		Page<Trope> result = tropeService.searchTropes(tropeName, filter, 0);
		List<Trope> resultList = result.toList();

		resultName = resultList.get(0).getName();

		assertNotNull(resultName);

	}

	@Test
	public void testSearchTropesByNameAndRelatedTropesCountGreaterThanAndOrderByName() {

		String tropeName = "...And That";
		Filter filter = new Filter(Filter.SearchBy.name, 0, 0, "", Filter.SortBy.name);

		Page<Trope> result = tropeService.searchTropes(tropeName, filter, 0);
		List<Trope> resultList = result.toList();

		assertEquals(10, resultList.size());

		filter.setRelatedTropesNumber(1000);

		result = tropeService.searchTropes(tropeName, filter, 0);
		resultList = result.toList();

		assertEquals(0, resultList.size());

	}

	@Test
	public void testSearchTropesByNameAndFilterByMediaType() {

		String tropeName = "...And That";
		Filter filter = new Filter(Filter.SearchBy.name, 0, 0, "", Filter.SortBy.name);

		Page<Trope> result = tropeService.searchTropes(tropeName, filter, 0);
		List<Trope> resultList = result.toList();
		
		assertEquals(10, resultList.size());

		filter.setMediaType("VideoGame");

		result = tropeService.searchTropes(tropeName, filter, 0);
		resultList = result.toList();
		assertEquals(10, resultList.size());

	}

}