package cn.extremeprogramming.jueyi;

import java.util.Arrays;
import java.util.List;

public class Yao {
    private final List<Yi> yis;
    private int position;

    public Yao(Yi... yis) {
        this.yis = Arrays.asList(yis);
    }

    public boolean isYang() {
        return yis.stream().filter(Yi::isYang).count() % 2 == 1;
    }

    public boolean isChange() {
        return yis.stream().distinct().count() == 1;
    }

    @Override
    public String toString() {
        String result = isYang() ? "⚊" : "⚋";
        if(isChange()) {
            result += isYang()? "o" : "×";
        }
        return result;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int position() {
        return position;
    }

    public boolean isStable() {
        return !isChange();
    }
}
