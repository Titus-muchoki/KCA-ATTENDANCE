package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CourseTest {
    @Before
    public void setup()throws Exception{

    }
    @After
    public void tearDown()throws Exception{

    }
    @Test
    public void getNameReturnsCorrectly() throws Exception{
        Course course = setupCourse();
        assertEquals("sot", course.getName());
    }
    //HELPERS
    public Course setupCourse()throws Exception{
        return new Course("sot");
    }
}
