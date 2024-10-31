Финальная версия находится в папке sharding-repl-cache

Запускаем docker-compose

```shell
docker-compose up -d

```

Настраиваем репликацию и шардирование

```shell
docker-compose exec mongodb-configsvr sh -c "mongosh < /scripts/init-config.js"

docker-compose exec mongodb-shard1-node-a sh -c "mongosh < /scripts/init-shard1.js"
docker-compose exec mongodb-shard2-node-a sh -c "mongosh < /scripts/init-shard2.js"

docker-compose exec mongodb-router1 sh -c "mongosh < /scripts/init-router.js"
```

Заполняем mongodb данными

```shell
./scripts/mongo-init.sh
```

Откройте в браузере http://localhost:8080

Проверяем скорость работы для коллекций: http://localhost:8080/docs#/default/list_users__collection_name__users_get

Очищаем собранный кластер:

```shell
docker-compose rm
```

Удаляем образы

```shell
docker-compose down -v --rmi all --remove-orphans
```

