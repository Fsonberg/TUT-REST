public class BinarySearch {

    public String search(int[] array, int searchParameter) {
        int first = 0;
        int last = array.length -1;
        int middle = (first + last) /2;

        System.out.println("searchParameter: "+ searchParameter);
        System.out.println();

        while (first <= last) {
            if (array[middle] > searchParameter) {
                System.out.println("first: "+ first);
                System.out.println("middle: "+ middle);
                System.out.println("last: "+ last);
                last = middle -1;
            } else if (array[middle] == searchParameter) {
                System.out.println("first: "+ first);
                System.out.println("middle: "+ middle);
                System.out.println("last: "+ last);
                return "The searchParameter: "+ searchParameter+ " is at array spot: "+ middle;
            } else {
                System.out.println("first: "+ first);
                System.out.println("middle: "+ middle);
                System.out.println("last: "+ last);
                first = middle +1;
            }
            System.out.println();
            middle = (first + last) /2;
        }
        return "The searchParameter is not present in this list";
    }
}


/*
                System.out.println("first: "+ first);
                System.out.println("middle: "+ middle);
                System.out.println("last: "+ last);

 */
