package ru.hogwarts.school.service;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students=new HashMap<>();
    private long lastId=0;

    public Student createStudent(Student student){
        student.setId(++lastId);
        students.put(lastId,student);
        return student;
    }
    public Student readStudent(long id){
        return students.get(id);
    }
    public Student updateStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }
    public Student deleteStudent(long id){
        return students.remove((id));
    }
    public List<Student> getStudents(){
        return new ArrayList<>(students.values());

    }
    public List<Student> getStudentsByAge(int age){
        if (age<7||age>60){
            throw new IllegalArgumentException();
        }
            return students.values().stream()
                .filter(s -> s.getAge()==age)
                .collect(Collectors.toList());
    }
}
