package cs.julia.backtopast.exhibitionpart.controller;

import cs.julia.backtopast.exhibit.domain.Exhibit;
import cs.julia.backtopast.exhibit.service.ExhibitService;
import cs.julia.backtopast.exhibition.domain.Exhibition;
import cs.julia.backtopast.exhibition.service.ExhibitionService;
import cs.julia.backtopast.exhibitionpart.controller.dto.ExhibitionPartDto;
import cs.julia.backtopast.exhibitionpart.service.ExhibitionPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/exhibitionParts")
public class ExhibitionPartController {
    private final ExhibitionService exhibitionService;
    private final ExhibitService exhibitService;
    private final ExhibitionPartService exhibitionPartService;

    @Autowired
    public ExhibitionPartController(ExhibitionService exhibitionService, ExhibitService exhibitService, ExhibitionPartService exhibitionPartService) {
        this.exhibitionService = exhibitionService;
        this.exhibitService = exhibitService;
        this.exhibitionPartService = exhibitionPartService;
    }

    @GetMapping("/createExhibitionPart")
    public String createExhibitionPart(Model model) {
        List<Exhibit> exhibits = (List<Exhibit>) exhibitService.findExhibitsByName("");
        List<Exhibition> exhibitions = (List<Exhibition>) exhibitionService.finaAllByName("");
        model.addAttribute("exhibits", exhibits);
        model.addAttribute("exhibitions", exhibitions);
        model.addAttribute("exhibitionPartDto", new ExhibitionPartDto(0, 0));

        return "createExhibitionPart";
    }

    @PostMapping("/createExhibitionPart")
    public String postCreateExhibitionPart(@ModelAttribute("exhibitionPartDto") ExhibitionPartDto exhibitionPartDto) {
        exhibitionPartService.createExhibitionPart(exhibitionPartDto);
        return "redirect:/exhibits";
    }
}
