package cn.extremeprogramming.jueyi;

import java.util.Arrays;
import java.util.List;

public class Yao {
    private final List<Yi> yis;

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
        String result;
        if(isYang()) {
            result = "⚊ ";
            if(isChange()) {
                result += "o️ 老阳";
            } else {
                result += "· 少阳";
            }
        } else {
            result = "⚋ ";
            if(isChange()) {
                result += "× 老阴";
            } else {
                result += ":: 少阴";
            }
        }
        return result;
    }
}
