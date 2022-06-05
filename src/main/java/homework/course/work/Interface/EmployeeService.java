package homework.course.work.Interface;


import homework.course.work.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addAnEmployee(String firstName, String lastName);

    Employee deleteAnEmployee(String firstName, String lastName);

    Employee findAnEmployee(String firstName, String lastName);

    List<Employee> employeesList();


}
