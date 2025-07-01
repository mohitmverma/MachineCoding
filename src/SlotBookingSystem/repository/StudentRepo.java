package SlotBookingSystem.repository;

import SlotBookingSystem.models.Student;
import SlotBookingSystem.models.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepo {

    Map<String,Student> studentMap;

    public StudentRepo() {
        studentMap = new HashMap<>();
    }

    public void save(Student student) {
        studentMap.put(student.getName(), student);
    }

    public void addLastTeacher(Student student, Teacher teacher) {
        student.setTeacher(teacher);
    }

    public Student getStudent(String name) {
        return studentMap.get(name);
    }
}
