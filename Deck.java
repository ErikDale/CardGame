package ntnu.idatg2001;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
    }

    public static void main(String[] args) {
        Deck deck = new Deck();

        deck.addCards();
        deck.printList();

        ArrayList<Card> deck3;
        deck3 = deck.assign(5);

        System.out.println("New deck:");
        for (Card card : deck3) {
            String text = card.getDetails();
            System.out.println(text);
        }

        System.out.println("Only spades:");
        deck.printSpades(deck3);

        System.out.println("Only suits:");
        deck.makeColorList();

        System.out.println("Total face-amount of new deck:");
        deck.sumOfFaces(deck3);

        System.out.println("Is S12 there:");
        deck.ladySpades(deck3);

        System.out.println("Do you have flush?");
        deck.findFlush(deck3);

    }

    /**
     * Method that creates a deck of cards consisting of 52 cards divided into
     * 4 different suits, Spades, Hearts, Diamonds and Clovers. The cards are put
     * into an ArrayList
     */
    public void addCards() {
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card('S', i));
        }
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card('H', i));
        }
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card('D', i));
        }
        for (int i = 1; i <= 13; i++) {
            deck.add(new Card('C', i));
        }
    }

    /**
     * Method that prints the whole deck of cards.
     */

    public void printList() {
        System.out.println("Whole deck:");
        for (Card card : deck) {
            String info = card.getDetails();
            System.out.println(info);
        }
    }

    /**
     * Method that shuffles the deck of cards.
     */
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    /**
     * Method that creates a new ArrayList consisting of an 'n' amount of random numbers
     * from the original deck.
     *
     * @param n integer that decides how many cards the new deck will consist of.
     * @return returns the new deck in the form of an ArrayList.
     */
    public ArrayList<Card> assign(int n) {
        ArrayList<Card> deck2 = new ArrayList<>();
        this.shuffleDeck();
        for (int i = 0; i < n; i++) {
            Card card = this.deck.get(i);
            deck2.add(card);

        }
        return deck2;
    }

    /**
     * Method that prints out all the spades-cards in the original deck.
     */
    public void printSpades(ArrayList<Card> deck) {
        deck
                .stream()
                .filter(s -> s.getSuit() == 'S')
                .forEach(s -> System.out.println(s.getDetails()));
    }

    /**
     * Method that collects all the hearts in the original deck and puts them into a new list.
     */
    public void makeHeartsList() {
        deck
                .stream()
                .filter(s -> s.getDetails().contains("H"))
                .collect(Collectors.toList());

    }


    /**
     * Method that creates an ArrayList of only the colors of the cards.
     */
    public void makeColorList() {
        ArrayList<String> colorList = new ArrayList<>();
        List<Character> suitDeck = deck
                .stream()
                .map(Card::getSuit)
                .collect(Collectors.toList());

        for (Character character : suitDeck) {
            if (character == 'S' || character == 'C') {
                colorList.add("Black card");
            } else if (character == 'H' || character == 'D') {
                colorList.add("Red card");
            }
        }
        for (String text : colorList) {
            System.out.println(text);
        }

    }

    /**
     * Method that finds the sum of the faces of all the cards in the deck consisting of a
     * specific number of random cards.
     *
     * @param deck takes an ArrayList of cards that it is to calculate the sum of.
     */
    public void sumOfFaces(ArrayList<Card> deck) {
        System.out.println(deck
                .stream()
                .map(s -> s.getFace())
                .reduce(0, (total, s) -> total + s));


    }

    /**
     * Method that finds out if lady spades is in the new deck. If it is it then prints out
     * 'Yes, S12 is in the deck'.
     *
     * @param deck takes the deck that it is to find S12 as parameter.
     */
    public void ladySpades(ArrayList<Card> deck) {
        boolean ladySpades = deck
                .stream()
                .anyMatch(s -> s.getDetails().equals("S12"));

        if (ladySpades) {
            System.out.println("Yes, lady Spades is in the deck");
        } else {
            System.out.println("No lady Spades is not in the deck");
        }


    }

    /**
     * Method that tells you if you deck contains a pokerflush (5 cards of same suit)
     *
     * @param deck takes the deck as a parameter
     */
    public void findFlush(ArrayList<Card> deck) {
        List<Card> flushDeck = deck
                .stream()
                .filter(s -> s.getSuit() == 'S')
                .collect(Collectors.toList());

        if (flushDeck.size() >= 5) {
            System.out.println("You have a flush of Spades");

        }

        List<Card> flushDeck2 = deck
                .stream()
                .filter(s -> s.getSuit() == 'H')
                .collect(Collectors.toList());

        if (flushDeck2.size() >= 5) {
            System.out.println("You have a flush of Hearts");

        }

        List<Card> flushDeck3 = deck
                .stream()
                .filter(s -> s.getSuit() == 'D')
                .collect(Collectors.toList());

        if (flushDeck3.size() >= 5) {
            System.out.println("You have a flush of Diamonds");

        }

        List<Card> flushDeck4 = deck
                .stream()
                .filter(s -> s.getSuit() == 'C')
                .collect(Collectors.toList());

        if (flushDeck4.size() >= 5) {
            System.out.println("You have a flush of Clovers");

        }


    }
}

