public class Player {

    private String name;
    private boolean inTurn;
    private Pieces[] pieces;
    private boolean sauCondition;

    // constructor
    public Player(String name) {
        this.name = name;
        this.inTurn = false;
        this.pieces = new Pieces[10];
    }

    // getter and setter
    public void turn(){
        inTurn =!inTurn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInTurn() {
        return inTurn;
    }

    public void setInTurn(boolean inTurn) {
        this.inTurn = inTurn;
    }

    public Pieces[] getPieces() {
        return pieces;
    }

    public void setPieces(Pieces[] pieces) {
        this.pieces = pieces;
    }

    public void setSauCondition(boolean c){
        sauCondition = c;
    }

    public boolean getSauCondition() {
        return sauCondition;
    }

    // functions
    public void movePiece(){
        //might need 2 arguments
        ;
    }

    public void losePiece() {
        ;
    }

    public void checkGotPiece() {
        ;
    }

    //need a function to return the pieces that is used to show at the GUI

}
