#!/bin/bash

###
#Проверяем на всех репликах данные
###

docker compose exec -T mongodb-shard1-1 mongosh --port 27011 --quiet <<EOF

use somedb;
db.helloDoc.countDocuments();
exit();
EOF

docker compose exec -T mongodb-shard1-2 mongosh --port 27012 --quiet <<EOF

use somedb;
db.helloDoc.countDocuments();
exit();
EOF

docker compose exec -T mongodb-shard1-3 mongosh --port 27013 --quiet <<EOF

use somedb;
db.helloDoc.countDocuments();
exit();
EOF

docker compose exec -T mongodb-shard2-1 mongosh --port 27021 --quiet <<EOF

use somedb;
db.helloDoc.countDocuments();
exit();
EOF

docker compose exec -T mongodb-shard2-2 mongosh --port 27022 --quiet <<EOF

use somedb;
db.helloDoc.countDocuments();
exit();
EOF

docker compose exec -T mongodb-shard2-3 mongosh --port 27023 --quiet  <<EOF

use somedb;
db.helloDoc.countDocuments();
exit();
EOF
