package BSTree;

import java.io.PrintWriter;

/**
 *
 * @author Eddie
 */
public class BSTree {

    Node root = null;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node n) {
        root = n;
    }

    public void addNode(Node n, Node cur, Node parent) {
        if (root == null) {
            root = n;
            return;
        }
        if (cur == null) {
            if (n.compareTo(parent) < 0) {
                parent.setLeft(n);
            }
            if (n.compareTo(parent) > 0) {
                parent.setRight(n);
            }
        } else {
            if (n.compareTo(cur) < 0) {
                addNode(n, cur.getLeft(), cur);
            } else if (n.compareTo(cur) > 0) {
                addNode(n, cur.getRight(), cur);
            } else if (n.getTitle().equals(cur.getTitle())) {
                cur.setAvalible(cur.getAvalible() + n.getAvalible());
                cur.setRented(cur.getRented() + n.getRented());
                if (cur.getAvalible() <= 0) {
                    remove(cur, parent);
                }
                return;
            }
            return;
        }
    }

    public void remove(Node n, Node parent) {
        if (n.left == null) {
            root = n.getRight();
            if (n.compareTo(parent) < 0) {
                parent.setLeft(n.getRight());
            } else {
                parent.setRight(n.getRight());
            }
        } else {
            //parent = findRightParent(n.getLeft());

        }
    }

    public void Print(Node n, PrintWriter out) {
        if (n.getLeft() == null) {
            System.out.format("%32s%5d%5d", n.getTitle(), n.getAvalible(), n.getRented());
            System.out.println();
        } else {
            if (n.left != null) {
                Print(n.getLeft(),out);
            }
            System.out.format("%32s%5d%5d", n.getTitle(), n.getAvalible(), n.getRented());
            System.out.println();
            if (n.left != null) {
                Print(n.getRight(),out);
            }
        }
    }

}
