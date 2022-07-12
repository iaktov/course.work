package homework.course.work;

import homework.course.work.Exeption.EmployeeNotFoundException;
import homework.course.work.model.Employee;
import homework.course.work.service.DepartmentServiceImpl;
import homework.course.work.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;


    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Ivan", "Ivanov", 24_000, 3),
                new Employee("Jack", "Jackson", 25_000, 1),
                new Employee("Marry", "Allen", 36_000, 2),
                new Employee("Don", "Mon", 20_000, 3),
                new Employee("James", "Franco", 21_000, 1),
                new Employee("Bond", "Low", 32_000, 2)
        );
        when(employeeService.employeesList()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeListMaxSalary")
    public void employeeWithMaxSalaryTest(int department, Employee employee) {
        assertThat(departmentService.maxSalary(department)).isEqualTo(employee);
    }

    @Test
    public void employeeWithMaxSalaryNotFoundTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.maxSalary(4));
    }


    @ParameterizedTest
    @MethodSource("employeeListMinSalary")
    public void employeeWithMinSalaryTest(int department, Employee employee) {
        assertThat(departmentService.minSalary(department)).isEqualTo(employee);
    }

    @Test
    public void employeeWithMinSalaryNotFoundTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.minSalary(4));
    }


    @ParameterizedTest
    @MethodSource("employeesInDepartment")
    public void employeesInDepartmentTest(int department, List<Employee> employee) {
        assertThat(departmentService.employeesInDepartment(department)).containsExactlyElementsOf(employee);
    }


    @Test
    public void employeesByDepartmentTest() {
        assertThat(departmentService.employeesByDepartment()).containsAllEntriesOf(
                Map.of(
                        1, List.of(
                                new Employee("Jack", "Jackson", 25_000, 1),
                                new Employee("James", "Franco", 21_000, 1)),
                        2, List.of(
                                new Employee("Marry", "Allen", 36_000, 2),
                                new Employee("Bond", "Low", 32_000, 2)),
                        3, List.of(
                                new Employee("Ivan", "Ivanov", 24_000, 3),
                                new Employee("Don", "Mon", 20_000, 3))
                )
        );

    }

    public static Stream<Arguments> employeeListMaxSalary() {
        return Stream.of(
                Arguments.of(3, new Employee("Ivan", "Ivanov", 24_000, 3)),
                Arguments.of(1, new Employee("Jack", "Jackson", 25_000, 1)),
                Arguments.of(2, new Employee("Marry", "Allen", 36_000, 2))
        );
    }

    public static Stream<Arguments> employeeListMinSalary() {
        return Stream.of(
                Arguments.of(3, new Employee("Don", "Mon", 20_000, 3)),
                Arguments.of(1, new Employee("James", "Franco", 21_000, 1)),
                Arguments.of(2, new Employee("Bond", "Low", 32_000, 2))
        );
    }

    public static Stream<Arguments> employeesInDepartment() {
        return Stream.of(
                Arguments.of(1, List.of(
                        new Employee("Jack", "Jackson", 25_000, 1),
                        new Employee("James", "Franco", 21_000, 1))),
                Arguments.of(2, List.of(
                        new Employee("Marry", "Allen", 36_000, 2),
                        new Employee("Bond", "Low", 32_000, 2))),
                Arguments.of(3, List.of(
                        new Employee("Ivan", "Ivanov", 24_000, 3),
                        new Employee("Don", "Mon", 20_000, 3)))
        );
    }
}
