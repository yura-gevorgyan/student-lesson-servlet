package am.itspace.studentlessonservlet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    private int id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private Lesson lesson;
}
