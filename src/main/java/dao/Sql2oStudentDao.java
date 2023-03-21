package dao;

import models.Student;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oStudentDao implements StudentDao{
    private final Sql2o sql2o;

    public Sql2oStudentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Student student) {
        String sql = "INSERT INTO students (name, reg, email, tel, unit, lecture, datetaught, studentRemark, courseid) VALUES (:name, :reg, :email, :tel, :unit, :lecture, :dateTaught, :studentRemark, :courseId)"; //raw sql
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(student)
                    .executeUpdate()
                    .getKey();
            student.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Student> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM students")
                    .executeAndFetch(Student.class);

        }
    }

    @Override
    public Student findById(int id) {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM students WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Student.class);

        }
    }

    @Override
    public void update(int id, String name, String reg, String email, String tel, String unit, String lecture, String dateTaught, String studentRemark, int courseId) {
        String sql = "UPDATE students SET (name, reg, email, tel, unit, lecture, dateTaught, studentRemark, courseId) = (:name, :reg, :email, :tel, :unit, :lecture, :dateTaught, :studentRemark, :courseId) WHERE id=:id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("reg", reg)
                    .addParameter("email", email)
                    .addParameter("tel", tel)
                    .addParameter("unit", unit)
                    .addParameter("lecture", lecture)
                    .addParameter("dateTaught", dateTaught)
                    .addParameter("studentRemark", studentRemark)
                    .addParameter("courseId", courseId)
                    .addParameter("id", id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from students WHERE id = :id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = " DELETE from students";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
