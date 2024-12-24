import java.util.Scanner;

// класс поля
class Field{
    Scanner in = new Scanner(System.in);
    private int[][] field; // двумерный массив, в котором хранятся все значения: 0 - пустая клетка, -1 - x, 1 - o
    private Player player1;
    private Player player2;

    // при создании на вход принимает 2 объекта класса игрок
    public Field(Player p1, Player p2){
        this.field = new int[3][3];
        this.player1 = p1;
        this.player2 = p2;
    }

    @Override
    public String toString(){
        String s = "";
        // вывод имен пользователей с их знаком
        s += player1.getName() + " - o\n" + player2.getName() + " - x\n";

        // формирование таблички 3x3 для вывода
        for (int i = 0; i < this.field.length; i++){
            s += "|";
            for (int j = 0; j < this.field.length; j++){String t;
                // 1 заменяем на щ
                if (this.field[i][j] == 1)
                    t = "o";

                    // -1 заменяем на x
                else if(this.field[i][j] == -1)
                    t = "x";
                    // иначе на -
                else
                    t = "-";
                s += " " + t + " |";
            }
            s += "\n"; // перходим к новой строке таблички
        }
        return s;
    }

    // возвращает имя игрока, который делает ход
    public String currentPlayer(){
        // если количество совершенных ходов у первого игрока меньше или равно количеству ходов второго, то ходит первый
        if (this.player1.getMoves() <= this.player2.getMoves())
            return this.player1.getName();
            // иначе второй
        else
            return this.player2.getName();
    }

    // метод, совершающий ход
    public void move(){
        int x;
        int y;
        // 2 цикла т.к сначала нужно сначала проверить то, что координаты не выходят за границы поля и только потом проверить то, что клетка пустая
        //пользователь вводит координаты пока не введет их еоректно
        do{
            do{
                System.out.println("Введите корректные координаты клетки");
                x = in.nextInt();
                y = in.nextInt();
            }while(!(x >= 1 && x <= 3 && y >= 1 && y <= 3));
        }while(this.field[y - 1][x - 1] != 0);
        // проверка того, кто сейчас ходит
        if (this.player1.getMoves() <= this.player2.getMoves()){
            this.field[y - 1][x - 1] = 1; // клеточке присваиваем 1 если ходит первый
            this.player1.increaseMoves(); // увеличиваем количество ходов на 1 у сходившего игрока
        }
        else{
            this.field[y - 1][x - 1] = -1; // иначе присваиваем -1
            this.player2.increaseMoves(); // увеличиваем количество ходов на 1 у сходившего игрока
        }
    }

    // определяет победителя
    public void defineWinner(){
        // проходимся по массиву
        for (int i = 0; i < this.field.length; i++){
            int s1 = 0;
            int s2 = 0;
            for (int j = 0; j < this.field[0].length; j++){
                s1 += this.field[i][j]; // считаем сумму строки
                s2 += this.field[j][i]; // считаем сумму столбца
            }
            // если какая-либо из сумм равна 3, то первый победил
            if (s1 == 3 || s2 == 3)
                this.player1.setIsWinner(true);
            // если какая-либо из сумм равна -3, то первый победил
            if (s1 == -3 || s2 == -3)
                this.player2.setIsWinner(true);
        }
        int s = 0;
        int s2 = 0;
        for (int i = 0; i < this.field.length; i++){
            s += this.field[i][i]; // считаем сумму диагонали из левого верхнего угла
            s2 += this.field[i][this.field.length - 1 - i]; // считаем сумму диагонали из правого верхнего угла
        }
        // если какая-либо из сумм равна 3, то первый победил
        if (s == 3 || s2 == 3)
            this.player1.setIsWinner(true);
        // если какая-либо из сумм равна -3, то первый победил
        if (s == -3 || s2 == -3)
            this.player2.setIsWinner(true);

    }
    // проверяет является ли игрок победителем
    public boolean checkWinner(int number){
        if (this.player1.getIsWinner() && number == 1 || this.player2.getIsWinner() && number == 2)
            return true;
        else
            return false;
    }

    // проверка на ничью
    public boolean isTie(){
        int k = 0;
        // считаем количество пустых клеток
        for (int i = 0; i < this.field.length; i++){
            for (int j = 0; j < this.field[0].length; j++){
                if (this.field[i][j] == 0)
                    k++;
            }
        }
        // если количество пустыъ клеток равно нулю и ни один из игроков не является победителем
        if (k == 0 && !this.player1.getIsWinner() && !this.player2.getIsWinner())
            return true;
        else
            return false;
    }

    // проверка на то, что игра в процессе
    public boolean isContinuing(){
        if (!this.isTie() && !this.checkWinner(1) && !this.checkWinner(2)) // если не ничья и ни один из игроков не победил, то игра в процессе
            return true;
        else
            return false;
    }

    // возвращает текущее состояние игры
    public String currentSituation(){
        String s = "";
        if (isTie()){ // если ничья
            s = "Ничья";
        }
        else if(this.player1.getIsWinner()){ // если первый побеил
            s = "Победитель " + this.player1.getName();
        }
        else if(this.player2.getIsWinner()){ // если второй победил
            s = "Победитель: " + this.player2.getName();
        }
        else{ // иначе
            s = "Игра в процессе";
        }
        return s;
    }

    // очищает игровое поле
    public void clearField(){
        String s;
        in.nextLine();
        do{
            // выбираем хотим ли оставить имена игроков
            System.out.println("Вы хотите оставить имена пользователей? Введите ответ y/n");
            s = in.nextLine();
            // если нет, то сбрасываем методом reset объекты класса player, запрашиваем новые имена
            if (s.equalsIgnoreCase("n")){
                System.out.println("Введите имя 1 игрока");
                this.player1.reset(in.nextLine());
                System.out.println("Введите имя 2 игрока");
                this.player2.reset(in.nextLine());
                this.field = new int[3][3]; // возможно не самый оптимальный вариант с точки зрения расхода памяти, но учитывая что поле 3x3 это допустимо
            }
            // сбрасываем методом reset объекты класса player, но оставляем их имена
            if (s.equalsIgnoreCase("y")){
                this.player1.reset(this.player1.getName());
                this.player2.reset(this.player2.getName());
                this.field = new int[3][3];
            }
        }while (!(s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")));
    }

}