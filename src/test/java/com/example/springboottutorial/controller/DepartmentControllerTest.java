package com.example.springboottutorial.controller;

import com.example.springboottutorial.entity.Department;
import com.example.springboottutorial.error.DepartmentNotFoundException;
import com.example.springboottutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentCode("ME-01")
                .departmentAddress("Nepal")
                .departmentName("ME")
                .departmentId(1L).
                        build();
    }

    @Test
    void saveDepartment() throws Exception {

        Department department1 = Department.builder()
                .departmentCode("ME-01")
                .departmentAddress("Nepal")
                .departmentName("ME")
                        .build();

        Mockito.when(departmentService.saveDepartment((department1)))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
        .contentType(MediaType.APPLICATION_JSON)
        .content("Insert Json"))
        .andExpect(status().isOk());
    }

    @Test
    void getDepartmentById() throws Exception {

        Mockito.when(departmentService.getDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/getDepart/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}