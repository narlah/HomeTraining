package algorithmic;

import java.util.Arrays;

public class FindDuplicateBeastModeOn {
    static int findDuplicate(int[] intArray) {

        final int n = intArray.length - 1;

        // STEP 1: GET INSIDE A CYCLE
        // start at position n+1 and walk n steps to
        // find a position guaranteed to be in a cycle
        int positionInCycle = n + 1;
        for (int i = 0; i < n; i++) {

            // we subtract 1 from the current position to step ahead:
            // the 2nd *position* in an array is *index* 1
            positionInCycle = intArray[positionInCycle - 1];
        }

        // STEP 2: FIND THE LENGTH OF THE CYCLE
        // find the length of the cycle by remembering a position in the cycle
        // and counting the steps it takes to get back to that position
        int rememberedPositionInCycle = positionInCycle;
        int currentPositionInCycle = intArray[positionInCycle - 1]; // 1 step ahead
        int cycleStepCount = 1;

        while (currentPositionInCycle != rememberedPositionInCycle) {
            currentPositionInCycle = intArray[currentPositionInCycle - 1];
            cycleStepCount += 1;
        }

        // STEP 3: FIND THE FIRST NODE OF THE CYCLE
        // start two pointers
        //   (1) at position n+1
        //   (2) ahead of position n+1 as many steps as the cycle's length
        int pointerStart = n + 1;
        int pointerAhead = n + 1;
        for (int i = 0; i < cycleStepCount; i++) {
            pointerAhead = intArray[pointerAhead - 1];
        }

        // advance until the pointers are in the same position
        // which is the first node in the cycle
        while (pointerStart != pointerAhead) {
            pointerStart = intArray[pointerStart - 1];
            pointerAhead = intArray[pointerAhead - 1];
        }

        // since there are multiple values pointing to the first node
        // in the cycle, its position is a duplicate in our array
        return pointerStart;
    }

    static int findDuplicateNegatives(int[] arr) {
        int size = arr.length - 1;
        for (int i = 0; i < size; i++) {
            int abs = Math.abs(arr[i]);
            if (arr[abs] > 0) {
                arr[abs] = -arr[abs];
            } else {
                System.out.println(Arrays.toString(arr));
                return abs;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 2, 7, 1};
        System.out.println(Arrays.toString(arr));
        System.out.println(findDuplicate(arr));
        System.out.println(findDuplicateNegatives(arr));

        arr = new int[]{4, 2, 3, 4, 5, 6, 7, 1};
        System.out.println(Arrays.toString(arr));
        System.out.println(findDuplicate(arr));
        System.out.println(findDuplicateNegatives(arr));


    }
}
