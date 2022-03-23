package cn.extremeprogramming.jueyi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Predicate;

import static cn.extremeprogramming.jueyi.Yi.YANG;
import static cn.extremeprogramming.jueyi.Yi.YIN;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Gua {
    public final int position;
    public final String name;
    public final String representation;

    private List<Yao> sixYao;
    private final List<Yi> lines;

    private Gua(int position, String name, String representation, Integer... sides) {
        this.position = position;
        this.name = name;
        this.representation = representation;
        this.lines = stream(sides).map(i -> i == 1 ? YANG : YIN).collect(toList());
    }

    public static Gua from(Yao... allYao) {
        Gua gua = ALL_64_GUA.stream().filter(g -> g.matches(asList(allYao))).findFirst().get();
        gua.sixYao = asList(allYao);
        return gua;
    }

    private boolean matches(List<Yao> yaos) {
        return yaos.stream().map(Yao::isYang).collect(toList()).equals(
                lines.stream().map(Yi::isYang).collect(toList())
        );
    }

    public static final List<Gua> ALL_64_GUA = asList(
            new Gua(1, "乾　", "䷀", 1, 1, 1, 1, 1, 1),
            new Gua(2, "坤　", "䷁", 0, 0, 0, 0, 0, 0),
            new Gua(3, "屯　", "䷂", 0, 1, 0, 0, 0, 1),
            new Gua(4, "蒙　", "䷃", 1, 0, 0, 0, 1, 0),
            new Gua(5, "需　", "䷄", 0, 1, 0, 1, 1, 1),
            new Gua(6, "讼　", "䷅", 1, 1, 1, 0, 1, 0),
            new Gua(7, "师　", "䷆", 0, 0, 0, 0, 1, 0),
            new Gua(8, "比　", "䷇", 0, 1, 0, 0, 0, 0),
            new Gua(9, "小畜", "䷈", 1, 1, 0, 1, 1, 1),
            new Gua(10, "履　", "䷉", 1, 1, 1, 0, 1, 1),
            new Gua(11, "泰　", "䷊", 0, 0, 0, 1, 1, 1),
            new Gua(12, "否　", "䷋", 1, 1, 1, 0, 0, 0),
            new Gua(13, "同人", "䷌", 1, 1, 1, 1, 0, 1),
            new Gua(14, "大有", "䷍", 1, 0, 1, 1, 1, 1),
            new Gua(15, "谦　", "䷎", 0, 0, 0, 1, 0, 0),
            new Gua(16, "豫　", "䷏", 0, 0, 1, 1, 1, 1),
            new Gua(17, "随　", "䷐", 0, 1, 1, 0, 0, 1),
            new Gua(18, "蛊　", "䷑", 1, 0, 0, 1, 1, 0),
            new Gua(19, "临　", "䷒", 0, 0, 0, 0, 1, 1),
            new Gua(20, "观　", "䷓", 1, 1, 0, 0, 0, 0),
            new Gua(21, "噬嗑", "䷔", 1, 0, 1, 0, 0, 1),
            new Gua(22, "贲　", "䷕", 1, 0, 0, 1, 0, 1),
            new Gua(23, "剥　", "䷖", 1, 0, 0, 0, 0, 0),
            new Gua(24, "复　", "䷗", 0, 0, 0, 0, 0, 1),
            new Gua(25, "无妄", "䷘", 1, 1, 1, 0, 0, 1),
            new Gua(26, "大畜", "䷙", 1, 0, 0, 1, 1, 1),
            new Gua(27, "颐　", "䷚", 1, 0, 0, 0, 0, 1),
            new Gua(28, "大过", "䷛", 0, 1, 1, 1, 1, 0),
            new Gua(29, "坎　", "䷜", 0, 1, 0, 0, 1, 0),
            new Gua(30, "离　", "䷝", 1, 0, 1, 1, 0, 1),
            new Gua(31, "咸　", "䷞", 0, 1, 1, 1, 0, 0),
            new Gua(32, "恒　", "䷟", 0, 0, 1, 1, 1, 0),
            new Gua(33, "遁　", "䷠", 1, 1, 1, 1, 0, 0),
            new Gua(34, "大壮", "䷡", 0, 0, 1, 1, 1, 1),
            new Gua(35, "晋　", "䷢", 1, 0, 1, 0, 0, 0),
            new Gua(36, "明夷", "䷣", 0, 0, 0, 1, 0, 1),
            new Gua(37, "家人", "䷤", 1, 1, 0, 1, 0, 1),
            new Gua(38, "睽　", "䷥", 1, 0, 1, 0, 1, 1),
            new Gua(39, "蹇　", "䷦", 0, 1, 0, 1, 0, 0),
            new Gua(40, "解　", "䷧", 0, 0, 1, 0, 1, 0),
            new Gua(41, "损　", "䷨", 1, 0, 0, 0, 1, 1),
            new Gua(42, "益　", "䷩", 1, 1, 0, 0, 0, 1),
            new Gua(43, "夬　", "䷪", 0, 1, 1, 1, 1, 1),
            new Gua(44, "姤　", "䷫", 1, 1, 1, 1, 1, 0),
            new Gua(45, "萃　", "䷬", 0, 1, 1, 0, 0, 0),
            new Gua(46, "升　", "䷭", 0, 0, 0, 1, 1, 0),
            new Gua(47, "困　", "䷮", 0, 1, 1, 0, 1, 0),
            new Gua(48, "井　", "䷯", 0, 1, 0, 1, 1, 0),
            new Gua(49, "革　", "䷰", 0, 1, 1, 1, 0, 1),
            new Gua(50, "鼎　", "䷱", 1, 0, 1, 1, 1, 0),
            new Gua(51, "震　", "䷲", 0, 0, 1, 0, 0, 1),
            new Gua(52, "艮　", "䷳", 1, 0, 0, 1, 0, 0),
            new Gua(53, "渐　", "䷴", 1, 1, 0, 1, 0, 0),
            new Gua(54, "归妹", "䷵", 0, 0, 1, 0, 1, 1),
            new Gua(55, "丰　", "䷶", 0, 0, 1, 1, 0, 1),
            new Gua(56, "旅　", "䷷", 1, 0, 1, 1, 0, 0),
            new Gua(57, "巽　", "䷸", 1, 1, 0, 1, 1, 0),
            new Gua(58, "兑　", "䷹", 0, 1, 1, 0, 1, 1),
            new Gua(59, "涣　", "䷺", 1, 1, 0, 0, 1, 0),
            new Gua(60, "节　", "䷻", 0, 1, 0, 0, 1, 1),
            new Gua(61, "中孚", "䷼", 1, 1, 0, 0, 1, 1),
            new Gua(62, "小过", "䷽", 0, 0, 1, 1, 0, 0),
            new Gua(63, "既济", "䷾", 0, 1, 0, 1, 0, 1),
            new Gua(64, "未济", "䷿", 1, 0, 1, 0, 1, 0)
    );

    @Override
    public String toString() {
        return format("%s\n%d. %s\t%s", sixYao.toString(), position, name, representation);
    }

    public List<Change> changes() {
        return sixYao.stream()
                .filter(Yao::isChange)
                .map(yao -> new Change(sixYao.indexOf(yao) + 1, yao)).collect(toList());
    }

    public int effectiveYaoPosition() {
        switch (changes().size()) {
            case 1:
                return changes().get(0).position;
            default:
                return 0;
        }
    }

    public String effectiveDeducible() throws IOException, ParseException {
        JSONArray allGua = (JSONArray) new JSONParser().parse(new InputStreamReader(
                Gua.class.getClassLoader().getResourceAsStream("64gua.json")));
        JSONObject gua = (JSONObject) allGua.stream().filter(
                obj -> parseInt(((JSONObject) obj).get("position").toString()) == this.position)
                .findFirst().get();
        JSONArray deducibles = (JSONArray) gua.get("deducibles");
        return valueOf(deducibles.get(effectiveYaoPosition()));
    }
}
