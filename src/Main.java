import java.io.File;
import java.util.Scanner;


public class Main {
    //static String used to determine the next action taken by the programme, it is invoked from static contexts and its state is consistent throughout them
    private static String choice;
    public static void main(String[] args) {
        //defining the files to be used by the readers
        File f1 = new File("brumber_nodes.csv");
        File f2 = new File("brumber_connections.csv");
        File f3 = new File("brumber_people.csv");
        //creating the Graph object named brum
        Graph brum = new Graph(f1, f2);
        //creating the collection of users
        Person.readUser(f3);
        //sorting the collection of nodes and users alphabetically
        Graph.nodesSort();
        Person.userSort();
        //initializing the text based user interface
        System.out.println("Welcome to Brumber.\n");
        Scanner input = new Scanner(System.in);
        System.out.println("Select an option (type option # to continue, or type Exit to quit):\n" +
                "1. Places \n2. Users \n3. Users starting at \n4. Users heading to \n" +
                "5. Shortest route for driver \n6. Driver for pickup \n7. Driver for pickup (Brumcab alternative) \n");
        //assigning a value to static variable choice, based on the user's demand, and commencing the function they requested
        choice = input.nextLine();
        //while loop used to continue the runtime of the programme, until the users requests it to quit
        while (!(choice.equals("Exit")) && !(choice.equals("exit"))) {
            executable(brum);
        }
    }
    //the method that executes the function the user requested, using a switch clause examining the choice variable and executing a function based on it,
    //and setting a new value for it after the function has been completed depending on user's demand
    private static void executable(Graph G) {
        switch (choice) {
            //functional requirement 1, prints the list of locations of Graph G in alphabetical order
            case "1":
                System.out.println("The available meeting spots are: \n" + G.nodesString());
                break;
            //functional requirement 2, prints the list of available users in alphabetical order
            case "2":
                System.out.println("The available users online are: \n" + Person.usersString(Person.users));
                break;
            //functional requirement 3, prints the list of users starting from a specific user-requested location in alphabetical order
            case "3":
                Scanner in = new Scanner(System.in);
                System.out.println("Select starting point (type name, case sensitive): \n" + G.nodesString());
                String n1 = "\"" + in.nextLine() + "\"";
                String d1 = Person.usersFrom(G.searchIndex(n1));
                if (d1.equals("")) {
                    System.out.println("There are no users starting from this location.\n");
                }
                else {
                    System.out.println("The users starting from this location are the following:\n" + d1);
                }
                break;
            //functional requirement 4, prints the list of users heading to a specific user-requested location in alphabetical order
            case "4":
                Scanner in2 = new Scanner(System.in);
                System.out.println("Select destination (type name, case sensitive): \n" + G.nodesString());
                String n2 = "\"" + in2.nextLine() + "\"";
                String d2 = Person.usersTo(G.searchIndex(n2));
                if (d2.equals("")){
                    System.out.println("There are no users headed to this location.\n");
                }
                else {
                    System.out.println("The users headed to this location are the following:\n" + d2);
                }
                break;
            //INCOMPLETE functional requirement 5
            case "5":
                Scanner in3 = new Scanner(System.in);
                System.out.println("Select driver (type name, case sensitive): \n" + Person.usersString(Person.drivers));
                String n3 = "\"" + in3.nextLine() + "\"";
                break;
            //functional requirement 6, takes in a user-requested non driver and finds a driver who makes the same trip
            case "6":
                Scanner in4 = new Scanner(System.in);
                System.out.println("Select non-driver (type name, case sensitive): \n" + Person.usersString(Person.nondrivers));
                String n4 = "\"" + in4.nextLine() + "\"";
                String d4 = Person.userSameTrip(Person.drivers, Person.searchStart(n4), Person.searchFinish(n4));
                if (d4.equals("")) {
                    System.out.println("There are no drivers available making this trip.\n");
                }
                else {
                    System.out.println("The following drivers are making this trip:\n" + d4);
                }
                break;
            //functional requirement 7, takes in a user-requested non driver and finds a driver who makes the same trip, directing them to Brumcab if there is none
            case "7":
                Scanner in5 = new Scanner(System.in);
                System.out.println("Select non-driver (type name, case sensitive): \n" + Person.usersString(Person.nondrivers));
                String n5 = "\"" + in5.nextLine() + "\"";
                String d5 = Person.userSameTrip(Person.drivers, Person.searchStart(n5), Person.searchFinish(n5));
                if (d5.equals("")) {
                    System.out.println("There are no drivers available making this trip. Redirecting you to Brumcab.\n");
                }
                else {
                    System.out.println("The following drivers are making this trip:\n" + d5);
                }
                break;
            default:
                System.out.println("Invalid option.");
        }
        //after the requested function has been completed, the user is asked again if they want some new function to be executed or to quit the programme
        Scanner input = new Scanner(System.in);
        System.out.println("Select an option (type option to continue, or type Exit to quit):\n" +
                "1. Places \n2. Users \n3. Users starting at \n4. Users heading to \n" +
                "5. Shortest route for driver \n6. Driver for pickup \n7. Driver for pickup (Brumcab alternative) \n");
        choice = input.nextLine();
    }
}
