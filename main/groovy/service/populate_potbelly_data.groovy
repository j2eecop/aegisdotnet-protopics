/* ===================================================
 * Copyright 2012 AEGIS.net, Inc.
 * 
 * Description:  
 * This Groovy class is a nice example of using SQL
 * DML as well as DDL.
 * =================================================== */
package service

import groovy.sql.Sql
import beans.PotBellyShop

def potbellyshops = []
potbellyshops << new PotBellyShop(street:'1400 Irving Street NW',city:'Washington',state:'DC',visitors:100)
potbellyshops << new PotBellyShop(street:'2301 Georgia Avenue NW',city:'Washington',state:'DC',visitors:150)
potbellyshops << new PotBellyShop(street:'1200 New Hampshire NW',city:'Washington',state:'DC',visitors:200)
potbellyshops << new PotBellyShop(street:'1900 L Street NW',city:'Washington',state:'DC',visitors:250)
potbellyshops << new PotBellyShop(street:'1660 L Street NW',city:'Washington',state:'DC',visitors:300)
potbellyshops << new PotBellyShop(street:'1445 K Street',city:'Washington',state:'DC',visitors:350)
potbellyshops << new PotBellyShop(street:'1701 Pennsylvania Ave NW',city:'Washington',state:'DC',visitors:400)
potbellyshops << new PotBellyShop(street:'1050 K Street NW',city:'Washington',state:'DC',visitors:450)
potbellyshops << new PotBellyShop(street:'555 12th Street NW',city:'Washington',state:'DC',visitors:500)
potbellyshops << new PotBellyShop(street:'50 Massachusetts Avenue',city:'Washington',state:'DC',visitors:550)
potbellyshops << new PotBellyShop(street:'1240 Maryland Ave. SW',city:'Washington',state:'DC',visitors:600)
potbellyshops << new PotBellyShop(street:'470 L\'Enfant Plaza East, SW Promenade Level',city:'Washington',state:'DC',visitors:650)
potbellyshops << new PotBellyShop(street:'409 3rd St SW',city:'Washington',state:'DC',visitors:700)


Sql db = Sql.newInstance(
    url:'jdbc:h2:build/potbelly',driver:'org.h2.Driver')

db.execute "drop table if exists potbelly;"
db.execute '''
    create table potbelly(
        id int not null auto_increment,
        street varchar(100),
        city varchar(100),
        state varchar(10),
        visitors int,
        latitude double,
        longitude double,
        primary key(id)
    );
'''

Geocoder geo = new Geocoder()
potbellyshops.each { s ->
    geo.fillInLatLng s
    sleep 200
    println s
    db.execute """
        insert into potbelly(street,city,state,visitors,latitude,longitude)
        values(${s.street},${s.city},${s.state},${s.visitors},${s.latitude},${s.longitude});
    """
}

assert db.rows('select * from potbelly').size() == potbellyshops.size()
db.eachRow('select latitude,longitude from potbelly') { row ->
  assert row.latitude > 25 && row.latitude < 48
  assert row.longitude > -123 && row.longitude < -71 
}
