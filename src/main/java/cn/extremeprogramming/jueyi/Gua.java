package cn.extremeprogramming.jueyi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.util.List;

import static cn.extremeprogramming.jueyi.Yi.YANG;
import static cn.extremeprogramming.jueyi.Yi.YIN;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Gua {
    public static final List<Gua> ALL_64_GUA = loadAllGuaFromFile();

    public Gua(JSONObject jsonObject) {
        position = parseInt(jsonObject.get("position").toString());
        name = jsonObject.get("name").toString();
        symbol = jsonObject.get("symbol").toString();
        deducibles = (List<String>) ((JSONArray) jsonObject.get("deducibles")).stream().collect(toList());
        lines = jsonObject.get("lines").toString().chars().mapToObj(c -> c == '1' ? YANG : YIN).collect(toList());
    }

    private static List<Gua> loadAllGuaFromFile() {
        JSONArray allGua;
        try {
            allGua = (JSONArray) new JSONParser().parse(new InputStreamReader(
                    Gua.class.getClassLoader().getResourceAsStream("64gua.json")));
        } catch (Exception e) {
            throw new RuntimeException("Reading 64gua.json file with problem - ", e);
        }
        return (List<Gua>) allGua.stream().map(o -> new Gua((JSONObject) o)).collect(toList());
    }

    public final int position;
    public final String name;
    public final String symbol;
    public final List<Yi> lines;
    public final List<String> deducibles;

    private List<Yao> sixYao;

    public static Gua from(Yao... sixYao) {
        return from(asList(sixYao));
    }

    private static Gua from(List<Yao> sixYao) {
        Gua gua = ALL_64_GUA.stream().filter(g -> g.matches(sixYao)).findFirst().get();
        gua.sixYao = sixYao;
        gua.sixYao.forEach(yao -> yao.setPosition(gua.sixYao.indexOf(yao) + 1));
        return gua;
    }

    private boolean matches(List<Yao> yaos) {
        return yaos.stream().map(Yao::isYang).collect(toList()).equals(
                lines.stream().map(Yi::isYang).collect(toList())
        );
    }

    @Override
    public String toString() {
        return format("%s\n%d. %s\t%s\n断辞：%s",
                sixYao.toString(), position, name, symbol, effectiveDeducible());
    }

    public List<Yao> changes() {
        return sixYao.stream().filter(Yao::isChange).collect(toList());
    }

    private List<Yao> stables() {
        return sixYao.stream().filter(Yao::isStable).collect(toList());
    }

    public int effectiveYaoPosition() {
        switch (changes().size()) {
            case 1:
                return changes().get(0).position();
            case 2: {
                Yao below = changes().get(0);
                Yao above = changes().get(1);
                if ((!below.isYang()) && above.isYang()) {
                    return below.position();
                } else {
                    return above.position();
                }
            }
            case 3:
                return changes().get(1).position();
            case 4:
            case 5:
                return stables().get(0).position();
            case 6:
                return 7;
            default:
                return 0;
        }
    }

    public String effectiveDeducible() {
        if (effectiveYaoPosition() == 7 && position > 2) {
            Gua changeGua = getChangeGua();
            return changeGua.deducibles.get(0);
        }
        return deducibles.get(effectiveYaoPosition());
    }

    private Gua getChangeGua() {
        return from(sixYao.stream().map(Yao::reverse).collect(toList()));
    }
}
