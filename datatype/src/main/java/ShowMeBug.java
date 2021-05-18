import java.util.*;

/**
 * @Author zuo_h
 * @Date 15:46 2021/5/17
 */
public class ShowMeBug {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        String str = "abcacdbeabdc";
        reve(str);
        System.out.println();
        count(str);
    }

    public static void reve(String str) {
        String buf = new StringBuffer(str).reverse().toString();
        HashSet set = new HashSet();
        for (char c : buf.toCharArray()) {
            if (!set.contains(c)) {
                System.out.print(c);
                set.add(c);
            }
        }
    }

    public static void count(String str) {
        HashMap<Character, Integer> map = new HashMap();
        for (char i : str.toCharArray()) {
            if (map.containsKey(i)) {
                int o = map.get(i);
                o++;
                map.put(i,o);
            } else {
                map.put(i, 1);
            }
        }
        for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry e = (Map.Entry) it.next();
            System.out.print(e.getKey() + "(" + e.getValue() + ")");
        }

    }
}