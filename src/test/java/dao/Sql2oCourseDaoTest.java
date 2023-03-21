package dao;

import models.Course;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2oCourseDaoTest {
    private Sql2OCourseDao courseDao; //ignore me for now. We'll create this soon.

    private Sql2oStudentDao studentDao;

    private static Connection con;

    @Before

    public void setUp(){
        String connectionString = "jdbc:postgresql://localhost:5432/kca_test"; // connect to postgres test database

        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        courseDao = new Sql2OCourseDao(sql2o); //ignore me for now
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
    //HELPER
    public Course setupCourse() throws Exception{
        return new Course("sot");
    }
}
