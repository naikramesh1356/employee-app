package com.employee_app.employee.service;



import com.employee_app.employee.dto.EmployeeDTO;
import com.employee_app.employee.exception.ResourceNotFoundException;
import com.employee_app.employee.model.Employee;
import com.employee_app.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository){
        this.repository = repository;
    }


    //convert DTO --> Entity
    private Employee toEntity(EmployeeDTO dto){
        return new Employee(dto.getName(), dto.getDepartment(), dto.getSalary());
    }

    //Convert Entity --> DTO
    private EmployeeDTO toDTO(Employee emp){
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(emp.getName());
        dto.setDepartment(emp.getDepartment());
        dto.setSalary(emp.getSalary());
        return dto;
    }
    public EmployeeDTO create(EmployeeDTO dto){
        Employee emp = repository.save(toEntity(dto));
        return toDTO(emp);
    }

    public List<EmployeeDTO> getAll(){
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getById(Long id){
        Employee emp = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return toDTO(emp);
    }
    public EmployeeDTO update(Long id, EmployeeDTO dto){
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found"));

        emp.setName(dto.getName());
        emp.setDepartment(dto.getDepartment());
        emp.setSalary(dto.getSalary());

        return toDTO(repository.save(emp));
    }

    public void delete(Long id){
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        repository.delete(emp);
    }



}
