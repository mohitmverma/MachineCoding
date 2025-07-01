package SlotBookingSystem.repository;

import SlotBookingSystem.models.Teacher;

import java.util.HashMap;
import java.util.Map;

public class TeacherRepo {
    Map<String, Teacher> teacherMap;

    public TeacherRepo() {
        teacherMap = new HashMap<>();
    }

    public void save(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }

    public Teacher getTeacher(String name) {
        return teacherMap.get(name);
    }
}
