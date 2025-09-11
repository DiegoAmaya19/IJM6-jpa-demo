package com.devsenior.cdiaz.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.cdiaz.jpa.model.dto.TeacherCreateRequest;
import com.devsenior.cdiaz.jpa.model.dto.TeacherResponse;
import com.devsenior.cdiaz.jpa.service.TeacherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    
    @GetMapping
    public List<TeacherResponse> getAllTeachers() {
        return teacherService.getAll();
    }

    @PostMapping
    public TeacherResponse createTeacher(@RequestBody TeacherCreateRequest entity) {
        return teacherService.create(entity);
    }
    
    
}
