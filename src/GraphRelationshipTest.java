import java.util.HashMap;
import java.util.LinkedList;

class Project {

    private String name;
    private LinkedList<Project> dependencies;
    private boolean marked;

    public Project(String name) {
        this.name = name;
        this.dependencies = new LinkedList<>();
        this.marked = false;
    }

    public void addDependency(Project project) {
        if (!dependencies.contains(project)) {
            dependencies.add(project);
        }
    }

    public LinkedList<Project> getDependencies() {
        return dependencies;
    }

    public boolean getMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public String getName() {
        return name;
    }
}

class ProjectManager {
    private HashMap<String, Project> projects;

    public ProjectManager(String[] names, String[][] dependencies) {
        buildProject(names);
        addDependencies(dependencies);
    }

    void addDependencies(String[][] dependencies) {
        for(String[] dependency : dependencies){
            Project before = findProject(dependency[0]);
            Project after = findProject(dependency[1]);
            after.addDependency(before);
        }
    }

    private int index;

    public Project[] buildOrder(){
        initMarkFlags();
        Project[] ordered = new Project[this.projects.size()];
        index = 0;
        for(Project project : this.projects.values()){
            buildOrder(project , ordered);
        }
        return ordered;
    }

    public void buildOrder(Project project , Project[] ordered){
        if(!project.getDependencies().isEmpty()){
            for(Project p : project.getDependencies()){
                buildOrder(p, ordered);
            }
        }
        if(!project.getMarked()){
            project.setMarked(true);
            ordered[index] = project;
            index++;
        }
    }

    private void initMarkFlags(){
        for(Project project : this.projects.values()){
            project.setMarked(false);
        }
    }


    void buildProject(String[] names) {
        projects = new HashMap<>();
        for(int i = 0 ; i < names.length; i++){
            projects.put(names[i] , new Project(names[i]));
        }
    }

    Project findProject(String name){
        return projects.get(name);
    }
}


public class GraphRelationshipTest {

    public static void main(String[] args) {
        String[] projects = {"a","b","c","d","e","f","g"};
        String[][] dependencies = {{"f","a"},{"f","b"},{"f","c"},{"b","a"},{"c","a"},{"a","e"},{"b","e"},{"d","g"}};
        ProjectManager pm = new ProjectManager(projects,  dependencies);
        Project[] ps = pm.buildOrder();
        for(Project p : ps){
            if(p!= null){
                Util.println(p.getName() + " ");
            }
        }
    }
}
