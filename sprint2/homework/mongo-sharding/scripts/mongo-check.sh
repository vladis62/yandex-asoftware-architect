#!/bin/bash

###
#Проверяем на всех репликах данные
###

docker compose exec -T mongodb-shard1-1 mongosh --port 27011 --quiet <<EOF

use somedb;
db.helloDoc.countDocuments();
exit();
EOF

docker compose exec -T mongodb-shard2-1 mongosh --port 27021 --quiet <<EOF

use somedb;
db.helloDoc.countDocuments();
exit();
EOF
