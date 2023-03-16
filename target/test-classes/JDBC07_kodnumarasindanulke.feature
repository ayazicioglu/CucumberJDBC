
Feature: Kod numarası bilgisi girilerek ulke telefon kodu bulma

  Scenario: Kullanıcı farklı kodlar girerek ulke telefon kodu bulur

    Given Database ile iletisim kurulur
    And Countries tablosundan "TR" ile istenen ülkenin phonecode bilgisinin dondugu test edilir
    Then Database baglantisi kapatilir




