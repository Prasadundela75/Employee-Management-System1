package managementsystem.employee.emsbackend.repository;

import managementsystem.employee.emsbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}


