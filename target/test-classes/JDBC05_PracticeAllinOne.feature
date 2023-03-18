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

    Scenario: TC05 brands tablosundan A ile baslayan brandlar listelenir

      Given Database baglantisi kurulur
      Then brands tablosundaki "A" ile baslayan brand isimlerinin listelendigi dogrulanir.
      And Database baglantisi kapatilir

    Scenario: TC06 carts tablosunda en son sepete atilan 5 ürünün fiyatlari listelenir

      Given Database baglantisi kurulur
      And  carts tablosundaki en son sepete atilan 5 ürünün fiyatlari listelenir
      Then Database baglantisi kapatilir

    Scenario: TC07 Products tablosunda unit_type_id'si 1 olmayanlari listelenir

      Given Database baglantisi kurulur
      And Products tablosundaki unit_type_id'si 1 olmayanlari listelenir
      Then Database baglantisi kapatilir

    Scenario: TC08 languages tablosundaki code bilgisi ile isim dogrulanir

      Given Database baglantisi kurulur
      And languages tablosundaki code= "af" bilgisi ile verilen dilin ismi dogrulanir
      And Database baglantisi kapatilir


    Scenario: TC09 users tablosundaki isimler tekrarsiz listelenir

      Given Database baglantisi kurulur
      And users tablosundaki isimler tekrarsiz listelenir
      Then Database baglantisi kapatilir

    Scenario: TC10  transactions tablosunda morphable_id bilgilerinin en buyuk degeri listelenir

      Given Database baglantisi kurulur
      And transactions tablosunda morphable_id bilgilerini buyukten kucuge siralanarak En büyük deger yazdirilir
      Then Database baglantisi kapatilir














