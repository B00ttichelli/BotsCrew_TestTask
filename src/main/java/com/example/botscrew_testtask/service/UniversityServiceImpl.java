package com.example.botscrew_testtask.service;

import com.example.botscrew_testtask.domain.Department;
import com.example.botscrew_testtask.domain.Lector;
import com.example.botscrew_testtask.enums.Degree;
import com.example.botscrew_testtask.exception.CustomException;
import com.example.botscrew_testtask.repository.DepartmentRepository;
import com.example.botscrew_testtask.repository.LectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class UniversityServiceImpl implements UniversityService {

    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;


    @Override
    @Transactional
    public String getHeadOfDepartmentName(String input) {
        if (input == null || input.equals("")) {
            throw new CustomException("DepartmentName cant be null or blank");
        }

        Department department = departmentRepository.getDepartmentByDepartmentName(input);
        if (department == null) {
            throw new CustomException("Department with name " + input + " not founded");
        }
        Lector lector = department.getDepartmentHead();

        if (lector == null) {
            throw new CustomException("Department " + input + " dont have an department head");
        }

        return lector.getName() + " " + lector.getLastName();
    }

    @Override
    public String showDepartmentStatistic(String input) {
        if (input == null || input.equals("")) {
            throw new CustomException("DepartmentName cant be null or blank");
        }
        Department department = departmentRepository.getDepartmentByDepartmentName(input);
        if (department == null) {
            throw new CustomException("Department with name " + input + " not founded");
        }
        Set<Lector> lectorsOfDepartment = department.getLectorsOfDepartment();
        long assistantsCount = lectorsOfDepartment.stream().filter(a -> a.getDegree().equals(Degree.ASSISTANT)).count();
        long associatesCount = lectorsOfDepartment.stream().filter(a -> a.getDegree().equals(Degree.ASSOCIATE_PROFESSOR)).count();
        long professorsCount = lectorsOfDepartment.stream().filter(a -> a.getDegree().equals(Degree.PROFESSOR)).count();

        return "Answer: " + "assistants - " + assistantsCount + " associate professors - " + associatesCount + " professors - " + professorsCount;
    }

    @Override
    public String showAwerageSalaryForDepartment(String input) {
        if (input == null || input.equals("")) {
            throw new CustomException("DepartmentName cant be null or blank");
        }

        Department department = departmentRepository.getDepartmentByDepartmentName(input);

        if (department == null) {
            throw new CustomException("Department with name " + input + " not founded");
        }
        List<BigDecimal> salaryList = department.getLectorsOfDepartment().stream().map(a -> a.getSalary()).collect(Collectors.toList());
        BigDecimal sum = salaryList.stream().map(Objects::requireNonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(salaryList.size()), RoundingMode.CEILING).toString();

    }

    @Override
    public String countOfEmployees(String input) {
        Department department = departmentRepository.getDepartmentByDepartmentName(input);
        if (input == null || input.equals("")) {
            throw new CustomException("DepartmentName cant be null or blank");
        }

        if (department == null) {
            throw new CustomException("Department with name " + input + " not founded");
        }
        return String.valueOf(department.getLectorsOfDepartment().size());
    }

    @Override
    public String searchByTemplate(String input) {
        String result = lectorRepository.findAllByNameContainsOrLastNameContains(input, input).toString();
        if(result == null || result.isEmpty() || result.isBlank() ||result.equals("[]")){
            return "Nothing found ";
        }
        return result;
    }
}
