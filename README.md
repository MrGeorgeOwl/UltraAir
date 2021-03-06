# Компания Ultra Air
*Покупай легко - летай далеко*

## Общие сведения
Менеджмент перевозчиков максимально минимизирует расходы. 
В результате существует огромное число дополнительных опций,
за которые приходится платить: перевозка багажа, приоритетная посадка,
питание на борту, бронирование места в аварийном ряду и даже регистрация.
Самый дешевый билет можно купить лишь за несколько месяцев до перелета.
Но в результате максимальная экономия позволяет предложить предельно низкие цены на билеты.

## Назначение системы
Позволяет клиенту покупать билеты на дешевые рейсы

## Требования к системе
### Функциональные требования
#### Описание пользователей системы
Пользователи это клиенты сервиса. Также будет присутствовать админ, который будет добавлять инфу о рейсах
#### Описание вариантов использования
Примерный flow:
* Клиент заходит на сайт
* Просматривает рейсы, где ещё есть места для пассажиров
* Находит нужный рейс
* Заполняет инфу о себе
* Если есть багаж:
    * Заполняет инфу о багаже
    * Если перевес:
        * Наценка на билет + 10$
* Выбирает доп опции(по желанию):
    * Первый на посадку + 5$
    * Первый на регистрацию + 5$
* Исходя от того, насколько близко по дате рейс и выбранные опции, а также загруженности самолета высчитываем стоимость билета
#### Архитектура системы
Проект будет иметь слоенную архитектуру
#### Требования к пользовательскому интерфейсу
На данный момент интерфейс для клиента будет в виде консоли
#### Требования надежности
Использование third party для совершения покупок

#### Ссылки
* Backlog: https://trello.com/c/iu32iZEv/4-project-documentation
* Figma: https://www.figma.com/file/fnsQNT6UoRPG9YEgjFWfDo/UltraAir?node-id=196971%3A452
* GithubWiki: https://github.com/MrGeorgeOwl/UltraAir/wiki
* Diagrams: https://app.creately.com/diagram/eveN0FgCzMV/view

| Актер | Возможности |
| -- | -- |
| Гость (незарегистрированный пользователь) | Может просмотреть доступные билеты, но не купить их. Так же может войти как клиент или админ. |
| Клиент | Может просмотреть доступныеацию. Может просмотреть заказанные билеты. |
| Админ | Может все то же, что и клиент, но к этому добавляется возможность управления полетами (добавление, удаление). |

| Актер | Use | Описание |
| -- | -- | -- |
| Гость | Check Flights | Выводит все доступные полеты. Требует залогиниться, чтобы заказать билет. |
| | Log In | Просит ввести логин. В случае успеха, меняет роль на клиента или админа. |
| Клиент | Check Flights | Выводит все доступные полеты. Затем клиент может перейти к “Order the ticket” use. |
| | Order the ticket | Для создания билета клиент вводит информацию о багаже, о желании быть первым на посадку или регистрацию. Подтверждает заказ билета.|
| | Check tickets | Выводит все заказанные пользователем билеты. |
| | Log Out | Изменяет роль клиента на гостя. |
| Admin | Check Flights | Выводит все доступные полеты. Затем админ может перейти к “Order the ticket” use. |
| | Order the ticket | Для создания билета админ вводит информацию о багаже, о желании быть первым на посадку или регистрацию. Подтверждает заказ билета. |
| | Log Out | Изменяет роль админа на гостя. |
| | Manage Flights | Выводит меню с возможностью перехода на “Add Flight” и “Delete Flight” use-ы |
| | Add Flight | Просит ввести значения Городов отправки и назначения, вместимость. Затем отправляет в Service запрос на добавление этого полета. |
| | Delete Flight | Выводит список полетов. Просит ввести номер полета из списка и отправляет запрос на его удаление. |

## Clean code
* Пархоменко https://github.com/MrGeorgeOwl/EPAM_cleancodeTask

## Unit tests
* Пархоменко https://github.com/MrGeorgeOwl/jenkins-project-test
