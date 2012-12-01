/* ===================================================
 * Copyright 2012 AEGIS.net, Inc.
 * 
 * Description:  
 * This Groovy class is a good example of how easily
 * one may parse the XML data file -- line #19.
 * Given a url, the response is already received with
 * as simple code as 'toURL().text'.
 * =================================================== */
package service
import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GameServiceIntegrationTests {
  @Test
  void testPotBellyService() {
    String response = 
      "http://localhost:8080/potbelly_washingtondc/PotBellyService.groovy".toURL().text
    def potbellyshops = new XmlSlurper().parseText(response)
    assert 13 == potbellyshops.potbellyshop.size()
    assert potbellyshops.potbellyshop[0].@visitors.toString().contains('100')
    assert potbellyshops.potbellyshop[4].@visitors.toString().contains('300')
  }
}