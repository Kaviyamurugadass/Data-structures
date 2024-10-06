import java.util.*;
public class AVLTree
{
    int data;
    int height;
    AVLTree left,right;
    public AVLTree(int data)
    {
        this.data = data;
        this.height  = 1;
    }
}
class  ATree
{
    static AVLTree root;
    public ATree()
    {
        this.root=null;
    }

void inOrder(AVLTree root)
{
    if(root!=null)
    {
        inOrder(root.left);   
        System.out.println("Height of "+root.data+" is "+root.height);
        inOrder(root.right);
    }
}
void preOrder(AVLTree root)
{
    if(root!=null)
    {
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }
}
void postOrder(AVLTree root)
{
    if(root!=null)
    {
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }
}

AVLTree insertData(int val,AVLTree node)
{
    if (node==null)
    {
        AVLTree newNode = new AVLTree(val);
        node=newNode;
        System.out.println("successfully node was added");
        return node;
    }
    else
    {
        if (val < node.data)
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
    updateHeight(node);
    if(getBalance(node) < -1) // leftheight = 0, rightheight =2 (Left)
    {
        if(node.right.data > val) // RL Rotate
        {
           node.right = rightRotate(node.right); 
        }
        node = leftRotate(node);
    }
    else if(getBalance(node) > 1) // leftheight = 2, rightheight =0
    {
        if(node.left.data < val) // LR Rotate
        {
           node.left = leftRotate(node.left); 
        }
        node = rightRotate(node);
    }
    return node; 
}

AVLTree leftRotate(AVLTree root) 
{
    AVLTree newRoot = root.right;
    root.right = newRoot.left;
    newRoot.left = root;
    updateHeight(root);
    updateHeight(newRoot);
    return newRoot;
}

AVLTree rightRotate(AVLTree root) 
{
    AVLTree newRoot = root.left;
    root.left = newRoot.right;
    newRoot.right = root;
    updateHeight(root);
    updateHeight(newRoot);
    return newRoot;
}

static void updateHeight(AVLTree node)
{
    int leftNodeHeight = node.left == null ? 0 : node.left.height;
    int rightNodeHeight = node.right == null ? 0 : node.right.height;
    node.height = (leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight)+1;
}

int getBalance(AVLTree node) 
{
    if (node == null) 
    {
        return 0;
    }
    int leftHeight = node.left == null ? 0 : node.left.height;
    int rightHeight = node.right == null ? 0 : node.right.height;
    return leftHeight - rightHeight;
}

AVLTree delete(AVLTree root,int val)
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
            System.out.println("two child");
            if(root.right.left == null)
            {
                System.out.println("null");
                root.right.left = root.left;
                root = root.right;               
            }
            else
            {
                System.out.println("two child");
                AVLTree tempNode = root.right;
                while(tempNode.left!=null)
                {
                    tempNode = tempNode.left;
                }
                root.data = tempNode.data;
                delete(root.right, tempNode.data);
            }
        }
    }
    updateHeight(root);
    int balance = getBalance(root);
    if (balance > 1) 
    {
        if(getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
        }
        root = rightRotate(root);
    } 
    else if(balance < -1)
    {
        if(getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
        }
        root = leftRotate(root);
    }
    return root;
} 

 boolean search(int searchVal,AVLTree root)
 {
    if(root == null)
    {
        return false;
    }
    else if(root.data == searchVal)
    {
        return true;
    }
    else if(root.data > searchVal)
    {
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
static AVLTree BFS(AVLTree root)
{
    Queue<AVLTree> queue = new LinkedList<>();
    queue.add(root);  
    while(queue.isEmpty() == false)
    {       
        AVLTree temp = queue.remove();
        if(temp.left!= null)
        {
            queue.add(temp.left);
        }
        if(temp.right!= null)
        {
            queue.add(temp.right);
        }
        System.out.println("--------------------------------");
        System.out.println("Height of "+temp.data+" is "+temp.height);
        if (temp.left != null) 
        {
            System.out.println("The left node data is : " + temp.left.data);
        } 
        else 
        {
            System.out.println("The left node data is : null");
        }
        if (temp.right != null) 
        {
            System.out.println("The right node data is : " + temp.right.data);
        } else 
        {
            System.out.println("Theright node data is : null");
        }     
    }            
return null;
}

    public static void main(String[] args) 
    {
        ATree avl = new ATree();
        
        int userInput;
        Scanner textFeild = new Scanner(System.in);
        do 
        { 
            System.out.println("---------------AVL Tree----------------");
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
            switch (userInput) 
            {
                case 1:
                    System.out.print("Enter the insert value : ");
                    int insertValue = textFeild.nextInt();
                    root = avl.insertData(insertValue,root);
                    break;
                case 2:
                    avl.inOrder(root);
                    break;
                case 3:
                    avl.preOrder(root);
                    break;
                case 4:
                    avl.postOrder(root);
                    break;
                case 5:
                        System.out.print("Enter the delete value : ");
                        int deleteValue = textFeild.nextInt();
                        root = avl.delete(root,deleteValue);    
                    
                    break;
                case 6:
                    System.out.print("Enter the update value : ");
                    int newValue = textFeild.nextInt();
                    System.out.print("Enter the delete value : ");
                    int delValue = textFeild.nextInt();
                    if(root==null)
                    {
                        // avl.insertData(insertValue,root);
                        System.err.println("Go and create a tree first");
                    }
                    else
                    {
                        
                        avl.update(newValue, delValue);
                    }
                    break;
                case 7:
                    BFS(root);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        while (userInput!=8);  
    }
}