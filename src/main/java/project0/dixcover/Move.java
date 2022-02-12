package project0.dixcover;

public class Move extends Position {
    OrientationMove orientation;
    SenseMove sense;
    boolean hasBomb;

    Move(int row, int col, OrientationMove orientation, SenseMove sense, boolean hasBomb){
        super(row, col);
        this.hasBomb = hasBomb;
        this.orientation = orientation;
        this.sense = sense;
    }

    public OrientationMove getOrientation() {
        return orientation;
    }

    public void setOrientation(OrientationMove orientation) {
        this.orientation = orientation;
    }

    public SenseMove getSense() {
        return sense;
    }

    public void setSense(SenseMove sense) {
        this.sense = sense;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }
}
