package BatchAllocatorSystem.models;

import BatchAllocatorSystem.enums.Gender;

abstract public class User {

    private String name;
    private Gender gender;

    public User(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }
}
