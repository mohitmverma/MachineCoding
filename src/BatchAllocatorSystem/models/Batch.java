package BatchAllocatorSystem.models;

import BatchAllocatorSystem.enums.Stream;
import BatchAllocatorSystem.enums.Timing;

public class Batch {

    private Admin admin;
    int capacity;
    private Stream stream;
    private Timing timing;
    private int totalCapacity;

    public Batch(Admin admin, int capacity, Stream stream, Timing timing) {
        this.admin = admin;
        this.capacity = capacity;
        this.stream = stream;
        this.timing = timing;
        this.totalCapacity = capacity;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Stream getStream() {
        return stream;
    }

    public Timing getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        String adminName= admin.getName();
        return "Batch{" +
                "capacity=" + totalCapacity +
                ", stream=" + stream +
                ", timing=" + timing +
                ", adminName=" + adminName +
                '}';
    }
}
