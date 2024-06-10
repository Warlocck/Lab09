package Ejercicios;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    private static int nodeCounter = 0;  // Static counter to generate unique IDs
    protected int idNode;

    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n + 1);  // n+1 children
        this.count = 0;
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
        this.childs.add(null);  // One extra slot for children
        this.idNode = ++nodeCounter;  // Assign a unique ID to the node
    }

    // Check if the current node is full
    public boolean nodeFull(int maxKeys) {
        return this.count == maxKeys;
    }

    // Check if the current node is empty
    public boolean nodeEmpty() {
        return this.count == 0;
    }

    // Search for a key in the current node
    public boolean searchNode(E key, int[] position) {
        int pos = 0;
        while (pos < this.count && key.compareTo(this.keys.get(pos)) > 0) {
            pos++;
        }
        if (pos < this.count && key.compareTo(this.keys.get(pos)) == 0) {
            position[0] = pos;
            return true;
        } else {
            position[0] = pos;
            return false;
        }
    }
    public int getIdNode() {
        return this.idNode;
    }


    // Return the keys found in the node along with the idNode
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node ID: ").append(this.idNode).append(" | Keys: ");
        for (int i = 0; i < this.count; i++) {
            sb.append(this.keys.get(i));
            if (i < this.count - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
