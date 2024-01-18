package am.itspace.studentlessonservlet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {

    private int id;
    private String name;
    private int duration;
    private String lecturerName;
    private double price;
    private User user;
}
