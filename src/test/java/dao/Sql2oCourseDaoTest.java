package dao;

import models.Course;
import models.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oCourseDaoTest {
    private Sql2oCourseDao courseDao; //ignore me for now. We'll create this soon.

    private Sql2oStudentDao studentDao;

    private static Connection con;

    @Before

    public void setUp(){
        String connectionString = "jdbc:postgresql://localhost:5432/kca_test"; // connect to postgres test database

        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        courseDao = new Sql2oCourseDao(sql2o); //ignore me for now
        studentDao = new Sql2oStudentDao(sql2o);
        con = sql2o.open();
    }
    @After
    public void tearDown() {
        System.out.println("clearing all database");
        courseDao.clearAllCourses();
        studentDao.clearAll();
        con.close();
    }
    @AfterClass
    public static void shutDown(){
        con.close();
        System.out.println("close connection");
    }
    @Test
    public void addingCourseSetsId() {
        Course course = new Course("infection");
        int originalCourseId = course.getId();
        courseDao.add(course);
        assertNotEquals(originalCourseId,course.getId());
    }
    @Test
    public void existingCourseCanBeFoundById() {
        Course course = new Course("infection");
        courseDao.add(course);
        Course foundCourse = courseDao.findById(course.getId());
        assertEquals(course, foundCourse); //should be the same
    }
    @Test
    public void addedCoursesAreReturnedFromGetAll() throws Exception {
        Course course = setupCourse();
        courseDao.add(course);
        assertEquals(1,courseDao.getAll().size());
    }
    @Test
    public void noCoursesReturnsEmptyList() {
        assertEquals(0,courseDao.getAll().size());
    }
    @Test
    public void updateChangesCourseContent() {
        String initialDescription = "viral";
        Course course = new Course(initialDescription);
        courseDao.add(course);
        courseDao.update(course.getId(),"Cleaning");
        Course updatedCourse = courseDao.findById(course.getId());
        assertNotEquals(initialDescription, updatedCourse.getName());
    }
    @Test
    public void deleteByIdDeletesCorrectCourse() throws Exception {
        Course course = setupCourse();
        courseDao.add(course);
        courseDao.deleteById(course.getId());
        assertEquals(0,courseDao.getAll().size());
    }
    @Test
    public void clearAllClearsAllCourses() throws Exception {
        Course course = setupCourse();
        Course otherCourse = new Course("Cleaning");
        courseDao.add(course);
        courseDao.add(otherCourse);
        int daoSize = courseDao.getAll().size();
        courseDao.clearAllCourses();
        assertTrue(daoSize > 0 && daoSize > courseDao.getAll().size());
    }
    @Test
    public void getAllStudentsByCoursesReturnsAllStudentCorrect() throws Exception {
        Course course = setupCourse();
        courseDao.add(course);
        int courseId = course.getId();
        Student newStudent = new Student("mow the lawn","1","2","3","4","5","","",courseId);
        Student otherStudent = new Student("pull weeds", "mow the lawn","1","2","3","4","","",courseId);
        Student thirdStudent = new Student("trim hedge", "mow the lawn","1","2","3","4","","",courseId);
        studentDao.add(newStudent);
        studentDao.add(otherStudent);
        assertEquals(2, courseDao.getAllStudentsByCourse(courseId).size());
        assertTrue(courseDao.getAllStudentsByCourse(courseId).contains(newStudent));
        assertTrue(courseDao.getAllStudentsByCourse(courseId).contains(otherStudent));
        assertFalse(courseDao.getAllStudentsByCourse(courseId).contains(thirdStudent));
    }

    //HELPER
    public Course setupCourse() throws Exception{
        return new Course("sot");
    }
}
