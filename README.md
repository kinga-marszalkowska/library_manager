# HS3 library manager

Spring MVC:
- Katalog książek (controller, serwis, repo)

Wyszukiwarka danych:
- Możliwość przeglądania katalogu
- Filtrowanie wyników po wszystkich kategoriach
- Search bar: Wyszukiwanie za pomocą Metamodel API, Entity manager JPQL, Full text search (+ LIKE) na tabeli book w bazie danych

Autoryzacja i autentykacja w Spring Security:


[ ] Dla osób zalogowanych możliwość zaznaczenia chęci wypożyczenia

    Jeśli ktoś wypożyczył wcześniej to dodanie kolejnej osoby do kolejki wypożyczeń
    
    Domyślnie książka jest wyożyczana na dwa miesiące

    Dla osób zalogowanych możliwość sprawdzenia kto ma książkę

[ ] Dla osób zalogowanych z prawami administracji możliwość dodawania i usuwania książek z katalogu

