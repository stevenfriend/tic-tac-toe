public class Display {
    private Type[][] board;

    void printOut(Type[][] board) {
        String s = "  123\n\n";
        for(int i = 0; i <= 2; i++) {
            s += (char)('a' + i) + " ";
            for(int j = 0; j <= 2; j++){
                s += getSymbol(board[j][i]);
            }
            s += "\n";
        }
        System.out.println(s);
    }

    String getSymbol(Type symbol) {
        if(symbol == Type.O) return "O";
        if(symbol == Type.X) return "X";
        if(symbol == Type.blank) return ".";
        return ".";
    }
}
