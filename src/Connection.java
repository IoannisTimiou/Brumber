public class Connection {
    //attributes for objects of class Connection
    int start;
    int finish;
    double weight;
    //constructor for Connection objects, includes command for putting them in an ArrayList upon creation
    public Connection(int source, int destination, double weight) {
        this.start = source;
        this.finish = destination;
        this.weight = weight;
        Graph.connections.add(this);
    }
}
