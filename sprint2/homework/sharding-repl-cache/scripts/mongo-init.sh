#!/bin/bash

###
# Включаем шардирование и добавляем данные в бд
###

docker-compose exec -T mongodb-router1 mongosh --port 27017 --quiet <<EOF

sh.enableSharding("somedb");
sh.shardCollection("somedb.helloDoc", { "name" : "hashed" } );

use somedb;

for(var i = 0; i < 1000; i++) db.helloDoc.insertOne({age:i, name:"ly"+i});

db.helloDoc.countDocuments();
exit();

EOF