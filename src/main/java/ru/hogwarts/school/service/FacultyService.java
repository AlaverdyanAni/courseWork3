package ru.hogwarts.school.service;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 100;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(lastId);
        faculties.put(lastId, faculty);
        lastId += 100;
        return faculty;
    }

    public Faculty readFaculty(long id) {
        return faculties.get(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

    public List<Faculty> getFaculties() {
        return new ArrayList<>(faculties.values());
    }

    public List<Faculty> getFacultiesByColour(String colour) {
        return faculties.values().stream()
                .filter((f -> f.getColor().equals(colour)))
                .collect(Collectors.toList());
    }
}