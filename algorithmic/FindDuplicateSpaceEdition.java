package algorithmic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindDuplicateSpaceEdition {
    private static int findRepeat(final List<Integer> a) {

        int floor = 1;
        int ceiling = a.size() - 1;

        while (floor < ceiling) {

            // divide our range 1..n into an upper range and lower range
            // (such that they don't overlap)
            // lower range is floor..midpoint
            // upper range is midpoint+1..ceiling
            int midpoint = floor + ((ceiling - floor) / 2);
            int lowerRangeFloor = floor;
            int lowerRangeCeiling = midpoint;
            int upperRangeFloor = midpoint + 1;
            int upperRangeCeiling = ceiling;

            // count number of items in lower range
            int itemsInLowerRange = 0;
            for (int item : a) {

                // is it in the lower range?
                if (item >= lowerRangeFloor && item <= lowerRangeCeiling) {
                    itemsInLowerRange += 1;
                }
            }

            int distinctPossibleIntegersInLowerRange = lowerRangeCeiling - lowerRangeFloor + 1;

            if (itemsInLowerRange > distinctPossibleIntegersInLowerRange) {

                // there must be a duplicate in the lower range
                // so use the same approach iteratively on that range
                floor = lowerRangeFloor;
                ceiling = lowerRangeCeiling;
            } else {

                // there must be a duplicate in the upper range
                // so use the same approach iteratively on that range
                floor = upperRangeFloor;
                ceiling = upperRangeCeiling;
            }
        }

        // floor and ceiling have converged
        // we found a number that repeats!
        return floor;
    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 100, 200, 7, 1, 2, 2};
        System.out.println(Arrays.toString(arr));
        System.out.println(findRepeat(new ArrayList(Arrays.asList(arr))));
    }
}
