import java.io.PrintStream;
import java.util.Scanner;


class Player{
    private String name;
    private int moves;

    public Player(String name){
        this.name = name;
        this.moves = 0;
    }

    public int getMoves(){
        return moves;
    }
    public String getName(){
        return this.name;
    }
}

class Field{
    private int[][] field;
    private Player player1;
    private Player player2;

    public Field(Player p1, Player p2){
        this.field = new int[3][3];
        this.player1 = p1;
        this.player2 = p2;
    }

    public String toString(){
        String s = "";
        s += player1.getName() + " - o\n" + player2.getName() + " - o\n";

        for (int i = 0; i < this.field.length; i++){
            s += "|";
            for (int j = 0; j < this.field.length; j++){
                s += " " + this.field[i][j] + " |";
            }
            s += "\n";
        }
        return s;
    }
}

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("123");
        Player p2 = new Player("биба");
        Field f = new Field(p1, p2);
        System.out.println(f);
        new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
    }
}