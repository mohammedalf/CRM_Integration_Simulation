package com.abcgroep.crmsimulation.application.services;


import com.abcgroep.crmsimulation.application.entities.Student;
import com.abcgroep.crmsimulation.application.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;



    public Student saveStudent(Student student){
        return this.studentRepository.save(student);
    }

    public void saveAllMigratedStudents(List<Student> migratedStudents){
        this.studentRepository.saveAll(migratedStudents);
    }
    public List<Student> getAllStudents(){
        return this.studentRepository.findAll();
    }
}
