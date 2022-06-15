package homework.course.work.controller;

import homework.course.work.model.Employee;
import homework.course.work.service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalary(@RequestParam(value = "departmentId") int department) {
        return departmentService.maxSalary(department);
    }

    @GetMapping(path = "/min-salary")
    public Employee minSalary(@RequestParam(value = "departmentId") int department) {
        return departmentService.minSalary(department);
    }



    @GetMapping(path = "/all", params = "department")
    public List<String> employeesInDepartment(@RequestParam(value = "departmentId", required = false) Integer department) {
        return departmentService.employeesInDepartment(department);
    }

    @GetMapping(path = "/all")
    public Map<Object, List<Employee>> employeesByDepartment() {
        return departmentService.employeesByDepartment();
    }




}
