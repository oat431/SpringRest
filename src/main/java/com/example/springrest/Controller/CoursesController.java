package com.example.springrest.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.springrest.Logging.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("course/{name}")
    public ResponseEntity<Courses> getCourseInfoByName(@PathVariable(value="name") String course_name) throws ResourceNotFound {
        Courses courses = coursesRepository.findCourseByName(course_name).orElseThrow(()-> new ResourceNotFound("No name or wrong name"));
        return ResponseEntity.ok().body(courses);
    }

    @PostMapping("/courses")
    public int addCourse(@Valid @RequestBody Courses courses){
        return coursesRepository.AddCourse(courses);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Integer> updateCourses(@PathVariable(value="id") Long course_id,@Valid @RequestBody Courses courseDetails) throws ResourceNotFound{
        Courses courses = coursesRepository.findCourseById(course_id).orElseThrow(()->new ResourceNotFound("Not have this course yet"));
        courses.setCourse_credit(courseDetails.getCourse_credit());
        courses.setCourse_name(courseDetails.getCourse_name());
        final int updateCourse = coursesRepository.update(courses);
        return ResponseEntity.ok(updateCourse);
    }

    @DeleteMapping("/courses/{id}")
    public Map<String,Boolean> deleteCourses(@PathVariable(value = "id")Long course_id) throws ResourceNotFound{
        Courses courses = coursesRepository.findCourseById(course_id).orElseThrow(()->new ResourceNotFound("Not have this course yet"));
        coursesRepository.deleteCourse(courses);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
