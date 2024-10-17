package kg.megalab.competitionproject.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private Double salary;

    public Employee() {}

    public Employee(String firstName, String lastName, LocalDate birthDate, String department, Double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Сотрудник {" +
                "\n\tID: " + id +
                "\n\tИмя: " + firstName +
                "\n\tФамилия: " + lastName +
                "\n\tДата рождения: " + birthDate +
                "\n\tОтдел: " + department +
                "\n\tЗарплата: " + salary +
                "\n}";
    }

}
