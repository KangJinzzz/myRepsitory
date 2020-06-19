package data_structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        for (String s : set) {
            System.out.println(s);
        }
    }
}
