# HS3 library manager

Aplikacja modułowa, zorientowana serwisowo:
- Serwis przeglądania katalogu, filtrowania biblioteczki (bez logowania)
- Serwis wypożyczania, przeglądania swojej historii (dla zalogowanych użytkowników)
- Panel admina do potwierdzania wypożyczeń i zwrotów, operacji na książkach (tylko admin)

Spring MVC:
- Katalog książek (controller, serwis, repo)
- Historia wypożyczeń j.w.

Wyszukiwarka danych:
- Możliwość przeglądania katalogu
- Filtrowanie wyników po kategoriach
- Search bar: Wyszukiwanie za pomocą Metamodel API, Entity manager JPQL, Full text search (+ LIKE) na tabeli book w bazie danych

Autoryzacja i autentykacja w Spring Security:
- JPA

