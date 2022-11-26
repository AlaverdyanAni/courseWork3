package ru.hogwarts.school.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;
    public FacultyController(FacultyService facultyService){
        this.facultyService=facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty){
        Faculty createdFaculity=facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculity);
    }
    @GetMapping("{id}")
    public ResponseEntity<Faculty> readFaculty(@PathVariable Long id){
        Faculty faculty=facultyService.readFaculty(id);
        if (faculty==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty){
      Faculty updatedFaculty=facultyService.updateFaculty(faculty);
      if (updatedFaculty==null){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.ok(updatedFaculty);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id){
     Faculty deletedFaculity=facultyService.deleteFaculty(id);
     return ResponseEntity.ok(deletedFaculity);
    }
    @GetMapping
    public ResponseEntity<List<Faculty>> getFaculties(){
        return ResponseEntity.ok(facultyService.getFaculties());
    }
    @GetMapping
    public ResponseEntity<List<Faculty>> getFacultiesByColour(@RequestParam String colour){
        if (colour==null && colour.isBlank()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(facultyService.getFacultiesByColour(colour));
    }
}
