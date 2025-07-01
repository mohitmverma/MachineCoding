package BatchAllocatorSystem.models;

import BatchAllocatorSystem.enums.Gender;
import BatchAllocatorSystem.enums.Stream;

public class Student extends User{

    private Stream stream;

    public Student(String name, Gender gender) {
        super(name, gender);
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }
}
