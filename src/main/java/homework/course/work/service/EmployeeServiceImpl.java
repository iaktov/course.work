package homework.course.work.service;

import homework.course.work.model.Employee;
import homework.course.work.Interface.EmployeeService;
import homework.course.work.Exeption.EmployeeAlreadyAddedException;
import homework.course.work.Exeption.EmployeeNotFoundException;
import homework.course.work.Exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap(10);

    private String getKey(Employee employee) {
        return employee.getFirstName() + " " + employee.getLastName();
    }


    @Override
    public Employee addAnEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(getKey(employee))) {
            throw new EmployeeAlreadyAddedException();

        }
        if (employees.size() < 10) {
            return employees.put(getKey(employee), employee);
        }
        throw new EmployeeStorageIsFullException();


    }


    @Override
    public Employee deleteAnEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(getKey(employee))) {
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
        if (!employees.containsKey(getKey(employee))) {
            throw new EmployeeNotFoundException();

        }
        employees.remove(getKey(employee));
        return employee;

    }

    @Override
    public List<Employee> employeesList() {
        return new ArrayList<>(employees.values());
    }


}

