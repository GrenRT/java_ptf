package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by gren on 28.11.2016.
 */
public class PointTest {

  @Test
  public void testDistance() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(10, 0);
    Assert.assertEquals(p1.distance(p2), 10.0);
 }
 @Test
  public void testDistance2() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, -10);
    Assert.assertEquals(p1.distance(p2), 10.0);
  }
  @Test
  public void testDistance3() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(-3, 10);
    Assert.assertEquals(p1.distance(p2), Math.sqrt(109));
  }
}
