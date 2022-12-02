package cs.julia.backtopast.department.controller;

import cs.julia.backtopast.department.domain.Department;
import cs.julia.backtopast.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ModelAttribute
    public Department department() {
        return new Department();
    }

    @GetMapping
    public String showDepartments(Model model) {
        List<Department> departments = (List<Department>) departmentService.findAll();
        model.addAttribute("deps", departments);
        return "departments";
    }

    @GetMapping("/filter")
    public String showDepartmentsByName(@RequestParam("name") String name, Model model) {
        List<Department> departments = (List<Department>) departmentService.findAllByName(name);
        model.addAttribute("deps", departments);
        return "departments";
    }

    @PostMapping
    public String createDepartment(@ModelAttribute Department department) {
        System.out.println(department.toString());
        departmentService.create(department);
        return "redirect:/departments";
    }

    @GetMapping("/deleteDepartment/{id}")
    public String getDeleteDepartment(@PathVariable("id") Integer id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }

}
