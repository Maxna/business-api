package dao;

import models.Service;
import org.sql2o.*;
import java.util.List;

public class Sql2oServiceDao implements ServiceDao {
    private final Sql2o sql2o;

    public Sql2oServiceDao (Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Service type) {
        String sql = "INSERT INTO services (type, projectId) VALUES (:type, :projectId)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(type)
                    .executeUpdate()
                    .getKey();
            type.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
