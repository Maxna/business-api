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
}
