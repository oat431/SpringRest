package com.example.springrest.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.springrest.Logging.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrest.Model.Courses;
import com.example.springrest.Repository.CoursesRepository;

@RestController
@RequestMapping("/api/v1")
public class CoursesController {
    @Autowired
    private CoursesRepository coursesRepository;

    @GetMapping("/courses")
    public List<Courses> getAllCourses(){
        return coursesRepository.allCourse();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Courses> getCourseInfoById(@PathVariable(value="id") Long course_id) throws ResourceNotFound{
        Courses courses = coursesRepository.findCourseById(course_id)
                .orElseThrow(()->new ResourceNotFound("Course not found for this id :: " + course_id + "maybe wrong id or we don't have this course yet"));
        return ResponseEntity.ok().body(courses);
    }
}
