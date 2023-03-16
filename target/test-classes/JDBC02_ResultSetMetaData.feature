
Feature: JDBC02_ResultSetMetaData metotlari ile JDBC sorgusu calistirma

  Scenario Outline: Staff tablosundan "ID" sorgulama

    Given Database ile iletisim kurulur
    Then Staff tablosundaki "id" leri listelenir
    And verilen "<id>" sorgulama yapilir
    * Database baglantisi kapatilir


    #Database baglantisi kurarak staff tablosundaki Id'lerin icinde "5" id numarasina sahip staff var mi kontrol ediniz?
    #SELECT * FROM u480337000_tlb_training.staff;

  Examples:
    |id|
    |5|
    |2|
    |3|
    |4|
    |1|
    |100|
    |10 |