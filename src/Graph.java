import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {
    //ArrayLists for Node objects and Connection objects, they are declared static since they are uniform among all classes and accessed by them
    static ArrayList<Node> nodes = new ArrayList<>();
    static ArrayList<Connection> connections = new ArrayList<>();


    //reader for nodes, takes a file as input and reads it to create the Node objects and fill the ArrayList
    public void readNode(File F) {
        try {
            Scanner scan = new Scanner(F);
            while(scan.hasNextLine()) {
                String info = scan.nextLine();
                String[] tokens = info.split(",");
                if (tokens.length == 2) {
                    String first = tokens[0];
                    int second = Integer.parseInt(tokens[1]);
                    Node node = new Node(first, second);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Missing file input.");
        }

    }
    //reader for connections, takes a file as input and reads it to create the Connection objects and fill the ArrayList
    public void readConnections(File F) {
        //File file = new File("brumber_connections.csv");
        try {
            Scanner scan = new Scanner(F);
            while(scan.hasNextLine()) {
                String info = scan.nextLine();
                String[] tokens = info.split(",");
                if (tokens.length == 3) {
                    int first = Integer.parseInt(tokens[0]);
                    int second = Integer.parseInt(tokens[1]);
                    Double third = Double.parseDouble(tokens[2]);
                    Connection edge = new Connection(first, second, third);
                    Connection back = new Connection(second, first, third);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Missing file input.");
        }
    }
    //Comparator for Node objects, compares them in terms of alphabetical priority
    private static Comparator<Node> nodeComparator = new Comparator<Node>() {
        @Override
        public int compare(Node n1, Node n2) {
            return (int) (n1.getName().compareTo(n2.getName()));
        }
    };
    //sorting command for the nodes ArrayList, sorts them in alphabetical order
    public static void nodesSort() {
        Collections.sort(nodes, nodeComparator);
    }
    //String output of the nodes ArrayList, uses a for loop to exhaust the collection and return all of the node names as a string
    public String nodesString() {
        String n1 = "";
        for (int i=0; i<nodes.size(); i++) {
            //String current = ((Node)nodes.get(i)).getName();
            n1 = n1 + ((Node)nodes.get(i)).getName() + "\n";
        }
        return n1;
    }
    //search algorithm for nodes, take the name of a node as input and returns its index
    public int searchIndex(String name) {
        int d = 0;
        for (int i=0; i<nodes.size(); i++) {
            String n1 = ((Node)nodes.get(i)).getName();
            if (n1.equals(name)) {
                d = ((Node)nodes.get(i)).getIndex();
            }
            if (d > 0) {
                break;
            }
        }
        return d;
    }
//    public Node searchNode(int index) {
//        Node n1 = null;
//        for (int i=0; i<nodes.size(); i++) {
//            int in1 = ((Node)nodes.get(i)).getIndex();
//            if (in1 == index) {
//                n1 = (Node)nodes.get(i);
//            }
//            if (n1.getIndex() > 0) {
//                break;
//            }
//        }
//        return n1;
//    }
    //constructor for Graph objects, takes two files as input and invokes the two readers to create the nodes and connections from them
    public Graph(File f1, File f2) {
        readNode(f1);
        readConnections(f2);
    }
}
