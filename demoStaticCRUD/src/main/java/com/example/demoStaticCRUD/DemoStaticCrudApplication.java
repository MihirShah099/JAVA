package com.example.demoStaticCRUD;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class DemoStaticCrudApplication {

    public static void main(String[] args) throws JsonProcessingException {
        //SpringApplication.run(DemoStaticCrudApplication.class, args);
        Integer i;
        Integer studentId = 1, departmentId = 1;
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigFile.class);
        CrudFunctions crudFunctions = context.getBean("crudFunctions", CrudFunctions.class);
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Static CRUD Operation : ");
            System.out.println("1. : Add Student.");                             //
            System.out.println("2. : Assign Department To Student.");             //
            System.out.println("3. : Add Department.");                          //
            System.out.println("4. : Display All Students.");                    //
            System.out.println("5. : Display All Departments.");                 //
            System.out.println("6. : Display Students In Department.");             //
            System.out.println("7. : Update Student.");                          //
            System.out.println("8. : Delete Student.");                          //
            System.out.println("9. : Test.");
            System.out.println("10. : Exit.");
            System.out.println("Enter Your Choice : ");
            i = sc.nextInt();

            switch (i) {

                case 1:
                    Student student = context.getBean("student", Student.class);
                    student.setStudentId(studentId);
                    System.out.println("Enter Name Of Student : ");
                    String studentName = sc.next();
                    student.setStudentName(studentName);
                    crudFunctions.addStudent(student);
                    studentId++;
                    break;
                case 2:
                    System.out.println("Enter Student ID : ");
                    Integer sid = sc.nextInt();
                    crudFunctions.assignDepartment(sid);
                    break;
                case 3:
                    Department department = context.getBean("department", Department.class);
                    department.setDepartmentId(departmentId);
                    System.out.println("Enter Department Name : ");
                    String departmentName = sc.next();
                    department.setDepartmentName(departmentName);
                    crudFunctions.addDepartment(department);
                    departmentId++;
                    break;
                case 4:
                    crudFunctions.getAllStudent();
                    break;
                case 5:
                    crudFunctions.getAllDepartment();
                    break;
                case 6:
                    crudFunctions.displayStudentsInDepartment();
                    break;
                case 7:
                    System.out.println("Enter StudentID Of Student To Update : ");
                    Integer updateId = sc.nextInt();
                    crudFunctions.updateStudent(updateId);
                    break;
                case 8:
                    System.out.println("Enter Student ID Which You Want To Delete : ");
                    Integer deleteId = sc.nextInt();
                    crudFunctions.deleteStudent(deleteId);
                    break;
                case 9:
                    Double w1 = 100.00, w2 = 100.00, p1 = 80.00, p2 = 80.00, itemPrice = 100.00, temp = 0.0;
                    temp = itemPrice - (w2 * (p2 - p1)) / (w1 - w2);
                    System.out.println("Price : " + temp);
                    System.out.println("Type :" + temp.isNaN());
                case 10:
                    return;
                default:
                    System.out.println("Enter Correct Choice..!");
            }
        }
        while (true);
    }

}
