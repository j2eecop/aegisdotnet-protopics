/* ===================================================
 * Copyright 2012 AEGIS.net, Inc.
 * 
 * Description:  
 * This Groovy class is a JUnit test that tests methods 
 * in the Groovy class by name GetPotBellyShopData.
 * =================================================== */
package service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import beans.PotBellyShop;

class GetPotBellyShopDataTest {
  GetPotBellyShopData gpbsd
  
  @Before
  public void setUp() throws Exception {
    gpbsd = new GetPotBellyShopData()
  }

  @Test
  public void testFillInPotBellyShopMap() {
    assertEquals 0, gpbsd.potbellyShopMap.size()
    gpbsd.fillInPotBellyShopMap()
    def potbellyshops = gpbsd.potbellyShopMap.values()
    potbellyshops.each { PotBellyShop potbellyshop ->
      assertTrue potbellyshop.latitude > 25 && potbellyshop.latitude < 48
      assertTrue potbellyshop.longitude > -123 && potbellyshop.longitude < -71
    }
  }

  @Test
  public void testGetPotBellyShops() {
    def potbellyshopsResults = gpbsd.getPotBellyShops()
    assertEquals 13, potbellyshopsResults.size()
  }

}
