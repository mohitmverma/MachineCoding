package SlotBookingSystem.models;

public class Slot {

    private String subject;
    private String center;
    private int startHour;
    private Teacher teacher;
    private Student reservedBy;


    public Slot(String center, String subject, int startHour, Teacher teacher) {
        this.subject = subject;
        this.center = center;
        this.startHour = startHour;
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public String getCenter() {
        return center;
    }

    public int getStartHour() {
        return startHour;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Student getReservedBy() {
        return reservedBy;
    }
    public boolean isReserved() {
        if(reservedBy !=null)
            return true;
        return false;
    }

    public void reserve(Student student) {
        this.reservedBy = student;
    }

    @Override
    public String toString() {
        String student="";
        if(reservedBy!=null)
            student = reservedBy.getName();
        return "Slot{" +
                "subject='" + subject + '\'' +
                ", center='" + center + '\'' +
                ", startHour=" + startHour +
 //               ", teacher=" + teacher +
                ", teacher=" + student  +
                '}';
    }
}
