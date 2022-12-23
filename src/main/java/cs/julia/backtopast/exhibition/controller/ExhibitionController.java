package cs.julia.backtopast.exhibition.controller;

import cs.julia.backtopast.exhibit.controller.dto.ExhibitDto;
import cs.julia.backtopast.exhibition.controller.dto.ExhibitionDto;
import cs.julia.backtopast.exhibition.domain.Exhibition;
import cs.julia.backtopast.exhibition.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/exhibitions")
public class ExhibitionController {
    private final ExhibitionService exhibitionService;

    @Autowired
    public ExhibitionController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @GetMapping
    public String showExhibitions(Model model, @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1), pageSize = size.orElse(25);
        Page<Exhibition> exhibitionPage = exhibitionService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("exhibitionPage", exhibitionPage);

        int totalPages = exhibitionPage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers;
            if (totalPages > 10) {
                pageNumbers = IntStream.range(
                        currentPage > 5 ? (currentPage - 5) : 1,
                        (totalPages - currentPage) > 5 ? (currentPage + 5) : totalPages
                ).boxed().toList();
            } else {
                pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed().toList();
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "exhibitions";
    }

    @PostMapping("/createExhibition")
    public String createExhibit(@ModelAttribute("exhibitionDto") ExhibitionDto exhibitionDto) {
        exhibitionService.createExhibition(exhibitionDto);
        return "redirect:/exhibitions";
    }

    @GetMapping("/createExhibition")
    public String getExhibitionDto(Model model) {
        model.addAttribute("exhibitionDto", new ExhibitionDto("", "", "", "", "", "", ""));
        return "createExhibition";
    }
}
