import java.lang.reflect.Array;
import java.util.*;
public class BSTree{
    int data;
    int height;
    BSTree left;
    BSTree right;
    public BSTree(int data)
    {
        this.data = data;
        this.height  = 1;
    }
}
class  BinaryTree
{
    static BSTree root;
    public BinaryTree()
    {
        this.root=null;
    }

void inOrder(BSTree root){
    if(root!=null){
        inOrder(root.left);   
        System.out.println("Height of "+root.data+" is "+root.height);
        inOrder(root.right);
    }
}

void preOrder(BSTree root){
    if(root!=null){
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }
}
void postOrder(BSTree root){
    if(root!=null){
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }
}

BSTree insertData(int val,BSTree node)
{
    if (node==null)
    {
        BSTree newNode = new BSTree(val);
        node=newNode;
        System.out.println("successfully node was added");
        return node;
    }
    else
    {
        if (val< node.data)
        {
            node.left = insertData(val, node.left);   
        }
        else if(val > node.data)
        {
            node.right = insertData(val, node.right);  
        }
        else
        {
            System.out.println("The root value already exists");
        }
    }
    return node; 
}

BSTree delete(BSTree root,int val)
{
    if(root==null)
    {
        System.err.println("the value does not exists");          
        return null;
    }
    if(val<root.data)
    {
       root.left = delete(root.left,val);
    }
    else if(val>root.data) 
    {
        root.right = delete(root.right,val);
    }
    else
    {
        //value == root.data;
        if(root.left==null && root.right == null)
        {
            return null;
        }
        else if (root.left == null && root.right!=null)
        {
            root = root.right;
        }
        else if (root.left != null && root.right == null)
        {
            root = root.left;
        }
        else
        {
            //handle two parent 
            if(root.right.left == null)
            {
                root = root.right;   
            }
            else
            {
                BSTree tempNode = root.right;
                    while(tempNode.left!=null)
                    {
                        tempNode = tempNode.left;
                    }
                    root.data = tempNode.data;
                    delete(root.right, tempNode.data);
            }
        }
    }
    return root;
 } 
 boolean search(int searchVal,BSTree root)
 {
    if(root == null){
        return false;
    }
    else if(root.data == searchVal){
        return true;
    }
    else if(root.data > searchVal){
        return   search(searchVal, root.left);
    }
      return  search(searchVal, root.right); 
 }  
void update(int newVal,int deleteVal)
{
    System.out.println(search(deleteVal,root));
    System.out.println(search(newVal,root));
    if (!search(deleteVal,root))
    {
        System.out.println("unable to update because old value not found");
    }
    else if(search(newVal,root))
    {
        System.out.println("unable to update because new value already exists");
    }
    else{
        delete(root, deleteVal);
        insertData(newVal,root);
        System.out.println("successfully updated");
    }
}
static BSTree BFS(BSTree root)
{
    Queue<BSTree> queue = new LinkedList<>();
    queue.add(root);  
    while(queue.isEmpty() == false)
    {
        
        BSTree temp = queue.remove();
        if(temp.left!= null)
        {
            queue.add(temp.left);
        }
        if(temp.right!= null)
        {
            queue.add(temp.right);
        }
        // System.out.println("Height of "+temp.data+" is "+temp.height);
        System.out.println(temp.data);
    }            
return null;
}

    public static void main(String[] args) {
        BinaryTree B1 = new BinaryTree();
        
        int userInput;
        Scanner textFeild = new Scanner(System.in);
        do 
        { 
            System.out.println("---------------Binary Tree----------------");
            System.out.println("1 -> Insert Data");
            System.out.println("2 -> Show Inorder");
            System.out.println("3 -> Show Preorder");
            System.out.println("4 -> Show Postorder");
            System.out.println("5 -> Delete Data");
            System.out.println("6 -> Update");
            System.out.println("7 -> BFS");
            System.out.println("8 -> Exit");
            System.out.print("Enter Your Option : ");

            userInput = textFeild.nextInt();

            switch (userInput) {
                case 1:
                    System.out.print("Enter the insert value : ");
                    int insertValue = textFeild.nextInt();
                    root = B1.insertData(insertValue,root);
                    break;
                case 2:
                    B1.inOrder(root);
                    break;
                case 3:
                    B1.preOrder(root);
                    break;
                case 4:
                    B1.postOrder(root);
                    break;
                case 5:
                        System.out.print("Enter the delete value : ");
                        int deleteValue = textFeild.nextInt();
                        root = B1.delete(root,deleteValue);    
                    
                    break;
                case 6:
                    System.out.print("Enter the update value : ");
                    int newValue = textFeild.nextInt();
                    System.out.print("Enter the delete value : ");
                    int delValue = textFeild.nextInt();
                    if(root==null)
                    {
                        // B1.insertData(insertValue,root);
                        System.err.println("Go and create a tree first");
                    }
                    else{
                        
                        B1.update(newValue, delValue);
                    }
                    break;
                case 7:
                    BFS(root);
                    break;
                default:
                    throw new AssertionError();
            }
        } while (userInput!=8);  
    }
}