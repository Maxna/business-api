package dao;

import models.Service;
import models.Project;

import java.util.List;

public interface ProjectDao {


    //LIST
    List<Project> getAll();
    List<Service> getAllServicesForAProject(int projectId);

    //CREATE
    void add(Project title);

    //READ
    Project findById(int id);

    //UPDATE
    void update(int id, String title, String description);

    //DELETE
    void deleteById(int id);
    void clearAllProjects();

}
