import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.Collection;

class AddService {
    private final Stack<Integer> stack;
    private final StackCallBack stackCallBack;
    private int sum;

    public AddService(final StackCallBack stackCallBack) {
        this.stack = new Stack<>();
        this.stackCallBack = stackCallBack;
        this.sum = 0;
    }

//    public void push(final Collection<Integer> numbers) {
//        for (final int number : numbers) {
//            stack.push(number);
//            stackCallBack.onPush(number);
//            sum += number;
//            stackCallBack.onSum(sum);
//        }
//    }

    public void push(final int number) {
        stack.push(number);
        stackCallBack.onPush(number);
        sum += number;
        stackCallBack.onSum(sum);
    }

    public void pop() {
        int number = stack.pop();
        stackCallBack.onPop(number);
        sum -= number;
        stackCallBack.onSum(sum);
    }

    public int getTotalCount() {
        return stack.size();
    }
}

public class Activator implements StackCallBack{

    private AddService stack;
    private Scanner scanner;

    public Activator() {
        this.stack = new AddService(this);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void onPush(final int number) {
        System.out.println("Pushed :" + number);
        System.out.println("Current Stack Size: " + stack.getTotalCount());
    }

    @Override
    public void onPop(final int number) {
        System.out.println("Popped :" + number);
        System.out.println("Current Stack Size: " + stack.getTotalCount());
    }

    @Override
    public void onSum(final int sum) {
        System.out.println("Sum of Stack Elements: " + sum);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an action:");
            System.out.println("1. Push a value");
            System.out.println("2. Pop a value");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter a value to push: ");
                    int valueToPush = scanner.nextInt();
                    scanner.nextLine();
                    stack.push(valueToPush);
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid action.");
            }
        }
    }

    public static void main(String[] args) {
        final Activator callBack = new Activator();

        callBack.run();
    }
}
