# Компания Ultra Air
*Покупай легко - летай далеко*

1. *Общие сведения*
Менеджмент перевозчиков максимально минимизирует расходы. 
В результате существует огромное число дополнительных опций,
за которые приходится платить: перевозка багажа, приоритетная посадка,
питание на борту, бронирование места в аварийном ряду и даже регистрация.
Самый дешевый билет можно купить лишь за несколько месяцев до перелета.
Но в результате максимальная экономия позволяет предложить предельно низкие цены на билеты.

2. *Назначение системы*
Позволяет клиенту покупать билеты на дешевые рейсы

3. **Требования к системе**
*Функциональные требования*
*Описание пользователей системы*
Пользователи это клиенты сервиса. Также будет присутствовать админ, который будет добавлять инфу о рейсах
*Описание вариантов использования*
Примерный flow:
Клиент заходит на сайт
Просматривает рейсы, где ещё есть места для пассажиров
Находит нужный рейс
Заполняет инфу о себе
Если есть багаж:
    Заполняет инфу о багаже
    Если перевес:
        Наценка на билет + 10$
Выбирает доп опции(по желанию):
    Первый на посадку + 5$
    Первый на регистрацию + 5$
Исходя от того, насколько близко по дате рейс и выбранные опции, а также загруженности самолета высчитываем стоимость билета
*Архитектура системы*
Проект будет иметь слоенную архитектуру
*Требования к пользовательскому интерфейсу*
На данный момент интерфейс для клиента будет в виде консоли
*Требования надежности*
Использование third party для совершения покупок