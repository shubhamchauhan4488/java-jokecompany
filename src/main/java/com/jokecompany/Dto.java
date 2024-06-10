package com.jokecompany;
import java.util.Objects;

public class Dto {
    private String name;
    private String surname;

    public Dto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Dto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dto dto = (Dto) o;
        return Objects.equals(name, dto.name) && Objects.equals(surname, dto.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}

