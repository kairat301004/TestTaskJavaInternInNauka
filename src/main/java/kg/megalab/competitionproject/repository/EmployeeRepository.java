package kg.megalab.competitionproject.repository;

import kg.megalab.competitionproject.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM employees WHERE id = ?1", nativeQuery = true)
    Employee findByIdNative(Long id);

    @Query(value = "SELECT first_name FROM employees GROUP BY first_name", nativeQuery = true)
    List<String> groupByName();

    @Query(value = "SELECT * FROM employees WHERE birth_date BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Employee> findBetween(LocalDate startDate, LocalDate endDate);
}
