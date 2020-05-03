import java.time.LocalDate;


public class Tree {

    public Node rootNode;
    
    public Tree(){
    }

    /**
     * traverse the tree inOrder
     * -leftChild - root - rightChild
     * 
     * @param localRoot the localRoot of the binaryTree
     */
    public void traverseInOrder(){
        if(rootNode != null)
            rootNode.traverseInOrder();
    }

    /**
     * 
     * finds a node using a certain date
     * 
     * @param dateOfNode date being looked for
     * @return  the node if it finds it, otherwise returns the parent
     */
    public Node get(LocalDate dateOfNode){
        if(rootNode != null)
            return rootNode.get(dateOfNode);
        return null;
    }

    /**
     * inserts a certain node with a certain date in the tree
     * 
     * @param dateOfNode date to be inserted
     */
    public void insert(LocalDate dateOfNode){
        if(rootNode == null)
            rootNode = new Node(dateOfNode);
        else
            rootNode.insert(dateOfNode);
    }

    public void delete(LocalDate dateOfNode){
        rootNode = delete(rootNode, dateOfNode);
    }


    public Node delete(Node subTreeRoot, LocalDate dateOfNode){
        if(subTreeRoot == null)
            return subTreeRoot;
        if(dateOfNode.isBefore(subTreeRoot.getDataNodo()))
            subTreeRoot.setLeftNode(delete(subTreeRoot.getLeftNode(), dateOfNode));
        else if(dateOfNode.isAfter(subTreeRoot.getDataNodo()))
            subTreeRoot.setRightNode(delete(subTreeRoot.getRightNode(), dateOfNode));
        else{
            if(subTreeRoot.getLeftNode() == null)
                return subTreeRoot.getRightNode();
            else if(subTreeRoot.getRightNode() == null)
                return subTreeRoot.getLeftNode();
            
            //node to delete has two children-> delete the node that has
            //the smallest value in the right subTree
            subTreeRoot.setDataNodo(subTreeRoot.getRightNode().min());
            subTreeRoot.setRightNode(delete(subTreeRoot.getRightNode(), subTreeRoot.getDataNodo()));
        }
        return subTreeRoot;
    }

    public LocalDate min(){
        if(rootNode == null)
            return LocalDate.MIN;
        else
            return rootNode.min();
    }

    public LocalDate max(){
        if(rootNode == null)
            return LocalDate.MAX;
        else
            return rootNode.max();
    }
    
}