package com.example.demoStaticCRUD;

import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Department {
    Integer departmentId;
    String departmentName;
    List<Student> studentList;      //...OneToMany...
}
