package cn.extremeprogramming.jueyi;

import java.util.List;
import java.util.Random;

import static cn.extremeprogramming.jueyi.Gua.from;
import static cn.extremeprogramming.jueyi.Yi.YANG;
import static cn.extremeprogramming.jueyi.Yi.YIN;
import static java.lang.Math.abs;
import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class Divination {

    private final List<Yao> sixYao;

    public static void main(String[] args) {
        String question = args[0];
        System.out.println("开始占卜您的问题：" + question);
        Divination divination = new Divination(question);
        System.out.println(divination.gua());
    }

    private final String question;
    private final Random random;

    public Divination(String question) {
        this.question = question;
        random = new Random(seed());
        sixYao = range(0, 6).mapToObj(i -> begAYao()).collect(toList());
    }

    public List<Yao> sixYao() {
        return sixYao;
    }

    private Yao begAYao() {
        return new Yao(range(0, 3).mapToObj(i -> flipACoin()).toArray(Yi[]::new));
    }

    private Yi flipACoin() {
        return abs(random.nextInt()) % 2 == 1 ? YANG : YIN;
    }

    private long seed() {
        return question.hashCode() + now().toEpochMilli();
    }

    public Gua gua() {
        return from(sixYao().toArray(new Yao[0]));
    }
}
