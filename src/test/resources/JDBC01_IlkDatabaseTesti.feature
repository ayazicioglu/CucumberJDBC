
Feature: JDBC uzerinden Database baglantisi kurularak ilk test

  Scenario: Kullanici Users tablosundan isim testi yapar

    Given Database ile iletisime baslar
    Then Query yi statement araciligi ile database e gonderir
    And Databaseden donen resultset verisi test eder
    And Database kapatir
