package homework.course.work.service;

import homework.course.work.model.Employee;
import homework.course.work.Interface.EmployeeService;
import homework.course.work.Exeption.EmployeeAlreadyAddedException;
import homework.course.work.Exeption.EmployeeNotFoundException;
import homework.course.work.Exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees = new ArrayList<>(10);


    @Override
    public Employee addAnEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();

        }
        if (employees.size() < 10) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();


    }


    @Override
    public Employee deleteAnEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();

        }
        for (int i = 0; i < employees.size(); i++) {
            if (Objects.equals(employees.get(i), employee)) {
                employees.remove(i);
                break;
            }
        }

        return employee;
    }


    @Override
    public Employee findAnEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();

        }
        employees.remove(employee);
        return employee;

    }

    @Override
    public List<Employee> employeesList() {
        return new ArrayList<>(employees);
    }


}

