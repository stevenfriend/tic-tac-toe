class Oxo {

    private Board board = new Board();
    private Display display = new Display();
    private boolean playing = true;
    private String move;

    public static void main(String[] args) {
        Oxo game = new Oxo();
        game.run();
    }

    private void run() {
        board.clear();
        while(playing) {
            display.printOut(board.get());
            move();
            check();
            board.switchPlayer();
        }
    }

    private void move() {
        move = display.askMove(board.player());
        if(!board.makeMove(move)) {
            display.invalid();
            move();
        }
    }

    private void check() {
        if(board.win(board.player())){
            playing = false;
            display.printOut(board.get());
            display.win(board.player());
        } else if(board.draw()) {
            playing = false;
            display.printOut(board.get());
            display.draw();
        }
    }

}
