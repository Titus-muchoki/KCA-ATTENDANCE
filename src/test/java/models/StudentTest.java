package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentTest {
    @Before
    public void setup(){

    }
    @After
    public void tearDown(){

    }
    @Test
    public void getNameReturnsCorrectly() throws Exception{
        Student student = setupStudent();
        assertEquals("tito",student.getName());
    }
    @Test
    public void getRegReturnsCorrectly() throws Exception{
        Student student = setupStudent();
        assertEquals("18", student.getReg());
    }
    @Test
    public void getEmailReturnsCorrectly()throws Exception{
        Student student = setupStudent();
        assertEquals("@t", student.getEmail());
    }
    @Test
    public void getTelReturnsTelCorrectly() throws Exception{
        Student student = setupStudent();
        assertEquals("07", student.getTel());
    }
    @Test
    public void getUnitReturnsUnitCorrectly() throws Exception{
        Student student = setupStudent();
        assertEquals("hv", student.getUnit());
    }
    @Test
    public void getLectureReturnsCorrectly() throws Exception{
        Student student = setupStudent();
        assertEquals("gl", student.getLecture());
    }
    @Test
    public void  getDateTaughtReturnsCorrectly()throws Exception{
        Student student = setupStudent();
        assertEquals("21", student.getDateTaught());
    }
    @Test
    public void getStudentRemarkReturnsCorrectly() throws Exception{
        Student student = setupStudent();
        assertEquals("good", student.getStudentRemark());
    }
    @Test
    public void getCourseIdReturnsCorrectly()throws Exception{
        Student student = setupStudent();
        assertEquals(1, student.getCourseId());
    }
    //HELPERS
    public Student setupStudent()throws Exception{
        return new Student("tito","18","@t","07","hv","gl","21","good",1);
    }
}
