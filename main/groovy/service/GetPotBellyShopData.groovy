/* ===================================================
 * Copyright 2012 AEGIS.net, Inc.
 * 
 * Description:  
 * This Groovy class is responsible for providing data
 * (address, latitude, longitute, number of visitors)
 * for all Potbelly shops in Washington DC, USA.
 * =================================================== */
package service;

import java.util.logging.Logger;

import beans.PotBellyShop;

class GetPotBellyShopData {

    def potbellyShopMap = [:]
    
    private static Logger logger = Logger.getLogger(GetPotBellyShopData.class.name)


    def fillInPotBellyShopMap() {
        def db = groovy.sql.Sql.newInstance(
            'jdbc:h2:build/potbelly',
            'org.h2.Driver'
        )
        logger.info "Value of db after newInstance is $db"
        db.eachRow("select * from potbelly") { row ->
            def id = row.id
            potbellyShopMap[id] =
              new PotBellyShop(street:row.street,
                               city:row.city,
                               state:row.state,
                               visitors:row.visitors,
                               latitude:row.latitude,
                               longitude:row.longitude)
        }
        db.close()
    }

    def getPotBellyShops() {
        if (potbellyShopMap.size() == 0) fillInPotBellyShopMap()
        def potbellyshopResults = []
        println "GetPotBellyShopData: Returning potbelly shops..."
        for (v in potbellyShopMap.values() ) {
          println "GetPotBellyShopData: $v"
          potbellyshopResults << v
        }
        return potbellyshopResults
    }
}
