package kg.megalab.competitionproject;

import kg.megalab.competitionproject.models.Employee;
import kg.megalab.competitionproject.services.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(EmployeeService employeeService) {
        return args -> {
            employeeService.saveEmployee(new Employee("Владислав", "Маслюков", LocalDate.of(1990, 1, 1), "IT", 95000.00));
            employeeService.saveEmployee(new Employee("Мария", "Петрова", LocalDate.of(1985, 5, 20), "HR", 80000.00));
            employeeService.saveEmployee(new Employee("Кирилл", "Жуйков", LocalDate.of(1992, 7, 10), "Sales", 75000.00));
        };
    }
}