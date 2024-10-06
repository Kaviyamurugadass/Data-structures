import java.util.*;
class  BinaryTree
{
    public class BTree
    {
        int data;
        BTree left,right;

        public BTree(int data)
        {
            this.data=data;
        }
    }
    static BTree root;

    public BinaryTree()
    {
        this.root=null;
    }  
    Queue<BTree> queue = new LinkedList<>(); 
    void insertData(int val)
    {
        BTree newNode = new BTree(val);
        if (root==null)
        {
            root=newNode;
            System.out.println("successfully root was created");
            queue.add(root);  
        }
        else
        {      
            BTree temp = queue.peek();
            if(temp.left == null)
            {
                temp.left = newNode;
                System.out.println("The value is added to the left.");
                queue.add(temp.left);
            } 
            else
            {
                temp.right = newNode;   
                System.out.println("The value is added to the right.");
                queue.add(temp.right);   
                queue.remove();
            }   
        }
    }

    BTree BFS(BTree root,int searchVal)
    {
        Queue<BTree> queue = new LinkedList<>();
        queue.add(root);  
        while(queue.isEmpty() == false)
        {
            BTree temp = queue.remove();
            if(temp.data == searchVal)
            {
                System.out.println("Search Value is founded "+temp.data);
                return temp;
            }
            if(temp.left!= null)
            {
                queue.add(temp.left);
            }
            if(temp.right!= null)
            {
                queue.add(temp.right);
            }
            System.out.println(temp.data+" ");
        }
    System.out.println("The value is not found in the tree");             
    return null;
    }

    boolean DFS(BTree root,int searchVal)
    {
        if(root != null)
        {
            if(root.data==searchVal)
            {
                System.out.print("Your search value found here ");

            }
            System.out.println(root.data);
            DFS(root.left, searchVal);
            DFS(root.right, searchVal);
            return true;
        }   
        return false;
    }

    void delete(BTree root,int searchVal) 
    {
        BTree temp = BFS(root,searchVal);
        if(temp==null)
        {
            return;
        }
        BTree lastright = root;
        while(lastright.right!=null)
        {
            lastright = lastright.right;
        }
        temp.data = lastright.data;
        lastright = null;
        System.out.println("Deleted successfully");
    }     
    void print(){
        
    }       
        
    public static void main(String[] args) 
    {
        BinaryTree BT = new BinaryTree();
        int choice;
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println("---------------Binary Tree Menu----------------");
            System.out.println("1 -> INSERT");
            System.out.println("2 -> BFS");
            System.out.println("3 -> DFS");
            System.out.println("4 -> DELETE");
            System.out.println("5 -> EXIT");
            System.out.print("Enter Your Option : ");
            choice = sc.nextInt();
            switch (choice) 
            {
                case 1:
                    System.out.print("Enter the insert value : ");
                    int insertValue = sc.nextInt();
                    BT.insertData(insertValue);
                    break;
                case 2:
                    if(root == null)
                    {
                        System.out.println("Tree is empty");
                        break;
                    }
                    System.out.print("Enter the  value to search in BFS: ");
                    int Bdata = sc.nextInt();
                    BT.BFS(root,Bdata);
                    break;
                case 3:
                    if(root == null)
                    {
                        System.out.println("Tree is empty");
                        break;
                    }
                    System.out.print("Enter the  value to search in DFS: ");
                    int Ddata = sc.nextInt();
                    BT.DFS(root,Ddata);
                    break;
                case 4:
                    if(root == null)
                    {
                        System.out.println("Tree is empty");
                        break;
                    }
                    System.out.println("Enter the value to delete");
                    int del = sc.nextInt();
                    BT.delete(root,del);
                    break;
                case 5:
                    System.out.println("Thank you for using our application");
                    break;
                default:
                    System.out.println("Please enter the valid choice");
            }
        } 
        while (choice!=5);
    }
}