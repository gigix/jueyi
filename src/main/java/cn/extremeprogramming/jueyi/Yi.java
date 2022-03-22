package cn.extremeprogramming.jueyi;

public class Yi {
    public static final Yi YANG = new Yi(true);
    public static final Yi YIN = new Yi(false);
    private final boolean yang;

    private Yi(boolean yang) {
        this.yang = yang;
    }

    public boolean isYang() {
        return yang;
    }
}
