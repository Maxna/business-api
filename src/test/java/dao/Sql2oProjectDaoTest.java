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
    public void getAllProjectsCorrectly() throws Exception{
        Project testProject = new Project("Vaseo", "Housing project in Phoenix");
        Project otherProject = new Project("Vaseo", "Housing project in Phoenix");
        Project emptyProject = new Project("Vaseo", "Housing project in Phoenix");
        projectDao.add(testProject);
        projectDao.add(otherProject);
        assertFalse(projectDao.getAll().contains(emptyProject));
        assertNotEquals(3, projectDao.getAll().size());
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

    @Test
    public void updatesProjectTitleCorrectly() throws Exception{
        Project testProject = new Project("Vaseo", "Housing project in Phoenix");
        projectDao.add(testProject);
        projectDao.update(testProject.getId(), "1600 Vine", "Housing project in Hollywood");
        Project updatedProject = projectDao.findById(testProject.getId());
        assertEquals("1600 Vine", updatedProject.getTitle());
        assertEquals("Housing project in Hollywood", updatedProject.getDescription());
    }

    @Test
    public void deletesCorrectlyById() throws Exception{
        Project testProject = new Project("Vaseo", "Housing project in Phoenix");
        projectDao.add(testProject);
        Project otherProject = new Project("1600 Vine", "Housing project in Hollywood");
        projectDao.add(otherProject);
        projectDao.deleteById(testProject.getId());
        assertFalse(projectDao.getAll().contains(testProject));
    }

    @Test
    public void clearsAllProjectsCorrectly() throws Exception{
        Project testProject = new Project("Vaseo", "Housing project in Phoenix");
        projectDao.add(testProject);
        Project otherProject = new Project("1600 Vine", "Housing project in Hollywood");
        projectDao.add(otherProject);
        projectDao.clearAllProjects();
        assertEquals(0, projectDao.getAll().size());
    }

    @Test
    public void getsAllServicesForAProjectCorrectly() throws Exception{
        Project testProject = new Project("Vaseo", "Housing project in Phoenix");
        projectDao.add(testProject);
        int projectId = testProject.getId();
        Service service1 = new Service("Consulting", "Bond Services",  1);
        Service service2 = new Service("Tax Credit", "Tax Credit Services",  1);
        Service service3 = new Service("Advising", "Consultations",  1);
        serviceDao.add(service1);
        serviceDao.add(service2);
        assertEquals(2, projectDao.getAllServicesForAProject(projectId).size());
        assertTrue(projectDao.getAllServicesForAProject(projectId).contains(service1));
//        assertTrue(projectDao.getAllServicesForAProject(projectId).contains(service2));
        assertFalse(projectDao.getAllServicesForAProject(projectId).contains(service3));

    }
}
