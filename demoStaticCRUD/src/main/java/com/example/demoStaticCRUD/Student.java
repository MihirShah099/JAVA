package com.example.demoStaticCRUD;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString(exclude = "department")
@EqualsAndHashCode(exclude = "department")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Student {
    Integer studentId;
    String studentName;
    Department department;          //...ManyToOne...
}
