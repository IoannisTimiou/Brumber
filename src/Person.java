import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Person {
    //attributes of Person objects
    int index;
    int start;
    int finish;
    boolean driving;
    String name;
    //ArrayLists containing created Person objects, they are declared static because they remain similar for all classes and invoked from static classes
    static ArrayList<Person> users = new ArrayList<>();
    static ArrayList<Person> drivers = new ArrayList<>();
    static ArrayList<Person> nondrivers = new ArrayList<>();
    //constructor for Person objects, adds each object to the users ArrayList, as well as to either the drivers or nondrivers one, depending on its driving attribute
    public Person(int index, int start, int finish, int driver, String name) {
        this.index = index;
        this.start = start;
        this.finish = finish;
        this.name = name;
        if ((driver == 0)) {
            this.driving = false;
            nondrivers.add(this);
        }
        if ((driver == 1)) {
            this.driving = true;
            drivers.add(this);
        }
        users.add(this);
    }
    //getters for the name, start and finish attributes of objects of class Person
    public String getName() { return name; }
    public int getStart() { return start; }
    public int getFinish() { return finish; }
    //Comparator for Person objects, compares them in terms of alphabetical priority
    private static Comparator<Person> userComparator = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return (int) (p1.getName().compareTo(p2.getName()));
        }
    };
    //sorting command for the users ArrayList, sorts them in alphabetical order
    public static void userSort() {
        Collections.sort(users, userComparator);
    }
    //String output of an ArrayList containing Person objects, uses a for loop to exhaust the collection and return all of the user names as a string
    public static String usersString(ArrayList<Person> p) {
        String p1 = "";
        for (int i=0; i<p.size(); i++) {
            p1 = p1 + (p.get(i)).getName() + "\n";
        }
        return p1;
    }

    //reader for users, takes a file as input and reads it to create the Person objects and fill the ArrayList
    public static void readUser(File F) {
        //File file = new File("brumber_connections.csv");
        try {
            Scanner scan = new Scanner(F);
            while (scan.hasNextLine()) {
                String info = scan.nextLine();
                String[] tokens = info.split(",");
                if (tokens.length == 5) {
                    int first = Integer.parseInt(tokens[0]);
                    int second = Integer.parseInt(tokens[1]);
                    int third = Integer.parseInt(tokens[2]);
                    int fourth = Integer.parseInt(tokens[3]);
                    if (!(fourth == 0) && !(fourth == 1)) {
                        throw new IllegalArgumentException("Fourth integer must be either 0 or 1");
                    }
                    String fifth = tokens[4];
                    Person user = new Person(first, second, third, fourth, fifth);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Missing file input.");
        }
    }
    //method for outputting all the names of the users starting from a specific location, it is declared static since it is called from static contexts
    public static String usersFrom(int n1) {
        String p1 = "";
        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getStart() == n1) {
                p1 = p1 + users.get(i).getName() + "\n";
            }
        }
        return p1;
    }
    //method for outputting all the names of the users heading to a specific location, it is declared static since it is called from static contexts
    public static String usersTo(int n1) {
        String p1 = "";
        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getFinish() == n1) {
                p1 = p1 + users.get(i).getName() + "\n";
            }
        }
        return p1;
    }
    //method that takes in a user's name, and returns their starting point of their trip as an integer
    public static int searchStart(String name) {
        int st = 0;
        for (int i=0; i<users.size(); i++) {
            String n1 = users.get(i).getName();
            if (n1.equals(name)) {
                st= users.get(i).getStart();
            }
            if (st > 0) {
                break;
            }
        }
        return st;
    }
    //method that takes in a user's name, and returns the destination of their trip as an integer
    public static int searchFinish(String name) {
        int fin = 0;
        for (int i=0; i<users.size(); i++) {
            String n1 = users.get(i).getName();
            if (n1.equals(name)) {
                fin= users.get(i).getFinish();
            }
            if (fin > 0) {
                break;
            }
        }
        return fin;
    }
    //method that takes in an arraylist of users, a starting point and a destination, and returns all users who are making that trip
    public static String userSameTrip(ArrayList<Person> p, int n1, int n2) {
        String p1 = "";
        for (int i=0; i<p.size(); i++) {
            if (n1 == p.get(i).getStart()) {
                if (n2 == p.get(i).getStart()) {
                    p1 = p1 + p.get(i).getName() + "\n";
                 }
            }
        }
        return p1;
    }
}
