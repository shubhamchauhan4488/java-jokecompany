package com.jokecompany;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class DtoTest {

    @Test
    void testGetName() {
        Dto dto = new Dto("John", "Doe");
        assertEquals("John", dto.getName());
    }

    @Test
    void testSetName() {
        Dto dto = new Dto("John", "Doe");
        dto.setName("Jane");
        assertEquals("Jane", dto.getName());
    }

    @Test
    void testGetSurname() {
        Dto dto = new Dto("John", "Doe");
        assertEquals("Doe", dto.getSurname());
    }

    @Test
    void testSetSurname() {
        Dto dto = new Dto("John", "Doe");
        dto.setSurname("Smith");
        assertEquals("Smith", dto.getSurname());
    }

    @Test
    void testToString() {
        Dto dto = new Dto("John", "Doe");
        String expected = "Dto{name='John', surname='Doe'}";
        assertEquals(expected, dto.toString());
    }

    @Test
    void testEquals() {
        Dto dto1 = new Dto("John", "Doe");
        Dto dto2 = new Dto("John", "Doe");
        Dto dto3 = new Dto("Jane", "Smith");
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
    }

    @Test
    void testHashCode() {
        Dto dto1 = new Dto("John", "Doe");
        Dto dto2 = new Dto("John", "Doe");
        Dto dto3 = new Dto("Jane", "Smith");
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }
}
