package pl.kurs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Shape {
    @JsonIgnore
    public abstract double getArea();
    @JsonIgnore
    public abstract double getPerimeter();
}
