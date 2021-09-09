package com.rguzman.s4.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentClassRepository  extends JpaRepository<StudentClass, Integer> {

}
