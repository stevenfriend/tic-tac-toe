class Oxo {

    public static void main(String[] args) {
        Oxo game = new Oxo();
        game.run();
    }

    void run() {
        Board board = new Board();
        Display display = new Display();
        board.clear();
        display.printOut(board.get());
    }
}
