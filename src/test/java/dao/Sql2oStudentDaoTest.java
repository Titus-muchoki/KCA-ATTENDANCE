package dao;

import models.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oStudentDaoTest {
    private Sql2oStudentDao studentDao; //ignore me for now. We'll create this soon.
    private static Connection con; //must be sql2o class conn

    @Before
    public void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/kca_test"; // connect to postgres test database

        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        studentDao = new Sql2oStudentDao(sql2o); //ignore me for now
        con = sql2o.open(); //keep connection open through entire test so, it does not get erased
    }

    @After
    public void tearDown() {
        System.out.println("clearing database");
        studentDao.clearAll();
        con.close();
    }

    @AfterClass
    public static void shutDown() {
        con.close();
        System.out.println("connection closed");
    }
    @Test
    public void addingStudentSetsId() throws Exception {
        Student student = setupStudent();
        int originalStudenttId = student.getId();
        studentDao.add(student);
        assertNotEquals(originalStudenttId, student.getId()); //how does this work?
    }
    @Test
    public void existingStudentCanBeFoundById() throws Exception {
        Student student = setupStudent();
        studentDao.add(student); //add to dao (takes care of saving)
        Student foundStudent = studentDao.findById(student.getId()); //retrieve
        assertEquals(student, foundStudent); //should be the same
    }
    @Test
    public void addedStudentsAreReturnedFromGetAll()throws Exception {
        Student student = setupStudent();
        studentDao.add(student);
        assertEquals(1, studentDao.getAll().size());
    }
    @Test
    public void noStudentReturnsEmptyList() {
        assertEquals(0, studentDao.getAll().size());
    }
    @Test
    public void updateChangesStudentContent() {
        String name = "mow the lawn";
        String reg = "1";
        String email = "2";
        String tel = "3";
        String unit = "4";
        String lecture = "5";
        String dateTaught = "1";
        String studentRemark = "2";
        int courseId = 1;
        int id = 1;
        Student student = new Student ("", "","","","","","","",1);// or use the helper method for easier refactoring
        studentDao.add(student);
        studentDao.update(student.getId(),"", "","","","", "", "","",1);
        Student updatedStudent =  studentDao.findById(student.getId()); //why do I need to refind this?
        assertNotEquals(student, studentDao.getAll().size());
    }
    @Test
    public void deleteByIdDeletesCorrectStudent() throws Exception {
        Student student = setupStudent();
        studentDao.add(student);
        studentDao.deleteById(student.getId());
        assertEquals(0, studentDao.getAll().size());
    }
    @Test
    public void clearAllClearsAll()throws Exception {
        Student student = setupStudent();
        Student otherStudent = new Student("tito","29","12","homa","17","20","","",1);
        studentDao.add(student);
        studentDao.add(otherStudent);
        int daoSize = studentDao.getAll().size();
        studentDao.clearAll();
        assertTrue(daoSize > 0 && daoSize > studentDao.getAll().size()); //this is a little overcomplicated, but illustrates well how we might use `assertTrue` in a different way.
    }
    @Test
    public void officerIdIsReturnedCorrectly() throws Exception {
        Student student = setupStudent();
        int originalCatId = student.getCourseId();
        studentDao.add(student);
        assertEquals(originalCatId,studentDao.findById(student.getId()).getCourseId());
    }

    //HELPERS
    public Student setupStudent() throws Exception {
        return new Student("tito", "18", "@t", "07", "hv", "gl", "21", "good", 1);
    }
}
