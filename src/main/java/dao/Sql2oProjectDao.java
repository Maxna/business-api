package dao;

import models.Project;
import models.Service;
import org.sql2o.*;
import java.util.List;

public class Sql2oProjectDao implements ProjectDao {
    private final Sql2o sql2o;

    public Sql2oProjectDao (Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Project title) {
        String sql = "INSERT INTO projects (title) VALUES (:title)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(title)
                    .executeUpdate()
                    .getKey();
            title.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Project findById(int id) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM projects WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Project.class);
        }
    }

    @Override
    public List<Project> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM projects")
                    .executeAndFetch(Project.class);
        }
    }

    @Override
    public void update(int id, String newTitle, String newDescription) {
        String sql = "UPDATE projects SET title = :title, description = :description WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("title", newTitle)
                    .addParameter("description", newDescription)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from projects WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }   catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllProjects() {
        String sql = "DELETE from projects";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Service> getAllServicesForAProject(int projectId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM services WHERE projectId = :projectId")
                    .addParameter("projectId", projectId)
                    .executeAndFetch(Service.class);
        }
    }
}
