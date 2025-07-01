package BatchAllocatorSystem.repository;

import BatchAllocatorSystem.models.Admin;

import java.util.HashMap;
import java.util.Map;

public class AdminRepository {

    Map<String, Admin> adminMap;

    public AdminRepository() {
        this.adminMap = new HashMap<>();
    }

    public void save(Admin admin) {
        adminMap.put(admin.getName(), admin);
    }

    public Admin getAdmin(String adminName){
        return adminMap.get(adminName);
    }
}
