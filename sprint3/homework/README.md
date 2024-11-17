# Задание 1. Анализ и планирование

## Домены и границы контекстов
1. по сути в репозитории представлен только домен "Управление устройствами отопления"

## Анализ архитектуры монолитного решения
1. Есть возможность регулировать температуру
2. Можно включить и отключить отопление
3. Нет возможности разделения регулирования температуры по различным участкам дома
4. Отсутствует авторизация и разделение по ролям
5. Нет балансировщика
6. Нет кеширования


## Визуализируйте контекст системы
Сделал диаграмму контекста для монолита в папке ./smart-home-monolith/diagrams/SmartHomeMonolit_Context

# Задание 2. Проектирование микросервисной архитектуры

## C4 — Уровень контейнеров
Реализация в папке ./smart-home-monolith/diagrams/containers/SmartHomeMicroservices_Containers.puml

## C4 — Уровень компонентов
Реализация в папке ./smart-home-monolith/diagrams/сomponents/SmartHomeMicroservices_Containers.puml

## C4 — Уровень кода (Code)
Основные взаимодействие:
1. ./smart-home-monolith/diagrams/code/DeviceCommandHandler.puml
2. ./smart-home-monolith/diagrams/code/DeviceIntegrationHandler.puml
3. ./smart-home-monolith/diagrams/code/DeviceStateManager.puml

В файле диаграмма последовательностей управления устройством ./smart-home-monolith/diagrams/code/Code.puml

## Задание 3. Разработка ER-диаграммы

Итоговая ER диаграмма в папке ./smart-home-monolith/diagrams/ER/SmartHome_ER.puml

