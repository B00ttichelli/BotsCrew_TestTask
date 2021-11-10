package com.example.botscrew_testtask;

import com.example.botscrew_testtask.exception.CustomException;
import com.example.botscrew_testtask.exception.CustomFn;
import com.example.botscrew_testtask.repository.DbInit;
import com.example.botscrew_testtask.service.UniversityService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@AllArgsConstructor
public class BotsCrewTestTaskApplication implements CommandLineRunner {

    private final UniversityService universityService;
    private final DbInit dbInit;  //Demonstration purpose only

    public static void main(String[] args) {

        SpringApplication.run(BotsCrewTestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        /*dbInit.init();*/ // uncomment to create DB example
        final String ERROR = "Upss... Something went wrong";
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int choice = 0;
        while (!input.equals("exit")) {

            if (choice == 0) {

                System.out.println("Type number to select a command  or exit to close an app");
                System.out.println("1.Who is head of Department");
                System.out.println("2.Show department statistic");
                System.out.println("3.Show average salary for department");
                System.out.println("4.Show count of employee for department");
                System.out.println("5.Global search for template");
                input = scanner.next();

                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println(ERROR);
                }

            }

            switch (choice) {

                case 1:
                    input = askForDepartmentName(scanner);
                    System.out.println("Answer :Head of " + input + " department is " + tryExec(universityService::getHeadOfDepartmentName, input));
                    choice = 0;
                    break;
                case 2:
                    input = askForDepartmentName(scanner);
                    System.out.println(tryExec(universityService::showDepartmentStatistic, input));
                    choice = 0;
                    break;
                case 3:
                    input = askForDepartmentName(scanner);
                    System.out.println("Answer: The average salary of " + input + " is " + tryExec(universityService::showAwerageSalaryForDepartment, input));
                    choice = 0;
                    break;
                case 4:
                    input = askForDepartmentName(scanner);
                    System.out.println("Answer: "+tryExec(universityService::countOfEmployees, input));
                    choice = 0;
                    break;
                case 5:
                    System.out.println("Type a keyword");
                    input = scanner.next();
                    System.out.println("Answer: "+ tryExec(universityService::searchByTemplate, input));
                    choice = 0;
                    break;

                default:
                    System.out.println(ERROR);
                    choice = 0;
                    break;

            }


        }

    }

    //Solution for repetitive try/catch
    public String tryExec(CustomFn<String, String> f, String arg) {
        try {
            return (f.apply(arg));
        } catch (CustomException e) {
            return "ERROR: " + e.getMessage();
        } catch (Exception ex) {
            return "ERROR: Upss... Something went wrong";
        }
    }

    private String askForDepartmentName(Scanner scanner) {
        System.out.println("Type department name");
        return scanner.next();
    }
}
