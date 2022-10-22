package cs.julia.backtopast.department.controller;

import cs.julia.backtopast.department.domain.Department;
import cs.julia.backtopast.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String showDepartments(Model model) {
        List<Department> departments = (List<Department>) departmentService.findAll();
        model.addAttribute("deps", departments);
        return "departments";
    }
}
