package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FacultyTest {
    @Before
    public void setup()throws Exception{

    }
    @After
    public void tearDown()throws Exception{

    }
    @Test
    public void getNameReturnsCorrectly() throws Exception{
        Faculty faculty = setupFaculty();
        assertEquals("sot", faculty.getName());
    }
    //HELPERS
    public Faculty setupFaculty()throws Exception{
        return new Faculty("sot");
    }
}
