/* ===================================================
 * Copyright 2012 AEGIS.net, Inc.
 * 
 * Description:  
 * This Groovy class is responsible for interfacing
 * with Google Maps services.
 * =================================================== */
package service

import beans.PotBellyShop;

class Geocoder {
    String base = 'http://maps.google.com/maps/api/geocode/xml?'

    void fillInLatLng(PotBellyShop potbellyshop) {
        String urlEncodedAddress = 
            [potbellyshop.street, potbellyshop.city, potbellyshop.state].collect { 
                URLEncoder.encode(it,'UTF-8')
            }.join(',+') 
        String url = base + [sensor:false,
            address: urlEncodedAddress].collect {k,v -> "$k=$v"}.join('&')
        println url
        def response = new XmlSlurper().parse(url)
        String latitude = response.result.geometry.location.lat[0] ?: "0.0"
        String longitude = response.result.geometry.location.lng[0] ?: "0.0"
        potbellyshop.latitude = latitude.toDouble()
        potbellyshop.longitude = longitude.toDouble()
    }
}
