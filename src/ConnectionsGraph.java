import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ConnectionsGraph {
    private int numAccounts;
    private HashMap<Integer, ArrayList<Integer>> connectionsList;
    public ConnectionsGraph(int numAccounts) {
        this.numAccounts = numAccounts;
        this.connectionsList = new HashMap<>();
        for (int i = 0; i < numAccounts; i++) {
            connectionsList.put(i, new ArrayList<>());
        }
    }

    public void addFriendshipEdge(int origin, int destination)
    {
        connectionsList.get(origin).add(destination);
        connectionsList.get(destination).add(origin);
    }

    public ArrayList<Integer> getFriendsList(int chosenIDNumber) {
        return connectionsList.getOrDefault(chosenIDNumber, new ArrayList<>());
    }

    public void printFriendsList(int chosenIDNumber) {
        ArrayList<Integer> friendsList = getFriendsList(chosenIDNumber);
        if (friendsList.isEmpty()) {
            System.out.println("Error! Inputted ID not found!");
        } else {
            System.out.println("User " + chosenIDNumber + "'s Friends List: ");
            System.out.println("Total # of friends: " + friendsList.size());
            int i = 1;
            for (int friendID : friendsList) {
                System.out.println("Friend " + i + ": " + friendID);
                i++;
            }
        }
    }

    public ArrayList<Integer> getConnectionPath(int originID, int destinationID) {
        boolean isAValid = connectionsList.containsKey(originID);
        boolean isBValid = connectionsList.containsKey(destinationID);
        if (!isAValid || !isBValid) {
            if (!isAValid && !isBValid) {
                System.out.println("Error! Both inputs (ID " + originID + " and ID " + destinationID + ") are not found!");
            } else if (!isAValid) {
                System.out.println("Error! Input ID " + originID + " not found!");
            } else {
                System.out.println("Error! Input ID " + destinationID + " not found!");
            }
            return null; // At least one of the inputs not found
        } else {
            HashSet<Integer> setOfVisitedNodes = new HashSet<>();
            ArrayList<Integer> path = new ArrayList<>();
            if (findConnectionPathDFS(originID, destinationID, setOfVisitedNodes, path)) {
                return path;
            } else {
                System.out.println("No connection found between ID " + originID + " and ID " + destinationID);
                return null; // No connection found
            }
        }
    }

    private boolean findConnectionPathDFS(int currentNode, int targetNode, HashSet<Integer> setOfVisitedNodes, ArrayList<Integer> path) {
        if (currentNode == targetNode) {
            path.add(currentNode);
            return true; // Found a connection.
        }

        setOfVisitedNodes.add(currentNode);
        for (int person : connectionsList.get(currentNode)) {
            if (!setOfVisitedNodes.contains(person)) {
                if (findConnectionPathDFS(person, targetNode, setOfVisitedNodes, path)) {
                    path.add(currentNode); // Add the current node to the beginning of the path
                    return true;
                }
            }
        }

        return false; // No connection found.
    }





}
