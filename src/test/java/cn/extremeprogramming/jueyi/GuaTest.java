package cn.extremeprogramming.jueyi;

import org.junit.Test;

import static cn.extremeprogramming.jueyi.Fixture.*;
import static cn.extremeprogramming.jueyi.Gua.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GuaTest {
    @Test
    public void should_create_from_6_yao() {
        Gua qian = from(SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG);
        assertThat(qian.position, is(1));

        Gua kun = from(SHAO_YIN, SHAO_YIN, SHAO_YIN, SHAO_YIN, SHAO_YIN, SHAO_YIN);
        assertThat(kun.position, is(2));

        Gua guimei = from(SHAO_YANG, SHAO_YANG, SHAO_YIN, LAO_YANG, SHAO_YIN, SHAO_YIN);
        assertThat(guimei.name, is("归妹"));
        assertThat(guimei.position, is(54));
    }

    @Test
    public void should_have_64_distinct_gua() {
        assertThat(ALL_64_GUA.stream().distinct().count(), is(64L));
        assertThat(ALL_64_GUA.stream().map(g -> g.lines).distinct().count(), is(64L));
    }

    @Test
    public void should_know_change_yao() {
        Gua guimei = from(SHAO_YANG, SHAO_YANG, SHAO_YIN, LAO_YANG, SHAO_YIN, SHAO_YIN);
        assertThat(guimei.changes().size(), is(1));
        assertThat(guimei.changes().get(0).position(), is(4));
        assertThat(guimei.changes().get(0), is(LAO_YANG));
    }

    @Test
    public void should_know_effective_yao() throws Exception {
        Gua qian = from(SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG);
        assertThat(qian.effectiveYaoPosition(), is(0));

        Gua qian1 = from(LAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG, SHAO_YANG);
        assertThat(qian1.effectiveYaoPosition(), is(1));
        assertThat(qian1.effectiveDeducible(), is("初九：潜龙勿用。"));

        // ䷵
        Gua guimei2 = from(SHAO_YANG, SHAO_YANG, LAO_YIN, LAO_YANG, SHAO_YIN, SHAO_YIN);
        assertThat(guimei2.changes().size(), is(2));
        assertThat(guimei2.effectiveYaoPosition(), is(3));

        // ䷷
        Gua lv2 = from(SHAO_YIN, SHAO_YIN, SHAO_YANG, LAO_YANG, LAO_YIN, SHAO_YANG);
        assertThat(lv2.effectiveYaoPosition(), is(5));

        Gua guimei3 = from(SHAO_YANG, SHAO_YANG, LAO_YIN, LAO_YANG, LAO_YIN, SHAO_YIN);
        assertThat(guimei3.effectiveYaoPosition(), is(4));

        Gua guimei4 = from(SHAO_YANG, LAO_YANG, LAO_YIN, LAO_YANG, LAO_YIN, SHAO_YIN);
        assertThat(guimei4.effectiveYaoPosition(), is(1));

        Gua guimei5 = from(LAO_YANG, LAO_YANG, LAO_YIN, LAO_YANG, SHAO_YIN, LAO_YIN);
        assertThat(guimei5.effectiveYaoPosition(), is(5));
    }

    @Test
    public void should_deal_with_6_change_yao() {
        Gua qian = from(LAO_YANG, LAO_YANG, LAO_YANG, LAO_YANG, LAO_YANG, LAO_YANG);
        assertThat(qian.effectiveYaoPosition(), is(7));
        assertThat(qian.effectiveDeducible(), is("用九：见群龙无首，吉。"));

        Gua kun = from(LAO_YIN, LAO_YIN, LAO_YIN, LAO_YIN, LAO_YIN, LAO_YIN);
        assertThat(kun.effectiveYaoPosition(), is(7));
        assertThat(kun.effectiveDeducible(), is("用六：利永贞。"));

        // ䷋ 否卦：否极泰来
        Gua pi = from(LAO_YIN, LAO_YIN, LAO_YIN, LAO_YANG, LAO_YANG, LAO_YANG);
        assertThat(pi.effectiveYaoPosition(), is(7));
        assertThat(pi.effectiveDeducible(), is("小往大来，吉亨。"));
    }
}
