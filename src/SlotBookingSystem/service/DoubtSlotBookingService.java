package SlotBookingSystem.service;

import SlotBookingSystem.enums.Preference;
import SlotBookingSystem.enums.UserType;
import SlotBookingSystem.models.Slot;
import SlotBookingSystem.models.Student;
import SlotBookingSystem.models.Teacher;
import SlotBookingSystem.repository.SlotRepo;
import SlotBookingSystem.repository.StudentRepo;
import SlotBookingSystem.repository.TeacherRepo;

import java.util.*;

public class DoubtSlotBookingService {

    public StudentRepo studentRepo;
    public TeacherRepo teacherRepo;
    public SlotRepo slotRepo;

    public DoubtSlotBookingService() {
        this.studentRepo = new StudentRepo();
        this.teacherRepo = new TeacherRepo();
        this.slotRepo = new SlotRepo();

    }

    public void addUser(String name, UserType userType) {
        if(userType.equals(UserType.STUDENT)) {
            Student student = new Student(name);
            studentRepo.save(student);
        } else {
            Teacher teacher = new Teacher(name);
            teacherRepo.save(teacher);
        }
    }

    public void addSlots(String teacherName, String center, String subject, int startHour, int endHour) {

        Teacher teacher = teacherRepo.getTeacher(teacherName);
        if (teacher == null) {
            System.out.println("Invalid teacher name.");
            return;
        }

        for (int i = startHour; i < endHour; i++) {
            Slot slot = new Slot(center, subject, i, teacher);
            slotRepo.save(slot);
        }
    }


    public void reserveSlot(String studentName, String center, String subject, Preference preference) {
        Student student = studentRepo.getStudent(studentName);

        if (student == null) {
            System.out.println("Invalid student name.");
            return;
        }

        List<Slot> availableSlots = slotRepo.listSlots(center, subject);
        if (availableSlots.isEmpty()) {
            System.out.println("No slots available.");
            return;
        }

        Slot selectedSlot;
        if (preference == Preference.FIRST_AVAILABLE) {
            availableSlots.sort(Comparator.comparingInt(Slot::getStartHour));
            selectedSlot = availableSlots.get(0);
        } else if (preference == Preference.PREVIOUSLY_ASSIGNED_TEACHER) {
            selectedSlot = findPreviouslyAssignedSlot(student, availableSlots);
        } else {
            System.out.println("Invalid preference.");
            return;
        }

        if (selectedSlot != null) {
            selectedSlot.reserve(student);
            student.setTeacher(selectedSlot.getTeacher());
            System.out.println("Slot reserved: " + selectedSlot);
        } else {
            System.out.println("No slots available.");
        }
    }

    public void reserveSlot(String studentName, String center, String subject) {
        reserveSlot(studentName, center, subject, Preference.FIRST_AVAILABLE);
    }

    public void reserveSlot(String studentName,String teacherName, String center, String subject, int startHour) {
        Student student = studentRepo.getStudent(studentName);

        if (student == null) {
            System.out.println("Invalid student name.");
            return;
        }

        Slot availableSlot = slotRepo.getSlot(center, subject, teacherName, startHour);
        if (availableSlot==null) {
            System.out.println("No slots available.");
            return;
        }

        availableSlot.reserve(student);
        student.setTeacher(availableSlot.getTeacher());
        System.out.println("Slot reserved: " + availableSlot);
    }



    public List<Slot> listReservedSlots(String studentName) {
        Student student = studentRepo.getStudent(studentName);
        if (student == null) {
            System.out.println("Invalid student name.");
            return Collections.emptyList();
        }

        List<Slot> reservedSlots = new ArrayList<>();
        for (Slot slot : slotRepo.getSlotList()) {
            if (slot.isReserved() && slot.getReservedBy() == student) {
                reservedSlots.add(slot);
            }
        }
        return reservedSlots;
    }

    private Slot findPreviouslyAssignedSlot(Student student, List<Slot> availableSlots) {
        Teacher teacher = student.getTeacher();
        for (Slot slot : availableSlots) {
            if (slot.getTeacher().getName().equals(teacher.getName())) {
                return slot;
            }
        }
        return null;
    }

    public List<Slot> getAvailableSlots(String center, String subject) {
        return slotRepo.listSlots(center, subject);
    }
}
