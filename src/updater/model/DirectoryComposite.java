package updater.model;

import java.util.ArrayList;
import java.util.List;

public class DirectoryComposite extends Component {

    private String directoryPath;
    private List<Component> components = new ArrayList<>();

    public DirectoryComposite(String directoryPath) {
        super(directoryPath);
        this.directoryPath = directoryPath;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public List<Component> getComponents() {
        return components;
    }

    @Override
    public void checkVersion() {
        System.out.println("Verificando directorio: " + directoryPath);
        for (Component component : components) {
            component.checkVersion();
        }
    }

    @Override
    public String getVersion() {
        return "v1.0"; // Placeholder, implement as needed
    }
}
