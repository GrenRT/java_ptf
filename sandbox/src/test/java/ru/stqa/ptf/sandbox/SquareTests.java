package ru.stqa.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Square;

/**
 * Created by gren on 28.11.2016.
 */
public class SquareTests {

  @Test
    public void testArea() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);

  }
}
