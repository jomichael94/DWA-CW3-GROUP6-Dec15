import java.util.Calendar;
import java.util.Scanner;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JLabel;

/**
 * @file Game.java
 * @author James Michael
 * @date 02 Dec 2015
 * @see GameSetup.java for setter and getter methods
 *
 * This class asks the player for their name and some details 
 * on what settings they would like to use in the game.
 */
public class Game{
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 450;
    private boolean gameStarted = false;
    private JTextField nameTF;
    private final int TEXT_FIELD_WIDTH = 10;

    public static boolean isGameRunning(boolean gameStarted){
        return gameStarted;
    }

	public static void main (String[] args){
        JFrame frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(false);
            
        Scanner in = new Scanner(System.in);
        String playerName;
        int numberOfTiles = 0;
        int numberOfMines = 0;
        String time;
        boolean gameType;
        int enteredNumOfTiles = 0;
        int enteredNumOfMines = 0;
        String name;

        boolean gameStarted = isGameRunning(false);
        System.out.println("GAME IS IN PROGRESS: " + gameStarted);
        GameWindow menu = new GameWindow();
        menu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        menu.setSize(400, 400);
        menu.add(menu.getNameTF());
        menu.add(menu.getNumOfTilesTF());
        menu.add(menu.getNumOfMinesTF());
        menu.add(menu.getConfirmJB());
        menu.setVisible( true );
        do{
            System.out.print("Please enter name: ");
            name = in.nextLine();
        }while (name.equals(""));
        System.out.print("Custom game?(y/n) ");
        String customGame = in.nextLine();
        if (customGame.equalsIgnoreCase("y")||customGame.equalsIgnoreCase("yes")){
            gameType = true;
            do {
                System.out.print("Enter number of tiles: ");
                enteredNumOfTiles = in.nextInt();
            }while (enteredNumOfTiles <= 0 || enteredNumOfTiles > 30);
            int totalTiles = enteredNumOfTiles * enteredNumOfTiles;
            do {
                System.out.print("Enter number of mines: ");
                enteredNumOfMines = in.nextInt();
            }while (enteredNumOfMines >= totalTiles || enteredNumOfMines > 150);
        }
        else {
            gameType = false;
            System.out.println("\n**GAME WILL BE PLAYED WITH DEFAULT SETTINGS**");
            enteredNumOfTiles = 10;
            enteredNumOfMines = 10;
        }

        GameSetup setup = new GameSetup(name, enteredNumOfTiles, enteredNumOfMines, gameType);
        Counter c = new Counter(enteredNumOfMines, enteredNumOfTiles);
        Time t = new Time();
        //Board b = new Board();
        playerName = setup.getName();
        //time = t.getCurrentTime();
        boolean selectedGameType = setup.getGameType();
        int boardSize = setup.getNumberOfTiles();
        int totalMines = setup.getNumberOfMines();
        int totalBoardSize = boardSize*boardSize;

        gameStarted = isGameRunning(true);

        System.out.println("\n**NAME: " + playerName + "\n\n**CUSTOM GAME: " + selectedGameType + "\n\n**SIZE OF BOARD: " + totalBoardSize + " (" + boardSize +
                            "x" + boardSize + ")\n\n**NUMBER OF MINES: " + totalMines); // + "\n\n**TIME (work in progress **IGNORE**): " + time);
        menu.setVisible(false);
        frame.setVisible(true);
        Board b;
        frame.add(b = new Board());

        JTextField displayTimeLabel = new JTextField(20);
        displayTimeLabel.setBounds(530,100,120,30);
        displayTimeLabel.setVisible(true);
        displayTimeLabel.setEditable(false);
        
        JTextField displayMinesDiffusedLabel = new JTextField(20);
        displayMinesDiffusedLabel.setBounds(530,160,120,30);
        displayMinesDiffusedLabel.setEditable(false);

        JTextField displayHiddenTilesLabel = new JTextField(20);
        displayHiddenTilesLabel.setBounds(530,280,120,30);
        displayHiddenTilesLabel.setEditable(false);

        JTextField displayRevealedTilesLabel = new JTextField(20);
        displayRevealedTilesLabel.setBounds(530,340,120,30);
        displayRevealedTilesLabel.setEditable(false);

        /*nameLabel.setVisible(true);
        displayNameLabel.setVisible(true);
        displayNameLabel.setEditable(false);*/
        //GameInfo.setNameLabel();
        GameInfo info = new GameInfo();
        frame.add(info.getNameLabel());
        frame.add(info.getDisplayNameLabel());
        frame.add(info.getTimeLabel());
        frame.add(info.getMinesDiffusedLabel());
        frame.add(info.getMinesPresentLabel());
        frame.add(info.getDisplayMinesPresentLabel());
        frame.add(info.getHiddenTilesLabel());
        frame.add(info.getRevealedTilesLabel());
        
        displayMinesDiffusedLabel.setVisible(true);
        displayHiddenTilesLabel.setVisible(true);
        displayRevealedTilesLabel.setVisible(true);

        b.setSize(400,400);

        frame.setResizable(false);
        /*GameWindow menu = new GameWindow();
        menu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        menu.setSize(200, 200);
        menu.add(menu.getNameTF());
        menu.add(menu.getNumOfTilesTF());
        menu.add(menu.getNumOfMinesTF());
        menu.setVisible( true ); */

        //while(isGameRunning(true)){
         //   menu.setNameTF();
        //}
        //frame.add(b);
        frame.setTitle("Kablewie!");
        //String defaultSettings = "**GAME WILL BE PLAYED WITH DEFAULT SETTINGS**";
        String welcome = "**NAME: " + playerName + "\n**CUSTOM GAME: " + selectedGameType + "\n**TOTAL SIZE OF BOARD: " + totalBoardSize + " (" + boardSize +
                            "x" + boardSize + ")\n**NUMBER OF MINES: " + totalMines; //+ "\n**TIME (work in progress **IGNORE**): " + time;
        //JOptionPane.showMessageDialog(frame, welcome, "Welcome to Kablewie!", JOptionPane.WARNING_MESSAGE);
        System.out.println("GAME IS IN PROGRESS: " + gameStarted);

        //frame.add(Board b = new Board(););
        //menu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        //menu.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        //menu.setVisible( true ); 
        //JTextField timeElapsedLabel = info.getDisplayTimeLabel();
        //frame.add(timeElapsedLabel);
        GameResult gr = new GameResult();
        do{

            displayTimeLabel.setText(t.getTimeElapsed());
            //timeElapsedLabel.setText(t.getTimeElapsed());
            //frame.add(info.getDisplayTimeLabel());
             if(gr.getGameResult()== true){
                gameStarted = false;
                //System.out.println(gr.getGameResult());

            }
            //System.out.println(gr.getGameResult());
            frame.add(displayTimeLabel);
            displayMinesDiffusedLabel.setText(c.getDiffusedCount() + "");
            frame.add(displayMinesDiffusedLabel);
            displayHiddenTilesLabel.setText(c.getTilesRemainingCount() + "");
            frame.add(displayHiddenTilesLabel);
            displayRevealedTilesLabel.setText(c.getRevealedTileCount() + "");
            frame.add(displayRevealedTilesLabel);
            gr.setGameResult();
 
        }while(gameStarted);

        //do{
            //menu.getNameTF().setText(t.getTimeElapsed());
        //}while(isGameRunning(true));
        //do{
        //    menu.getNameTF();
        //}while(isGameRunning(true));

        //Board b = new Board();
        //do{
        //    System.out.println(t.getTimeElapsed());
        //}while(isGameRunning(true));
        gameStarted = isGameRunning(false);
        String gameOver = "Game Over!\nThank you for playing Kablewie!";
        JOptionPane.showMessageDialog(frame, gameOver, "Game Over", JOptionPane.WARNING_MESSAGE);
        frame.dispose();

    }
}