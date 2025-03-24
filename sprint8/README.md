## Задание спринт 8

Запускаем контейнеры:

```bash

docker-compose up -d --build

```

Отправляем запрос без токена:

```
curl 'http://localhost:8000/reports' \
  -H 'Accept: */*' \
  -H 'Accept-Language: ru-RU,ru;q=0.9' \
  -H 'Connection: keep-alive' \
  -H 'DNT: 1' \
  -H 'Origin: http://localhost:3000' \
  -H 'Referer: http://localhost:3000/' \
  -H 'Sec-Fetch-Dest: empty' \
  -H 'Sec-Fetch-Mode: cors' \
  -H 'Sec-Fetch-Site: same-site' \
  -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36' \
  -H 'sec-ch-ua: "Chromium";v="134", "Not:A-Brand";v="24", "Google Chrome";v="134"' \
  -H 'sec-ch-ua-mobile: ?0' \
  -H 'sec-ch-ua-platform: "macOS"'
```

Получаем 401

Переходим на UI:

```
http://localhost:3000
```

Логинимся и вводим учетные данные:

```
user1
password123
```

Получаем 403

Перелогинимся через через режим инкогнито в браузере, вводим:

```
prothetic1
prothetic123
```

Получаем 200.
