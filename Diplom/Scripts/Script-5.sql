SELECT cast(p.time as date) from posts p where date_part('year', cast (p.time as date)) = 2020 ;
