package com.example.springrest.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.springrest.Model.Courses;

@Repository
public class CoursesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class CourseMapper implements RowMapper< Courses > {
        @Override
        public Courses mapRow(ResultSet resultSet, int i) throws SQLException {
            Courses courses = new Courses();
            courses.setCourse_id(resultSet.getInt("course_id"));
            courses.setCourse_name(resultSet.getString("course_name"));
            courses.setCourse_credit(resultSet.getDouble("course_credit"));
            return courses;
        }
    }

    public List<Courses> allCourse() {
        return jdbcTemplate.query("Select * From courses",new CourseMapper());
    }

    public Optional<Courses> findCourseById(long course_id){
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "Select * From courses Where course_id=?",
                new Object[] {course_id},
                new BeanPropertyRowMapper<Courses>(Courses.class)
                )
        );
    }

    public Optional<Courses> findCourseByName(String course_name){
        return Optional.ofNullable(jdbcTemplate.queryForObject(
            "Select * From courses Where course_name=?",
            new Object[] {course_name},
            new BeanPropertyRowMapper<Courses>(Courses.class)
        )
        );
    }

    public int deleteCourse(Courses course){
        return jdbcTemplate.update("Delete From courses where course_id=?",
                new Object[]{course.getCourse_id()}
        );
    }

    public int AddCourse(Courses courses){
        return jdbcTemplate.update(
                "Insert into courses (course_id,course_name,course_credit) values(?,?,?)",
                new Object[]{
                    courses.getCourse_id(),courses.getCourse_name(),courses.getCourse_credit()
                });
    }

    public int update(Courses courses){
        return jdbcTemplate.update(
                "Update courses set course_name=?, course_credit=? where course_id=?",
                new Object[]{
                        courses.getCourse_name(),courses.getCourse_credit(), courses.getCourse_id()
                }
        );
    }
}
