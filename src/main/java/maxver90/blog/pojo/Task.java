package maxver90.blog.pojo;

public class Task {

    private final String name;

    private final boolean complete;

    public Task(String name, boolean complete) {
        this.name = name;
        this.complete = complete;
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return complete;
    }
}
