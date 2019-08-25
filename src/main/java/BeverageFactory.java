import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BeverageFactory {

    static final Map<String, Double> PRICE_MAP = new HashMap<>();
    static final Map<String, Set<String>> ITEM_MAP = new HashMap<>();
    static {
        PRICE_MAP.put("coffee", 5.0);
        PRICE_MAP.put("chai", 4.0);
        PRICE_MAP.put("banana smoothie", 6.0);
        PRICE_MAP.put("strawberry shake", 7.0);
        PRICE_MAP.put("mojito", 7.5);
        PRICE_MAP.put("milk", 1.0);
        PRICE_MAP.put("sugar", 0.5);
        PRICE_MAP.put("soda", 0.5);
        PRICE_MAP.put("mint", 0.5);
        PRICE_MAP.put("water", 0.5);

        ITEM_MAP.put("coffee", new HashSet<>(Arrays.asList("coffee","milk","sugar","water")));
        ITEM_MAP.put("chai", new HashSet<>(Arrays.asList("tea","milk","sugar","water")));
        ITEM_MAP.put("banana smoothie", new HashSet<>(Arrays.asList("banana", "milk", "sugar", "water")));
        ITEM_MAP.put("strawberry shake", new HashSet<>(Arrays.asList("strawberries", "sugar", "milk", "water")));
        ITEM_MAP.put("mojito", new HashSet<>(Arrays.asList("lemon", "sugar", "water", "soda", "mint")));

    }

    public static void main(String[] args) {
        System.out.println(new OrderMaker().placeOrder("Chai, -sugar"));
        System.out.println(new OrderMaker().placeOrder("Chai, -sugar, -milk"));
        System.out.println(new OrderMaker().placeOrder("Chai"));
        System.out.println(new OrderMaker().placeOrder(new String[]{"Chai, -sugar", "Chai", "Coffee, -milk"}));
    }
}
