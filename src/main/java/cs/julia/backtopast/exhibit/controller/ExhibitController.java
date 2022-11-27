package cs.julia.backtopast.exhibit.controller;

import cs.julia.backtopast.department.domain.Department;
import cs.julia.backtopast.department.service.DepartmentService;
import cs.julia.backtopast.exhibit.controller.dto.ExhibitDto;
import cs.julia.backtopast.exhibit.domain.Exhibit;
import cs.julia.backtopast.exhibit.service.ExhibitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/exhibits")
public class ExhibitController {
    private final ExhibitService exhibitService;
    private final DepartmentService departmentService;

    @Autowired
    public ExhibitController(ExhibitService exhibitService, DepartmentService departmentService) {
        this.exhibitService = exhibitService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String showExhibits(Model model, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1), pageSize = size.orElse(25);
        Page<Exhibit> exhibitPage = exhibitService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("exhibitPage", exhibitPage);

        int totalPages = exhibitPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "exhibits";
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
    public String getDeleteExhibit(Model model, @PathVariable("id") int id) {
        model.addAttribute("exhibitToDelete", new Exhibit());
        Exhibit exhibit = exhibitService.findById(id);
        model.addAttribute("exhibit", exhibit);
        return "redirect:/exhibits";
    }

    @PostMapping("/deleteExhibit/{id}")
    public String deleteExhibit(@ModelAttribute("exhibitToDelete") Exhibit exhibit) {
        exhibitService.deleteExhibit(exhibit.getId());
        return "redirect:/exhibits";
    }

    @GetMapping("/updateExhibit/{id}")
    public String getUpdateExhibit(Model model, @PathVariable("id") int id) {
        model.addAttribute("exhibitToUpdate", new Exhibit());
        Exhibit exhibit = exhibitService.findById(id);
        model.addAttribute("exhibit", exhibit);
        return "/updateExhibit";
    }

    @PostMapping("/updateExhibit/{id}")
    public String updateExhibit(@ModelAttribute("exhibitToUpdate") Exhibit exhibit) {
        exhibitService.updateExhibitDescription(exhibit.getId(), exhibit.getDescription());
        return "redirect:/exhibits";
    }
}
