public class Cell {

    private char character = Utils.DEFAULT_CELL_CHARACTER;

    public Cell() {}
    
    public String toString() {
        return Character.toString(this.character);
    }

}