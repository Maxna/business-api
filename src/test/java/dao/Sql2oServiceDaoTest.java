package dao;

import models.Service;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oServiceDaoTest {
    private Sql2oServiceDao serviceDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        serviceDao = new Sql2oServiceDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingServiceSetsId() throws Exception{
        Service testService = new Service("Consulting", "Tax Credit Bond Services", 1);
        int originalServiceId = testService.getId();
        serviceDao.add(testService);
        assertNotEquals(originalServiceId, testService.getId());
    }
}
