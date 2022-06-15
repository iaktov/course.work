package homework.course.work.controller;

import homework.course.work.Interface.EmployeeService;
import homework.course.work.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee add(@RequestParam(value = "firstName") String firstName,
                        @RequestParam(value = "lastName") String lastName,
                        @RequestParam(value = "salary") int salary,
                        @RequestParam(value = "departmentId") int department){
        return employeeService.addAnEmployee(firstName, lastName, salary, department);
    }


    @GetMapping
    public List<Employee> allEmployees() {

        return employeeService.employeesList();
    }


}
