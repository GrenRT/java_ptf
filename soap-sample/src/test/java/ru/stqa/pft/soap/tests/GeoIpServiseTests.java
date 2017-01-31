package ru.stqa.pft.soap.tests;

        import net.webservicex.GeoIP;
        import net.webservicex.GeoIPService;
        import org.testng.Assert;
        import org.testng.annotations.Test;

        import static org.testng.Assert.assertEquals;

/**
 * Created by gren on 31.01.2017.
 */
public class GeoIpServiseTests {

  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("46.0.13.151");
    assertEquals(geoIP.getCountryCode(), "RUS");
  }
}
