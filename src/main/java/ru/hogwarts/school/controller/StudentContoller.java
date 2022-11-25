package ru.hogwarts.school.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentContoller {
    private final StudentService studentService;

    public StudentContoller(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student createdStudent=studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> readStudent(@PathVariable Long id){
        Student student=studentService.readStudent(id);
        if (student==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        Student updatedStudent=studentService.updateStudent(student);
        if (updatedStudent==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        Student deletedStudent=studentService.deleteStudent(id);
        return ResponseEntity.ok(deletedStudent);
    }
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        return ResponseEntity.ok(studentService.getStudents());
    }
    @GetMapping("{age}")
    public ResponseEntity<List<Student>> getStudnentsByAge(@PathVariable Integer age){
        return ResponseEntity.ok(studentService.getStudentsByAge(age));
    }

}
