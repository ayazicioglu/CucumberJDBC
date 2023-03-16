
Feature: User datalarini test etme


  Scenario: Verilen datalar ile user bilgilerini dogrular

  * Database ile iletisim kurulur
  * verilen first_name ve last_name sorgulama yapilir
  * donen resultset datasi dogrulanir
  * Database baglantisi kapatilir


    #Database üzerinden Users tablosundaki first_name ve last_name'i verilen kullanicinin bilgilerinin döndügü dogrulanmali.
    #(first_name= Admin, last_name=User)
    #admin@gmail.com