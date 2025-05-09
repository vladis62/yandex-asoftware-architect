
---

## Аргументация

### Почему это работает

| Аргумент                                 | Обоснование                                                                                      |
|------------------------------------------|---------------------------------------------------------------------------------------------------|
| **Изоляция бизнес-логики**               | Каждое направление (финансы, медицина, фармацевтика и т.д.) может развиваться без единого DWH     |
| **Гибкость в интеграциях**               | Новые компании (например, фарм-производители) можно встраивать без переписывания ядра             |
| **Проще внедрять микросервисы**          | Каждый домен может использовать свои технологии, форматы хранения и логики                        |
| **Разделение ответственности**           | У каждого домена — своя команда и продуктовый владелец                                            |
| **Оптимизация нагрузки**                 | BI и аналитика работают на специально подготовленной витрине, не тормозя боевые системы           |
| **Повышение безопасности и управления доступом** | Ограничение доступа по доменам, в том числе на уровне чувствительных данных                      |
| **Фокус на самообслуживание**            | Бизнес может сам строить отчёты по данным своего домена в рамках витрины                          |

---

## Преимущества для бизнеса

- **Быстрые отчёты и аналитика** — time-to-insight снижается с часов до минут.
- **Лёгкое масштабирование** — можно легко подключать новые подразделения.
- **Уменьшение зависимости от легаси** — DWH не становится «бутылочным горлышком».
- **Инновации внутри доменов** — каждая команда может использовать современные решения (например, стриминг, lakehouse, графовые базы и т.п.).
- **Самообслуживание** — бизнес-пользователи получают больше контроля и меньше зависят от IT.
