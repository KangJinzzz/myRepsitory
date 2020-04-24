package kang.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Duck {
    private String name;
    private Integer age;

    public Duck(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
