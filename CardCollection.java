import java.util.*;

class CardCollection {
    private Map<String, List<String>> cards = new HashMap<>();

    public void addCard(String symbol, String cardName) {
        cards.computeIfAbsent(symbol, k -> new ArrayList<>()).add(cardName);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cards.getOrDefault(symbol, Collections.emptyList());
    }

    public void displayAllCards() {
        for (Map.Entry<String, List<String>> entry : cards.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        CardCollection collection = new CardCollection();
        collection.addCard("Hearts", "Ace");
        collection.addCard("Hearts", "King");
        collection.addCard("Spades", "Queen");
        collection.addCard("Diamonds", "Jack");

        System.out.println("Cards in Hearts: " + collection.getCardsBySymbol("Hearts"));
        System.out.println("All Cards:");
        collection.displayAllCards();
    }
}