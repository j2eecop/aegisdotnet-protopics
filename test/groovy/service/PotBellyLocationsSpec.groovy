/* ===================================================
 * Copyright 2012 AEGIS.net, Inc.
 * 
 * Description:  
 * This Groovy class is a good example of using the
 * Spock testing framework.  A good starting point to 
 * learn more about Spock will be:
 * http://groovy.dzone.com/news/introducing-spock-testing-and
 * =================================================== */
package service

import spock.lang.Shared;
import spock.lang.Specification;
import spock.lang.Unroll;
import groovy.sql.Sql

class PotBellyLocationsSpec extends Specification {
    @Shared Sql db
    
    def setupSpec() {
        db = Sql.newInstance(
            'jdbc:h2:build/potbelly', 
            'org.h2.Driver')
    }
    
    @Unroll
    def "#name: #lat and #lng in range"() {
        expect:
        lat > 25 && lat < 48
        lng > -123 && lng < -71
        
        where:
        [name,lat,lng] << db.rows('select visitors,latitude,longitude from potbelly')
    }
}
