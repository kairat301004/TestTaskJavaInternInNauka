package kg.megalab.competitionproject;

import kg.megalab.competitionproject.models.Employee;
import kg.megalab.competitionproject.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleApp implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    // Метод для проверки корректности даты
    private LocalDate parseDate(String dateStr, DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка: неверный формат даты. Используйте формат yyyy-MM-dd.");
            return null;
        }
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("1. Добавить сотрудника");
            System.out.println("2. Найти сотрудника по ID");
            System.out.println("3. Сгруппировать сотрудников по именам");
            System.out.println("4. Найти сотрудников между датами рождения");
            System.out.println("5. Выйти");
            System.out.print("Выберите действие (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите имя: ");
                    String firstName = scanner.next();
                    System.out.print("Введите фамилию: ");
                    String lastName = scanner.next();
                    System.out.print("Введите дату рождения (yyyy-MM-dd): ");
                    String birthDateStr = scanner.next();
                    LocalDate birthDate = parseDate(birthDateStr, formatter);
                    if (birthDate == null) {
                        break; // Выход из case 1, если дата некорректна
                    }
                    System.out.print("Введите отдел: ");
                    String department = scanner.next();
                    System.out.print("Введите зарплату: ");
                    Double salary = scanner.nextDouble();
                    employeeService.saveEmployee(new Employee(firstName, lastName, birthDate, department, salary));
                    System.out.println("Сотрудник добавлен.");
                    break;

                case 2:
                    System.out.print("Введите ID сотрудника: ");
                    Long id = scanner.nextLong();
                    Optional<Employee> employee = employeeService.findById(id);
                    employee.ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Сотрудник с таким ID не найден.")
                    );
                    break;

                case 3:
                    List<String> names = employeeService.groupByName();
                    System.out.println("Имена сотрудников:");
                    names.forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Введите начальную дату (yyyy-MM-dd): ");
                    String startDateStr = scanner.next();
                    LocalDate startDate = parseDate(startDateStr, formatter);
                    if (startDate == null) {
                        break; // Выход из case4, если дата некорректна
                    }
                    System.out.print("Введите конечную дату (yyyy-MM-dd): ");
                    String endDateStr = scanner.next();
                    LocalDate endDate = parseDate(endDateStr, formatter);
                    if (endDate == null) {
                        break;
                    }
                    List<Employee> employees = employeeService.findBetween(startDate, endDate);
                    System.out.println("Сотрудники между датами:");
                    employees.forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("Спасибо за использование программы. Программа завершена.");
                    System.exit(0);
                    break;


                default:
                    System.out.println("Неверный ввод. Попробуйте снова :)");
            }
        }
    }
}
