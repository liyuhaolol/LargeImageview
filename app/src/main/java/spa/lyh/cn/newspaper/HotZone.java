package spa.lyh.cn.newspaper;

public class HotZone {

    private int left;
    private int right;
    private int top;
    private int bottom;
    private String name;

    public HotZone(){}

    public HotZone(int left,int top,int right,int bottom,String name){
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.name = name;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
