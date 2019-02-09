class Board {
    private Type[][] board = new Type[3][3];
    private Type player;

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

    boolean won(Type p) {
        for(int i = 0; i <= 2; i++) {
            if(board[0][i] == p) {
                if(board[1][i] == p && board[2][i] == p) return true;
                if(board[1][1] == p && board[2][2-i] == p) return true;
            }
            if(board[i][0] == p && board[i][1] == p && board[i][2] == p) return true;
        }
        return false;
    }

    boolean draw() {
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                if(board[j][i] == Type.blank) return false;
            }
        }
        if(won(Type.O) || won(Type.X)) return false;
        return true;
    }

    Type getPlayer() {
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
        System.out.println("All tests pass");
    }

    private void test() {
        testClear();
        testValid();
        testWon();
        testDraw();
        testGetPlayer();
        testSwitchPlayer();
    }

    private void testClear() {
        assert(board[0][0] != Type.blank);
        assert(board[2][2] != Type.blank);
        clear();
        assert(board[0][0] == Type.blank);
        assert(board[2][2] == Type.blank);
    }

    private void testValid() {
        assert(valid(0, 0) == true);
        assert(valid(2, 2) == true);
        board[0][0] = Type.O; assert(valid(0, 0) == false);
        board[2][2] = Type.X; assert(valid(2, 2) == false);
    }

    private void testWon() {
        clear();
        assert(won(Type.O) == false);
        assert(won(Type.X) == false);
        board[0][0] = Type.O; board[1][1] = Type.O; board[2][2] = Type.O;
        assert(won(Type.O) == true);
        assert(won(Type.X) == false);
        board[2][0] = Type.X; board[2][1] = Type.X; board[2][2] = Type.X;
        assert(won(Type.O) == false);
        assert(won(Type.X) == true);
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

    private void testGetPlayer() {
        assert(getPlayer() == Type.X);
        player = Type.O; assert(getPlayer() == Type.O);
        player = Type.blank; assert(getPlayer() == Type.X);
    }

    private void testSwitchPlayer() {
        switchPlayer(); assert(player == Type.O);
        switchPlayer(); assert(player == Type.X);
    }
}
