import java.util.ArrayList;

public class GameMaster {
	
	// game master hold information about the game as well as the logic of the game;
	private Deck deck = new Deck();
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player whoseTurn;
	private int whoseTurnNumber = 0;
	private int passCount = 0;
	private int topCard = 0;
	private boolean isPileClear = true;
	
	public boolean isPileClear() {
		return isPileClear;
	}

	public void setPileClear(boolean isPileClear){
		this.isPileClear = isPileClear;
	}

	public boolean isPlayerMoveValid( String valuePick,int numberOfCardsToPlay) {
		int value = cardValuePickToInt(valuePick);
		if(topCard < value){
			topCard = value;
			passCount = 0;
			return true;
		}else{
			return false;
		}
	
	}// end of isPlayerGuessCorrect

	public GameMaster(String[] playerNames) {
		
		
		for (String player : playerNames) {
			createPlayer(player,(int)52 / playerNames.length);
		}
		whoseTurn = players.get(0);
	}// end of GameMaster

	

	private void createPlayer(String playerName, int cardsPerPlayer) { 
		
		players.add(new Player(playerName, deck.takeCardsFromDeck(cardsPerPlayer)));
	}// end of getPlayer

	public int getNumberOfPlayers() {
		return players.size();
	}// end of getNumberOfPlayers

	public String getWhoseTurnAsName() {
		return whoseTurn.getName();
	}// end of getWhoseTurnAsName

	public Player getWhoseTurn() {
		return whoseTurn;
	}// end of getWhoseTurn

	public void playerWin() {
		for (Player player : players) {
			if (player.getHandObject().size() <= 0) {
				//player wins and is placed on the leader board
			}
		}
	}// end of handEmpty

	public String getPlayerNames() {
		String playerNames = "";
		int whatPlayer = 1;
		for (Player player : players) {
			if (player.getName() != getWhoseTurnAsName()) {
				playerNames += whatPlayer + ": " + player.getName() + " \n";
			}
			whatPlayer++;
		}

		return playerNames;
	}// end of getPlayerNames


	public String printCardValues() {
		String output = "";
		int i = 2;
		for (CardValue value : CardValue.values()) {

			output += i + ": " + value.toString() + "\n";
			i++;
		}
		return output;
	}// end of cardValues

	private int cardValuePickToInt(String value) {
		int valueAsInt = -100;
		try {
			valueAsInt = Integer.parseInt(value);
			return whoseTurn.getHandObject().getHand().get(valueAsInt).getValueInt();
		} catch (Exception e) {
			String[] cardsInHand = whoseTurn.handToString();
			for (int i = 0; i < cardsInHand.length; i++) {
				if (value.equals(cardsInHand[i].substring(3))) {
					return whoseTurn.getHandObject().getHand().get(i).getValueInt();
				}
			}
		}
		return valueAsInt;

	}// end of cardValuePickToInt

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void nextTurn() {
		if (whoseTurnNumber < players.size() - 1) {
			whoseTurnNumber++;
			whoseTurn = players.get(whoseTurnNumber);
		} else {
			whoseTurnNumber = 0;
			whoseTurn = players.get(whoseTurnNumber);

		}

	}// end of nextTurn

	public boolean isGameOver() {
		return false;
	}// end of isGameOver

}