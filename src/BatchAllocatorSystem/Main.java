package BatchAllocatorSystem;

import BatchAllocatorSystem.enums.*;
import BatchAllocatorSystem.service.BatchAllocatorService;

public class Main {
    public static void main(String[] args) {
        BatchAllocatorService service = new BatchAllocatorService();

        service.register("Akhilesh", Gender.MALE, UserType.STUDENT);        // student1
        service.register("Komal", Gender.FEMALE, UserType.STUDENT);         // student2
        service.register("Rajnish", Gender.MALE, UserType.STUDENT);         // student3
        service.register("Mayuri", Gender.FEMALE, UserType.STUDENT);        // student4

        service.enroll("Akhilesh", Stream.IIT);         //student 1
        service.enroll("Komal", Stream.IIT);         // student2
        service.enroll("Rajnish", Stream.IIT);         // student3
        service.enroll("Mayuri", Stream.IIT);         // student4

        service.register("Kamesh", Gender.MALE , UserType.ADMIN);       //Admin 1
        service.register("Saurabh", Gender.MALE , UserType.ADMIN);       //Admin 2

        service.createBatch("Kamesh", 3, Stream.IIT, Timing.MORNING);          // B1
        service.createBatch("Kamesh", 2, Stream.NEET, Timing.EVENING);          // B2
        service.createBatch("Kamesh", 3, Stream.IIT, Timing.EVENING);           // B3

        service.allocateBatch("Saurabh", "Mayuri", AllocationType.HIGHER_CAPACITY);      //student 4
        service.allocateBatch("Kamesh", "Akhilesh", AllocationType.HIGHER_CAPACITY);      //student 1
        service.allocateBatch("Kamesh", "Rajnish", AllocationType.HIGHER_CAPACITY);      //studetn 3
        service.allocateBatch("Saurabh", "Komal", AllocationType.GENDER_BASED);         //student 2
    }
}
