import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    /*
     * This function reads a file from the given path and constructs the social graph
     * based on the data in the file. It returns the SocialGraph object.
     */
    public ConnectionsGraph readConnectionsGraph(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            // Read the number of accounts and friendships
            int numAccounts = scanner.nextInt();
            int numFriendships = scanner.nextInt();

            // Create a new SocialGraph object
            ConnectionsGraph newGraph = new ConnectionsGraph(numAccounts);

            // Read the friendships and add edges to the graph
            for (int i = 0; i < numFriendships; i++) {
                int origin = scanner.nextInt();
                int destination = scanner.nextInt();
                newGraph.addFriendshipEdge(origin, destination);
            }

            scanner.close();
            return newGraph;
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            e.printStackTrace();
            return null;
        }
    }
}
