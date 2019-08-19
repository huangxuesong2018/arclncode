import java.util.ArrayList;
import java.util.List;

/**
 * @author HXS
 * @copyright
 * @since 2019-05-05
 */
public class Main {
    public static void main(String[] args) {
        /*Logs.init();
        Logs.Console.info("launch Lion server...");*/
        //6,2,2,2,1,1,5,5,4,4,4,3,3,6,2
        // 6,2,1,5,4,3,6,2
        //String a = "6,2,1,4,2#2,1,5,4,3#2,5,4,3,6";


        String a = "2,1,5,4,3#6,2,1,4,2#2,5,4,3,6";
        List<Elements> list = buildList(a);
        maxLevel = maxLevel(list);

        List<Element> result = new ArrayList<Element>();
        //一层层查找
        for (int i = 0 ; i< maxLevel; i ++){
            for (Elements e : elementsList) {
               int length =  e.elements.length;
               if( i <= length-1){
                   Element r = e.elements[i];
                   if (!r.isFind){
                       r.setFind(true);
                       result.add(r);
                       findNextLevelSameNum(result,  r);
                   }
               }
            }
        }
        System.out.println();System.out.println();

        for (Element e : result){
            System.out.print(e.num);
        }
        System.out.println();
        int temp = 0;
       for (Element e : result){
           if(temp == e.num)continue;
           temp = e.num;

           System.out.print(temp);
       }
    }

    private static List<Elements> elementsList;
    private static int maxLevel = 0;
    //查找下一层相同的数字
    static void findNextLevelSameNum(List<Element> result,Element element){
        System.out.println("查找——"+element);
        int currentX = element.x;
        int currentY = element.y;
        int loop = currentY+1;
        //查找当前元素的同一层和下一层
        for (int i = currentY ; i<= loop; i ++){
            for (Elements e : elementsList) {
                int length =  e.elements.length;
                if( i <= length-1){
                    Element et = e.elements[i];
                    System.out.println(et);
                    if (et.isFind)continue;
                    int x = et.x;
                    int y = et.y;
                    //只查找相邻一组
                    if(Math.abs(x-currentX) > 1)continue;

                    if (element.num == et.num){
                        //标示为已重复
                        et.setFind(true);
                        result.add(et);
                        findNextLevelSameNum(result,et);
                    }
                }
            }
        }

    }

    //构建数据
    static List<Elements> buildList(String number){
        String[] ary = number.split("#");
        List<Elements> list = new ArrayList<>();
        int group = 1;
        for (String str: ary) {
            list.add(new Elements(str,group++));
        }
        elementsList = list;
        return list;
    }

    /**
     * 查找长度最长的数组长度
     * @param list
     * @return
     */
    static int maxLevel(List<Elements> list){
        int[] ary = new int[list.size()];
        for (int i = 0 ; i < ary.length; i++){
            ary[i] = list.get(i).length;
        }
        int max = ary[0];
        if (ary.length == 1)return max;

        for (int i = 1 ; i < ary.length ; i++){
            if (ary[i] > max)
                max = ary[i];
        }
        return max;
    }

    //数组封装
    static class Elements{
        int group;//组
        Element[] elements;
        int length;

        public Elements(String str,int group) {
            String[] ary = str.split(",");
            elements =  new Element[ary.length];
            for (int i =0 ; i< ary.length; i++){
                elements[i] = new Element(Integer.parseInt(ary[i]),i,group);
            }
            this.length = ary.length;
            this.group = group;
        }
    }

    /**
     * 元素
     */
    static class Element{
        int x;//横坐标
        int y;//纵坐标
        int num;//数字
        boolean isFind;//查找标示
        public Element(int num, int index,int group) {
            this.num = num;
            this.x = group;
            this.y = index;
        }
        /**
         * 标示为已查找,已查找过的元素不参与下次的遍历
         * @param isFind
         */
        public void setFind(boolean isFind) {
            this.isFind = isFind;
        }


        @Override
        public String toString() {
            return "Element{" +
                    "x=" + x +
                    ", y=" + y +
                    ", num='" + num + '\'' +
                    ", isRepeat=" + isFind +
                    '}';
        }
    }
}
