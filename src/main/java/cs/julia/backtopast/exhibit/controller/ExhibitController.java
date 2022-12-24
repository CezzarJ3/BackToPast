package cs.julia.backtopast.exhibit.controller;

import cs.julia.backtopast.department.domain.Department;
import cs.julia.backtopast.department.service.DepartmentService;
import cs.julia.backtopast.exhibit.controller.dto.ExhibitDto;
import cs.julia.backtopast.exhibit.domain.Exhibit;
import cs.julia.backtopast.exhibit.service.ExhibitService;
import cs.julia.backtopast.exhibitionpart.domain.ExhibitionPart;
import cs.julia.backtopast.exhibitionpart.service.ExhibitionPartService;
import cs.julia.backtopast.exhibitionpart.service.ExhibitionPartServiceImpl;
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
@RequestMapping("/exhibits")
public class ExhibitController {
    private final ExhibitService exhibitService;
    private final DepartmentService departmentService;

    private final ExhibitionPartService exhibitionPartService;

    @Autowired
    public ExhibitController(ExhibitService exhibitService, DepartmentService departmentService, ExhibitionPartService exhibitionPartService) {
        this.exhibitService = exhibitService;
        this.departmentService = departmentService;
        this.exhibitionPartService = exhibitionPartService;
    }

    @GetMapping
    public String showExhibits(Model model, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size, @RequestParam(value = "name", required = false) String name) {

        if (name == null) {
            name = "";
        }
        int currentPage = page.orElse(1), pageSize = size.orElse(25);
        Page<Exhibit> exhibitPage = exhibitService.findPaginated(PageRequest.of(currentPage - 1, pageSize), name);
        model.addAttribute("exhibitPage", exhibitPage);

        int totalPages = exhibitPage.getTotalPages();
        System.out.println(totalPages);

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
        model.addAttribute("exhibitToUpdate", new Exhibit());

        int totalExhibits = exhibitService.findExhibitsByName("").size();
        model.addAttribute("totalExhibits", totalExhibits);
        return "exhibits";
    }

    @GetMapping("/exhibitionParts/{id}")
    public String showExhibitsAtExhibitions(Model model, @PathVariable("id") int id) {
        List<ExhibitionPart> exhibitionPartsList = (List<ExhibitionPart>) exhibitionPartService.findExhibitionPartByExhibitId(id);
        model.addAttribute("exhibitionParts", exhibitionPartsList);
        return "exhibitionParts";
    }

    @GetMapping("/filter")
    public String showExhibitsByName(@RequestParam(value = "name", required = false) String name, Model model) {
        List<Exhibit> exhibits = (List<Exhibit>) exhibitService.findExhibitsByName(name);
        model.addAttribute("exhibits", exhibits);
        return "exhibits";
    }

    @PostMapping("/createExhibit")
    public String createExhibit(@ModelAttribute("exhibitDto") ExhibitDto exhibitDto) {
        exhibitService.createExhibit(exhibitDto);
        return "redirect:/exhibits/createExhibit";
    }

    @GetMapping("/createExhibit")
    public String getExhibitDto(Model model) {
        model.addAttribute("exhibitDto", new ExhibitDto("", "", 0, "", 0));
        List<Department> departments = (List<Department>) departmentService.findAll();
        model.addAttribute("deps", departments);
        return "createExhibit";
    }

    @GetMapping("/deleteExhibit/{id}")
    public String getDeleteExhibit(@PathVariable("id") int id) {
        exhibitService.deleteExhibit(id);
        return "redirect:/exhibits";
    }

    @GetMapping("/updateExhibit/{id}")
    public String getUpdateExhibit(Model model, @PathVariable("id") int id) {
        model.addAttribute("exhibitToUpdate", new Exhibit());
        model.addAttribute("exhibitToDelete", new Exhibit());
        Exhibit exhibit = exhibitService.findById(id);
        model.addAttribute("exhibit", exhibit);
        return "/updateExhibit";
    }

    @PostMapping("/{id}")
    public String updateExhibit(@ModelAttribute("exhibitToUpdate") Exhibit exhibit, @PathVariable("id") Integer id) {
        exhibitService.updateExhibitDescription(id, exhibit.getDescription());
        return "redirect:/exhibits";
    }
}
