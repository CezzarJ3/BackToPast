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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/createExhibitionPart/{id}")
    public String createExhibitionPart(Model model, @PathVariable("id") int id) {
        Exhibit exhibits = exhibitService.findById(id);
        List<Exhibition> exhibitions = (List<Exhibition>) exhibitionService.finaAllByName("");
        model.addAttribute("exhibit", exhibits);
        model.addAttribute("exhibitions", exhibitions);
        model.addAttribute("exhibitionPartDto", new ExhibitionPartDto(0, 0));

        return "createExhibitionPart";
    }

    @PostMapping("/createExhibitionPart/{id}")
    public String postCreateExhibitionPart(@ModelAttribute("exhibitionPartDto") ExhibitionPartDto exhibitionPartDto, @PathVariable("id") int id) {
        exhibitionPartService.createExhibitionPart(exhibitionPartDto);
        return "redirect:/exhibits";
    }
}
