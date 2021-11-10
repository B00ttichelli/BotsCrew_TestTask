package com.example.botscrew_testtask.repository;

import com.example.botscrew_testtask.domain.Department;
import com.example.botscrew_testtask.domain.Lector;
import com.example.botscrew_testtask.enums.Degree;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class DbInit {


    private final DepartmentRepository departmentRepository;

    @Transactional
    public void init() {


        // I used repetitive code just for visual aspect,anyway that code is only for testing purpose
        Department department1 = new Department();
        department1.setDepartmentName("Math");
        Department department2 = new Department();
        department2.setDepartmentName("Chemistry");
        Department department3 = new Department();
        department3.setDepartmentName("Philosophy");

        Lector lector1 = new Lector();
        lector1.setDegree(Degree.PROFESSOR);
        lector1.setName("Ivan");
        lector1.setLastName("Ivanov");
        lector1.setSalary(new BigDecimal(3000));

        Lector lector2 = new Lector();
        lector2.setDegree(Degree.ASSISTANT);
        lector2.setName("Andrey");
        lector2.setLastName("Ivanov");
        lector2.setSalary(new BigDecimal(1200));

        Lector lector3 = new Lector();
        lector3.setDegree(Degree.ASSOCIATE_PROFESSOR);
        lector3.setName("Eugene");
        lector3.setLastName("Demidov");
        lector3.setSalary(new BigDecimal(2300));

        Lector lector4 = new Lector();
        lector4.setDegree(Degree.ASSISTANT);
        lector4.setName("Ahmed");
        lector4.setLastName("Planvanov");
        lector4.setSalary(new BigDecimal(1800));


        Lector lector5 = new Lector();
        lector5.setDegree(Degree.PROFESSOR);
        lector5.setName("Svinvan");
        lector5.setLastName("Lezhatskiy");
        lector5.setSalary(new BigDecimal(8000));

        Set<Lector> lectorSet1 = new HashSet<>();
        lectorSet1.add(lector1);
        lectorSet1.add(lector3);
        lectorSet1.add(lector5);

        department1.setLectorsOfDepartment(lectorSet1);
        department1.setDepartmentHead(lector3);
        departmentRepository.save(department1);

        Set<Lector> lectorSet2 = new HashSet<>();
        lectorSet2.add(lector3);
        lectorSet2.add(lector1);
        lectorSet2.add(lector4);

        department2.setLectorsOfDepartment(lectorSet2);
        department2.setDepartmentHead(lector2);
        departmentRepository.save(department2);

        Set<Lector> lectorSet3 = new HashSet<>();
        lectorSet3.add(lector2);
        lectorSet3.add(lector1);
        lectorSet3.add(lector5);
        lectorSet3.add(lector4);
        lectorSet3.add(lector3);

        department3.setLectorsOfDepartment(lectorSet3);
        department3.setDepartmentHead(lector1);
        departmentRepository.save(department3);


    }


}
