package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by gren on 04.12.2016.
 */
public class EqutionTest {

  @Test
  public void test0() {
    Eqution e = new Eqution(1,1,1);
    Assert.assertEquals(e.rootNumber(), 0);
  }

  @Test
  public void test1() {
    Eqution e = new Eqution(1,2,1);
    Assert.assertEquals(e.rootNumber(), 1);
  }

  @Test
  public void test2() {
    Eqution e = new Eqution(1,5,6);
    Assert.assertEquals(e.rootNumber(), 2);
  }
}
