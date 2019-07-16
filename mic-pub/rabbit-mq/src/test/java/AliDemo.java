import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 阿里守则： 不要在foreach里进行remove/add 操作，remove 请使用Iterator方式
 * @author HXS
 * @copyright
 * @since 2019-04-12
 */
public class AliDemo {
    /**
     * 正确示例
     * @param list
     * @return
     */
    public static List<String> rightDemo(List<String> list){
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String item = iterator.next();
            if("hollis".equals(item)){
                iterator.remove();
            }
        }
        return list;
    }

    /**
     * 错误示例
     * @param list
     * @return
     */
    public static List<String> errorDemo(List<String> list){
        for (String item: list) {
            if("hollis".equals(item)){
                list.remove(item);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> list =  new ArrayList<String>() {{
            add("HolliH");
            add("hollis");
            add("HollisChuang");
            add("H");

        }};
        List<String> result = errorDemo(list);
        System.out.println(list);
    }

}
