package conway;

public class Cell {
    private boolean state;

    public Cell() {
        this.state = false;
    }
    public Cell(boolean status){
        this.state = status;
    }
    public void setState(boolean newState) {
        this.state = newState;
    }

    public boolean getState(){
        return this.state;
    }

}
