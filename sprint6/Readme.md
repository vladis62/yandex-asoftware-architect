# Задание 1. Проектирование технологической архитектуры

## Определение стратегии масштабирования и отказоустойчивости

1. Для отказоусточивости и надежности и высокой доступности, нам нужна стратегия Active-Active стратегия с GSLB (Global Server Load Balancer)
2. Каждый кластер будет независимый, без сетевого соединения между другими кластерами. У каждого кластера будет изоляция и независимость
3. Шардирование на раннем этапе не требуется

# Задание 2. 

1. 
```bash
minikube start
```
2. 
```bash
minikube addons enable metrics-server
```
3. добавляем манифест deployment из Exc2: 
```bash
kubectl apply -f deployment.yaml
```
4. добавляем манифест service из Exc2:  
```bash 
kubectl apply -f service.yaml
```
5. Получаем урл нашего контейнера. Ждем запуск.
```bash 
minikube service scaletestapp-service --url
```
Ждем запуск, готовность пода можно проверить следующей командой:
```bash 
kubectl get pods
```
6. добавляем hpa service из Exc2:
```bash 
kubectl apply -f hpa.yaml
```
7. установка locust.py:
```bash 
pip install locust
```
8. запускаем locust:
```bash
locust
```
и открываем http://localhost:8089
9. minikube dashboard
10. дропаем кластер:
```bash
minikube delete --profile minikube
```

# Задание 3. Переход на Event-Driven архитектуру

##  Анализ текущей архитектуры

1. Отсутствие кеширование
2. Масштабируемость монолита
3. Деградация API при превышении лимита запросов
4. Нет мониторинга и алертов во время сбоя, что приводит к долгим простоям

## Event-driven архитектура

1. ins-product-aggregator: с ростом количества страховых компаний,
с которым агрегирует сервис, запрос на получение данных из страховой компании будет увеличиваться.
Добавить Scheduller в ins-product-aggregator, который будет собирать информацию со страховых компаний
и отправлять по кафке данные в core-app.
2. В core-app добавить producer, который будет отсылать в consumer ins-comp-settlement оформленную страховку.
Реализовать через паттерн transactional outbox для надежной отправки и отсутствия дублирования.
