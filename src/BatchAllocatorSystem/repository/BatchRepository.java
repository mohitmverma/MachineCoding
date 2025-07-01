package BatchAllocatorSystem.repository;

import BatchAllocatorSystem.enums.Stream;
import BatchAllocatorSystem.models.Batch;

import java.util.ArrayList;
import java.util.List;

public class BatchRepository {

    List<Batch> batchList;

    public BatchRepository() {
        this.batchList = new ArrayList<>();
    }

    public void save(Batch batch) {
        batchList.add(batch);
    }

    public List<Batch> getAvailableBatchList(Stream stream) {
        List<Batch> availableBatches = new ArrayList<>();

        for(Batch batch: batchList) {
            if(batch.getCapacity()>0 && batch.getStream().equals(stream)) {
                availableBatches.add(batch);
            }
        }
        return availableBatches;
    }
}
