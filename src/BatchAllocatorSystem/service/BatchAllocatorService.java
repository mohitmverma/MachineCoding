package BatchAllocatorSystem.service;


import BatchAllocatorSystem.enums.*;
import BatchAllocatorSystem.models.*;
import BatchAllocatorSystem.repository.*;


import java.util.List;

public class BatchAllocatorService {

    private AdminRepository adminRepository;
    private StudentRepository studentRepository;
    private BatchRepository batchRepository;

    public BatchAllocatorService() {
        this.adminRepository = new AdminRepository();
        this.studentRepository = new StudentRepository();
        this.batchRepository = new BatchRepository();
    }

    public void register(String name, Gender gender, UserType userType) {

        if(userType.equals(UserType.STUDENT)) {
            Student student =  new Student(name, gender);
            studentRepository.save(student);
        } else if(userType.equals(UserType.ADMIN)) {
            Admin admin = new Admin(name, gender);
            adminRepository.save(admin);
        } else {
            System.out.println("Invalid User");
        }
    }

    public void enroll(String name, Stream stream) {
        Student student = studentRepository.getStudent(name);
        student.setStream(stream);
    }

    public void createBatch(String adminName, int capacity, Stream stream, Timing timing) {
        Admin admin = adminRepository.getAdmin(adminName);
        Batch batch = new Batch(admin, capacity, stream, timing);
        batchRepository.save(batch);
    }

    public void allocateBatch(String adminName, String studentName, AllocationType allocationType) {

        Student student = studentRepository.getStudent(studentName);

        if(student==null) {
            System.out.println("Student Not found");
        }
        List<Batch> batchList = batchRepository.getAvailableBatchList(student.getStream());

        Batch suitableBatch;

        if(allocationType.equals(AllocationType.GENDER_BASED)) {
            if(student.getGender().equals(Gender.FEMALE)) {
                suitableBatch = getFemaleBatch(batchList);
            } else {
                suitableBatch = batchList.get(0);
            }

        } else if (allocationType.equals(AllocationType.HIGHER_CAPACITY)) {
            suitableBatch = getHighestCapacityBatch(batchList);
        } else {
            System.out.println("Invalid Allocation Type");
            return;
        }

        int capacity = suitableBatch.getCapacity();
        suitableBatch.setCapacity(capacity-1);

        System.out.println("Batch assigned " + suitableBatch);

    }

    private Batch getHighestCapacityBatch(List<Batch> batchList) {

        Batch batchRequired = batchList.get(0);
        for(int i=1;i< batchList.size(); i++) {
            if(batchList.get(i).getTotalCapacity()>batchRequired.getTotalCapacity()) {
                batchRequired = batchList.get(i);
            }
        }
        return batchRequired;
    }

    private Batch getFemaleBatch(List<Batch> batchList) {

        Batch batchRequired=null;
        for(int i=0;i< batchList.size(); i++) {
            if(batchList.get(i).getTiming().equals(Timing.MORNING)) {
                batchRequired = batchList.get(i);
                return batchRequired;
            }
        }

        for(int i=0;i< batchList.size(); i++) {
            if(batchList.get(i).getTiming().equals(Timing.NOON)) {
                batchRequired = batchList.get(i);
                return batchRequired;
            }
        }

        for(int i=0;i< batchList.size(); i++) {
            if(batchList.get(i).getTiming().equals(Timing.EVENING)) {
                batchRequired = batchList.get(i);
                return batchRequired;
            }
        }
        return batchRequired;
    }


}
