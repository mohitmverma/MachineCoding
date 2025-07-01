package SlotBookingSystem.repository;

import SlotBookingSystem.models.Slot;

import java.util.ArrayList;
import java.util.List;

public class SlotRepo {

    List<Slot> slotList;

    public SlotRepo() {
        this.slotList = new ArrayList<>();
    }

    public void save(Slot slot) {
        slotList.add(slot);
    }

    public List<Slot> listSlots(String center, String subject) {
        List<Slot> availableSlots = new ArrayList<>();
        for (Slot slot : slotList) {
            if (slot.getCenter().equals(center) && slot.getSubject().equals(subject) && !slot.isReserved()) {
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }

    public List<Slot> listSlots(String center, String subject, String teacherName) {
        List<Slot> availableSlots = new ArrayList<>();
        for (Slot slot : slotList) {
            if (slot.getCenter().equals(center) && slot.getSubject().equals(subject) && slot.getTeacher().getName().equals(teacherName) && !slot.isReserved()) {
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }

    public Slot getSlot(String center, String subject, String teacherName, int startTime) {
        Slot availableSlot = null;
        for (Slot slot : slotList) {
            if (slot.getCenter().equals(center) &&
                    slot.getSubject().equals(subject) &&
                    slot.getTeacher().getName().equals(teacherName) &&
                    (slot.getStartHour() == startTime) &&
                    !slot.isReserved()) {
                availableSlot=slot;
                break;
            }
        }
        return availableSlot;
    }


    public List<Slot> getSlotList() {
        return slotList;
    }
}
