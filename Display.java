import java.util.Scanner;

public class Display {

    private Type[][] board;
    private Scanner input = new Scanner(System.in);
    private boolean testing = false;

    void printOut(Type[][] board) {
        String s = "\n  123\n\n";
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

    String askMove(Type player) {
        String move;
        String s = "Player " + player + "'s move: ";
        System.out.print(s);
        move = input.nextLine();
        if(valid(move)) return move;
        return askMove(player);
    }

    private boolean valid(String move) {
        if(move.matches("[abcABC][1-3]")) return true;
        if(!testing) System.out.println("\nInvalid move, write in form: b2\n");
        return false;
    }

    void invalid() {
        System.out.println("\nThis position is already taken\n");
    }

    void win(Type player) {
        System.out.println("\nPlayer " + player + " won the game!\n");
    }

    void draw() {
        System.out.println("\nIt's a draw.\n");
    }

    // ---------- Testing -----------

    public static void main(String[] args) {
        Display test = new Display();
        test.run();
    }

    private void run() {
        assert(testing = true);
        if(testing) test();
    }

    private void test() {
        testValid();
        System.out.println("All tests pass");
    }

    private void testValid() {
        assert(valid("a1") == true);
        assert(valid("a0") == false);
        assert(valid("b2") == true);
        assert(valid("b 2") == false);
        assert(valid("c3") == true);
        assert(valid("cc") == false);
        assert(valid("A3") == true);
        assert(valid("A33") == false);
        assert(valid("A4") == false);
        assert(valid("d3") == false);
    }

}
