package cn.extremeprogramming.jueyi;

import static cn.extremeprogramming.jueyi.Yi.YANG;
import static cn.extremeprogramming.jueyi.Yi.YIN;

public class Fixture {
    static final Yao SHAO_YANG = new Yao(YANG, YIN, YIN);
    static final Yao SHAO_YIN = new Yao(YANG, YIN, YANG);
    static final Yao LAO_YANG = new Yao(YANG, YANG, YANG);
    static final Yao LAO_YIN = new Yao(YIN, YIN, YIN);
}
