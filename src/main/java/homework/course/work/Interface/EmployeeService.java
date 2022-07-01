package homework.course.work.Interface;


import homework.course.work.model.Employee;

import java.util.List;


public interface EmployeeService {

    Employee addAnEmployee(String firstName, String lastName, int salary, int department);



    List<Employee> employeesList();


}
