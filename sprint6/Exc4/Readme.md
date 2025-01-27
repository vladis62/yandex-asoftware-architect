# Задание 4. Проектирование продажи ОСАГО

Потребуется база данных для следующих кейсов:
1. Сохранения статусов заявок (создана, в обработке, завершена).
2. Хранения временных данных для долгоживущих процессов, так как, ожидание ответа до 60 секунд весьма долго для ожидания клиента

Данные передаем по kafka когда будет готово предложение.
Данные, которые отправляем в osago-aggregator
```json
{
  "event_type": "new_osago_application",
  "application_id": "123456",
  "user_id": "user_789",
  "car_info": {
    "make": "Toyota",
    "model": "Camry",
    "year": 2020,
    "license_plate": "ABC123"
  },
  "client_info": {
    "name": "John Doe",
    "contact": "john.doe@example.com"
  },
  "timestamp": "2025-01-27T10:00:00Z"
}
```
Данные, которые отправляем в core-app
```json
{
  "event_type": "insurance_offer_received",
  "application_id": "123456",
  "insurance_company_id": "company_a",
  "offer_details": {
    "premium": 5000,
    "coverage": "Full",
    "validity_period": "1 year"
  },
  "offer_status": "received",
  "timestamp": "2025-01-27T10:05:00Z"
}
```
финальное сообщение:
```json
{
  "event_type": "application_processing_completed",
  "application_id": "123456",
  "status": "completed",
  "final_offer": {
    "insurance_company": "Company A",
    "premium": 5000,
    "coverage": "Full"
  },
  "timestamp": "2025-01-27T10:10:00Z"
}
```

Добавить Retry для отказоустойчивости и Circuit Breaker, чтобы не положить стороннюю систему