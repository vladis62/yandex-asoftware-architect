#!/bin/bash

###
#Настройка конфигурационного сервера
###

docker compose exec -T configSrv mongosh --port 27017 --quiet <<EOF

rs.initiate({ _id : "config_server", configsvr: true, members: [{ _id : 0, host : "configSrv:27017" }]});
exit();
EOF

docker compose exec -T mongos_router mongosh --port 27020 --quiet <<EOF;

sh.addShard( "rs0/mongodb-shard1-1:27011");
sh.addShard( "rs1/mongodb-shard2-1:27021");

sh.enableSharding("somedb");
sh.shardCollection("somedb.helloDoc", { "name" : "hashed" } );
exit()
EOF