package updater.model;

public class FileLeaf extends Component {
    private String version;

    public FileLeaf(String path, String version) {
        super(path);
        this.version = version;
    }

    @Override
    public void checkVersion() {
        // Implement version check logic if needed
    }

    @Override
    public String getVersion() {
        return version;
    }
}
