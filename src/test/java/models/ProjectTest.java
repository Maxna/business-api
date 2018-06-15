package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectTest {

    @Test
    public void Project_InstantiatesNewInstanceCorrectly() throws Exception{
        Project testProject = new Project("Vaseo", "Housing project in Phoenix");
        assertTrue(testProject instanceof Project);
    }

    @Test
    public void getTitle_CorrectlyGetsTitle() throws Exception{
        Project testProject = new Project("Vaseo", "Housing project in Phoenix");
        assertEquals("Vaseo", testProject.getTitle());
    }

    @Test
    public void getDescription_CorrectlyGetsDescription() throws Exception{
        Project testProject = new Project("Vaseo", "Housing project in Phoenix");
        assertEquals("Housing project in Phoenix", testProject.getDescription());
    }
}
