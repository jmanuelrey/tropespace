package muei.riws.tropespace.tropespace.web.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import muei.riws.tropespace.tropespace.model.Filter;
import muei.riws.tropespace.tropespace.model.Trope;
import muei.riws.tropespace.tropespace.service.TropeService;

@Controller
@RequestMapping("/tropes")
public class TropeController {
    
    @Autowired
    private TropeService tropeService;
    
    private Page<Trope> resultTropes;
    
    // TODO quitar filters, meter parámetros
    @GetMapping("/search/{startIndex}")
    public String searchTropes(@RequestParam("searchBoxText") String searchBoxText,
    		@RequestParam("searchBy") String searchBy,
    		@RequestParam(defaultValue = "0") Integer relatedMediaMin,
    		@RequestParam(defaultValue = "0") Integer relatedTropesMin,
    		@RequestParam(defaultValue = "*") String mediaType,
    		@RequestParam("sortBy") String sortBy,
    		@RequestParam("sortByOrder") String sortByOrder,
    		@PathVariable int startIndex,
    		Model model) {
        // We set the values needed by the template (TODO: pensar si enviarle tropo a tropo o la lista entera de tropos)
    	Filter filter = new Filter(Filter.SearchBy.valueOf(searchBy), relatedMediaMin, relatedTropesMin, mediaType, Filter.SortBy.valueOf(sortBy), Filter.SortByOrder.valueOf(sortByOrder));
    	resultTropes = tropeService.searchTropes(searchBoxText, filter, startIndex);
    	//System.out.println(resultTropes.toList().get(0).getName());

    	model.addAttribute("requestedString", searchBoxText);
        model.addAttribute("tropes", resultTropes);
        model.addAttribute("currentIndex", startIndex);
        model.addAttribute("totalIndex", resultTropes.getTotalPages());
        model.addAttribute("currentResults", resultTropes.getNumberOfElements());
        model.addAttribute("totalResults", resultTropes.getTotalElements());
        
        if (resultTropes.hasNext()) {
            model.addAttribute("hasNext", resultTropes.hasNext());
            int nextIndex = startIndex + 1;
            String nextUrl = "/tropes/search/" + nextIndex +
                    "?searchBoxText=" + searchBoxText +
                    "&searchBy=" + searchBy +
                    "&mediaType=" + mediaType +
                    "&relatedMediaMin=" + relatedMediaMin +
                    "&relatedTropesMin=" + relatedTropesMin + 
                    "&sortBy=" + sortBy +
                    "&sortByOrder=" + sortByOrder;
            model.addAttribute("nextUrl", nextUrl);
        }
        
        if (resultTropes.hasPrevious()) {
            model.addAttribute("hasPrevious", resultTropes.hasPrevious());
            int prevIndex = startIndex - 1;
            String prevUrl = "/tropes/search/" + prevIndex +
                    "?searchBoxText=" + searchBoxText +
                    "&searchBy=" + searchBy +
                    "&mediaType=" + mediaType +
                    "&relatedMediaMin=" + relatedMediaMin +
                    "&relatedTropesMin=" + relatedTropesMin + 
                    "&sortBy=" + sortBy +
                    "&sortByOrder=" + sortByOrder;
            model.addAttribute("prevUrl", prevUrl);
        }


        return "result-page";
    }
    
	@GetMapping("/search/trope")
    public String showTrope(@RequestParam("id") String id, Model model) {
    	Trope trope = new Trope();
    	
    	for(Trope tr : resultTropes.toList()) {
    		if(tr.getId().contentEquals(id)) {
    			trope = tr;
    		}
    	}

    	model.addAttribute("trope", trope);
    	
    	return "trope-page";
    }

}