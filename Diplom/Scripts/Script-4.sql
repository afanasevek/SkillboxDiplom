select * from posts p join ta where p.user_id = (select u.id from users u);
