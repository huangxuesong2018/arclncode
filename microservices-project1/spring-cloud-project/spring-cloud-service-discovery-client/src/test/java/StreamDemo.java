import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
       /* List<Integer> list = Arrays.asList(9,2,3,4,5);
        java8(list);
        System.out.println(list);*/
        MathOperation c = (int a,int b) -> b+ a;
    }
    interface MathOperation {
        int operation(int a, int b);
    }
    public static void java8(List<Integer> list ){
        Collections.sort(list,(o1, o2) -> o1.compareTo(o2));

    }
    /**
     * java7
     * @param list
     */
    public static void java7(List<Integer> list ){
       Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
