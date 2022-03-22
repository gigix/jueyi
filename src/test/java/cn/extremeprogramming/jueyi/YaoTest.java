package cn.extremeprogramming.jueyi;

import org.junit.Test;

import static cn.extremeprogramming.jueyi.Fixture.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class YaoTest {

    @Test
    public void should_know_yin_yang() {
        assertThat(SHAO_YANG.isYang(), is(true));
        assertThat(SHAO_YIN.isYang(), is(false));
        assertThat(LAO_YANG.isYang(), is(true));
        assertThat(LAO_YIN.isYang(), is(false));
    }

    @Test
    public void should_know_lao_shao() {
        assertThat(SHAO_YANG.isChange(), is(false));
        assertThat(SHAO_YIN.isChange(), is(false));
        assertThat(LAO_YANG.isChange(), is(true));
        assertThat(LAO_YIN.isChange(), is(true));
    }
    
    @Test
    public void should_have_text_representation() {
        assertThat(SHAO_YANG.toString(), is("⚊"));
        assertThat(SHAO_YIN.toString(), is("⚋"));
        assertThat(LAO_YANG.toString(), is("⚊o"));
        assertThat(LAO_YIN.toString(), is("⚋×"));
    }
}
