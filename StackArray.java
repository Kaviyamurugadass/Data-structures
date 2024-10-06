import java.util.Scanner;

public class StackArray {
    static int arr[];
    static int position = 0;
    public int size;

    StackArray(int size) {
        arr = new int[size];
        this.size = size;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the Stack size:");
        int size = sc.nextInt();
        StackArray obj = new StackArray(size);
        int choice;

        do {
            System.out.println("----------------------------STACK MENU----------------------------");
            System.out.println(
                    "1.Add new value in the stack(push)\n2.Delete value from stack(pop)\n3.View last element in the Stack(peek)\n4.Check the Stack is empty(isEmpty)\n5.Display\n6.Exit");
            System.out.println("Please Enter your choice");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the values to add into the stack");
                    int val = sc.nextInt();
                    if (position > size) {
                        System.out.println("Stack Overflow");
                        break;
                    }
                    obj.push(val);
                    break;
                case 2:
                    if (position == 0)
                        System.out.println("Stack is empty");
                    else {
                        obj.pop();
                        System.out.println("Successfully deleted from the stacK!");
                    }
                    break;
                case 3:
                    if (position == 0)
                        System.out.println("Stack is empty");
                    else {
                        System.out.println(
                                "Recent element in the stack is: \n____\n|  " + arr[position - 1] + "  |\n____");
                    }
                    break;
                case 4:
                    if (position == 0) {
                        System.out.println("Stack is Empty");
                    } else
                        System.out.println("Stack is Not Empty");
                    break;
                case 5:
                    System.out.println("Display values in the stack are:");
                    obj.display();
                    break;
                case 6:
                    System.out.println("Thank you for using our Application");
                    break;
                default:
                    System.out.println("Please enter the valid choice");
            }
        } while (choice != 5);
        sc.close();
    }

    // 1. push method
    public void push(int value) {
        arr[position] = value;
        position++;
        System.out.println("Successfully added to the stacK!");
    }

    // 2. pop method
    public void pop() {
        // int recent_pop = arr[position-1];
        arr[position - 1] = 0;
        position--;
    }

    // display method
    public void display() {
        // System.out.println(position);
        // System.out.println(size);
        for (int i = size - 1; i >= 0; i--) {
            System.out.println("|   " + arr[i] + "   |");
            System.out.println("|_______|");
        }
    }
}
