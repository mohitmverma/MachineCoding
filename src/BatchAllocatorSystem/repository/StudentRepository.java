package BatchAllocatorSystem.repository;

import BatchAllocatorSystem.models.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentRepository {

    Map<String, Student> studentMap;

    public StudentRepository() {
        this.studentMap = new HashMap<>();
    }

    public void save(Student student) {
        studentMap.put(student.getName(), student);
    }

    public Student getStudent(String studentName){
        return studentMap.get(studentName);
    }

}
