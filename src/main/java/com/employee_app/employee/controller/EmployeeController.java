package com.employee_app.employee.controller;


import com.employee_app.employee.dto.EmployeeDTO;
import com.employee_app.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @PostMapping
    public EmployeeDTO create(@RequestBody EmployeeDTO dto){
        return service.create(dto);
    }
    @GetMapping
    public List<EmployeeDTO> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public EmployeeDTO update(@PathVariable Long id){
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public EmployeeDTO update(@PathVariable Long id, @RequestBody EmployeeDTO dto){
        return service.update(id, dto);

    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "Employee deleted";
    }
}
