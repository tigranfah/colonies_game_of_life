package utils;

// minimum integer class implementation that extends PCloneable
// cannot extend from class Integer because its final.
public class Int implements Copyable {

    private int val = 0;

    public Int(int val) {
        this.val = val;
    }

    public int unbox() { return this.val; }

    public void box(int val) { this.val = val; }

    @Override
    public Int copy() {
        try {
            return (Int) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
