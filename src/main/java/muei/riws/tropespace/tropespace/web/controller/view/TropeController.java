package muei.riws.tropespace.tropespace.web.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import muei.riws.tropespace.tropespace.model.Filter;
import muei.riws.tropespace.tropespace.model.Trope;
import muei.riws.tropespace.tropespace.service.TropeService;

@RestController
@RequestMapping("/tropes")
public class TropeController {
    
    @Autowired
    private TropeService tropeService;
    
    @GetMapping("/tropes/searchByName")
    public String searchTropesByName(@RequestAttribute String name, @RequestAttribute Filter filter, Model model) {
        // We set the values needed by the template (TODO: pensar si enviarle tropo a tropo o la lista entera de tropos)
        model.addAttribute("tropes", tropeService.searchTropesByName(name, filter));
        // We return the template name as a string (TODO: tropePage es un nombre de ejemplo para la página con la lista de tropos)
        return "resultPage";
    }
    
    @GetMapping("/tropes/searchByKeywords")
    public String searchTropesByKeywords(@RequestAttribute String keywords, @RequestAttribute Filter filter, Model model) {
        model.addAttribute("tropes", tropeService.searchTropesByKeywords(keywords, filter));
        return "resultPage";
    }
    
    @GetMapping("/tropes/searchByMedia")
    public String searchTropesByMedia(@RequestAttribute List<String> media, @RequestAttribute Filter filter, Model model) {
        model.addAttribute("tropes", tropeService.searchTropesByMedia(media, filter));
        return "resultPage";
    }
    
    @GetMapping("/tropes/searchByTropeName")
    public String searchTropesByTropeName(@RequestAttribute String tropeName, @RequestAttribute Filter filter, Model model) {
        model.addAttribute("tropes",tropeService.searchTropesByTropeName(tropeName, filter));
        return "resultPage";
    }

}