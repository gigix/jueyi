package cn.extremeprogramming.jueyi;

import org.junit.Test;

import static cn.extremeprogramming.jueyi.Fixture.SHAO_YANG;
import static cn.extremeprogramming.jueyi.Fixture.SHAO_YIN;
import static cn.extremeprogramming.jueyi.Gua.ALL_64_GUA;
import static cn.extremeprogramming.jueyi.Gua.from;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GuaTest {
    @Test
    public void should_create_from_6_yao() {
        Gua qian = from(SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG);
        assertThat(qian.index, is(1));

        Gua kun = from(SHAO_YIN, SHAO_YIN, SHAO_YIN, SHAO_YIN, SHAO_YIN, SHAO_YIN);
        assertThat(kun.index, is(2));
    }

    @Test
    public void should_have_64_distinct_gua() {
        assertThat(ALL_64_GUA.stream().distinct().count(), is(64L));
    }
}