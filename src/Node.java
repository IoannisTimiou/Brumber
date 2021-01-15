

public class Node {
    //attributes for objects of class nodes
    private String name;
    private int index;
    boolean visited = false;
    //constructor for Node objects, includes putting each created Node into an ArrayList
    public Node(String name, int index)
    {
        this.name = name;
        this.index = index;
        Graph.nodes.add(this);
    }

    //getters for class attributes
    public String getName() { return name; }
    public int getIndex() { return index; }
}
