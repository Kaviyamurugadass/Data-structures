import java.util.Scanner;
public class Stack 
{
    static Node head;
    class Node 
    {
        int data;
        Node next;
        Node(int data) 
        {
            this.data = data;
        }
    }
    Stack() 
    {
        head = null;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Stack stack = new Stack();
        int choice;
        do 
        {
            System.out.println("---------------------STACK USING LINKED LIST---------------------");
            System.out.println("Enter Your Choice:\n1.PUSH\n2.POP\n3.PEEK\n4.ISEMPTY\n5.CLEAR\n6.DISPLAY\n7.EXIT");
            choice = sc.nextInt();
            switch (choice) 
            {
                case 1:
                    System.out.println("Enter the Data to insert into the stack");
                    int Data = sc.nextInt();
                    stack.push(Data);
                    break;

                case 2:
                    if(head==null)
                    {
                        System.out.println("Stack is Empty"); 
                    }
                    else
                    {
                        head = head.next; 
                        System.out.println("Deleted successfully");
                    } 
                    break;

                case 3:
                    System.out.println("Top most element is --> "+head.data);
                    break;

                case 4:
                    if(head==null)
                    {
                        System.out.println("Stack is Empty"); 
                    }
                    else
                    {
                        System.out.println("Stack is Not Empty");
                    }
                    break;
                
                case 5:
                    head = null;
                    System.out.println("Stack is cleared completely");
                    break;
                case 6:
                    if (head == null) 
                    {
                        System.err.println("Queue is Empty");
                        return;
                    }
                    stack.display();
                    break;

                case 7:
                    System.out.println("Thank you for using our application");
                    break;
                    
                default:
                    System.out.println("Please enter the valid choice");
                    break;
            }
        } 
        while (choice <= 6);
    }

    void push(int data) 
    {
        Node newNode = new Node(data);    
        if (head == null) 
        {
            head = newNode;
        } 
        else
        {
            Node temp = head;
                while (temp != null) 
                {             
                    if (temp.data == data) 
                    {
                        System.out.println("Data is already present in the Stack");
                        return;
                    }
                    temp = temp.next;
                }
            newNode.next = head;
            head = newNode;
        }
        System.out.println("Successfully added");
    }

    void display() 
    {
        Node temp = head;
        while (temp != null) 
        {
            System.out.println("|   " + temp.data + "   |");
            // System.out.print(temp.data + " ");
            System.out.println("|_______|");
            temp = temp.next;
        }
        System.out.println();
    }
}