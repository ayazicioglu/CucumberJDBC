
Feature: JDBC baglantisi uzerinden update sorgusu yapma

      #  Bir yönetici olarak DataBase üzerinden customer_addresses tablosundaki
      # istenen Customer'in adress bilgisini güncelleyebilmeli ve güncellenen
      # adresi customer_addresses tablosunda oldugunu dogrulayabilmeliyim

  Scenario Outline:

    Given Database ile iletisime baslar
    Then "<id>" degeri verilen customerin "<adresi>" guncellenir
    And customer address tablosundaki "address" bilgileri listelenir
    * customer "<adresi>" guncellendigi dogrulanir
    * Database baglantisi kapatilir



  Examples:
    |id|adresi|
    |1|bartın|
