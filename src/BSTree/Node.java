package BSTree;

/**
 *
 * @author Eddie
 */
public class Node implements Comparable<Node>
{
    private String Title;
    private int avalible;
    private int rented;
    Node left = null, right = null;
    
    public Node(String t,int a,int r){
        Title = t;
        avalible = a;
        rented = r; 
        left = null;
        right = null;
    }

    public Node getLeft()
    { return left;  }
    
    public void setLeft(Node n)
    {  left = n;    }
    
    public Node getRight()
    { return right;  }
    
    public void setRight(Node n)
    {  right = n;    }
    

    @Override
    public int compareTo(Node n)
    {
        return this.getTitle().compareTo(n.getTitle());
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getAvalible() {
        return avalible;
    }

    public void setAvalible(int avalible) {
        this.avalible = avalible;
    }

    public int getRented() {
        return rented;
    }

    public void setRented(int rented) {
        this.rented = rented;
    }
}
