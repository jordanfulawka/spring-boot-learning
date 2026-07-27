package ca.jordanfulawka.springboot.cruddemo.dao;
import ca.jordanfulawka.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
