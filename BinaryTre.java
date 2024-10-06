import java.util.Scanner;
class  BinaryTre{
public class BTree{
    int data;
    BTree left;
    BTree right;

    public BTree(int data){
        this.data=data;
    }
}


    static BTree root;
    public BinaryTre(){
        this.root=null;
    }

    void inOrder(BTree root){
        if(root!=null){
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        }
    }

    void preOrder(BTree root){
        if(root!=null){
            System.out.println(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    void postOrder(BTree root){
        if(root!=null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.data);
        }
    }



    void insertData(int val){
        BTree newNode = new BTree(val);
        if (root==null){
            root=newNode;
            System.out.println("successfully root was created");
        }else if(root.data==val){
            System.out.println("The root value already exists");
        }
        else
        {
            BTree curr = root;
            while(val<curr.data || val>curr.data)
            {
                if (val < curr.data)
                {
                    if (curr.left == null)
                    {
                        curr.left = newNode;
                        System.out.println("the value of added in left");
                        break;
                    }else
                    {
                        curr = curr.left;
                    }

                }
                else
                {
                    if (curr.right == null)
                    {
                        curr.right = newNode;
                        System.out.println("the value of added in right");
                        break;
                    }else{
                        curr = curr.right;
                    }

                }
            }
        }
    }

// void delete(int val)
// {
//     BTree curr = root;


//     while(val != curr.data)
//     {   
//         if(val<curr.data)
//         {
//             curr = curr.left;
//         }
//         else
//         {
//             curr = curr.right;
//         }
//     }
//     //no child
//     if (curr.left == null && curr.right == null)
//     {
//        System.err.println("no child");  
//     }
//     //has one child
//     else if (curr.left == null || curr.right == null) 
//     {
//         System.err.println("one child");  
//     }
//     //has two child
//     else
//     {
//         System.err.println("two child");  
//     }
// } 

BTree delete(BTree root,int val) {
    if(root==null)
    {
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
        if(root.left==null)
        {
            return root.right;
        }
        else if(root.right == null)
        {
            return root.left;
        }
        else
        {
            BTree currentNode = root.right;

            while(currentNode.left!=null)
            {
                currentNode = currentNode.left;
            }
            root.data = currentNode.data;
            root.right = delete(root.right,currentNode.data);
        }
    }
    return root;
    }
        public static void main(String[] args) {
        BinaryTre B1 = new BinaryTre();

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
            System.out.println("6 -> Exit");
            System.out.print("Enter Your Option : ");

            userInput = textFeild.nextInt();

            switch (userInput) {
                case 1:
                    System.out.print("Enter the insert value : ");
                    int insertValue = textFeild.nextInt();
                    B1.insertData(insertValue);
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
                    if(root==null)
                    {
                        System.err.println("Root is empty");
                    }
                    else
                    {
                        System.out.print("Enter the delete value : ");
                        int deleteValue = textFeild.nextInt();
                        root = B1.delete(root,deleteValue);
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        } while (userInput!=6);



    }
}