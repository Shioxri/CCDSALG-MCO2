import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ConnectionsGraph {
    private int numAccounts;
    private HashMap<Integer, HashSet<Integer>> connectionsList;
    public ConnectionsGraph(int numAccounts) {
        this.numAccounts = numAccounts;
        this.connectionsList = new HashMap<>();
        for (int i = 0; i < numAccounts; i++) {
            connectionsList.put(i, new HashSet<>());
        }
    }

    public void addFriendshipEdge(int origin, int destination) {
        if (origin >= 0 && origin < numAccounts && destination >= 0 && destination < numAccounts) {
            connectionsList.get(origin).add(destination);
            connectionsList.get(destination).add(origin);
        } else {
            System.out.println("Error! Invalid input ID(s).");
        }
    }

    public HashSet<Integer> getFriendsList(int chosenIDNumber) {
        return connectionsList.getOrDefault(chosenIDNumber, new HashSet<>());
    }

    public void printFriendsList(int chosenIDNumber) {
        HashSet<Integer> friendsList = getFriendsList(chosenIDNumber);
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
        }

        ArrayList<Integer> queue = new ArrayList<>();
        HashSet<Integer> setOfVisitedNodes = new HashSet<>();
        int[] parentArr = new int[numAccounts]; // Array to store the parent of each node
        queue.add(originID);
        setOfVisitedNodes.add(originID);
        boolean isTargetFound = false;

        int queueHead = 0; // Index pointing to the Head of the queue

        while (queueHead < queue.size()) {
            int currentNode = queue.get(queueHead);
            queueHead++;

            if (currentNode == destinationID) {
                isTargetFound = true;
                break;
            }

            for (int person : connectionsList.get(currentNode)) {
                if (!setOfVisitedNodes.contains(person)) {
                    queue.add(person);
                    setOfVisitedNodes.add(person);
                    parentArr[person] = currentNode;
                }
            }
        }

        if (isTargetFound) {
            return reconstructPath(originID, destinationID, parentArr);
        } else {
            System.out.println("No connection found between ID " + originID + " and ID " + destinationID);
            return null; // No connection found
        }
    }

    private ArrayList<Integer> reconstructPath(int originID, int destinationID, int[] parentArr) {
        ArrayList<Integer> path = new ArrayList<>();
        int currentNode = destinationID;

        while (currentNode != originID) {
            path.add(0, currentNode); // Add the current node to the beginning of the path
            currentNode = parentArr[currentNode];
        }

        path.add(0, originID); // Add the origin node to the beginning of the path
        return path;
    }






}
