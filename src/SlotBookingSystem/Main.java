package SlotBookingSystem;

import SlotBookingSystem.enums.Preference;
import SlotBookingSystem.enums.UserType;
import SlotBookingSystem.models.Slot;
import SlotBookingSystem.service.DoubtSlotBookingService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        DoubtSlotBookingService system = new DoubtSlotBookingService();
        system.addUser("Akhilesh", UserType.TEACHER);
        system.addUser("Nikhil", UserType.TEACHER);
        system.addUser("Raj", UserType.STUDENT);
        system.addUser("Megha", UserType.STUDENT);
        system.addSlots("Akhilesh", "Jayanagar", "Physics", 9, 10);
        system.addSlots("Akhilesh", "Jayanagar", "Physics", 14, 17);
        system.addSlots("Nikhil", "HSR", "Chemistry", 9, 10);
        system.addSlots("Nikhil", "HSR", "Chemistry", 12, 13);
        system.addSlots("Akhilesh", "HSR", "Chemistry", 11, 13);
        List<Slot> availableSlots = system.getAvailableSlots("HSR", "Chemistry");
        for (Slot slot : availableSlots) {
            System.out.println(slot);
        }
        system.reserveSlot("Raj", "Nikhil", "HSR", "Chemistry", 9);
        system.reserveSlot("Megha", "HSR", "Chemistry", Preference.FIRST_AVAILABLE);
        system.reserveSlot("Raj", "HSR", "Chemistry", Preference.PREVIOUSLY_ASSIGNED_TEACHER);
        system.reserveSlot("Raj", "HSR", "Physics");


        List<Slot> reservedSlots = system.listReservedSlots("Raj");
        for (Slot slot : reservedSlots) {
            System.out.println(slot);
        }
    }
}
