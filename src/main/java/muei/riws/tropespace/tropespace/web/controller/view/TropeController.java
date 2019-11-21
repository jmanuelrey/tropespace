package muei.riws.tropespace.tropespace.web.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
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
    // TODO quitar filters, meter parámetros
    @GetMapping("/search")
    public String searchTropes(@RequestParam("searchBoxText") String searchBoxText, @RequestAttribute(required = false) Filter filter, Model model) {
        // We set the values needed by the template (TODO: pensar si enviarle tropo a tropo o la lista entera de tropos)
    	List<Trope> tropes = tropeService.searchTropes(searchBoxText, filter);
    	model.addAttribute("requestedString", searchBoxText);
        model.addAttribute("tropes", tropes);
        // We return the template name as a string (TODO: tropePage es un nombre de ejemplo para la página con la lista de tropos)
        return "result-page";
    }
    
    @GetMapping("/trope/")
    public String showTrope(@RequestAttribute Trope trope) {
    	return "trope-page";
    }

}