package Actividades;
public class Main {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>(5);

       	int[] keys = {31,12,19,41,57,63,3,10,13,16,22,25,28,33,38,40,49,52,55,60,62,67,70,72};


        for (int key : keys) {
            bTree.insert(key);
        }

        System.out.println(bTree.toString());
    }
}
