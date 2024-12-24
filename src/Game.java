import java.util.Scanner;

// класс самой игры
class Game{
    Scanner in = new Scanner(System.in);
    private Field fl;

    // в конструктор принимает
    public Game(Field fl){
        this.fl = fl;
    }

    // метод, в котором описывается внутриигровая логика
    public void play(){
        System.out.println(this.fl);
        while (this.fl.isContinuing()){ // пока игра продолжается
            String s;
            do{
                System.out.println("Введите c для продолжения игры, n для начала новой игры (латиница)");
                s = in.nextLine(); // ввод ответа на вопрос
                if (s.equalsIgnoreCase("c")){ // если продолжаем игру
                    if (!this.fl.isContinuing()){ // если игра закончилась
                        System.out.println(this.fl.currentSituation()); // то выводим статус игры
                    }
                    else{ // иначе совершаем ход
                        System.out.println("Ходит " + this.fl.currentPlayer());
                        this.fl.move();
                    }
                    System.out.println(this.fl); // выводим обновленное поле
                    this.fl.defineWinner(); // проверяем победил ли кто-то из игроков
                    System.out.println(this.fl.currentSituation());

                }
                if (s.equalsIgnoreCase("n")) // если начинаем новую игру
                    this.fl.clearField(); // то очищаем поле
                if (!this.fl.isContinuing()){ // если игра закончилась
                    String answer;
                    do{
                        System.out.println("Вы хотите начать игру заново? y/n");
                        answer = in.nextLine();
                    }while(!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))); // то спрашиваем хочет ли начать новую игру
                    if (answer.equalsIgnoreCase("y")){ // если да, то очищаем поле
                        this.fl.clearField();
                    }
                }
            }while(!(s.equalsIgnoreCase("c") || s.equalsIgnoreCase("n")) && this.fl.isContinuing());
        }
    }
}
