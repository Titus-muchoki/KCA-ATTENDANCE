package dao;

import models.Course;
import models.Student;

import java.util.List;

public interface CourseDao {
    //CREATE
    void add(Course course);

    //READ
    List<Course> getAll();
    List<Student> getAllStudentsByCourse(int courseId);

    Course findById(int id);

    //UPDATE

    void update( int id, String name);

    //DELETE
    void deleteById(int id);
    void clearAllCourses();
}
