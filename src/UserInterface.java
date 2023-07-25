import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    // user interface is what the players will use to play the game.
    // it will call methods from game master for the game logic
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        boolean playAgain = false;
        int numberOfCardsToPlay = -100;
        do {
            /* starting a new game */
            welcome();
            int numberOfPlayer = getNumberOfPlayers();
            String[] PlayerNames = getPlayerNames(numberOfPlayer);
            GameMaster game = new GameMaster(PlayerNames);
            do {
                String valuePick = valuePick(game).toUpperCase();
                if (valuePick.equals("pass") || valuePick.equals("0")) {
                    game.nextTurn();
                    System.out.println(game.getWhoseTurnAsName() + "has passed!");
                    continue;
                }
                if (game.isPileClear()) {
                    numberOfCardsToPlay = getNumberOfCardsToPlay();
                }

                if (game.isPlayerMoveValid(valuePick, numberOfCardsToPlay)) {
                    System.out.println("well done!");
                    game.nextTurn();
                } else {
                    System.out.println("you didn't play valid card/s");
                }
                game.playerWin();

            } while (!game.isGameOver());// end of game
            System.out.println("would you like to play again? enter yes if so");
            playAgain = doTheyWantToPlayerAgain(userInput.nextLine());
        } while (playAgain);
    }// end of main

    private static int getNumberOfCardsToPlay() {
        System.out.println("please enter the number of cards you would like to play");

        return Integer.parseInt(userInput.nextLine());
    }

    private static String[] getPlayerNames(int numberOfPlayer) {
        String[] PlayerNames = new String[numberOfPlayer];
        System.out.println("please enter the name of the first player");
        PlayerNames[0] = userInput.nextLine();
        for (int i = 1; i < numberOfPlayer; i++) {
            System.out.println("please enter the name of player " + (i + 1));
            PlayerNames[i] = userInput.nextLine();
        }
        return PlayerNames;
    }// end of getPlayerNames

    private static String valuePick(GameMaster game) {
        System.out.println("0. pass");
        for (String card : game.getWhoseTurn().handToString()) {
            System.out.println(card);
        }

        System.out.println("its " + game.getWhoseTurnAsName() + " turn");
        System.out.println(
                "please enter the value of the card you would like to place or enter 0/pass to skip your turn");

        return userInput.nextLine();
    }// end of getPlayerNames

    private static void welcome() {
        String rules = "nothing yet";
        System.out.println("hello and welcome to president");
        System.out.println("here are the rules\n" + rules);
        System.out.println("please enter the number of players");
    }// end of welcome

    private static int getNumberOfPlayers() {
        boolean anyErrors = false;
        do {
            try {
                int playerAmount = Integer.parseInt(userInput.nextLine());
                if (playerAmount > 6) {
                    System.out.println("their is a max of 6 players please enter a number less than 10");
                    anyErrors = true;
                } else if (playerAmount > 0) {
                    return playerAmount;
                } else {
                    anyErrors = true;
                    System.out.println("please enter a number greater than zero");
                }
            } catch (Exception e) {
                System.out.println("please enter a number");
                anyErrors = true;
            }
        } while (anyErrors);
        return -100;
    }// end of getNumberOfPlayers

    private static boolean doTheyWantToPlayerAgain(String playAgain) {
        if (playAgain.equalsIgnoreCase("yes") || playAgain.equalsIgnoreCase("y") || playAgain.equalsIgnoreCase("ya")
                || playAgain.equalsIgnoreCase("yep") || playAgain.equalsIgnoreCase("1")) {
            return true;
        }
        return false;
    }// end of doTheyWantToPlayerAgain
}// end of UserInterface