@jdbc
  Feature: Kullanıcı deger girerek tum product listeler

    Scenario: TC01 Kullanıcı ablosundaki discount_type degeri (1) olan tüm product'lari listeler

      Given Database baglantisi kurulur
      And Database üzerinden seller_products tablosundaki discount_type degeri (1) olan tüm product'lari listeler
      Then Database baglantisi kapatilir

    Scenario: TC02 languages tablosundan id ile data dogrulama
      Given Database baglantisi kurulur
      Then verilen 8 numarasinin "Bislama" degerinin istenen datayi dondurdugu dogrular
      Then Database baglantisi kapatilir

    Scenario: TC03 kod numarasi ile phonecode bilgisi dogrulama

      Given Database baglantisi kurulur
      Then Countries tablosundan "TR" kodu ile istenen ülkenin phonecode bilgisinin dondugu test edilir
      Then Database baglantisi kapatilir

    Scenario: TC04 Belirli tarihteki katılımcı id sini listeler

      Given Database baglantisi kurulur
      Then attendances tablosundan "2021" yilinda "Monday" gunu katilim yapan user larin id'leri listelenir
      And Database baglantisi kapatilir









