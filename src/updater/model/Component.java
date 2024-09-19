package updater.model;

public abstract class Component {
    private String path;

    public Component(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public abstract void checkVersion();

    public abstract String getVersion();
}
