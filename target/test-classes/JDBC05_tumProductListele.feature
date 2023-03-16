
Feature: Database uzerinde belirli filtreleme ile deger okuma

  Scenario: Kullanici deferleri gore product listeler

    Given Database ile iletisim kurulur
    And seller_products tablosuna discount_type degeri (1) olan tüm product'lari listeler
    Then Database baglantisi kapatilir