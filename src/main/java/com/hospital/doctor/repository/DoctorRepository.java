package com.hospital.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.doctor.entity.Doctor;

public interface DoctorRepository
        extends JpaRepository<Doctor, Long> {

}