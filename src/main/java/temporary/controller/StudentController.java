package temporary.controller;

import org.springframework.web.bind.annotation.*;
import temporary.dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Students> studentList = new ArrayList<>();

    @PostMapping
    public Students createStudent(@RequestBody StudentDTO studentDTO) {
        Students student = new Students(
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getZone(),
                studentDTO.getGender()
        );
        studentList.add(student);
        return student;
    }

    @GetMapping
    public List<Students> getAllStudents() {
        return studentList;
    }

    @GetMapping("/{id}")
    public Students getStudentById(@PathVariable int id) {

        return studentList.get(id);
    }

    @PutMapping("/{id}") // PUT아니면 PATCH
    public Students updateStudent(@PathVariable int id, @RequestBody StudentDTO studentDTO) {

        Students student = studentList.get(id);
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setZone(studentDTO.getZone());
        student.setGender(studentDTO.getGender());
        return student;
    }

    @DeleteMapping("/{id}") // 중요 정보는 바로 삭제 보다는 상태변경 어떨까
    public void deleteStudent(@PathVariable int id) {

        studentList.remove(id);
    }
}
