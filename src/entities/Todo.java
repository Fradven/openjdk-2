package entities;

public class Todo {
    private String title;
    private Boolean isDone;
    private Integer id;

    public Todo(String title, Boolean isDone, Integer id) {
        this.title = title;
        this.isDone = isDone;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
