import java.util.Scanner;

class Node
{
    int data;
    Node left;
    Node right;
    boolean color;
    Node parent;    

    Node (int val)
    {
        data = val;
        color = true;
    }
}


public class RBTree 
{
    static Node root;

    boolean leftLeft = false;
    boolean rightRight = false;
    boolean leftRight = false;
    boolean rightLeft = false;
    boolean redRed = false;
    Node insertedNode;

    public Node insertData (int val, Node node)
    {
        if (node == null)   // processing at leaf/child level
        {
            Node newNode = new Node(val);
            System.out.println(val + " was successfully added.");
            return newNode;
        }
        else 
        {
            if (val < node.data)
            {
                node.left = insertData (val, node.left);
                // additional processing at parent level after insert the node
                node.left.parent = node;
                if (node.color == true && node.left.data == val)     // fixed
                {
                    System.out.println(node.data);
                    redRed = true;   
                    insertedNode = node.left;
                }  
            } 
            else if (val > node.data) 
            {
                node.right = insertData (val, node.right);
                // additional processing at parent level after insert the node
                node.right.parent = node;
                if (node.color == true && node.right.data == val)     // fix needed
                {
                    redRed = true;
                    insertedNode = node.right;
                }
            } 
            else 
            {
                System.out.println(val + " was already exist.");
                return node;
            }
            if (leftLeft)   // processing at grand parent level
            {
                boolean grandParentColor = node.color;
                boolean parentColor = node.left.color;
                node = rightRotate(node);
                node.color = grandParentColor;
                node.right.color = parentColor;
                redRed = false;
                leftLeft = false;
            }
            else if (leftRight) 
            {
                boolean grandParentColor = node.color;
                boolean parentColor = node.left.color;
                node = leftRightRotate(node);
                node.color = grandParentColor;
                node.right.color = parentColor;
                redRed = false;   
                leftRight = false;            
            }
            else if (rightRight) 
            {
                boolean grandParentColor = node.color;
                boolean parentColor = node.right.color;
                node = leftRotate(node);
                node.color = grandParentColor;
                node.left.color = parentColor;
                redRed = false;
                rightRight = false;
            }
            else if (rightLeft)
            {
                System.out.println("here");
                boolean grandParentColor = node.color;
                boolean parentColor = node.right.color;
                node = rightLeftRotate(node);
                node.color = grandParentColor;
                node.right.color = parentColor;
                redRed = false; 
                rightLeft = false;    
            }

            if (redRed)   // processing at parent level
            {
                Node uncle = getUncleNode(insertedNode);

                if (uncle != null && uncle.color) 
                { 
                    changeColor(insertedNode, uncle);
                    redRed = false;
                }
                else
                {
                    if (node.parent.left == node && node.left == insertedNode)  // left left case
                    {
                        leftLeft = true;                        
                    }
                    else if (node.parent.left == node && node.right == insertedNode)    // left right case
                    {
                        leftRight = true;
                    }
                    else if (node.parent.right == node && node.right == insertedNode)   // right right case
                    {
                        rightRight = true;
                    }
                    else if (node.parent.right == node && node.left == insertedNode)    // right left case
                    {
                        rightLeft = true;
                    }
                }
            }
            return node;
        }
    }


    void inOrder(Node node) 
    {
        if (node != null) 
        {
            inOrder(node.left);
            System.out.println("\nNode Data - " + node.data);
            if (node.left != null)
            {
                System.out.println("Left   - " + node.left.data);
            } 
            else 
            {
                System.out.println("Left   - Null");
            }
            if (node.right != null) 
            {
                System.out.println("Right  - " + node.right.data);
            } 
            else 
            {
                System.out.println("Right  - Null");
            }
            System.out.println("Color  - " + ((node.color == true)? "Red" : "Black"));
            System.out.println("Parent - " + ((node.parent != null)? node.parent.data : "Null"));
            Node uncle = getUncleNode(node);
            System.out.println("Uncle  - " + ((uncle != null)? uncle.data : "Null"));
            inOrder(node.right);
        }
    }


    void preOrder(Node node) 
    {
        if (node != null) 
        {
            System.out.println("\nNode Data - " + node.data);
            if (node.left != null)
            {
                System.out.println("Left   - " + node.left.data);
            } 
            else 
            {
                System.out.println("Left   - Null");
            }
            if (node.right != null) 
            {
                System.out.println("Right  - " + node.right.data);
            } 
            else 
            {
                System.out.println("Right  - Null");
            }
            System.out.println("Color  - " + ((node.color == true)? "Red" : "Black"));
            System.out.println("Parent - " + ((node.parent != null)? node.parent.data : "Null"));
            Node uncle = getUncleNode(node);
            System.out.println("Uncle  - " + ((uncle != null)? uncle.data : "Null"));

            preOrder(node.left);
            preOrder(node.right);
        }
    }


    void postOrder(Node node) 
    {
        if (node != null) 
        {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println("\nNode Data - " + node.data);
            if (node.left != null)
            {
                System.out.println("Left   - " + node.left.data);
            } 
            else 
            {
                System.out.println("Left   - Null");
            }
            if (node.right != null) 
            {
                System.out.println("Right  - " + node.right.data);
            } 
            else 
            {
                System.out.println("Right  - Null");
            }
            System.out.println("Color  - " + ((node.color == true)? "Red" : "Black"));
            System.out.println("Parent - " + ((node.parent != null)? node.parent.data : "Null"));
            Node uncle = getUncleNode(node);
            System.out.println("Uncle  - " + ((uncle != null)? uncle.data : "Null"));
        }
    }


    Node leftRotate(Node node) 
    {
        Node newRoot = node.right;
        Node rightNode = newRoot.left;
        newRoot.left = node;
        node.right = rightNode;
        newRoot.parent = node.parent;
        if (rightNode != null) 
        {
            rightNode.parent = node;   
        }
        node.parent = newRoot;
    
        return newRoot;
    }


    Node rightRotate(Node node) 
    {
        Node newRoot = node.left;
        Node leftNode = newRoot.right;
        newRoot.right = node;
        node.left = leftNode;
        newRoot.parent = node.parent;
        if (leftNode != null) 
        {
            leftNode.parent = node;   
        }
        node.parent = newRoot;
        return newRoot;
    }


    Node leftRightRotate (Node node)
    {
        node.left = leftRotate(node.left);
        node = rightRotate(node);
        return node;
    }


    Node rightLeftRotate (Node node)
    {
        node.right = rightRotate(node.right);
        node = leftRotate(node);
        return node;
    }


    Node rotation(Node node)
    {
        Node grandparent = node.parent.parent;
        if(grandparent.left != null && grandparent.left.left != null && grandparent.left.left == node)
        {
            node = rightRotate(grandparent);
        }
        else if (grandparent.right != null && grandparent.right.right != null && grandparent.right.right == node) 
        {
            node = leftRotate(grandparent);
        }
        else if (grandparent.left != null && grandparent.left.right != null && grandparent.left.right == node) 
        {
            node = leftRightRotate(grandparent);
        }
        else if (grandparent.right != null && grandparent.right.left != null && grandparent.right.left == node) 
        {
            node = rightLeftRotate(grandparent);
        }
        node.color = false;
        node.left.color = true;
        node.right.color = true;
        return node;
    }

    Node getUncleNode(Node node)
    {
        if (node.parent != null) 
        {
            if (node.parent.parent != null) 
            {
                if (node.parent.parent.left != null && node.parent.parent.left != node.parent) 
                {
                    return (node.parent.parent.left);                        
                }
                else if (node.parent.parent.right != null && node.parent.parent.right != node.parent) 
                {
                    return (node.parent.parent.right);
                }
                else 
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    } 


    void changeColor(Node node, Node uncle)
    {
        // // Node uncle = getUncleNode(node);
        // if (uncle != null && uncle.color) 
        // {
            node.parent.color = false;
            uncle.color = false;
            uncle.parent.color = true;
        // }
        
        // else if(node.parent.parent != null && !(uncle == null ? false : !uncle.color))
        // {
        //     Node grandparent = node.parent.parent;
        //     if(grandparent.left != null && grandparent.left.left != null && grandparent.left.left == node)
        //     {
        //         node = rightRotate(grandparent);
        //     }
        //     else if (grandparent.right != null && grandparent.right.right != null && grandparent.right.right == node) 
        //     {
        //         node = leftRotate(grandparent);
        //     }
        //     else if (grandparent.left != null && grandparent.left.right != null && grandparent.left.right == node) 
        //     {
        //         node = leftRightRotate(grandparent);
        //     }
        //     else if (grandparent.right != null && grandparent.right.left != null && grandparent.right.left == node) 
        //     {
        //         node = rightLeftRotate(grandparent);
        //     }
        //     node.color = false;
        //     node.left.color = true;
        //     node.right.color = true;
        // }
        // return node;
    }


    public static void main(String args[])
    {
        RBTree rbTree = new RBTree();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do 
        {
            System.out.println("\n----------  RED-BLACK TREE  ----------\n");
            System.out.println("1. Insert Data");
            System.out.println("2. Show Inorder");
            System.out.println("3. Show Preorder");
            System.out.println("4. Show Postorder");
            System.out.println("5. Delete");
            System.out.println("6. Update");
            System.out.println("7. Rotate Left");
            System.out.println("8. Rotate Right");
            System.out.println("9. Rotate Left Right");
            System.out.println("10. Rotate Right Left");
            System.out.println("11. Exit\n");
            System.out.print("Enter Your Option : ");
            choice = scanner.nextInt();

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter the insert value : ");
                    int insertValue = scanner.nextInt();
                    root = rbTree.insertData(insertValue, root);
                    root.color = false;
                    rbTree.inOrder(root);
                    break;
                case 2:
                    rbTree.inOrder(root);
                    break;
                case 3:
                    rbTree.preOrder(root);
                    break;
                case 4:
                    rbTree.postOrder(root);
                    break;
                case 5:
                    System.out.print("The deletion process has not started yet.");
                    break;
                case 6:
                    System.out.print("The upgrade process has not started yet.");
                    break;
                case 7:
                    root = rbTree.leftRotate(root);
                    break;
                case 8:
                    root = rbTree.rightRotate(root);
                    break;
                case 9:
                    root = rbTree.leftRightRotate(root);
                    break;
                case 10:
                    root = rbTree.rightLeftRotate(root);
                    break;
                case 11:
                    System.out.print("Program Exit....");
                default:
                    System.out.print("Enter a valid input.");
            }
        } while (choice != 11);
        scanner.close();
    }
    
}