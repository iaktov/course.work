package homework.course.work;

import homework.course.work.Exeption.EmployeeAlreadyAddedException;
import homework.course.work.Exeption.EmployeeStorageIsFullException;
import homework.course.work.model.Employee;
import homework.course.work.service.EmployeeServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class EmployeeServiceTest {

    private final EmployeeServiceImpl actual = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource("paramsForAdd")
    public void employeeAlreadyAddedTest(String firstName, String lastName, int salary, int department) {
        Employee expected = new Employee(firstName, lastName, salary, department);
        assertThat(actual.addAnEmployee(firstName, lastName, salary, department)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> actual.addAnEmployee(firstName, lastName, salary, department));

    }

    @ParameterizedTest
    @MethodSource("paramsForAdd")
    public void employeeStorageIsFullTest(String firstName, String lastName, int salary, int department) {
        List<Employee> employees = generateEmployees(10);
        employees.forEach(employee ->
                assertThat(actual.addAnEmployee(employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary(),
                        employee.getDepartment())).isEqualTo(employee)
        );

        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> actual.addAnEmployee(firstName, lastName, salary, department)
                );
    }

    private List<Employee> generateEmployees(int size) {
        return Stream.iterate(1, i -> i + 1)
                .limit(size)
                .map(i -> new Employee("First Name " + (char) ((int) 'a' + i),
                        "Last Name" + (char) ((int) 'a' + i),
                        25_693 + i,
                        i))
                .collect(Collectors.toList());
    }

    public static Stream<Arguments> paramsForAdd() {
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 24_000, 3),
                Arguments.of("Jack", "Jackson", 25_000, 1),
                Arguments.of("Mary", "Allen", 36_000, 2)
        );
    }
}
