package dao;

import models.Service;

import java.util.List;

public interface ServiceDao {


    //LIST
    List<Service> getAll();

    //CREATE
    void add(Service type, Service detail, int projectId);

    //READ
    Service findById(int id);

    //UPDATE
    void update(String type, String detail, int projectId);

    //DELETE
    void deleteById(int id);
    void clearAllServices();

}
