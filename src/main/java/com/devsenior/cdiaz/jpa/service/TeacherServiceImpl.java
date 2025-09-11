package com.devsenior.cdiaz.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.cdiaz.jpa.model.dto.TeacherCreateRequest;
import com.devsenior.cdiaz.jpa.model.dto.TeacherResponse;
import com.devsenior.cdiaz.jpa.model.entity.City;
import com.devsenior.cdiaz.jpa.model.entity.Teacher;
import com.devsenior.cdiaz.jpa.repository.CityRepository;
import com.devsenior.cdiaz.jpa.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CityRepository cityRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, CityRepository cityRepository) {
        this.teacherRepository = teacherRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<TeacherResponse> getAll() {
        var teachers = teacherRepository.findAll();

        var teacherResponse = teachers.stream()
                .map(this::toResponse)
                .toList();
        return teacherResponse;
    }

    @Override
    public TeacherResponse create(TeacherCreateRequest teacher) {
        var entity = teacherRepository.save(toEntity(teacher));
        return toResponse(entity);
    }

    private TeacherResponse toResponse(Teacher entity) {
        var response = new TeacherResponse();
        response.setDocument(entity.getDocument());
        response.setFirstName(entity.getFirstName()
                + (entity.getSecondName() == null ? "" : " " + entity.getSecondName()));
        response.setLastName(entity.getFirstLastName()
                + (entity.getSecondLastName() == null ? "" : " " + entity.getSecondLastName()));
        response.setEmail(entity.getEmail());
        response.setPhone((entity.getIndicative() == null ? "" : entity.getIndicative() + " ")
                + entity.getPhoneNumber());
        response.setAddress(entity.getAddress() +
                (entity.getAdditionalAddress() == null ? "" : ", " + entity.getAdditionalAddress()));
        response.setCity(entity.getCity().getName());
        response.setDepartment(entity.getCity().getDepartment());

        return response;
    }

    private Teacher toEntity(TeacherCreateRequest teacher){
        var city = cityRepository.findById(teacher.getCity());
        return new Teacher(
                teacher.getDocument(),
                teacher.getFirstName(),
                teacher.getSecondName(),
                teacher.getFirstLastName(),
                teacher.getSecondLastName(),
                teacher.getEmail(),
                teacher.getIndicative(),
                teacher.getPhoneNumber(),
                teacher.getAddress(),
                teacher.getAdditionalAddress(),
                teacher.getSalary(),
                city.orElseGet(() -> {
                    var entity = new City(teacher.getCity());
                    entity = cityRepository.save(entity);
                    return entity;
                }));
    }

}
