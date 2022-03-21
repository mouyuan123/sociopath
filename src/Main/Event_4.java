package Main;

import java.util.ArrayList;
import java.util.Stack;

/*
 * @author houren
 */
public class Event_4 {

    private int totalOutput = 0;
    private ArrayList<String> sArray = new ArrayList<>();

    public Event_4(int book, String input) {
        go(book, input);
    }

    public void go(int book, String input) {
        String[] split = input.split(" ");
        Stack<Integer> mainStack = new Stack<>();
        Stack<Integer> tempStack = new Stack<>();
        Stack<Integer> deleteStack = new Stack<>();
        int count = book;
        int output = 0;
        for (int i = book - 1; i >= 0; i--) {
            mainStack.push(Integer.parseInt(split[i]));
            count++;
        }

        boolean changed = true;
        while (changed) {
            changed = false;

            //Display method
            Object[] mArray = mainStack.toArray();
            int[] mIArray = new int[mArray.length];
            String print = "";
            print += "[";
            for (int i = mArray.length - 1; i >= 0; i--) {
                mIArray[i] = (int) mArray[i];
                if (i == 0) {
                    print +=mIArray[i];
                } else {
                    print +=mIArray[i] + ", ";
                }
            }
            print +="]\n";
            sArray.add(print);

            //Start
            for (int i = 0; i < count - 1; i++) {
                int a = mainStack.pop();
                int b;
                tempStack.push(a);
                if (!mainStack.isEmpty()) {
                    b = mainStack.peek();
                } else {
                    break;
                }
                if (a < b) {
                    changed = true;
                    deleteStack.push(b);
                } else {
                }

            }
            if (changed == true) {
                output++;
            }
            Object[] oArray = deleteStack.toArray();
            int[] iArray = new int[oArray.length];
            for (int i = 0; i < oArray.length; i++) {
                iArray[i] = (int) oArray[i];
            }
            while (!tempStack.isEmpty()) {

                int a = tempStack.pop();
                mainStack.push(a);
                for (int i = 0; i < iArray.length; i++) {
                    if (iArray[i] == a) {
                        mainStack.pop();
                        iArray[i] = -1;
                        break;
                    } else {

                    }
                }
            }

            deleteStack.clear();

        }
        totalOutput = output;
    }

    public ArrayList<String> getsArray() {
        return sArray;
    }

    public int getTotalOutput() {
        return totalOutput;
    }

}
