package project0.dixcover;

public enum CellType {
    EMPTY(' '), TARGET('X'),
    OBSTACLE('O'), PLAYAGAIN('+'),
    BOMB('*'), SUPERBOMB('#');

    private char value;

    CellType(char value){
        this.value = value;
    }

    char getValue(){
        return value;
    }
}
