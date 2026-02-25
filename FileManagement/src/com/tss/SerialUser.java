package com.tss;

import java.io.Serial;
import java.io.Serializable;

public class SerialUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private transient long id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return name + " " + age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }
}
