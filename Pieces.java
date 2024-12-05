public abstract class Pieces {

    private Ram[] ram;
    private Biz[] biz;
    private Tor tor;
    private Xor xor;
    private Sau sau;

    public abstract void move();
    public abstract void findMoves();
    public abstract void takeEnemy();


}
