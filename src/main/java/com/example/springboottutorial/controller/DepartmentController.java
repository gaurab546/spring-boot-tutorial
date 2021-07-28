package com.example.springboottutorial.controller;

import com.example.springboottutorial.entity.Department;
import com.example.springboottutorial.error.DepartmentNotFoundException;
import com.example.springboottutorial.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/getDepart")
    public List<Department> getDepartmentList(){
        return departmentService.getDepartmentList();
    }


    @GetMapping("/getDepart/{id}")
    public Department getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.getDepartmentById(departmentId);
    }

    @DeleteMapping("/deleteDepart/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department deleted successfully";

    }
    
    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable ("id") Long departmentId, 
                                       @RequestBody Department department){
        return departmentService.updateDepartment(departmentId, department);
        
    }

    @GetMapping("/departments/name/{name}")
    public  Department getDepartmentByName(@PathVariable ("name") String departmentName){
        return departmentService.getDepartmentByName(departmentName);

    }

}
