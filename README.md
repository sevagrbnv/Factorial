# Factorial

В виду простоты приложения было принято решение не реализовывать чистую архитектуру, а просто использовать MVVM с добавлением репозитория БД во viewModel.
По той же причине в проекте не используются DI библиотеки.

Используемые решения:
1) Room - для взаимодействия с БД
2) Coroutines - для асинхронного выполнения операций с БД и вычислений
3) Из-за возможности частого обновления списка был выбран ListAdapter вместо RecyclerView.Adapter

Скриншоты приложения:

<img src="https://user-images.githubusercontent.com/65513466/232333276-3885ea52-349c-4952-8456-36cbdb5beb68.jpg" width="250">    <img src="https://user-images.githubusercontent.com/65513466/232333283-1e8f4fa6-728c-438d-afc4-911d0b51ba92.jpg" width="250">
