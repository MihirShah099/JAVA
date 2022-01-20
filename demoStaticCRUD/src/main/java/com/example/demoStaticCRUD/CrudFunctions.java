package com.example.demoStaticCRUD;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class CrudFunctions {
    Scanner sc = new Scanner(System.in);
    List<Student> studentList = new ArrayList<>();
    List<Department> departmentList = new ArrayList<>();

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void getAllStudent() {
        String customerName1 = "-123aha45fhjs46";
        String date = "14/09/2006";
        String customerName2 = customerName1.replaceAll("[^a-z]", "");
        System.out.println("HHH : " + customerName2);
        System.out.println("MAIN : " + customerName1.replaceAll("[^-0-9]", ""));
        System.out.println("RESULT 1 : " + Double.parseDouble(date.replaceAll("[^-0-9]", "")));
        String abc = "abcdefg";
        System.out.println("LEN : " + abc.getBytes().length);
        System.out.println("DAT : " + abc.substring(0, 5));
        System.out.println("DAT 1 : " + abc.substring(1, 5));
        System.out.println("DAT 2 : " + abc.substring(0, 7));
        System.out.println("DAT 3 : " + abc.substring(0, 6));
        Double inf = -1.0 / 0;
        System.out.println("INFINITE : " + inf.isInfinite());
        System.out.println("INFINITE : " + inf);


        if (0 < studentList.size()) {
            for (Student student : studentList) {
                System.out.println("Rollno = " + student.studentId);
                System.out.println("Name = " + student.studentName);
                if (null != student.department) {
                    System.out.println("Department ID : " + student.department.getDepartmentId());
                    System.out.println("Department Name : " + student.department.getDepartmentName());
                } else
                    System.out.println("Department : " + null);
            }
        } else
            System.out.println("No Entry Of Student Exist..!!");
    }

    public void addDepartment(Department department) {
        departmentList.add(department);
    }

    public void getAllDepartment() {
        if (0 < departmentList.size()) {
            for (Department department : departmentList) {
                System.out.println("Department ID = " + department.departmentId);
                System.out.println("Department Name = " + department.departmentName);
                if (null != department.studentList) {                    //Check If Any Student Assigned To Department Or Not..
                    List<Student> studList = department.studentList.stream()
                            .filter(student -> department.equals(student.getDepartment()))
                            .collect(Collectors.toList());
                    System.out.println(studList);
                } else
                    System.out.println("Student : " + null);
            }
        } else
            System.out.println("No Entry Of Department Exist..!!");
    }

    public void updateStudent(Integer studentId) {
        List<Student> filterList = studentList.stream()
                .filter(student -> student.getStudentId() == studentId)
                .collect(Collectors.toList());

        if (0 < filterList.size()) {
            Student student = filterList.get(0);
            System.out.println("Updating Student :");
            System.out.println("Enter Name Of Student : ");
            String name = sc.next();
            student.setStudentName(name);
        }

        /*
        if(0<studentList.size()){
            for(Student student : studentList){
                if(student.getStudentId() == studentId){
                    System.out.println("Updating Student :");
                    System.out.println("Enter Name Of Student : ");
                    String name = sc.next();
                    student.setStudentName(name);
                }
            }
        }
        */
        else
            System.out.println("No Record Found Of Student With ID : " + studentId);
    }

    public void displayStudentsInDepartment() {
        if (0 < departmentList.size()) {                //Check If Any Department Exist Or Not..
            System.out.println("Enter Department ID : ");
            Integer departmentId = sc.nextInt();
            List<Department> deptlist = departmentList.stream()
                    .filter(department -> department.getDepartmentId() == departmentId)
                    .collect(Collectors.toList());
            if (null != deptlist.get(0).studentList) {                   //Check If Any Student Assigned To Department..
                List<Student> studList = deptlist.get(0).studentList.stream()
                        .filter(student -> deptlist.get(0).equals(student.getDepartment()))      //HERE deptlist.get(0)= 1 Department
                        .collect(Collectors.toList());
                System.out.println("Students : ");
                System.out.println(studList);
            } else
                System.out.println("Students : " + null);
        } else
            System.out.println("No Entry For Department Exist..!!");

    }

    public void deleteStudent(Integer studentId) {
        List<Student> filterList = studentList.stream()
                .filter(student -> student.getStudentId() == studentId)
                .collect(Collectors.toList());

        if (0 < filterList.size()) {
            Student student = filterList.get(0);
            studentList.remove(student);
        } else
            System.out.println("No Record Found Of Student With ID : " + studentId);
    }

    public void assignDepartment(Integer sid) {
        List<Student> filterList = studentList.stream()
                .filter(student -> student.getStudentId() == sid)
                .collect(Collectors.toList());
        if (0 < filterList.size()) {                             //Check If Student Exist..!!
            Student student = filterList.get(0);
            if (null == student.getDepartment()) {              //Check If Department Already Assigned..!!
                System.out.println("Enter Department ID : ");
                Integer did = sc.nextInt();
                List<Department> dlist = departmentList.stream()
                        .filter(department -> department.getDepartmentId() == did)
                        .collect(Collectors.toList());
                if (0 < dlist.size()) {                           //Check If Department Exist..!!
                    student.setDepartment(dlist.get(0));
                    dlist.get(0).setStudentList(studentList);
                } else
                    System.out.println("Department Not Exist Having ID : " + did);
            } else
                System.out.println("Already Have Entry For Department..!!");
        } else
            System.out.println("No Student Found Having ID : " + sid);
    }
}
