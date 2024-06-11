CREATE TABLE routes (
    route_id varchar(20) PRIMARY KEY,
    agency_id integer,
    route_short_name text,
    route_long_name text,
    route_desc text,
    route_type integer,
    route_url varchar,
    route_color char(6),
    route_text_color char(6)
);

CREATE TABLE stops (
    stop_id varchar(30) PRIMARY KEY,
    stop_name text,
    stop_desc text,
    stop_lat double precision,
    stop_lon double precision,
    zone_id integer,
    stop_url varchar,
    location_type integer,
    parent_station varchar(30) references stops,
    wheelchair_boarding integer
);

CREATE TABLE trips (
    route_id varchar(20) references routes,
    service_id varchar(5),
    trip_id varchar(17) primary key,
    trip_headsign varchar,
    trip_short_name varchar,
    direction_id integer,
    block_id integer,
    wheelchair_accessible integer,
    bikes_allowed integer,
    trip_desc text,
    shape_id integer
);

CREATE TABLE stop_times (
    trip_id varchar(17) references trips,
    arrival_time char(8), --ne peut pas etre time a cause de valeurs out-of-range (24:04:00)
    departure_time char(8),
    stop_id varchar(30) references stops not null,
    stop_sequence integer,
    stop_headsign varchar,
    pickup_type integer,
    drop_off_type integer,
    PRIMARY KEY (trip_id, stop_sequence)
);





\copy routes from 'routes.txt' WITH (FORMAT CSV, HEADER);
\copy stops from 'stops.txt' WITH (FORMAT CSV, HEADER);
\copy trips from 'trips.txt' WITH (FORMAT CSV, HEADER);
\copy stop_times from 'stop_times.txt' WITH (FORMAT CSV, HEADER);



\! echo requete 1 
select stop_id from stops where parent_station is null and location_type <> 1;

select stop_id from stops where location_type =1 and stop_id not like 'StopArea%';

select stop_id from stops where stop_id like 'StopArea%' and parent_station is not null;


\! echo requete 2
select stop_times.stop_id 
from stop_times join stops on (stop_times.stop_id = stops.stop_id)  
where parent_station is null;

\! echo requete 3
select stop_id from stops where parent_station is not null;

\! echo requete 4 
select stop_id from stops where parent_station is null;


\! echo requete 5
CREATE INDEX stops_stop_name_idx ON stops USING btree (stop_name);
CREATE INDEX stops_parent_station_idx ON stops USING btree (parent_station); 
-- evitent les jointures couteuses

select s.stop_id,s.stop_name 
from stops s, stops s0 
where s0.stop_name='GARE DE BAGNEUX' and
s.parent_station=s0.stop_id 
order by s.stop_name;



\! echo requete 6
CREATE INDEX stop_times_stop_id_idx ON stop_times USING btree (stop_id);
-- pour aider la jointure entre s et t 
CREATE INDEX trips_route_id_idx ON trips USING btree (route_id);
-- pour aider la jointure entre u et r

select distinct r.route_long_name 
from stops s0, stops s, stop_times t,  trips u, routes r
where s0.stop_name='GARE DE BAGNEUX' and s.parent_station=s0.stop_id 
and s.stop_id=t.stop_id and
u.trip_id=t.trip_id
and r.route_id=u.route_id  
order by route_long_name;

\! echo requete 7
CREATE INDEX stop_times_trip_id_idx ON stop_times USING btree (trip_id);
--pour aider le self join entre t1 et t2

select distinct s2.stop_name, r2.route_long_name 
from stops s0, stops s1, stop_times t1, stop_times t2,  stops s2, trips u2, routes r2
where s0.stop_name='GARE DE BAGNEUX' and s1.parent_station=s0.stop_id and
s1.stop_id=t1.stop_id and
t1.trip_id=t2.trip_id and s2.stop_id=t2.stop_id 
and t1.stop_sequence<t2.stop_sequence
and u2.trip_id=t2.trip_id
and r2.route_id=u2.route_id  
order by route_long_name, s2.stop_name;


\! echo requete 8
select distinct s2.stop_name, r2.route_long_name 
from stops s0, stops s1, stop_times t1, stop_times t2,  stops s2, trips u2, routes r2
where s0.stop_name='GARE DE BAGNEUX' and s1.parent_station=s0.stop_id and
s1.stop_id=t1.stop_id and
t1.trip_id=t2.trip_id and s2.stop_id=t2.stop_id 
and t1.stop_sequence<t2.stop_sequence
and u2.trip_id=t2.trip_id
and r2.route_id=u2.route_id  
and route_type = 2
order by route_long_name, s2.stop_name;


\! echo requete 9

-- StopArea:8775868 = GARE DE BAGNEUX
WITH RECURSIVE accessible(stop_area,n_trains) AS (
VALUES('StopArea:8775868',0)
UNION 
SELECT s2.parent_station, n_trains + 1 
FROM accessible, stops s1, stops s2,
stop_times t1, stop_times t2, trips u2, routes r2 
where
s1.parent_station=accessible.stop_area and
s1.stop_id=t1.stop_id and t1.trip_id=t2.trip_id and s2.stop_id=t2.stop_id
and t1.stop_sequence < t2.stop_sequence
and u2.trip_id=t2.trip_id and r2.route_id=u2.route_id and route_type=2 
and  n_trains < 2 
) 
SELECT stop_name, n_trains
from accessible,stops WHERE accessible.stop_area=stops.stop_id
ORDER BY n_trains ASC, stop_name;


\! echo requete 10
WITH RECURSIVE accessible(stop_area,n_trains) AS (
VALUES('StopArea:8775868',0)
UNION 
SELECT s2.parent_station, n_trains + 1 
FROM accessible, stops s1, stops s2,
stop_times t1, stop_times t2, trips u2, routes r2 
where
s1.parent_station=accessible.stop_area and
s1.stop_id=t1.stop_id and t1.trip_id=t2.trip_id and s2.stop_id=t2.stop_id
and t1.stop_sequence < t2.stop_sequence
and u2.trip_id=t2.trip_id and r2.route_id=u2.route_id and route_type=2 
and  n_trains < 2 
) 
SELECT stop_name, min(n_trains) as n
from accessible,stops WHERE accessible.stop_area=stops.stop_id
group by stop_area, stop_name
ORDER BY n ASC, stop_name;



\! echo requete 11

WITH RECURSIVE accessible(stop_area,n_trains, seq) AS (
VALUES('StopArea:8775868',0, '')
UNION 
SELECT s2.parent_station, n_trains + 1, seq || case seq when '' then '' else ' ' end || route_long_name
FROM accessible, stops s1, stops s2,
stop_times t1, stop_times t2, trips u2, routes r2 
where
s1.parent_station=accessible.stop_area and
s1.stop_id=t1.stop_id and t1.trip_id=t2.trip_id and s2.stop_id=t2.stop_id
and t1.stop_sequence < t2.stop_sequence
and u2.trip_id=t2.trip_id and r2.route_id=u2.route_id and route_type=2 
and  n_trains < 2 
) 
SELECT stop_name, seq
from accessible A,stops 
WHERE A.stop_area=stops.stop_id
and n_trains = (select min(n_trains) from accessible where stop_area = A.stop_area)
ORDER BY seq, stop_name;

\! echo requete 12

WITH RECURSIVE accessible(stop_area,n_trains, seq) AS (
VALUES('StopArea:8775868',0, '')
UNION 
SELECT s2.parent_station, n_trains + 1, seq || case seq when '' then '' else ' ' end || route_long_name
FROM accessible, stops s1, stops s2,
stop_times t1, stop_times t2, trips u2, routes r2 
where
s1.parent_station=accessible.stop_area and
s1.stop_id=t1.stop_id and t1.trip_id=t2.trip_id and s2.stop_id=t2.stop_id
and t1.stop_sequence < t2.stop_sequence
and u2.trip_id=t2.trip_id and r2.route_id=u2.route_id and route_type=2 
and  n_trains < 2 
) 
SELECT stop_name, array_agg(seq)
from accessible A,stops 
WHERE A.stop_area=stops.stop_id
and n_trains = (select min(n_trains) from accessible where stop_area = A.stop_area)
group by stop_area, stop_name
ORDER BY stop_name;



\! echo requete 13

CREATE INDEX routes_route_type_idx ON routes USING btree (route_type); -- amelioration moderée

WITH RECURSIVE accessible(stop_area,distance) AS (
VALUES('StopArea:8775868',0)
UNION 
SELECT s2.parent_station, distance + t2.stop_sequence-t1.stop_sequence 
FROM accessible, stops s1, stops s2, stop_times t1, stop_times t2, trips u2, routes r2
where
s1.stop_id=t1.stop_id and t1.trip_id=t2.trip_id and s2.stop_id=t2.stop_id
and s1.parent_station=stop_area 
and t1.stop_sequence < t2.stop_sequence
and u2.trip_id=t2.trip_id and r2.route_id=u2.route_id 
and (route_type = 0 or route_type = 1 or route_type = 2 or route_type = 7)
and distance + t2.stop_sequence-t1.stop_sequence <= 3 ) 
SELECT
stop_name,min(distance) 
from accessible,stops 
WHERE accessible.stop_area=stops.stop_id
GROUP BY stop_id, stop_name 
ORDER BY min(distance) ASC, stop_name;

