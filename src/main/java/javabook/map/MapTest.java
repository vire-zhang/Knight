package javabook.map;

import javabook.Employee;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

import static java.util.Map.*;

public class MapTest {
    enum Weekday { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

    public static void main(String[] args) {
        WeakHashMap<Integer, Integer> weakHashMap = new WeakHashMap();

        var cache = new LinkedHashMap<Integer, Integer>(128, 0.75F, true)
        {
            @Override
            protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
                return size() > 100;
            }
        };

        EnumSet<Weekday> always = EnumSet.allOf(Weekday.class);
        EnumSet<Weekday> never = EnumSet.noneOf(Weekday.class);
        EnumSet<Weekday> workday = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);

        var personInCharge = new EnumMap<Weekday, Employee>(Weekday.class);

        IdentityHashMap<Integer, Integer> identityHashMap = new IdentityHashMap<>();

        List<Integer> arrayList = List.of(1, 2, 3);
//        不可更改
//        arrayList.remove(0);
        var nums = new ArrayList<Integer>(arrayList);
        nums.remove(0);
        Map<Integer, Integer> map = ofEntries(
                entry(1, 2),
                entry(2, 3),
                entry(3, 4)
        );

        // nCopies
        List<String> settings = Collections.nCopies(100, "DEFAULT");
        List<String> settings2 = settings.subList(91, 100);
//        settings2.clear();

        // 映射视图
        Map<Integer, Integer> map1 = new HashMap<>(map);
        Set<Integer> k = map1.keySet();
        Collection<Integer> v = map1.values();
        Set<Map.Entry<Integer, Integer>> entrySet = map1.entrySet();
        var unmodifyMap = Collections.unmodifiableMap(map1);
//        unmodifyMap.remove(1);    // 不可修改

        // 同步视图
        var map2 = Collections.synchronizedMap(map1);

        // 检查型视图
        var ss = new ArrayList<String>();
        List<String> safeStrings = Collections.checkedList(ss, String.class);
        List raw = safeStrings;
//        raw.add(new Date());

        // 算法
//        Collections.max(ss);
//        Collections.sort(ss);
//        ss.sort(Comparator.comparing(String::length));
//        ss.sort(Comparator.reverseOrder());
//        ss.sort(Comparator.comparing(String::length).reversed());
//        Collections.shuffle(ss);    // 洗牌
//        Collections.binarySearch(ss, "M");  // 集合需有序或提供比较器
//        ss.removeIf(w -> w.length() <= 3);
//        ss.replaceAll(String::toLowerCase);
//        Collections.fill(ss, "A");
//        Collections.swap(ss, 0, 1);
//        Collections.reverse(ss);
//        Collections.frequency(ss, "A"); // ss中与"A"相等的个数
//        Collections.disjoint(ss, raw);  // 如果没有共同元素，返回true
//        ss.removeAll(raw);  // 删除所有在raw中出现的元素
//        ss.retainAll(raw);  // 删除所有未在raw中出现的元素

        // 集合与数组转换
        String[] values = { "A", "B", "C" };
        var staff = new HashSet<>(List.of(values));
        String[] values2 = staff.toArray(new String[0]);

        // 属性映射 Properties
        var setting = new Properties();
        setting.setProperty("Width", "600.0");
        setting.setProperty("filehome", "/home/usrs/alex");
        try {
            var out = new FileOutputStream("program.properties");
            setting.store(out, "program properties");
        } catch (Exception e) {

        }
        var setting1 = new Properties();
        try {
            var in = new FileInputStream("program.properties");
            setting1.load(in);
        } catch (Exception e) {}

        // 位集 BitSet
        var bitset = new BitSet(8);
        bitset.set(5);
        var f = bitset.get(3);
        bitset.set(7);
        bitset.clear(7);
        var bitset2 = new BitSet(8);
        bitset2.set(4);
        bitset.and(bitset2);
        bitset.or(bitset2);
        bitset.xor(bitset2);
        bitset.andNot(bitset2);
    }
}
