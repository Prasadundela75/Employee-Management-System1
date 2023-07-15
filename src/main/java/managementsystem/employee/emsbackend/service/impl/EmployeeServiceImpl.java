package managementsystem.employee.emsbackend.service.impl;

import lombok.AllArgsConstructor;
import managementsystem.employee.emsbackend.dto.EmployeeDto;
import managementsystem.employee.emsbackend.entity.Employee;
import managementsystem.employee.emsbackend.exception.ResourceNotFoundException;
import managementsystem.employee.emsbackend.mapper.EmployeeMapper;
import managementsystem.employee.emsbackend.repository.EmployeeRepository;
import managementsystem.employee.emsbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exists with given id :" + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

       Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id :" +employeeId));

       employee.setFristName(updatedEmployee.getFirstName());
       employee.setLastname(updatedEmployee.getLastName());
       employee.setEmail(updatedEmployee.getEmail());

      Employee updatedEmployeeobj = employeeRepository.save(employee);

      return EmployeeMapper.mapToEmployeeDto(updatedEmployeeobj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id :" +employeeId));

        employeeRepository.deleteById(employeeId);
    }

}
