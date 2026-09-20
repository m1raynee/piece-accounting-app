package com.m1raynee.pieceaccountingapp.students;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m1raynee.pieceaccountingapp.web.PageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public PageResponse<StudentResponseDto> getAllStudents(
            @RequestParam String name,
            @PageableDefault Pageable pageable) {
        return PageResponse.from(studentService.findAll(name, pageable));
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PostMapping
    public StudentResponseDto createStudent(@RequestBody String name) {
        return new StudentResponseDto(1L, name);
    }

}
