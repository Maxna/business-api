package models;

public class Service {
    private int id;
    private String type;
    private String detail;
    private int projectId;


    public Service(String type, String detail, int projectId) {
        this.type = type;
        this.detail = detail;
        this.projectId = projectId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }
}
