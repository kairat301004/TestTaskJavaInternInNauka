package kg.megalab.competitionproject.services;

import kg.megalab.competitionproject.models.Employee;
import kg.megalab.competitionproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(employeeRepository.findByIdNative(id));
    }

    public List<String> groupByName() {
        return employeeRepository.groupByName();
    }

    public List<Employee> findBetween(LocalDate startDate, LocalDate endDate) {
        return employeeRepository.findBetween(startDate, endDate);
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
