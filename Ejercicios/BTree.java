package Ejercicios;

public class BTree<E extends Comparable<E>> {

    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<E>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int pos[] = new int[1];
        E mediana;
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl;
            fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado");
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(this.orden - 1)) {
                    mediana = dividedNode(current, mediana, pos[0]);
                } else {
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }
            return mediana;
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;
        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<E>(this.orden);
        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        if (k <= this.orden / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    public boolean search(E key) {
        return search(root, key);
    }

    private boolean search(BNode<E> node, E key) {
        if (node == null) {
            return false;
        }

        int[] pos = new int[1];
        boolean found = node.searchNode(key, pos);

        if (found) {
            System.out.println(key + " is found in node " + node.getIdNode() + " at position " + pos[0]);
            return true;
        } else {
            if (pos[0] < node.childs.size()) {
                return search(node.childs.get(pos[0]), key);
            } else {
                return false;
            }
        }
    }

    public void delete(E key) {
        if (root == null) {
            System.out.println("El árbol está vacío.");
            return;
        }
        delete(root, key);
        if (root.count == 0) {
            if (root.childs.get(0) == null) {
                root = null;
            } else {
                root = root.childs.get(0);
            }
        }
    }

    private boolean delete(BNode<E> node, E key) {
        int pos[] = new int[1];
        boolean found = node.searchNode(key, pos);

        if (found) {
            if (node.childs.get(pos[0]) == null) {
                removeKey(node, pos[0]);
                return true;
            } else {
                E pred = getPredecessor(node, pos[0]);
                node.keys.set(pos[0], pred);
                return delete(node.childs.get(pos[0]), pred);
            }
        } else {
            if (node.childs.get(pos[0]) == null) {
                return false;
            } else {
                boolean isDeleted = delete(node.childs.get(pos[0]), key);
                if (node.childs.get(pos[0]).count < (orden - 1) / 2) {
                    fix(node, pos[0]);
                }
                return isDeleted;
            }
        }
    }

    private void removeKey(BNode<E> node, int index) {
        for (int i = index; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    private E getPredecessor(BNode<E> node, int index) {
        BNode<E> current = node.childs.get(index);
        while (current.childs.get(current.count) != null) {
            current = current.childs.get(current.count);
        }
        return current.keys.get(current.count - 1);
    }

    private void fix(BNode<E> parent, int index) {
        if (index > 0 && parent.childs.get(index - 1).count > (orden - 1) / 2) {
            borrowFromLeft(parent, index);
        } else if (index < parent.count && parent.childs.get(index + 1).count > (orden - 1) / 2) {
            borrowFromRight(parent, index);
        } else {
            if (index > 0) {
                merge(parent, index - 1);
            } else {
                merge(parent, index);
            }
        }
    }

    private void borrowFromLeft(BNode<E> parent, int index) {
        BNode<E> leftSibling = parent.childs.get(index - 1);
        BNode<E> current = parent.childs.get(index);

        current.keys.set(current.count, parent.keys.get(index - 1));
        current.count++;

        parent.keys.set(index - 1, leftSibling.keys.get(leftSibling.count - 1));

        current.childs.set(current.count, leftSibling.childs.get(leftSibling.count));
        leftSibling.childs.set(leftSibling.count, null);

        leftSibling.count--;
        current.count++;
    }

    private void merge(BNode<E> parent, int index) {
        BNode<E> leftSibling = parent.childs.get(index);
        BNode<E> rightSibling = parent.childs.get(index + 1);

        leftSibling.keys.set(leftSibling.count, parent.keys.get(index));
        leftSibling.count++;

        for (int i = 0; i < rightSibling.count; i++) {
            leftSibling.keys.set(leftSibling.count + i, rightSibling.keys.get(i));
            leftSibling.count++;
        }

        for (int i = 0; i <= rightSibling.count; i++) {
            leftSibling.childs.set(leftSibling.count + i, rightSibling.childs.get(i));
        }

        parent.keys.remove(index);
        parent.childs.remove(index + 1);
        parent.count--;
    }

    private void borrowFromRight(BNode<E> parent, int index) {
        BNode<E> current = parent.childs.get(index);
        BNode<E> rightSibling = parent.childs.get(index + 1);

        current.keys.set(current.count, parent.keys.get(index));
        current.count++;

        parent.keys.set(index, rightSibling.keys.get(0));

        current.childs.set(current.count, rightSibling.childs.get(0));

        for (int i = 0; i < rightSibling.count - 1; i++) {
            rightSibling.keys.set(i, rightSibling.keys.get(i + 1));
            rightSibling.childs.set(i, rightSibling.childs.get(i + 1));
        }

        current.childs.set(current.count, rightSibling.childs.get(rightSibling.count));

        current.count++;
        rightSibling.count--;
    }
    
    public String toString() {
        String s = "";
        if (isEmpty()) {
            s += "BTree is empty...";
        } else {
            s += "Id.Nodo\tClaves Nodo\tId.Padre\tId.Hijos\n";
            s += writeTree(this.root, null);
        }
        return s;
    }

    private String writeTree(BNode<E> current, BNode<E> parent) {
        StringBuilder sb = new StringBuilder();
        sb.append(current.idNode).append("\t(");
        for (int i = 0; i < current.count; i++) {
            sb.append(current.keys.get(i));
            if (i < current.count - 1) {
                sb.append(", ");
            }
        }
        sb.append(")\t");
        if (parent == null) {
            sb.append("--\t");
        } else {
            sb.append("[").append(parent.idNode).append("]\t");
        }
        sb.append("[");
        boolean hasChildren = false;
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) != null) {
                if (hasChildren) {
                    sb.append(", ");
                }
                sb.append(current.childs.get(i).idNode);
                hasChildren = true;
            }
        }
        sb.append("]\n");
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) != null) {
                sb.append(writeTree(current.childs.get(i), current));
            }
        }
        return sb.toString();
    }
}
