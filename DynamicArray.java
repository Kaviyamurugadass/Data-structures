import java.util.*;

class DynamicArraySub {
    private static final int initialCap = 4;
    private int arr[];
    private int size;
    private int capacity;
    //construtor
    DynamicArraySub() {
        arr = new int[initialCap];
        size = 0;
        capacity = initialCap;
    }
    //1.add method
    public void add(int val) {

        if (size >= capacity) {
            addArraySize(); 
        }
        arr[size++] = val;
    }
    //submethod used to increase size
    private  void addArraySize(){
        capacity *= 2;
            arr = java.util.Arrays.copyOf(arr, capacity);
    }

    //2.display
    public void display() {
        if (size == 0) {
            System.out.println("List is empty");
            return;
        }
        for (int listele = 0; listele < capacity; listele++) {
            System.out.print(arr[listele] + " ");
        }
    }
    //5.update
    public void update(int pos,int val) {
        arr[pos] = val;
    }
    //3.insert with position
    public void insert(int pos, int value) {
        if(size==capacity){
            addArraySize();
        }
        for(int item=size-1;item>=pos;item--){
            arr[item+1] = arr[item];         
        }
        arr[pos]=value;
            size++;       
    }
    //6/size
    public void size(){
        System.err.println(size);
        // System.err.println(arr.length);
    }
    //4.delete
    public void delete(int pos) {
        for(int i = pos;i>=pos && i<size; i++)
             arr[i] = arr[i+1];
        // for(int i=pos+1;i<size;i++)
		// 	arr[i-1] = arr[i];
		size--;
		
		if(capacity > initialCap && capacity > 3*size)
	    	shrinkArray();
    }
    //method used to reduce the array size
    private void shrinkArray(){
        capacity /= 2;
        arr = java.util.Arrays.copyOf(arr, capacity);

    }
}

public class DynamicArray {
    public static void main(String[] args) {
        DynamicArraySub arr1 = new DynamicArraySub();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------------------------------------------");
            System.out.println(
                    "1.Add new values at the end\n2.Display List\n3.Insert with position\n4.Delete with position\n5.Update\n6.Size\n7.Exit");
            System.out.println("Please Enter your choice");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the values to add");
                    int val = sc.nextInt();
                    arr1.add(val);
                    break;
                case 2:
                    System.out.println("Values in the list are: ");
                    arr1.display();
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Enter the position to insert:");
                    int pos = sc.nextInt();
                    System.out.println("Enter the value to insert:");
                    int value = sc.nextInt();
                    arr1.insert(pos, value);
                    break;
                case 4:
                    System.out.println("Enter the position to delete:");
                    int posd = sc.nextInt();
                    arr1.delete(posd);

                    break;
                case 5:
                    System.out.println("Enter the position to update:");
                    int posu = sc.nextInt();
                    System.out.println("Enter the value to update:");
                    int val1 = sc.nextInt();
                    arr1.update(posu,val1);
                    break;
                case 6:
                    System.out.println("Size of array is: ");
                    arr1.size();
                    break;
                case 7:
                    System.out.println("Thank you for using our Application");
                    break;
                default:
                    System.out.println("Please enter the valid choice");
            }
        } while (choice != 7);
        // while (choice <= 6);
    }

}