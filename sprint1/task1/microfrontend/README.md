Привет! прошу прощения, файл readme был повыше в дереве папок и не отправился на проверку

1) В качестве управления микрофронтами выбрал фреймворк Module Federation, потому что с его помощью можно гибко управлять фронтами. 
Изучив код проекта, решил разделить микрофронтенд на следующие части:
* Приложение для хостинга - Module Federation
* Авторизация
* Управление фотографиями (загрузка, удаление) и Сбор и учет лайков под фото
* Создание профиля и его редактирование

2) Создал следующие проекты
* host - Module Federation. В него вынес Header.js, Footer.js и ProtectedRoute.js
* users - регистрация и аутентификация. В этот проект вынес компоненты с 
регистрацией, аутентификацией и успешной регистрацией (Login, Register, InfoTooltip)
и api для аутентификации и регистрации (auth.js).
* processing_photo - Выносим основной компонент Card.js, добавление картинки AddPlacePopup.js,
управление окнами PopupWithForm и картинками ImagePopup
Так же вынести функции из api.js - getCardList, addCard, removeCard, changeLikeCardStatus
* profile - управление профилем. Выносим компоненты EditAvatarPopup, EditProfilePopup, PopupWithForm
и api - getUserInfo, setUserInfo, setUserAvatar

Все микрофронты реализованы с использованием js и React.
Наша команда больше спициализируется на backend и у нас нет должной экспертизы для других фреймворках


```
/host
  /src
    /components
      Footer.js                 // Компонент для отображения нижней части фронта
      Header.js                 // Компонент для отображения шапки фронта
  /styles
    footer.css               // Стили для нижней части фронта
    header.css               // Стили для шапки
  /utils
    auth.js                  // Утилиты для аутентификации
  package.json               // Зависимости и скрипты микрофронтенда
  webpack.config.js
```

```
/users
  /src
    /components
      Login.js               // Компонент входа пользователя
      Register.js            // Компонент регистрации пользователя
      InfoTooltip.js         // Компонент успешной регистрации
      ProtectedRoute.js         // Компонент для для проверки авторизован ли пользователь
  /styles
    login.css                // Стили для компонента входа
    register.css             // Стили для компонента регистрации
    popup.css.               // Стили для компонента успешной аутентификации
  /utils
    auth.js                  // Утилиты для аутентификации
  package.json               // Зависимости и скрипты микрофронтенда
  webpack.config.js
```

```
/processing_photo
  /src
    /components
      AddPlacePopup.js          // Компонент добавления картинки
      Card.js                   // Компонент отображения картинки
      ImagePopup.js             // Компонент выпадающего окна для картинки
      PopupWithForm.js          // Компонент форм
  /styles
    popup.css                   // Стили для выпадающего окна
    card.css                    // Стили для компонента карточек
  /utils
    api.js                      // Утилиты для управления картинкой
  package.json                  // Зависимости и скрипты микрофронтенда
  webpack.config.js
```

```
/profile
  /src
    /components
      EditAvatarPopup.js         // Компонент управления аватаркой профиля
      EditProfilePopup.js        // Компонент управления профилем
      PopupWithForm.js           // Компонент форм
  /styles
    popup.css                    // Стили для выпадающего окна
    profile.css                  // Стили для управления профиля
    avatar.css.                  // Стили для управления аватаркой
  /utils
    api.js                      // Утилиты для управления профилем
  package.json                  // Зависимости и скрипты микрофронтенда
  webpack.config.js
```


