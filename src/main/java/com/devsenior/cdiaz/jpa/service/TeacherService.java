package com.devsenior.cdiaz.jpa.service;

import java.util.List;

import com.devsenior.cdiaz.jpa.model.dto.TeacherCreateRequest;
import com.devsenior.cdiaz.jpa.model.dto.TeacherResponse;

public interface TeacherService {
    
    List<TeacherResponse> getAll();

    TeacherResponse create(TeacherCreateRequest teacher);
}
