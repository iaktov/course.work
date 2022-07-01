package homework.course.work.Interface;

import homework.course.work.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee maxSalary(int department);

    Employee minSalary(int department);

    List<String> employeesInDepartment(Integer department);

    Map<Object, List<Employee>> employeesByDepartment();



}
