package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utilities.DBUtils.*;

public class Stepdefinition {

    //JDBC (DataBase) testi yapilmaya baslamadan once Sistem Yoneticisi'nden izinle Database bilgileri alinir

    /*
    Database baglantisi icin gerekli bilgiler.
        type: jdbc:mysql
        host/ip: 45.84.206.41
        port:3306
        database: u480337000_tlb_training
        username: u480337000_tbl_training_u
        password: pO9#4bmxU
     */

    String url="jdbc:mysql://45.84.206.41:3306/u480337000_tlb_training";
    String username="u480337000_tbl_training_u";
    String password="pO9#4bmxU";

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    List<Object> staffID=new ArrayList<>();

    List<Object> adresList=new ArrayList<>();

    @Given("Database ile iletisime baslar")
    public void database_ile_iletisime_baslar() throws SQLException {
        connection= DriverManager.getConnection(url,username,password);
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        // connection objemizi olusturduk ve (url,username ve password datalarini connection objesinin icine koyduk.)
        // olusturdugumuz connection objesini kullanarak typelari belli bir satatement create ettik.
    }
    @Then("Query yi statement araciligi ile database e gonderir")
    public void query_yi_statement_araciligi_ile_database_e_gonderir() throws SQLException {

        String query="SELECT * FROM u480337000_tlb_training.users";
        resultSet=statement.executeQuery(query);
        //statement araciligi ile Database'e gonderdigimiz query sonucunu bir resultset icine store ettik

    }
    @Then("Databaseden donen resultset verisi test eder")
    public void databaseden_donen_resultset_verisi_test_eder() throws SQLException {
        resultSet.first();//ilk değer için iterator ilk sıraya aldık
        System.out.println(resultSet.getString("first_name"));

        String actualName=resultSet.getString("first_name");
        String expName="Super";
        Assert.assertEquals(expName,actualName);

        resultSet.next();//ikinci sırada iteratoru bir alta aldık
        System.out.println(resultSet.getString("first_name"));

        resultSet.absolute(11);//11.sıradaki değeri almak için
        System.out.println(resultSet.getString("first_name"));

        //resultSet.first(); //iteratoru basa aldık, tum degerleri almaya calısıyoruz
        resultSet.absolute(0); //yukarıdakini denedik işe yaramadı çünkü while icinde next ile ileri alıp
                                    //basladigi icin ilk degeri kacirmis oluruz bu yuzden absolute
        System.out.println("========================================");
        int sira=1;
        while (resultSet.next()){
            System.out.println(sira+"--"+resultSet.getString("first_name"));
            sira++;
        }

        resultSet.absolute(11);
        System.out.println(sira+"--"+resultSet.getString("email"));
    }
    @Then("Database kapatir")
    public void database_kapatir() throws SQLException {
        connection.close();
    }
//=========================================================================================================
    @Given("Database ile iletisim kurulur")
    public void database_ile_iletisim_kurulur() {
        createConnection();
    }

    @Then("Staff tablosundaki {string} leri listelenir")
    public void staff_tablosundaki_leri_listelenir(String id) {
        staffID=DBUtils.getColumnData("SELECT * FROM u480337000_tlb_training.staff",id);
        System.out.println(staffID);
    }

    @Then("verilen {string} sorgulama yapilir")
    public void verilen_sorgulama_yapilir(String verilenID) {
        assertTrue(staffID.toString().contains(verilenID));
    }

    @Then("Database baglantisi kapatilir")
    public void database_baglantisi_kapatilir() {
        closeConnection();
    }

    //=================================================================================
    @Then("{string} degeri verilen customerin {string} guncellenir")
    public void degeri_verilen_customerin_guncellenir(String id, String adres) throws SQLException {
       String query="UPDATE u480337000_tlb_training.customer_addresses\n" +
               "SET address= '"+adres+"' WHERE id="+id+";";
        /* son hali:
        query = UPDATE u480337000_tlb_training.customer_addresses SET address= 'kadikoy' WHERE id=1
         */
        update(query);
    }
    @Then("customer address tablosundaki {string} bilgileri listelenir")
    public void customer_address_tablosundaki_bilgileri_listelenir(String columnName) {
       String query="SELECT * FROM u480337000_tlb_training.users";
        adresList= getColumnData(query,columnName);
        System.out.println(adresList);

    }
    @Then("customer {string} guncellendigi dogrulanir")
    public void customer_guncellendigi_dogrulanir(String actualAdres) {
        assertTrue(adresList.toString().contains(actualAdres));

    }

    //====================================================================================
    @Given("verilen first_name ve last_name sorgulama yapilir")
    public void verilen_first_name_ve_last_name_sorgulama_yapilir() throws SQLException {
        String query = "SELECT email FROM u480337000_tlb_training.users WHERE first_name='admin' and last_name='user';";
        resultSet=getStatement().executeQuery(query);
    }
    @Given("donen resultset datasi dogrulanir")
    public void donen_resultset_datasi_dogrulanir() throws SQLException {
       resultSet.first();
       String actualEmailData=resultSet.getString("email");
       String expData="admin@gmail.com";
       assertEquals(expData,actualEmailData);

        System.out.println("actualEmailData = " + actualEmailData);

    }


    //=====================================================================================

    @And("seller_products tablosuna discount_type degeri \\({int}) olan tüm product'lari listeler")
    public void seller_productsTablosunaDiscount_typeDegeriOlanTümProductLariListeler(int arg0) throws SQLException {
        String query = "SELECT * FROM u480337000_tlb_training.seller_products WHERE discount_type=1 ;";
        resultSet=getStatement().executeQuery(query);
        resultSet.first();
        String actualData=resultSet.getString("product_name");
        String expData="Exclusive Bag 1";
        assertEquals(expData,actualData);

        System.out.println("actualData = " + actualData);
    }

    @And("languages tablosundan verilen {int} id numarasinin native degerinin istenen datayi dondurdugunu test eder")
    public void languagesTablosundanVerilenIdNumarasininNativeDegerininIstenenDatayiDondurdugunuTestEder(int id) throws SQLException {
        String query = "SELECT native FROM u480337000_tlb_training.languages WHERE id="+id+";";
        resultSet=getStatement().executeQuery(query);
        resultSet.first();
        String actualData=resultSet.getString("native");
        String expData="Bosanski";
        assertEquals(expData,actualData);

        System.out.println("actualData = " + actualData);
    }

    @And("Countries tablosundan {string} ile istenen ülkenin phonecode bilgisinin dondugu test edilir")
    public void countriesTablosundanIleIstenenÜlkeninPhonecodeBilgisininDonduguTestEdilir(String kod) throws SQLException {
        String query="SELECT phonecode FROM u480337000_tlb_training.countries WHERE code='"+kod+"';";
        resultSet=getStatement().executeQuery(query);
        resultSet.first();
        int actualData=resultSet.getInt("phonecode");
        int expData=90;
        Assert.assertEquals(expData,actualData);
    }

    @And("Database üzerinden seller_products tablosundaki discount_type degeri \\({int}) olan tüm product'lari listeler")
    public void databaseÜzerindenSeller_productsTablosundakiDiscount_typeDegeriOlanTümProductLariListeler(int typeDegeri) throws SQLException {
        String query="SELECT * FROM u480337000_tlb_training.seller_products WHERE discount_type='"+typeDegeri+"';";
        resultSet=getStatement().executeQuery(query);
        resultSet.absolute(0);
        int sira=1;
        while (resultSet.next()) {
            System.out.println(sira+"--"+resultSet.getString("product_name"));
            sira++;
        }
    }

    @Given("Database baglantisi kurulur")
    public void databaseBaglantisiKurulur() {
        createConnection();
    }

    @Then("Database baglantisi kapanir")
    public void databaseBaglantisiKapanir() {
        DBUtils.closeConnection();
    }


    @Then("verilen {int} numarasinin {string} degerinin istenen datayi dondurdugu dogrular")
    public void verilenNumarasininDegerininIstenenDatayiDondurduguDogrular(int id, String nat) throws SQLException {
        String query="SELECT native FROM u480337000_tlb_training.languages WHERE id="+id+";";
        resultSet=getStatement().executeQuery(query);
        List<Object> nativeList=getColumnData(query,"native");
        String actualData=nativeList.get(0).toString();
        assertEquals(nat,actualData);
    }


    @Then("Countries tablosundan {string} kodu ile istenen ülkenin phonecode bilgisinin dondugu test edilir")
    public void countriesTablosundanKoduIleIstenenÜlkeninPhonecodeBilgisininDonduguTestEdilir(String ulke) throws SQLException {
        String query="SELECT phonecode FROM u480337000_tlb_training.countries WHERE code='"+ulke+"'";
        resultSet=getStatement().executeQuery(query);
        List<Object> phohecodeList=getColumnData(query,"phonecode");
        resultSet.first();
        int actualData=resultSet.getInt("phonecode");
        System.out.println("actualData = " + actualData);
        assertEquals(90,actualData);

    }


    @Then("attendances tablosundan {string} yilinda {string} gunu katilim yapan user larin id'leri listelenir")
    public void attendancesTablosundanYilindaGunuKatilimYapanUserLarinIdLeriListelenir(String yil, String day) throws SQLException {
        String query="SELECT id FROM u480337000_tlb_training.attendances WHERE year='"+yil+"' and day='"+day+"';";
        System.out.println(getColumnData(query, "id"));
        System.out.println(getCellValue(query).toString());
        /*  resultSet=getStatement().executeQuery(query);
        resultSet.absolute(0);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
        */

    }
}
