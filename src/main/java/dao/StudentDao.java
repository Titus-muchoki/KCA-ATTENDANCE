package dao;

import models.Student;

import java.util.List;

public interface StudentDao {
    //CREATE
    void add(Student student);
    //READ
    List<Student> getAll();
    Student findById(int id);
    //UPDATE
    void update(int id,String name, String reg, String email, String tel, String unit, String lecture, String dateTaught, String studentRemark, int courseId);
    //DELETE
    void deleteById(int id);
    void clearAll();
}
