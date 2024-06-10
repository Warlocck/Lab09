package Ejercicios;
public class Main2 {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(5);

       	int[] keys = {31,12,19,41,57,63,3,10,13,16,22,25,28,33,38,40,49,52,55,60,62,67,70,72};


        for (int key : keys) {
            tree.insert(key);
        }
        
        System.out.println(tree.search(52)); 
        System.out.println(tree.search(67)); 
        System.out.println(tree.search(31)); 
        System.out.println(tree.search(55)); 
        System.out.println(tree.search(31)); 
        System.out.println(tree.toString());

        tree.delete(31);

        System.out.println("Arbol despues de eliminar:");
        System.out.println(tree);
    }
}
