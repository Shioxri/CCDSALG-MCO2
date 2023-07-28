import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the file path for the social graph data: ");
        String filePath = scanner.nextLine();

        FileReader fileReader = new FileReader();
        ConnectionsGraph connectionsGraph = fileReader.readConnectionsGraph(filePath);

        if (connectionsGraph == null) {
            System.out.println("Error loading the social graph. Exiting...");
            return;
        }

        System.out.println("Social graph loaded successfully!");
        System.out.println();

        while (true) {
            System.out.println("-----[ MAIN MENU ]-----");
            System.out.println("1. Display Friend List");
            System.out.println("2. Display Connection");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter the ID number of the person to be checked: ");
                    int personID = scanner.nextInt();
                    scanner.nextLine();
                    connectionsGraph.printFriendsList(personID);
                    break;
                case 2:
                    System.out.print("Enter the first ID number (origin) : ");
                    int id1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the second ID number (destination): ");
                    int id2 = scanner.nextInt();
                    scanner.nextLine();
                    ArrayList<Integer> connectionPath = connectionsGraph.getConnectionPath(id1, id2);
                    if (connectionPath != null) {
                        System.out.println("(x -> y means \"x is friends with y\")");
                        System.out.println("There is a connection between ID " + id1 + " and ID " + id2);
                        System.out.println();
                        System.out.print("Connection Path: ");
                        for (int i = 0; i < connectionPath.size(); i++) {
                            System.out.print(connectionPath.get(i));
                            if (i < connectionPath.size() - 1) {
                                System.out.print(" -> ");
                            }
                        }
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

