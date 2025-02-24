// src/main/java/com/example/busbooking/model/Passenger.java
package com.example.busbooking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "passengers")
public class Passenger {
    @Id
    private String id;
    private String name;
    private int age;

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
