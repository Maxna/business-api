package dao;

import models.Project;
import models.Service;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oProjectDaoTest {
    private Sql2oProjectDao projectDao;
    private Sql2oServiceDao serviceDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        projectDao = new Sql2oProjectDao(sql2o);
        serviceDao = new Sql2oServiceDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingProjectSetsId() throws Exception{
        Project testProject = new Project("Vaseo", "Housing project in Phoenix");
        int originalProjectId = testProject.getId();
        projectDao.add(testProject);
        assertNotEquals(originalProjectId, testProject.getId());
    }

    @Test
    public void findExistingProjectById() throws Exception{
        Project testProject = new Project("Vaseo", "Housing project in Phoenix");
        int originalProjectId = testProject.getId();
        projectDao.add(testProject);
        assertNotEquals(originalProjectId, testProject.getId());
        assertEquals(1, testProject.getId());
    }


}
