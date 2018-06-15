package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void Service_InstantiatesNewInstanceCorrectly() throws Exception{
        Service testService = new Service("Consultant", "Tax Exempt Bond Services", 1);
        assertTrue(testService instanceof Service);
    }

    @Test
    public void getType_CorrectlyGetsType() throws Exception{
        Service testService = new Service("Consultant", "Tax Exempt Bond Services", 1);
        assertEquals("Consultant", testService.getType());
    }

    @Test
    public void getDetail_CorrectlyGetsDetail() throws Exception{
        Service testService = new Service("Consultant", "Tax Exempt Bond Services", 1);
        assertEquals("Tax Exempt Bond Services", testService.getDetail());
    }

    @Test
    public void getProjectId_CorrectlyGetsProjectId() throws Exception{
        Service testService = new Service("Consultant", "Tax Exempt Bond Services", 1);
        assertEquals(1, testService.getProjectId());
    }
}
