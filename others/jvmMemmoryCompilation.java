package others;

//must be run with -Xcomp  to avoid the OutOfMemoryError
class jvmMemoryCompilation {
    public static void main(String[] args){
        final int size = 512*1024*1024/ Integer.BYTES;
        int[] arr1 = new int[size];
        System.out.println("arr1(" + arr1 + ") allocated");
        System.gc();
        int[] arr2 = new int[size];
        System.out.println("arr1("+arr2+") allocated");

    }

}
