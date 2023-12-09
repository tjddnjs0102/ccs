package temporary.controller;

import org.springframework.web.bind.annotation.*;
import temporary.dto.TeacherDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private List<Teachers> teacherList = new ArrayList<>();

    @PostMapping
    public Teachers createTeacher(@RequestBody TeacherDTO teacherDTO) {
        Teachers teacher = new Teachers(
                teacherDTO.getName(),
                teacherDTO.getAge(),
//                teacherDTO.getZone(),
                teacherDTO.getGender()
        );
        teacherList.add(teacher);
        return teacher;
    }

    @GetMapping
    public List<Teachers> getAllTeachers() {
        return teacherList;
    }

    @GetMapping("/{id}")
    public Teachers getTeacherById(@PathVariable int id) {

        return teacherList.get(id);
    }

    @PutMapping("/{id}")
    public Teachers updateTeacher(@PathVariable int id, @RequestBody TeacherDTO teacherDTO) {

        Teachers teacher = teacherList.get(id);
        teacher.setName(teacherDTO.getName());
        teacher.setAge(teacherDTO.getAge());
//        teacher.setZone(teacherDTO.getZone());
        teacher.setGender(teacherDTO.getGender());
        return teacher;
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {

        teacherList.remove(id);
    }
}
