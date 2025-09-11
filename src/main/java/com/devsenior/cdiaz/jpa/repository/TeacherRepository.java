package com.devsenior.cdiaz.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsenior.cdiaz.jpa.model.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    
}
