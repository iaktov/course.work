package homework.course.work.service;

import homework.course.work.Interface.DepartmentService;
import homework.course.work.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {

        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalary(int department) {
        final List<Employee> employeeList = employeeService.employeesList();
        return employeeList.stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow();
    }

    @Override
    public Employee minSalary(int department) {
        final List<Employee> employeeList = employeeService.employeesList();
        return employeeList.stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow();
    }

    @Override
    public List<String> employeesInDepartment(Integer department) {
        final List<Employee> employeeList = employeeService.employeesList();
        return employeeList.stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(e -> e.getFirstName() + "_" + e.getLastName() + "_" + e.getSalary() + "_" + e.getDepartment())
                .collect(Collectors.toList());

    }

    @Override
    public Map<Object, List<Employee>> employeesByDepartment() {
        final List<Employee> employeeList = employeeService.employeesList();
        return employeeList.stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment(),
                        Collectors.toList()));

    }
}
