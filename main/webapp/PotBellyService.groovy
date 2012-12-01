/* ===================================================
 * Copyright 2012 AEGIS.net, Inc.
 * 
 * Description:  
 * This Groovy class is a good example of what core
 * Groovy code would look like.  Notice the various
 * cool and convenient syntax in this code -- line 14
 * does "not" use the get method, the .each operator,
 * the implicit 's' looping variable with .each -- all
 * populating an html element with various attribute
 * values; that are subsequently accessed by the 
 * Javascript method 'addMarker()' in index.jsp (line 93)
 * Here, feel the power of Groovy! :)
 * =================================================== */
import beans.PotBellyShop;
import service.GetPotBellyShopData;

response.contentType = 'text/xml'

results = new GetPotBellyShopData().potBellyShops

html.potbellyshops {
    results.each { s ->
        potbellyshop(
            outcome:"$s.street,$s.city,$s.state",
            visitors:"$s.visitors",
            lat:s.latitude,
            lng:s.longitude
        )
    }
}
