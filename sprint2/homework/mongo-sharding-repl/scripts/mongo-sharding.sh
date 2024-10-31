#!/bin/bash

###
#Настройка конфигурационного сервера
###

docker compose exec -T configSrv mongosh --port 27017 --quiet <<EOF

rs.initiate({ _id : "config_server", configsvr: true, members: [{ _id : 0, host : "configSrv:27017" }]});
exit();
EOF

###
# Репликация для шарда 1
###

docker compose exec -T mongodb-shard1-1 mongosh --port 27011 --quiet <<EOF
rs.initiate({_id: "rs0", members: [{_id: 0, host: "mongodb-shard1-1:27011"}, {_id: 1, host: "mongodb-shard1-2:27012"}, {_id: 2, host: "mongodb-shard1-3:27013"}]});
exit();
EOF

###
# Репликация для шарда 2
###
docker compose exec -T mongodb-shard2-1 mongosh --port 27021 --quiet <<EOF
rs.initiate({_id: "rs1", members: [{_id: 0, host: "mongodb-shard2-1:27021"}, {_id: 1, host: "mongodb-shard2-2:27022"}, {_id: 2, host: "mongodb-shard2-3:27023"}]});
exit();
EOF

###
#Создаем шарды
###

docker compose exec -T mongos_router mongosh --port 27020 --quiet <<EOF;

sh.addShard( "rs0/mongodb-shard1-1:27011");
sh.addShard( "rs1/mongodb-shard2-1:27021");

sh.enableSharding("somedb");
sh.shardCollection("somedb.helloDoc", { "name" : "hashed" } );
exit()
EOF