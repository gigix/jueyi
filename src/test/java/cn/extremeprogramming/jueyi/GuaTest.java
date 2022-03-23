package cn.extremeprogramming.jueyi;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static cn.extremeprogramming.jueyi.Fixture.*;
import static cn.extremeprogramming.jueyi.Gua.ALL_64_GUA;
import static cn.extremeprogramming.jueyi.Gua.from;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GuaTest {
    @Test
    public void should_create_from_6_yao() {
        Gua qian = from(SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG);
        assertThat(qian.position, is(1));

        Gua kun = from(SHAO_YIN, SHAO_YIN, SHAO_YIN, SHAO_YIN, SHAO_YIN, SHAO_YIN);
        assertThat(kun.position, is(2));
    }

    @Test
    public void should_have_64_distinct_gua() {
        assertThat(ALL_64_GUA.stream().distinct().count(), is(64L));
    }

    @Test
    public void should_know_change_yao() {
        Gua guimei = from(SHAO_YANG, SHAO_YANG, SHAO_YIN, LAO_YANG, SHAO_YIN, SHAO_YIN);
        assertThat(guimei.changes().size(), is(1));
        assertThat(guimei.changes().get(0).position, is(4));
        assertThat(guimei.changes().get(0).yao, is(LAO_YANG));
    }

    @Test
    public void should_know_effective_yao() throws Exception {
        Gua qian = from(SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG);
        assertThat(qian.effectiveYaoPosition(), is(0));

        Gua qian1 = from(LAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG);
        assertThat(qian1.effectiveYaoPosition(), is(1));
        assertThat(qian1.effectiveDeducible(), is("初九：潜龙勿用。"));
        // TODO: finish other variations here
    }
}
