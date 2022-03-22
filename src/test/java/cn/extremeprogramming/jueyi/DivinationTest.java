package cn.extremeprogramming.jueyi;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DivinationTest {
    @Test
    public void should_generate_6_random_yao_to_get_a_gua() {
        Divination divination = new Divination("A trivial question");
        List<Yao> sixYao = divination.sixYao();
        assertThat(sixYao.size(), is(6));

        Gua gua = divination.gua();
        System.out.println(sixYao);
        System.out.println(gua);
    }
}
