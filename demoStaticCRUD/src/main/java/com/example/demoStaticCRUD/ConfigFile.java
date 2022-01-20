package com.example.demoStaticCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demoStaticCRUD")

public class ConfigFile {
    @Autowired
    CrudFunctions crudFunctions;
    @Autowired
    Student student;
    @Autowired
    Department department;
}
