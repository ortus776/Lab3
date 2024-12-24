import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // иннициализация классов
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя 1 игрока");
        Player p1 = new Player(in.nextLine());
        System.out.println("Введите имя 2 игрока");
        Player p2 = new Player(in.nextLine());
        Field f = new Field(p1, p2);
        Game game = new Game(f);
        game.play();
    }
}