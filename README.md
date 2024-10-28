# CinemaApp 🎬

**CinemaApp** — это Android-приложение для просмотра списка фильмов с возможностью фильтрации по жанрам и отображением детальной информации о каждом фильме.

## 📋 Описание
CinemaApp предоставляет пользователю каталог фильмов с изображениями, рейтингами и кратким описанием. Приложение реализовано с использованием архитектуры MVVM и обеспечивает плавную и стабильную навигацию между экранами. В приложении также предусмотрена анимация загрузки данных и обработка ошибок сети.

## 📱 Скриншоты
*Основной фрагмент - список фильмов:*
![Main Fragment Screenshot](https://github.com/Ryota77777/CinemaApp/blob/main/Assets/HomeScreen.jpg?raw=true)

*Фрагмент с описанием фильма:*
![Movie Details Fragment Screenshot](https://github.com/Ryota77777/CinemaApp/blob/main/Assets/DetailsScreen.jpg?raw=true)

---

## ⚙️ Функциональность
- **Список фильмов**: просмотр фильмов с изображениями, рейтингом и названием.
- **Фильтрация по жанрам**: возможность выбора жанра из выпадающего списка для фильтрации списка фильмов.
- **Детальная информация**: при клике на фильм открывается отдельный экран с детальной информацией.
- **Обработка ошибок сети**: при сбое загрузки данных показывается сообщение с кнопкой для повторной попытки.
- **Поворот экрана**: поддержка адаптации макетов для вертикальной и горизонтальной ориентации.

## 🛠️ Зависимости и технологии
- **Kotlin**: основной язык разработки.
- **Android Architecture Components**:
  - `ViewModel` для управления UI-данными.
  - `LiveData` для обновления UI при изменении данных.
- **Navigation Component**: для управления навигацией между фрагментами.
- **Retrofit**: для сетевых запросов и получения данных о фильмах из JSON API.
- **Glide**: для загрузки и отображения изображений фильмов.
- **Koin**: для внедрения зависимостей.
- **RecyclerView**: для отображения списка фильмов с оптимизированной прокруткой.
- **ConstraintLayout**: для создания адаптивных макетов.

## 📂 Структура проекта
    ├── ui                  # Пакет с UI-компонентами
    │   ├── movies          # Основной фрагмент для списка фильмов
    │   └── details         # Фрагмент с описанием фильма
    ├── data                # Пакет с данными и сетевыми моделями
    │   ├── api             # Интерфейсы для сетевых запросов (Retrofit)
    │   └── models          # Модели данных для фильмов
    ├── viewmodel           # Пакет с ViewModel для управления данными
    └── di                  # Конфигурация зависимостей Koin

## 🚀 Установка и запуск
1. Клонируйте репозиторий:
    ```bash
    git clone https://github.com/yourusername/CinemaApp.git
    ```
2. Откройте проект в Android Studio.
3. Синхронизируйте проект для загрузки зависимостей.
4. Запустите приложение на эмуляторе или физическом устройстве.

## 🔑 Основные классы и файлы
- **MovieAdapter**: адаптер для отображения элементов списка фильмов.
- **MoviesFragment**: фрагмент с основным списком фильмов и фильтрацией.
- **MovieDetailsFragment**: фрагмент с описанием фильма.
- **MovieViewModel**: управляет данными о фильмах, включая запросы к API.
- **MovieApiService**: интерфейс Retrofit для получения списка фильмов из JSON API.
- **item_movie.xml**: макет элемента списка фильмов.
- **fragment_movies.xml** и **fragment_movie_details.xml**: макеты основных экранов приложения.

## 🌟 Планы по улучшению
- Поддержка дополнительной информации о фильмах (актеры, режиссеры и т.д.).
- Добавление сохранения любимых фильмов в локальную базу данных.
- Улучшение обработки ошибок и логирования.

## 📜 Лицензия
Этот проект находится под лицензией MIT. Подробности можно найти в файле LICENSE.

---

**Enjoy the movies with CinemaApp!**
