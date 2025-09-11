package com.devsenior.cdiaz.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsenior.cdiaz.jpa.model.entity.City;
import java.util.List;


public interface CityRepository extends JpaRepository<City, String> {

    List<City> findByDepartmentContainingIgnoringCase(String department);
    
}
