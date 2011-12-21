package ru.hh.school.stdlib;

import org.junit.Assert;
import org.junit.Test;

public class Substitutor3000Test {
  @Test
  public void replacement() {
    Substitutor3000 sbst = new Substitutor3000();
    sbst.put("k1", "one");
    sbst.put("k2", "two");
    sbst.put("keys", "1: ${k1}, 2: ${k2}");
    
    Assert.assertEquals("1: one, 2: two", sbst.get("keys"));
  }

  @Test
  public void emptyReplacement() {
    Substitutor3000 sbst = new Substitutor3000();
    sbst.put("k", "bla-${inexistent}-bla");
    
    Assert.assertEquals("bla--bla", sbst.get("k"));
  }
    @Test
    public void emptyGet() {
        Substitutor3000 sbst = new Substitutor3000();
        Assert.assertEquals("", sbst.get("k"));
    }
    @Test
    public void complex1() {
        Substitutor3000 sbst = new Substitutor3000();
        sbst.put("k1","v1");
        sbst.put("k2","v2");
        sbst.put("k3","a${k1}b${k2}c");
        Assert.assertEquals("av1bv2c", sbst.get("k3"));
    }
    @Test
    public void complex2() {
        Substitutor3000 sbst = new Substitutor3000();
        sbst.put("k1","v1");
        sbst.put("k3","a${k1}b${k2}c");
        Assert.assertEquals("av1bc", sbst.get("k3"));
    }
    @Test
    public void complex3() {
        Substitutor3000 sbst = new Substitutor3000();
        sbst.put("k1","v1");
        sbst.put("k3","a${k1}b${k2}c");
        sbst.put("k1","v3");
        Assert.assertEquals("av3bc", sbst.get("k3"));
    }
}
