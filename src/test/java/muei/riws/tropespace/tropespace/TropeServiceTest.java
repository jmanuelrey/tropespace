package muei.riws.tropespace.tropespace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

		List<Trope> result = tropeService.searchTropes(tropeName, filter);

		resultName = result.get(0).getName();

		assertNotNull(resultName);
		assertTrue(resultName.contains(tropeName));
		assertEquals(2, result.size());

	}

	@Test
	public void testSearchTropesByNameAndRelatedTropesCountGreaterThanAndOrderByName() {

		String tropeName = "...And That";
		Filter filter = new Filter(Filter.SearchBy.name, 0, 0, "", Filter.SortBy.name);

		List<Trope> result = tropeService.searchTropes(tropeName, filter);

		assertEquals(1, result.size());

		filter.setRelatedTropesNumber(1000);

		result = tropeService.searchTropes(tropeName, filter);

		assertEquals(0, result.size());

	}

	@Test
	public void testSearchTropesByNameAndFilterByMediaType() {

		String tropeName = "...And That";
		Filter filter = new Filter(Filter.SearchBy.name, 0, 0, "", Filter.SortBy.name);

		List<Trope> result = tropeService.searchTropes(tropeName, filter);
		assertEquals(2, result.size());

		filter.setMediaType("VideoGame");

		result = tropeService.searchTropes(tropeName, filter);
		assertEquals(1, result.size());

	}

}