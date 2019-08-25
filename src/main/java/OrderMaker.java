import java.util.HashSet;
import java.util.Set;

public class OrderMaker {

    public Double placeOrder(String[] orders) {
        Double orderPrice = 0.0d;
        for(String order : orders) {
            orderPrice += this.placeOrder(order);
        }
        return orderPrice;
    }

    public Double placeOrder(String order) {
        String[] orderDetails = order.split(",");
        String item = orderDetails[0].trim().toLowerCase();
        Set<String> ingredientsToRemove = new HashSet<>();
        Set<String> ingredientSet = BeverageFactory.ITEM_MAP.get(item);
        Double orderPrice = 0.0d;

        if(isValidOrder(orderDetails, ingredientsToRemove, ingredientSet)) {
            orderPrice = calculatePrice(item, ingredientsToRemove);
        }
        return orderPrice;
    }

    private boolean isValidOrder(String[] orderDetails, Set<String> ingredientsToRemove, Set<String> ingredientSet) {
        if(ingredientSet == null) {
            throw new InvalidOrderException("Item not present");
        }

        for(int i = 1; i < orderDetails.length; i++) {
            String orderIngredient = orderDetails[i].trim().substring(1).toLowerCase();
            if(!ingredientSet.contains(orderIngredient)) {
                throw new InvalidOrderException("Invalid ingredient");
            }
            ingredientsToRemove.add(orderIngredient);
        }

        if(ingredientsToRemove.containsAll(ingredientSet)) {
            throw new InvalidOrderException("No ingredients left");
        }
        return true;
    }

    private Double calculatePrice(String item, Set<String> ingredientsToRemove) {
        Double itemPrice = BeverageFactory.PRICE_MAP.get(item);
        for(String ingredient : ingredientsToRemove) {
            itemPrice -= BeverageFactory.PRICE_MAP.get(ingredient);
        }
        return itemPrice;
    }
}
