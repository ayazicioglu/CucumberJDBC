
Feature: id ile native ulke degeri bulma

  Scenario: Kullanici verilen id ye karsilik gellen native ulke dgerini bulur

    Given Database ile iletisim kurulur
    And languages tablosundan verilen 10 id numarasinin native degerinin istenen datayi dondurdugunu test eder
    Then Database baglantisi kapatilir
