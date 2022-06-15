package homework.course.work.service;

import homework.course.work.model.Employee;
import homework.course.work.Interface.EmployeeService;
import homework.course.work.Exeption.EmployeeAlreadyAddedException;
import homework.course.work.Exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap(10);

    private String getKey(Employee employee) {

        return employee.getFirstName() + " " + employee.getLastName();
    }


    @Override
    public Employee addAnEmployee(String firstName, String lastName, int salary,int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);

        if (employees.containsKey(getKey(employee))) {
            throw new EmployeeAlreadyAddedException();

        }
        if (employees.size() < 10) {
            return employees.put(getKey(employee), employee);
        }
        throw new EmployeeStorageIsFullException();


    }

    @Override
    public List<Employee> employeesList() {

        return new ArrayList<>(employees.values());
    }


}

