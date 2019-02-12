class Board {

    private Type[][] board = new Type[3][3];
    private Type player = Type.X;

    void clear() {
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                board[j][i] = Type.blank;
            }
        }
    }

    boolean valid(int x, int y) {
        if(board[y][x] != Type.blank) return false;
        return true;
    }

    private int[] str2pos(String s) {
        int[] pos = new int[2];
        s = s.toLowerCase();
        pos[0] = (int)(s.charAt(0) - 'a');
        pos[1] = (int)(s.charAt(1) - '1');
        return pos;
    }

    boolean makeMove(String move) {
        int[] pos = str2pos(move);
        if(valid(pos[0], pos[1])) {
            board[pos[1]][pos[0]] = player;
            return true;
        }
        return false;
    }

    boolean win() {
        for(int i = 0; i <= 2; i++) {
            if(board[0][i] != Type.blank) {
                if(board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;
                if(board[0][i] == board[1][1] && board[1][1] == board[2][2-i]) return true;
            }
            if(board[i][0] != Type.blank) {
                if(board[i][1] == board[i][2] && board[i][0] == board[i][1]) return true;
            }
        }
        return false;
    }

    boolean draw() {
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                if(board[j][i] == Type.blank) return false;
            }
        }
        if(win()) return false;
        return true;
    }

    Type player() {
        if(player == Type.O) return player;
        return Type.X;
    }

    void switchPlayer() {
        if(player == Type.O) player = Type.X;
        else player = Type.O;
    }

    Type[][] get() {
        return board;
    }

    // ---------- Testing -----------

    public static void main(String[] args) {
        Board testboard = new Board();
        testboard.run();
    }

    private void run() {
        boolean testing = false;
        assert(testing = true);
        if(testing) test();
    }

    private void test() {
        testClear();
        testValid();
        testS2P();
        testWin();
        testDraw();
        testPlayer();
        testSwitchPlayer();
        System.out.println("All tests pass");
    }

    private void testClear() {
        assert(board[0][0] != Type.blank);
        assert(board[2][2] != Type.blank);
        clear();
        assert(board[0][0] == Type.blank);
        assert(board[2][2] == Type.blank);
    }

    private void testS2P() {
        int[] pos;
        pos = str2pos("a1"); assert(pos[0] == 0 && pos[1] == 0);
        pos = str2pos("A7"); assert(pos[0] == 0 && pos[1] == 6);
        pos = str2pos("c3"); assert(pos[0] == 2 && pos[1] == 2);
        pos = str2pos("Z9"); assert(pos[0] == 25 && pos[1] == 8);
    }

    private void testValid() {
        assert(valid(0, 0) == true);
        assert(valid(2, 2) == true);
        board[0][0] = Type.O; assert(valid(0, 0) == false);
        board[2][2] = Type.X; assert(valid(2, 2) == false);
    }

    private void testWin() {
        clear();
        assert(win() == false);
        board[0][0] = Type.X; board[0][1] = Type.X; board[0][2] = Type.X;
        assert(win() == true);
        clear(); assert(win() == false);
        board[1][0] = Type.O; board[1][1] = Type.O; board[1][2] = Type.O;
        assert(win() == true);
        clear(); assert(win() == false);
        board[2][0] = Type.X; board[1][1] = Type.X; board[0][2] = Type.X;
        assert(win() == true);
        clear(); assert(win() == false);
        board[0][0] = Type.O; board[1][1] = Type.O; board[2][2] = Type.O;
        assert(win() == true);
    }

    private void testDraw() {
        assert(draw() == false);
        Type[][] test1 = {
            {Type.X, Type.O, Type.X},
            {Type.O, Type.O, Type.X},
            {Type.X, Type.X, Type.O}};
        board = test1; assert(draw() == true);
        Type[][] test2 = {
            {Type.O, Type.O, Type.O},
            {Type.O, Type.X, Type.X},
            {Type.X, Type.O, Type.X}};
        board = test2; assert(draw() == false);
        board[0][1] = Type.X; assert(draw() == true);
    }

    private void testPlayer() {
        assert(player() == Type.X);
        player = Type.O; assert(player() == Type.O);
        player = Type.blank; assert(player() == Type.X);
    }

    private void testSwitchPlayer() {
        switchPlayer(); assert(player == Type.O);
        switchPlayer(); assert(player == Type.X);
    }

}
