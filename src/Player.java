// класс игрока, хранит информацию об игроке
class Player{
    private String name;
    private int moves; // количество совершенных ходов
    private boolean isWinner; // победитель ли игрок

    public Player(String name){
        this.name = name;
        this.moves = 0;
        this.isWinner = false;
    }

    public boolean getIsWinner(){
        return this.isWinner;
    }

    public void setIsWinner(boolean value){
        this.isWinner = value;
    }

    public int getMoves(){
        return moves;
    }
    public String getName(){
        return this.name;
    }

    // увеличивает количество ходов на 1
    public void increaseMoves(){
        this.moves++;
    }

    // сброс пользователя
    public void reset(String name){
        this.name = name;
        this.moves = 0;
        this.isWinner = false;
    }
}