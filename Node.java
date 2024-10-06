import java.util.Scanner;
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;

    }

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";

    static Node head;

    static void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else if (head.data == data) {
            System.out.println("this data already exists");
            return;
        } else if (head.data > data) {
            newNode.next = head;
            head = newNode;
        } else {
            Node present = head;
            while (present.next != null && present.next.data < data) {
                present = present.next;
            }
            if (present.next != null && present.next.data == data) {
                System.out.println("this data already exists");
                return;
            }
            newNode.next = present.next;
            present.next = newNode;
        }
        System.out.println("Your entered value successfully added ");
    }
    // add end

    // Delete Method

    static void Delete(int uselessData) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.data == uselessData) {
            head = head.next;
            System.out.println("The entered Value has been successfully delected");
            return;
        } else if (uselessData < head.data) {
            System.err.println("The entered Value not found in the list");
            return;
        } else {
            Node present = head;
            while (present.next != null && present.next.data != uselessData) {
                if (uselessData < present.data) {
                    System.err.println("The entered Value not found in the list");
                    return;
                }
                present = present.next;
            }
            if (present.next != null) {
                present.next = present.next.next;
                System.out.println("The entered Value has been successfully delected");
                return;
            }
            System.err.println("The entered Value not found in the list");

        }
    }

    static public void Update(int oldValue, int newValue) {
        Node newNode = new Node(newValue);
        Node present = head;
        boolean addinHead = false, deleteinHead = false;
        Node prevNode = null;
        if (newValue < oldValue) {
            // add
            if (present.data == newValue) 
            {
                System.out.println("Updated value already exists");
                return;
            } 
            else if (present.data > newValue) 
            {
                addinHead = true;
                //newNode.next = present;
                //present = newNode;
                //head = present;
            } 
            else 
            {
                while (present.next != null && present.next.data < newValue) 
                {
                    present = present.next;
                }
                if (present.next != null && present.next.data == newValue) 
                {
                    System.out.println("Updated value already exists");
                    return;
                }
                //newNode.next = present.next;
                //present.next = newNode;
                prevNode = present;  
            }

            //delete
            if (oldValue < head.data) 
            {
                System.err.println("The entered old Value not found in the list");
                return;
            } 
            else if(oldValue == head.data)
            {
                head= head.next;
            }
            else 
            {
                while (present.next != null && present.next.data != oldValue) 
                {
                    if (oldValue < present.data) 
                    {
                        System.err.println("The entered Value not found in the list");
                        return;
                    }
                    present = present.next;
                }
                if (present.next != null) 
                {
                    present.next = present.next.next;
                }
                else
                {
                    System.err.println("The entered Value not found in the list");
                    return;
                }
            } 
            //Actual Add
            if(addinHead)
            {
                newNode.next = head;
                head = newNode;
            }
            else
            {
                newNode.next = prevNode.next;
                prevNode.next = newNode;
            }
            System.out.println("The entered Value has been successfully Updated");
        }
        else
        {
            //delete
            if (oldValue < head.data) 
            {
                System.err.println("The entered old Value not found in the list");
                return;
            } 
            else if(oldValue == head.data)
            {
                deleteinHead = true;
            }
            else 
            {
                while (present.next != null && present.next.data != oldValue) 
                {
                    if (oldValue < present.data) 
                    {
                        System.err.println("The entered Old Value not found in the list");
                        return;
                    }
                    present = present.next;
                }
                if (present.next != null) 
                {
                    prevNode = present;
                }
                else
                {
                    System.err.println("The entered old Value not found in the list");
                    return;
                }
            }

            //Add
            if (present.data == newValue) 
            {
                System.out.println("Updated value already exists");
                return;
            } 
            else if (present.data > newValue) 
            {
                newNode.next = present.next;
                present.next = newNode;
            } 
            else 
            {
                while (present.next != null && present.next.data < newValue) 
                {
                    present = present.next;
                }
                if (present.next != null && present.next.data == newValue) 
                {
                    System.out.println("Updated value already exists");
                    return;
                }
                newNode.next = present.next;
                present.next = newNode;
            }
            // Actual delete
            if(deleteinHead)
            {
                head = head.next;
            }
            else
            {
                prevNode.next = prevNode.next.next;
            }
        }
    }

    static void display() {
        if (head == null) {
            System.out.println("list  is empty");
        } else {
            Node temp = head;
            while (temp.next != null) {
                System.out.print(temp.data + "->");
                temp = temp.next;
            }
            System.out.println(temp.data);
        }
    }

    static void FindMaximum() {
        if (head == null) {
            System.out.println("Node is empty");
            return;
        }
        Node temp = head;
        int max = temp.data;
        while (temp != null) {
            if (temp.data > max) {
                max = temp.data;
            }
            temp = temp.next;
        }
        System.out.println("The Maximum Value is : " + max);
    }

    static void FindMinimum() {
        if (head == null) {
            System.out.println("Node is empty");
            return;
        }
        Node temp = head;
        int min = temp.data;
        // traverse by palani anna requirement
        // while (temp != null) {
        // if (temp.data < min) {
        // min = temp.data;
        // }
        // temp = temp.next;
        // }

        System.out.println("The Minimum Value is : " + min);
    }

    static void findLength() {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            count += 1;
            temp = temp.next;
        }
        System.out.println("The length of the list is : " + count);
    }

    static void findAverage() {
        if (head == null) {
            System.out.println("Node is empty");
            return;
        }
        Node temp = head;
        int sum = 0;
        int count = 0;
        while (temp != null) {
            sum += temp.data;
            count++;
            temp = temp.next;
        }
        double average = (double) sum / count;
        System.out.println("The Average Value is : " + average);
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        int userChoice;
        do {
            System.out.println(GREEN + "\n<----------- LINKED LIST ------------>\n" + RESET);
            System.out.println("1 --> Add values in list");
            System.out.println("2 --> Delete values in list");
            System.out.println("3 --> Update values in list");
            System.out.println("4 --> Display list");
            System.out.println("5 --> Find maximum Value");
            System.out.println("6 --> Find minimum Value");
            System.out.println("7 --> Find Average Value");
            System.out.println("8 --> Exit\n");
            System.out.print(BLUE + "Enter Your Choice : " + RESET);
            userChoice = inputScanner.nextInt();
            switch (userChoice) {
                case 1:
                    System.out.print(GREEN + "\nEnter your value you want to add at the beginning: " + RESET);
                    int data = inputScanner.nextInt();
                    add(data);
                    break;
                case 2:
                    System.out.print(GREEN + "\nEnter your value you want to delete " + RESET);
                    int uselessData = inputScanner.nextInt();
                    Delete(uselessData);
                    break;
                case 3:
                    if (head == null) {
                        System.err.println("List is empty");

                    } else {
                        System.out.print(GREEN + "\nEnter the old value " + RESET);
                        int oldValue = inputScanner.nextInt();
                        if (head.data > oldValue) {
                            System.out.println("The Entered old value not Found in the list");
                        } else {
                            System.out.print(GREEN + "\nEnter the new value " + RESET);
                            int newValue = inputScanner.nextInt();
                            Update(oldValue, newValue);
                        }

                    }
                    break;
                case 4:
                    display();
                    break;
                case 5:
                    FindMaximum();
                    break;
                case 6:
                    FindMinimum();
                    break;
                case 7:
                    findAverage();
                    break;
                case 8:
                    findLength();
                    break;
                case 9:
                    System.out.println(GREEN + "Exit successfully" + RESET);
                    break;
                default:
                    System.out.println(RED + "Invalid choice" + RESET);
                    break;
            }
        } while (userChoice != 8);

        inputScanner.close();
    }
}