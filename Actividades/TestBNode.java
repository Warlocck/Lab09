package Actividades;


public class TestBNode {
    public static void main(String[] args) {
        // Crear un nodo con capacidad para 3 claves
        BNode<Integer> node = new BNode<>(4);

        // Insertar algunas claves
        insertKey(node, 30);
        insertKey(node, 10);
        insertKey(node, 45);
        insertKey(node, 3);

        // Mostrar el contenido del nodo
        System.out.println("Contenido del nodo: " + node);

        // Probar el método searchNode
        int[] position = new int[1];

        // Caso 1: Clave existente
        boolean found = node.searchNode(20, position);
        System.out.println("Buscar 20: " + (found ? "Encontrado en posicion " + position[0] : "No encontrado"));

        // Caso 2: Clave no existente, menor que todas
        found = node.searchNode(5, position);
        System.out.println("Buscar 5: " + (found ? "Encontrado en posicion " + position[0] : "No encontrado, deberia estar en posicion " + position[0]));

        // Caso 3: Clave no existente, mayor que todas
        found = node.searchNode(45, position);
        System.out.println("Buscar 45: " + (found ? "Encontrado en posicion " + position[0] : "No encontrado, deberia estar en posicion " + position[0]));

        // Caso 4: Clave no existente, entre dos claves
        found = node.searchNode(25, position);
        System.out.println("Buscar 25: " + (found ? "Encontrado en posicion " + position[0] : "No encontrado, deberia estar en posicion " + position[0]));
           // Crear un nodo con capacidad para 3 claves
        BNode<Integer> node2 = new BNode<>(4);

        // Insertar algunas claves
        insertKey(node2, 69);
        insertKey(node2, 10);
        insertKey(node2, 34);
        insertKey(node2, 3);

        // Mostrar el contenido del nodo
        System.out.println("Contenido del nodo: " + node2);

        // Probar el método searchNode
        int[] position2 = new int[1];

        // Caso 1: Clave existente
        boolean found2 = node2.searchNode(69, position2);
        System.out.println("Buscar 20: " + (found2 ? "Encontrado en posicion " + position2[0] : "No encontrado"));

        // Caso 2: Clave no existente, menor que todas
        found = node2.searchNode(5, position2);
        System.out.println("Buscar 5: " + (found ? "Encontrado en posicion " + position2[0] : "No encontrado, deberia estar en posicion " + position2[0]));

        // Caso 3: Clave no existente, mayor que todas
        found = node2.searchNode(45, position2);
        System.out.println("Buscar 45: " + (found2 ? "Encontrado en posicion " + position2[0] : "No encontrado, deberia estar en posicion " + position2[0]));

        // Caso 4: Clave no existente, entre dos claves
        found = node2.searchNode(25, position2);
        System.out.println("Buscar 25: " + (found2 ? "Encontrado en posicion " + position2[0] : "No encontrado, deberia estar en posicion " + position2[0]));
    }

    // Método auxiliar para insertar una clave en el nodo (simplificado para fines de prueba)
    private static <E extends Comparable<E>> void insertKey(BNode<E> node, E key) {
        if (node.nodeFull(node.keys.size())) {
            System.out.println("El nodo esta lleno, no se puede insertar la clave " + key);
            return;
        }

        int pos = 0;
        while (pos < node.count && key.compareTo(node.keys.get(pos)) > 0) {
            pos++;
        }

        node.keys.add(pos, key);
        node.keys.remove(node.keys.size() - 1); // Mantener el tamaño de la lista
        node.count++;
    }
}
