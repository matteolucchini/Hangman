package console;

import hangman.Game;
import hangman.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RemotePlayer extends Player {

    private BufferedReader console;
    private PrintWriter out;


    /**
     * Constructor.
     */
    public RemotePlayer(BufferedReader br, PrintWriter out) {
        this.out = out;
        console = br;
    }

    @Override
    public void update(Game game) {
        switch(game.getResult()) {
            case FAILED:
                out.println("FAILED " +
                        game.getSecretWord());
                break;
            case SOLVED:
                out.println("SOLVED " + game.getSecretWord());
                break;
            case OPEN:
                int rem = Game.MAX_FAILED_ATTEMPTS - game.countFailedAttempts();
                out.println("\n" + rem + " tentativi rimasti\n");
                //out.println(this.gameRepresentation(game));
                out.println(game.getKnownLetters());
                break;
        }
    }

    /*
    private String gameRepresentation(Game game) {
        int a = game.countFailedAttempts();

        String s = "   ___________\n  /       |   \n  |       ";
        s += (a == 0 ? "\n" : "O\n");
        s += "  |     " + (a == 0 ? "\n" : (a < 5
                ? "  +\n"
                : (a == 5 ? "--+\n" : "--+--\n")));
        s += "  |       " + (a < 2 ? "\n" : "|\n");
        s += "  |      " + (a < 3 ? "\n" : (a == 3 ? "/\n" : "/ \\\n"));
        s += "  |\n================\n";
        return s;
    }
    */

    /*
    private void printBanner(String message) {
        System.out.println("");
        for (int i = 0; i < 80; i++)
            System.out.print("*");
        System.out.println("\n***  " + message);
        for (int i = 0; i < 80; i++)
            System.out.print("*");
        System.out.println("\n");
    }
    */

    /**
     * Ask the user to guess a letter.
     *
     * @param game
     * @return
     */
    @Override
    public char chooseLetter(Game game) {
        for (;;) {
            out.println("Inserisci una lettera: ");
            String line = null;
            try {
                while(!console.ready()){
                    System.out.print("Waiting...");
                }
                line = console.readLine();
            } catch (IOException e) {
                line = "";
            }
            if (line.length() == 1 && Character.isLetter(line.charAt(0))) {
                return line.charAt(0);
            } else {
                out.println("Lettera non valida.");
            }
        }
    }
}
