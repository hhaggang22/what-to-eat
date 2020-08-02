package com.example.whattoeat_for_sungshin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity {
    static CategoryFrag categoryFrag;
    static HomeFrag homeFrag;
    static StoreFrag storeFrag;
    static PreferFragKor preferFragKor;
    static PreferFragSnack preferFragSnack;
    static PreferFragMeat preferFragMeat;
    static PreferFragJpn preferFragJpn;
    static PreferFragWest preferFragWest;
    static PreferFragChi preferFragChi;
    static PreferFragEtc preferFragEtc;
    static RouletteFrag rouletteFrag;
    static StoreDetailFrag storeDetailFrag;
    static String homeBtnName="";
    static int notMenuCnt;

    public static FragmentManager manager;

    /* DB 생성에 필요한 변수 선언 */
    public static SQLiteDatabase database;
    public static String result = "";
    protected static String storename = "";
    protected static int attrcount = 0;
    protected static String[] attrarray;
    public static String databaseName = "store_info";
    String sql = "";
    static int cursorCnt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFrag = new HomeFrag();
        categoryFrag = new CategoryFrag();
        storeFrag = new StoreFrag();
        preferFragKor = new PreferFragKor();
        preferFragMeat = new PreferFragMeat();
        preferFragChi = new PreferFragChi();
        preferFragJpn = new PreferFragJpn();
        preferFragSnack = new PreferFragSnack();
        preferFragWest = new PreferFragWest();
        preferFragEtc = new PreferFragEtc();
        rouletteFrag = new RouletteFrag();
        storeDetailFrag = new StoreDetailFrag();

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, homeFrag);
        transaction.commit();

        // MainActivity.java 에서 DB 생성
        openDatabase(databaseName);
        createTable();
        insertTable();
    }

    /* DB 생성에 필요한 메소드 선언 */
    //0. DB 삭제
    public void deleteTable(){
        println("deleteTable() 호출됨.");
        database.execSQL("delete from store_cafe");
        database.execSQL("delete from store_chi");
        database.execSQL("delete from store_jpn");
        database.execSQL("delete from store_kor");
        database.execSQL("delete from store_meat");
        database.execSQL("delete from store_snack");
        database.execSQL("delete from store_west");
    }

    //1. DB 저장소 생성
    public void openDatabase(String databaseName) {
        println("openDatabase() 호출됨.");
        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
        if (database != null) {
            println("데이터베이스 오픈됨.");
        }
    }

    //2. DB 테이블 생성
    public void createTable() {
        println("createTable() 호출됨");
        if (database != null) {
            String sqlCreateCafe = "CREATE TABLE IF NOT EXISTS `store_cafe` (\n" +
                    "  `st_id` varchar(20) PRIMARY KEY NOT NULL,\n" +
                    "  `st_name` varchar(20) UNIQUE NOT NULL,\n" +
                    "  `map_y` decimal(9,7) NOT NULL,\n" +
                    "  `map_x` decimal(9,6) NOT NULL,\n" +
                    "  `map_location` varchar(100) NOT NULL,\n" +
                    "  `st_re_menu1` varchar(20) NOT NULL,\n" +
                    "  `st_re_menu2` varchar(20) DEFAULT NULL\n" +
                    ")";
            database.execSQL(sqlCreateCafe);

            String sqlCreateChi = "CREATE TABLE IF NOT EXISTS `store_chi` (\n" +
                    "  `st_id` varchar(20) PRIMARY KEY NOT NULL,\n" +
                    "  `st_name` varchar(20) UNIQUE NOT NULL,\n" +
                    "  `map_y` decimal(9,7) NOT NULL,\n" +
                    "  `map_x` decimal(9,6) NOT NULL,\n" +
                    "  `map_location` varchar(100) NOT NULL,\n" +
                    "  `st_re_menu1` varchar(20) NOT NULL,\n" +
                    "  `st_re_menu2` varchar(20) DEFAULT NULL,\n" +
                    "  `attr1` varchar(20) DEFAULT NULL,\n" +
                    "  `attr2` varchar(20) DEFAULT NULL,\n" +
                    "  `attr3` varchar(20) DEFAULT NULL,\n" +
                    "  `attr4` varchar(20) DEFAULT NULL,\n" +
                    "  `attr5` varchar(20) DEFAULT NULL,\n" +
                    "  `attr6` varchar(20) DEFAULT NULL,\n" +
                    "  `attr7` varchar(20) DEFAULT NULL,\n" +
                    "  `attr8` varchar(20) DEFAULT NULL,\n" +
                    "  `attr9` varchar(20) DEFAULT NULL,\n" +
                    "  `attr10` varchar(20) DEFAULT NULL\n" +
                    ")";
            database.execSQL(sqlCreateChi);

            String sqlCreateEtc = "CREATE TABLE IF NOT EXISTS `store_etc` (\n" +
                    "  `st_id` varchar(20) PRIMARY KEY NOT NULL,\n" +
                    "  `st_name` varchar(20) UNIQUE NOT NULL,\n" +
                    "  `map_y` decimal(9,7) NOT NULL,\n" +
                    "  `map_x` decimal(9,6) NOT NULL,\n" +
                    "  `map_location` varchar(100) NOT NULL,\n" +
                    "  `st_re_menu1` varchar(20) NOT NULL,\n" +
                    "  `st_re_menu2` varchar(20) DEFAULT NULL,\n" +
                    "  `attr1` varchar(20) DEFAULT NULL,\n" +
                    "  `attr2` varchar(20) DEFAULT NULL\n" +
                    ")";
            database.execSQL(sqlCreateEtc);

            String sqlCreateJpn = "CREATE TABLE IF NOT EXISTS `store_jpn` (\n" +
                    "  `st_id` varchar(20) PRIMARY KEY NOT NULL DEFAULT '',\n" +
                    "  `st_name` varchar(20) UNIQUE NOT NULL DEFAULT '',\n" +
                    "  `map_y` decimal(9,7) DEFAULT NULL,\n" +
                    "  `map_x` decimal(9,6) DEFAULT NULL,\n" +
                    "  `map_location` varchar(100) NOT NULL DEFAULT '',\n" +
                    "  `st_re_menu1` varchar(255) NOT NULL DEFAULT '',\n" +
                    "  `st_re_menu2` varchar(255) DEFAULT '',\n" +
                    "  `attr1` varchar(20) NOT NULL DEFAULT '',\n" +
                    "  `attr2` varchar(20) DEFAULT '',\n" +
                    "  `attr3` varchar(20) DEFAULT '',\n" +
                    "  `attr4` varchar(20) DEFAULT '',\n" +
                    "  `attr5` varchar(20) DEFAULT '',\n" +
                    "  `attr6` varchar(20) DEFAULT '',\n" +
                    "  `attr7` varchar(20) DEFAULT '',\n" +
                    "  `attr8` varchar(20) DEFAULT ''\n" +
                    ") ";
            database.execSQL(sqlCreateJpn);

            String sqlCreateKor = "CREATE TABLE IF NOT EXISTS `store_kor` (\n" +
                    "  `st_id` varchar(10) PRIMARY KEY NOT NULL,\n" +
                    "  `st_name` varchar(20) UNIQUE NOT NULL,\n" +
                    "  `map_x` decimal(9,7) NOT NULL,\n" +
                    "  `map_y` decimal(9,6) NOT NULL,\n" +
                    "  `map_location` varchar(100) NOT NULL,\n" +
                    "  `st_re_menu1` varchar(20) NOT NULL,\n" +
                    "  `st_re_menu2` varchar(20) NOT NULL,\n" +
                    "  `attr1` varchar(20) NOT NULL,\n" +
                    "  `attr2` varchar(20) NOT NULL,\n" +
                    "  `attr3` varchar(20) NOT NULL,\n" +
                    "  `attr4` varchar(20) NOT NULL,\n" +
                    "  `attr5` varchar(20) NOT NULL,\n" +
                    "  `attr6` varchar(20) NOT NULL,\n" +
                    "  `attr7` varchar(20) NOT NULL,\n" +
                    "  `attr8` varchar(20) NOT NULL\n" +
                    ")";
            database.execSQL(sqlCreateKor);

            String sqlCreateMeat = "CREATE TABLE IF NOT EXISTS `store_meat` (\n" +
                    "  `st_id` varchar(20) PRIMARY KEY NOT NULL,\n" +
                    "  `st_name` varchar(20) NOT NULL,\n" +
                    "  `map_y` decimal(9,7) NOT NULL,\n" +
                    "  `map_x` decimal(9,6) NOT NULL,\n" +
                    "  `map_location` varchar(100) NOT NULL,\n" +
                    "  `st_re_menu1` varchar(20) NOT NULL,\n" +
                    "  `st_re_menu2` varchar(20) DEFAULT NULL,\n" +
                    "  `attr1` varchar(20) NOT NULL,\n" +
                    "  `attr2` varchar(20) DEFAULT NULL,\n" +
                    "  `attr3` varchar(20) DEFAULT NULL,\n" +
                    "  `attr4` varchar(20) DEFAULT NULL,\n" +
                    "  `attr5` varchar(20) DEFAULT NULL\n" +
                    ")";
            database.execSQL(sqlCreateMeat);

            String sqlCreateSnack = "CREATE TABLE IF NOT EXISTS `store_snack` (\n" +
                    "  `st_id` varchar(20) PRIMARY KEY NOT NULL,\n" +
                    "  `st_name` varchar(20) UNIQUE NOT NULL,\n" +
                    "  `map_y` decimal(9,7) NOT NULL,\n" +
                    "  `map_x` decimal(9,6) NOT NULL,\n" +
                    "  `map_location` varchar(100) NOT NULL,\n" +
                    "  `st_re_menu1` varchar(20) NOT NULL,\n" +
                    "  `st_re_menu2` varchar(20) DEFAULT NULL,\n" +
                    "  `attr1` varchar(20) DEFAULT NULL,\n" +
                    "  `attr2` varchar(20) DEFAULT NULL,\n" +
                    "  `attr3` varchar(20) DEFAULT NULL,\n" +
                    "  `attr4` varchar(20) DEFAULT NULL,\n" +
                    "  `attr5` varchar(20) DEFAULT NULL,\n" +
                    "  `attr6` varchar(20) DEFAULT NULL\n" +
                    ")";
            database.execSQL(sqlCreateSnack);

            String sqlCreateWest = "CREATE TABLE IF NOT EXISTS `store_west` (\n" +
                    "  `st_id` varchar(20) PRIMARY KEY NOT NULL DEFAULT '',\n" +
                    "  `st_name` varchar(20) UNIQUE NOT NULL DEFAULT '',\n" +
                    "  `map_y` decimal(9,7) DEFAULT NULL,\n" +
                    "  `map_x` decimal(9,6) DEFAULT NULL,\n" +
                    "  `map_location` varchar(100) NOT NULL DEFAULT '',\n" +
                    "  `st_re_menu1` varchar(255) NOT NULL DEFAULT '',\n" +
                    "  `st_re_menu2` varchar(255) DEFAULT '',\n" +
                    "  `attr1` varchar(20) NOT NULL DEFAULT '',\n" +
                    "  `attr2` varchar(20) DEFAULT '',\n" +
                    "  `attr3` varchar(20) DEFAULT '',\n" +
                    "  `attr4` varchar(20) DEFAULT ''\n" +
                    ")";
            database.execSQL(sqlCreateWest);

            println("테이블 생성됨");
        } else { //데이터베이스 생성이 안된 경우
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }

    //3. 테이블에 값 추가
    public void insertTable() {
        if (database != null) {
            String sqlInsertCafe = "INSERT OR IGNORE INTO `store_cafe` (`st_id`, `st_name`, `map_x`, `map_y`, `map_location`, `st_re_menu1`, `st_re_menu2`) VALUES\n" +
                    "('cafe_01', '슬로우브레드파파', '37.5906846', '127.020235', '서울특별시 성북구 보문로30나길 29 1 층', '앙버터', '타르트'),\n" +
                    "('cafe_02', '와플앨리', '37.5909551', '127.019227', '서울 성북구 보문로34길 68-3', '아메리카노', '와플2조각'),\n" +
                    "('cafe_03', '카페b', '37.5905496', '127.019408', '서울특별시 성북구 동선동 보문로30길 71', '아메리카노', '티라미스'),\n" +
                    "('cafe_04', '다인제과', '37.5937570', '127.020360', '서울 성북구 동소문로26길 28', '마카롱', '케이크'),\n" +
                    "('cafe_05', '레인드롭', '37.5906397', '127.019216', '서울특별시 성북구 동선동 동소문로22길 57-12', '아메리카노', '머랭쿠키'),\n" +
                    "('cafe_06', '메종드 루즈', '37.5908830', '127.019261', '서울특별시 성북구 동선동2가 135-19', '마카롱', '머랭'),\n" +
                    "('cafe_07', '미미상회', '37.5908109', '127.019295', '서울특별시 성북구 동선동2가 동소문로22길 57-17', '서울바나나', '말차숲'),\n" +
                    "('cafe_08', '본크레페', '37.5919914', '127.018197', '서울특별시 성북구 동선동 동소문로20다길 26', '누텔라 바나나 크레페', '로투스 딸기 크레페');";
            database.execSQL(sqlInsertCafe);

            String sqlInsertChi = "INSERT OR IGNORE INTO `store_chi` (`st_id`, `st_name`, `map_x`, `map_y`, `map_location`, `st_re_menu1`, `st_re_menu2`, `attr1`, `attr2`, `attr3`, `attr4`, `attr5`, `attr6`, `attr7`, `attr8`, `attr9`, `attr10`) VALUES\n" +
                    "('chi_01', '공푸', '37.5887931', '127.016090', '서울특별시 성북구 삼선동5가 299-10', '짜장면', '차돌짬뽕밥', '국물O', '국물X', '고기O', NULL, '매운', '안매운', '찬', '뜨거운', '밥', '밀가루'),\n" +
                    "('chi_02', '동북미식', '37.5948658', '127.015842', '서울특별시 성북구 동소문동6가 229', '꿔바로우', '어향가지덮밥', NULL, '국물X', '고기O', '고기X', NULL, '안매운', NULL, '뜨거운', '밥', '밀가루'),\n" +
                    "('chi_03', '라라면가', '37.5909280', '127.019646', '서울특별시 성북구 동선동2가 135-8', '우육면', '딴딴면', '국물O', NULL, '고기O', NULL, '매운', '안매운', NULL, '뜨거운', NULL, '밀가루'),\n" +
                    "('chi_04', '마라삼국지', '37.5919555', '127.016770', '서울특별시 성북구 동선동1가 2-12 2층', '마라탕', '마라향궈', '국물O', '국물X', '고기O', NULL, '매운', NULL, NULL, '뜨거운', NULL, '밀가루'),\n" +
                    "('chi_05', '보배반점', '37.5907571', '127.017438', '서울특별시 성북구 동선동1가 48', '차돌 짬뽕', '해물짬뽕', '국물O', NULL, '고기O', NULL, '매운', NULL, NULL, '뜨거운', NULL, '밀가루'),\n" +
                    "('chi_06', '선호짬뽕', '37.5920817', '127.016725', '서울특별시 성북구 동선동1가 2-11 대성빌딩 2층', '탕짜면', '탕짬면', '국물O', '국물X', '고기O', NULL, '매운', '안매운', NULL, '뜨거운', NULL, '밀가루'),\n" +
                    "('chi_07', '홍콩반점', '37.5920906', '127.017620', '서울특별시 성북구 동선동 동소문로20나길 18', '짜장', '짬뽕', '국물O', '국물X', '고기O', NULL, '매운', '안매운', NULL, '뜨거운', NULL, '밀가루'),\n" +
                    "('chi_08', '하이니하오', '37.5916578', '127.01951', '서울 성북구 동소문로24가길 17', '마라탕', '마라샹궈', '국물O', '국물X', '고기O', '고기x', '매운', '안매운', NULL, '뜨거운', '', '밀가루'),\n" +
                    "('chi_09', '짬뽕지존', '37.5908023', '127.016657', '서울 성북구 동소문로20길 40 ', '지존짬뽕', '지존짜장', '국물O', '', '', '고기X', '매운', '안매운', NULL, '뜨거운', NULL, '밀가루');";
            database.execSQL(sqlInsertChi);

            String sqlInsertEtc = "INSERT OR IGNORE INTO `store_etc` (`st_id`, `st_name`, `map_x`, `map_y`, `map_location`, `st_re_menu1`, `st_re_menu2`, `attr1`, `attr2`) VALUES\n" +
                    "('etc_01', '72420', '37.5930815', '127.018718', '서울특별시 성북구 동선동 동소문로24길 13 2층', '양지쌀국수', '매운쌀국수', '식사', NULL),\n" +
                    "('etc_02', '샌드위치하우스', '37.5915586', '127.020054', '서울특별시 성북구 동선동3가 232', '에그샐러드', '갈릭치즈치킨 샌드위치', NULL, '간편식'),\n" +
                    "('etc_03', '키프레쉬', '37.5920545', '127.018072', '동선동1가 85-84번지 1층 성북구 서울특별시 KR', '키프레시볼', '치킨아보볼', NULL, '간편식'),\n" +
                    "('etc_04', '투고샐러드', '37.5909369', '127.020484', '서울특별시 성북구 동선동2가 312', '리코타치즈샐러드', '목살스테이크샐러드', NULL, '간편식'),\n" +
                    "('etc_05', '포보', '37.5920184', '127.017937', '서울 성북구 동선동1가 85-86 2층', '쇠고기쌀국수 정식', '치킨까스', '식사', NULL),\n" +
                    "('etc_06', '포앤시드니', '37.5913785', '127.019465', '서울특별시 성북구 동선동1가 84-1', '포', '분짜', '식사', NULL),\n" +
                    "('etc_07', '궤도에 오르다', '37.5909551', '127.019374', '서울특별시 성북구 동선동 보문로34길 72-4', '메소핫도그 세트', '트로포 핫도그 세트', NULL, '간편식'),\n" +
                    "('etc_08', '그릭데이', '37.5908740', '127.018967', '서울 성북구 보문로34길 66', '그릭요거트', '플레인그릭요거트', NULL, '간편식'),\n" +
                    "('etc_09', '라이라이라이', '37.5908829', '127.019974', '서울특별시 성북구 동선동 보문로30길 76', '반미', NULL, '식사', NULL),\n" +
                    "('etc_10', '쎼오쎄오', '37.590784', '127.01857', '서울 성북구 보문로34길 56', '분짜', '반쎄오', '식사',  NULL),\n" +
                    "('etc_11', '어스핸드위치', '37.5904236', '127.018457', '서울 성북구 동소문로22길 60', '에베카도 샌드위치', '블루리조또', '식사',  NULL),\n" +
                    "('etc_12', '베르데9', '37.5911357', '127.016453', '서울 성북구 동소문로20길 28-3', '치킨부리또', '하드타코', NULL, '간편식'),\n" +
                    "('etc_13', '미스사이공', '37.5920727', '127.016748', '서울 성북구 동소문로20가길 2', '소고기쌀국수', '볶음면', '식사', NULL),\n" +
                    "('etc_14', '생쿡쌀국수', '37.5911357', '127.016453', '서울 성북구 동소문로20길 28-3', '소고기쌀국수', '쌀냉면', '식사', NULL);";
            database.execSQL(sqlInsertEtc);

            String sqlInsertJpn = "INSERT OR IGNORE INTO `store_jpn` (`st_id`, `st_name`, `map_x`, `map_y`, `map_location`, `st_re_menu1`, `st_re_menu2`, `attr1`, `attr2`, `attr3`, `attr4`, `attr5`, `attr6`, `attr7`, `attr8`) VALUES\n" +
                    "('jap_01', '동경산책', '37.5908021', '127.017676', '서울특별시 성북구 동선동1가 53', '오늘초밥', '특선초밥', '찬', '', '국물X', '국물O', '날것O', '날것X', '밥', ''),\n" +
                    "('jap_02', '김태완의초밥좋은날', '37.5911266', '127.016623', '서울특별시 성북구 동소문동5가 118-15', '오늘초밥', '특선초밥', '찬', '', '국물X', '국물O', '날것O', '날것X', '밥', ''),\n" +
                    "('jap_03', '윤휘식당', '37.5910272', '127.019182', '서울특별시 성북구 동선동2가 135-21', '함박스테이크', '챠슈동', '', '뜨거운', '국물X', '', '', '날것X', '밥', ''),\n" +
                    "('jap_04', '나카노라멘', '37.5924057', '127.018933', '서울특별시 성북구 동선동1가 85-36', '냉라멘', '나카노라멘', '찬', '뜨거운', '국물X', '', '', '날것X', '밥', ''),\n" +
                    "('jap_05', '너를위한부엌', '37.5921535', '127.018390', '서울특별시 성북구 동선동 동소문로20다길 31', '연어덮밥', '찹스테이크', '찬', '뜨거운', '국물X', '', '날것O', '날것X', '밥', ''),\n" +
                    "('jap_06', '스시미', '37.5911532', '127.020031', '서울특별시 성북구 동선동2가 155', '모둠초밥', '연어초밥', '찬', '', '국물X', '', '날것O', '', '밥', ''),\n" +
                    "('jap_07', '오월키친', '37.5865756', '127.023155', '서울특별시 성북구 안암동2가 124번지 1층', '치즈카츠', '치킨카츠', '', '뜨거운', '국물X', '', '', '날것X', '밥', ''),\n" +
                    "('jap_08', '키작은아저씨초밥', '37.5919642', '127.018899', '서울특별시 성북구 동선동1가 85-40', '냉모밀', '알밥우동정식', '찬', '뜨거운', '국물X', '국물O', '날것O', '날것X', '밥', '밀가루'),\n" +
                    "('jap_09', '스시진', '37.5909645', '127.016623', '서울특별시 성북구 동소문동5가 동소문로20길 36', '회덮밥', '사시미정식', '찬', '', '국물X', '', '날것O', '', '밥', ''),\n" +
                    "('jap_10', '도쿄커틀릿', '37.5906128', '127.018615', '서울특별시 성북구 동선동2가 동소문로22길 57-1', '점보세트', '소불고기우동', '', '뜨거운', '', '국물O', '', '날것X', '밥', '밀가루'),\n" +
                    "('jap_11', '지구당', '37.5891264', '127.016996', '서울특별시 성북구 삼선동5가 364', '규동', '우동', '', '뜨거운', '국물X', '국물O', '', '날것X', '밥', '밀가루'),\n" +
                    "('jap_12', '가야가야', '37.5905947', '127.019091', '서울특별시 성북구 동선동 동소문로22길 57-10', '돈코츠라멘', '소유라멘', '', '뜨거운', '', '국물O', '', '날것X', '', '밀가루'),\n" +
                    "('jap_13', '최고당돈까스', '37.5913785', '127.019465', '서울특별시 성북구 동선동1가 84-1', '생등심돈가스', '치즈돈가스', '', '뜨거운', '', '국물X', '', '날것X', '밥', ''),\n" +
                    "('jap_14', '오가와규동', '37.593757', '127.02036', '서울특별시 성북구 동소문로26길 28 ', '규동', '가츠동', '', '뜨거운', '', '국물X', '', '날것X', '밥', ''),\n" +
                    "('jap_15', '온달 왕 돈까스', '37.5921447', '127.016884', '서울 성북구 동소문로20가길 4 ', '왕돈까스', '치즈돈까스', '', '뜨거운', '', '국물X', '', '날것X', '밥', '밀가루'),\n" +
                    "('jap_16', '상미규카츠', '37.5908112', '127.016997', '서울 성북구 동소문로20길 39 ', '규카츠정식', '사케동정식', '', '뜨거운', '', '국물X', '', '날것X', '밥', '');";
            database.execSQL(sqlInsertJpn);

            String sqlInsertKor = "INSERT OR IGNORE INTO `store_kor` (`st_id`, `st_name`, `map_x`, `map_y`, `map_location`, `st_re_menu1`, `st_re_menu2`, `attr1`, `attr2`, `attr3`, `attr4`, `attr5`, `attr6`, `attr7`,`attr8`) VALUES\n" +
                    "('kor_01', '내가찜한닭', '37.5927933', '127.017563', '서울특별시 성북구 동선동 동소문로22길 5', '안동찜닭', '매콤고추장찜닭', '', '뜨거운', '매운', '안매운', '', '국물X', '밥',''),\n" +
                    "('kor_02', '도리연', '37.5912256', '127.017755', '서울특별시 성북구 동선동1가 18', '닭도리탕', '', '', '뜨거운', '매운', '', '국물O', '', '밥',''),\n" +
                    "('kor_03', '돈암동찌개', '37.5924238', '127.018446', '서울시 성북구 동선동 1가 85-72', '김치찌개', '국물닭도리탕', '', '뜨거운', '매운', '', '국물O', '', '밥',''),\n" +
                    "('kor_04', '등마루', '37.5919464', '127.017257', '서울특별시 성북구 동선동1가 3-5', '닭한마리', '돼지곱창', '', '뜨거운', '', '안매운', '국물O', '', '밥',''),\n" +
                    "('kor_05', '미향', '37.5943070', '127.017144', '서울특별시 성북구 동선동4가 178', '숯불제육볶음', '숯불제육덮밥', '', '뜨거운', '매운', '안매운', '', '국물X', '밥',''),\n" +
                    "('kor_06', '방이샤브샤브', '37.5937301', '127.019149', '서울특별시 성북구 동선동1가 123', '샤브샤브세트', '', '', '뜨거운', '매운', '안매운', '국물O', '', '밥',''),\n" +
                    "('kor_07', '방이편백 육분삼십', '37.5915861', '127.017178', '서울특별시 성북구 동선동 동소문로20다길 6', '소고기 편백찜', '간장새우밥', '', '뜨거운', '', '안매운', '국물O', '', '밥',''),\n" +
                    "('kor_08', '서도바지락칼국수', '37.5913609', '127.016317', '서울특별시 성북구 동소문동5가 동소문로20길 28-8', '바지락칼국수', '냉콩국수', '찬', '뜨거운', '', '안매운', '국물O', '','', '밀가루'),\n" +
                    "('kor_09', '성북손두부', '37.5900723', '127.018038', '서울특별시 성북구 동선동2가 3-4', '해물순두부', '청국장', '', '뜨거운', '매운', '안매운', '국물O', '', '밥',''),\n" +
                    "('kor_10', '신의 한 수', '37.5922707', '127.017982', '서울특별시 성북구 동선동1가 85-79', '돼지갈비찜', '소갈비찜', '', '뜨거운', '매운', '안매운', '국물O', '', '밥',''),\n" +
                    "('kor_11', '신의주찹쌀순대', '37.5930546', '127.018005', '서울특별시 성북구 동선동1가 111', '신의주순대국', '얼큰순대국', '', '뜨거운', '매운', '안매운', '국물O', '', '밥',''),\n" +
                    "('kor_12', '알촌', '37.5909371', '127.019069', '서울특별시 성북구 동선동2가 135-23', '약매알밥', '진매알밥', '', '뜨거운', '매운', '안매운', '', '국물X', '밥',''),\n" +
                    "('kor_13', '일흥콩나물국밥', '37.5886397', '127.018321', '서울특별시 성북구 삼선동5가 인촌로5길 77', '콩나물해장국', '', '', '뜨거운', '매운', '', '국물O', '', '밥',''),\n" +
                    "('kor_14', '정키친프로젝트', '37.5917570', '127.018605', '서울특별시 성북구 동선동1가 85-58', '간장닭', '바삭김치전', '', '뜨거운', '매운', '안매운', '', '국물X', '','밀가루'),\n" +
                    "('kor_15', '제순식당', '37.5918382', '127.018367', '서울특별시 성북구 동선동1가 85-94', '제육볶음', '오징어볶음', '', '뜨거운', '매운', '', '', '국물X', '밥',''),\n" +
                    "('kor_16', '쭈꾸미달인', '37.5925049', '127.018673', '서울특별시 성북구 동선동1가 85-70', '쭈달쭈꾸미', '쭈꾸미삼겹살', '', '뜨거운', '매운', '안매운', '', '국물X', '밥',''),\n" +
                    "('kor_17', '태조감자국', '37.5921268', '127.015932', '서울특별시 성북구 동소문동5가 73-2', '감자탕', '', '', '뜨거운', '매운', '안매운', '국물O', '', '밥',''),\n" +
                    "('kor_18', '팔백집', '37.5894774', '127.019804', '서울특별시 성북구 동선동2가 172', '쫄돼지갈비', '불고기', '', '뜨거운', '', '안매운', '국물O', '', '밥',''),\n" +
                    "('kor_19', '할매숯불닭갈비', '37.5910184', '127.017166', '서울특별시 성북구 동선동1가 8', '숯불닭갈비', '', '', '뜨거운', '매운', '안매운', '', '국물X', '밥',''),\n" +
                    "('kor_20', '육쌈냉면', '37.5910800', '127.018381', '서울특별시 성북구 동선동 동소문로22길 45', '물냉면', '비빔냉면', '찬', '', '매운', '안매운', '국물O', '국물X', '','밀가루'),\n" +
                    "('kor_21', '혜화면옥', '37.5908760', '127.013974', '서울특별시 성북구 삼선동 345-2', '물냉면', '비빔냉면', '찬', '', '매운', '안매운', '국물O', '국물X','', '밀가루'),\n" +
                    "('kor_22', '성북면가', '37.5917790', '127.012755', '서울특별시 성북구 동소문동4가 동소문', '물냉면', '비빔냉면', '찬', '', '매운', '안매운', '국물O', '국물X','', '밀가루'),\n" +
                    "('kor_23', '육회본가', '37.5912887', '127.017416', '서울 성북구 동소문로20길 29-12', '육회', '육회비빔밥', '찬', '', '', '안매운', '', '국물X','밥', ''),\n" +
                    "('kor_24', '오늘한끼', '37.5920905', '127.017937', '서울 성북구 동소문로22길 19-2', '한끼집밥', '가래떡 크림 파스타', '', '뜨거운', '', '안매운', '국물O', '국물X','밥', '밀가루'),\n" +
                    "('kor_25', '한우누렁소', '37.5934148', '127.01879', '서울특별시 성북구 동소문로20가길 46', '설렁탕', '육회비빔밥', '', '뜨거운', '', '안매운', '국물O', '국물X','밥', ''),\n" +
                    "('kor_26', '바다목장', '37.5934148', '127.01879', '서울 성북구 동소문로20가길 46', '생대구탕', '알탕', '', '뜨거운', '', '안매운', '국물O', '','밥', ''),\n" +
                    "('kor_27', '육수당', '37.5932617', '127.018639', '서울 성북구 동소문로24길 9', '순대국밥', '육개장', '', '뜨거운', '매운', '안매운', '국물O', '','밥', ''),\n" +
                    "('kor_28', '미소샤브칼국수', '37.5917752', '127.017789', '서울 성북구 동소문로22길 28', '쇠고기샤브샤브', '낙지덮밥', '', '뜨거운', '매운', '안매운', '국물O', '국물X','밥', ''),\n" +
                    "('kor_29', '신천순대국', '37.5907392', '127.01711', '서울 성북구 동소문로20길 41', '순대국밥', '순두부찌개', '', '뜨거운', '매운', '안매운', '국물O', '','밥', ''),\n" +
                    "('kor_30', '연화화덕생선구이', '37.59064', '127.017416', '서울 성북구 보문로34길 31', '고등어구이', '삼치구이', '', '뜨거운', '', '안매운', '', '국물X','밥', '');";
            database.execSQL(sqlInsertKor);

            String sqlInsertMeat = "INSERT OR IGNORE INTO `store_meat` (`st_id`, `st_name`, `map_x`, `map_y`, `map_location`, `st_re_menu1`, `st_re_menu2`, `attr1`, `attr2`, `attr3`, `attr4`, `attr5`) VALUES\n" +
                    "('meat_01',  '김덕후의 곱창조', '37.5912616', '127.017868', '서울특별시 성북구 동선동 동소문로20길 37-21', '모듬 곱창', '', '소', '', '', '부속', ''),\n" +
                    "('meat_02',  '김통', '37.5937843', '127.018628', '서울특별시 성북구 동선동1가 120-14번지 1층', '삼겹살', '목살', '돼지', '살', '', '', ''),\n" +
                    "('meat_03',  '노랑통닭', '37.5908050', '127.018707', '서울특별시 성북구 동선동2가', '마늘치킨', '후라이드치킨', '닭', '', '튀김', '', ''),\n" +
                    "('meat_04',  '닭만리', '37.5899821', '127.018966', '서울특별시 성북구 동선동 보문로30라길 14', '매콤짜글이통닭', '애간장태운통닭', '닭', '', '튀김', '', ''),\n" +
                    "('meat_05',  '백두산양꼬치구이', '37.5919463', '127.018050', '서울특별시 성북구 동선동1가 85-88', '양꼬치', '', '양', '', '', '', '살'),\n" +
                    "('meat_06',  '쓰리로보스', '37.5920815', '127.018061', '서울특별시 성북구 동선동 동소문로20다길 25', '치킨로스트', '', '닭', '', '튀김', '', ''),\n" +
                    "('meat_07',  '오늘통닭', '37.5929193', '127.018933', '서울특별시 성북구 동선동 동소문로20나길 47', '통닭삼총사', '켄터키 후라이드', '닭', '', '튀김', '', ''),\n" +
                    "('meat_08',  '이가한우곱창구이', '37.5918924', '127.017099', '서울특별시 성북구 동선동1가', '소모듬', '소곱창', '소', '', '', '', ''),\n" +
                    "('meat_09',  '뚜레곱창', '37.5913519', '127.016555', '서울특별시 성북구 동소문동5가 107', '치즈 야채곱창', '돼지막창 소금구이', '돼지', '', '', '', ''),\n" +
                    "('meat_10',  '순자네야채곱창', '37.5920095', '127.017427', '서울특별시 성북구 동선동1가 3-3', '야채곱창', '순대곱창', '돼지', '', '', '', ''),\n" +
                    "('meat_11',  '상관양꼬치', '37.5912616', '127.017857', '서울특별시 성북구 동선동1가 20','양꼬치', '세트A','양', '', '', '', ''),\n" +
                    "('meat_12',  '북경양꼬치', '37.5923157', '127.018265', '서울특별시 성북구 동선동1가 85-78', '양념숙성 양꼬치', '생양고기꼬치', '양', '', '', '', ''),\n"  +
                    "('meat_13',  '미운오리', '37.5859817', '127.018933', '서울 성북구 보문로 123', '소금구이', '주물럭','오리', '', '', '', ''),\n" +
                    "('meat_14',  '정통집', '37.5914508', '127.017812', '서울 성북구 동소문로20길 29-20', '돼지김치구이', '꽃껍데기', '돼지', '살', '부속', '', ''),\n" +
                    "('meat_15',  '사계진미', '37.591595', '127.017676', '서울 성북구 동소문로20길 29-17', '숯불닭갈비', '', '닭', '튀김X', '', '', ''),\n" +
                    "('meat_16',  '만복식당', '37.5913609', '127.01655', '서울 성북구 동소문로20길 28-4', '만복삼겹살', '만복특허목살', '돼지', '살', '', '', '');";

            database.execSQL(sqlInsertMeat);

            String sqlInsertSnack = "INSERT OR IGNORE INTO `store_snack` (`st_id`, `st_name`, `map_x`, `map_y`, `map_location`, `st_re_menu1`, `st_re_menu2`, `attr1`, `attr2`, `attr3`, `attr4`, `attr5`, `attr6`) VALUES\n" +
                    "('snack_01', '김가네', '37.5910993', '127.018695', '서울특별시 성북구 동선동 보문로34길 61', '김밥', '떡볶이', '매운', '안매운', NULL, '튀김X', '밥', '밀가루'),\n" +
                    "('snack_02', '돈암동떡볶이', '37.5920814', '127.018899', '서울특별시 성북구 동선동 동소문로24길 35', '떡볶이', '튀김', '매운', '안매운', '튀김o', NULL, NULL, '밀가루'),\n" +
                    "('snack_03', '돈암순대', '37.5914421', '127.015910', '서울특별시 성북구 삼선동 동소문로18길 20', '김밥', '순대', NULL, '안매운', NULL, '튀김x', '밥', NULL),\n" +
                    "('snack_04', '따끈하니 쫄깃하니', '37.5913609', '127.016555', '서울특별시 성북구 동소문동5가 동소문로20길 28-4', '그냥 라면', '팔뚝김밥', '매운', '안매운', NULL, '튀김x', '밥', '밀가루'),\n" +
                    "('snack_05', '떡깨비', '37.5892879', '127.021208', '서울 성북구 보문로30가길 27-1', '깨비 떡 2인세트', '깨비 오뎅', '매운', NULL, NULL, '튀김x', NULL, '밀가루'),\n" +
                    "('snack_06', '모두와떡볶이', '37.5920364', '127.018356', '서울특별시 성북구 동선동1가 85-63', '치즈오븐떡볶이', '짜장떡볶이', '매운', '안매운', NULL, '튀김x', NULL, '밀가루'),\n" +
                    "('snack_07', '수아당', '37.5915497', '127.019340', '서울특별시 성북구 동선동 동소문로24가길 18 1층', '불갈비 마요 김밥', '수아 김밥', NULL, '안매운', NULL, '튀김x', '밥', NULL),\n" +
                    "('snack_08', '수유리우동집', '37.5919555', '127.016782', '서울특별시 성북구 동선동1가 동소문로20나길 3', '우동', '얼큰우동', '매운', '안매운', NULL, '튀김x', NULL, '밀가루'),\n" +
                    "('snack_09', '싸이렌 떡볶이', '37.5913787', '127.017857', '서울특별시 성북구 동선동 동소문로20길 29-22', '싸이렌 떡볶이', '오뎅튀김', '매운', NULL, NULL, '튀김x', NULL, '밀가루'),\n" +
                    "('snack_10', '오래누드김밥', '37.5938111', '127.019975', '서울특별시 성북구 동선동3가 173번지 1층', '누드김밥', '누드계란말이', NULL, '안매운', NULL, '튀김x', '밥', NULL),\n" +
                    "('snack_11', '옹기종기', '37.5915677', '127.019646', '서울특별시 성북구 동선동3가 248', '육개장', '치즈볶음밥', '매운', NULL, NULL, '튀김x', '밥', '밀가루'),\n" +
                    "('snack_12', '엽기떡볶이', '37.5920185', '127.017438', '서울특별시 성북구 동선동 동소문로20나길 16', '엽기떡볶이', '엽기떡오뎅', '매운', NULL, NULL, '튀김x', NULL, '밀가루'),\n" +
                    "('snack_13', '짱구분식', '37.5913785', '127.019465', '서울특별시 성북구 동선동1가 84-1', '칼국수', '치즈돌솥밥', NULL, '안매운', NULL, '튀김x', '밥', '밀가루'),\n" +
                    "('snack_14', '추억의 옛날떡볶이', '37.5914417', '127.018537', '서울특별시 성북구 동선동 동소문로22길 39-5', '떡볶이', '튀김', '매운', NULL, '튀김o', NULL, NULL, '밀가루'),\n" +
                    "('snack_15', '띵라면', '37.5916850', '127.018288', '동선동1가 85-96번지 1층 3호 성북구 서울특별시 KR', '띵라면', NULL, '매운', NULL, NULL, '튀김x', NULL, '밀가루'),\n" +
                    "('snack_16', '청년다방', '37.5910454', '127.017744', '서울 성북구 동소문로20길 37-20', '차돌박이떡볶이', '통큰오짱떡볶이', '매운', NULL, '튀김o', NULL, NULL, '밀가루'),\n" +
                    "('snack_17', '두끼', '37.5911174', '127.018570', '서울 성북구 보문로34길 59', '떡볶이', '', '매운', '안매운', '튀김o', '튀김x', '밥', '밀가루');";

            database.execSQL(sqlInsertSnack);

            String sqlInsertWest = "INSERT OR IGNORE INTO `store_west` (`st_id`, `st_name`, `map_x`, `map_y`, `map_location`, `st_re_menu1`, `st_re_menu2`, `attr1`, `attr2`, `attr3`, `attr4`) VALUES\n" +
                    "('west_01', '니뽕내뽕', '37.5912247', '127.017754', '서울특별시 성북구 동선동1가 18', '봉골레', '차뽕', '고기X', '', '밀가루', ''),\n" +
                    "('west_02', '소르빌로', '37.5929642', '127.018501', '서울특별시 성북구 동선동1가 92-3', '까르보나라', '스테이크백반', '고기X', '고기O', '밀가루', '밥'),\n" +
                    "('west_03', '소테', '37.5909653', '127.019714', '서울시 성북구 동선동2가 135-7', '마늘소테', '알리오올리오', '고기X', '고기O', '밀가루', '밥'),\n" +
                    "('west_04', '언엘리셰프', '37.5918999', '127.018471', '서울특별시 성북구 동소문로20다길 30-7 지하 1층', '백명란크림파스타', '알리오올리오', '고기X', '고기O', '밀가루', '밥'),\n" +
                    "('west_05', '쵸이양식', '37.5922699', '127.017982', '서울특별시 성북구 동선동1가 85-79', '클래식치즈피자', '페퍼로니피자', '고기X', '고기O', '밀가루', ''),\n" +
                    "('west_06', '레지아노', '37.5907669', '127.020065', '서울특별시 성북구 동선동2가 164', '게살로제파스타', '토마토 모짜렐라 파스타', '고기X', '고기O', '밀가루', ''),\n" +
                    "('west_07', '롤링파스타', '37.5927849', '127.017563', '서울특별시 성북구 동선동1가 104 2층', '매운우삼겹토마토파스타', '로제크림쉬림프파스타', '고기X', '고기O', '밀가루', ''),\n" +
                    "('west_08', '마늘과 올리브', '37.5917130', '127.018074', '서울시 성북구 동선동 1가 85-93 2층', '알리오올리오', '하프앤하프피자', '고기X', '', '밀가루', ''),\n" +
                    "('west_09', '마이란', '37.5916862', '127.018288', '서울특별시 성북구 동선동1가 85-96', '새우알리오올리오', '오믈렛', '고기X', '고기O', '밀가루', '밥'),\n" +
                    "('west_10', '미오지오', '37.5915680', '127.019646', '서울특별시 성북구 동선동3가 248', '빠네크림파스타', '새우로제파스타', '고기X', '고기O', '밀가루', ''),\n" +
                    "('west_11', '문화식당', '37.5910645', '127.020294', '서울시 성북구 동선동2가 150 지하1층', '오므라이스', '삼합', '고기X', '고기O', '밀가루', ''),\n" +
                    "('west_12', '버거바이블', '37.5901642', '127.018173', '서울특별시 성북구 동선동2가 3-6 1층', '바이블버거', 'USA버거', '고기X', '고기O', '밀가루', ''),\n" +
                    "('west_13', '버거파크', '37.5906219', '127.018402', '서울특별시 성북구 동선동2가 10 1층', '베이컨치즈버거', '치즈버거', '', '고기O', '밀가루', ''),\n" +
                    "('west_14', '리얼파스타', '37.5918924', '127.017484', '서울 성북구 동소문로20다길 15', '차돌매콤크림파스타', '베이컨새우크림파스타', '', '고기O', '밀가루', '');";
            database.execSQL(sqlInsertWest);

            println("테이블 생성됨");
        } else { //데이터베이스 생성이 안된 경우
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }

    //4. 데이터 조회
    //StoreDetailFrag 에서 사용 - 음식점 정보 조회
    public void getStoreDetail(String selected_table_name, String selected_store_name) {
        notMenuCnt=0;
        if (database != null) {
            String sql = "select st_id, st_name, map_x, map_y, map_location, st_re_menu1, st_re_menu2 from " + selected_table_name + " where st_name like '" + selected_store_name + "'";
            Cursor cursor = database.rawQuery(sql, null); //execSQL 은 반환값이 없고 rawQuery는 반환값 있는 경우 사용.
            cursorCnt = cursor.getCount();

            for (int i = 0; i < cursor.getCount(); i++) { //테이블에 존재하는 레코드를 모두 출력
                cursor.moveToNext(); //for문 돌며 다음 레코드를 가리킴
                String st_id = cursor.getString(0);
                String st_name = cursor.getString(1);
                String map_x = cursor.getString(2);
                String map_y = cursor.getString(3);
                String map_location = cursor.getString(4);
                String st_re_menu1 = cursor.getString(5);
                String st_re_menu2 = cursor.getString(6);
                if(TextUtils.isEmpty(st_re_menu2)) {
                    result = st_id + "," + st_name + "," + st_re_menu1 + "," + map_location + "," + map_x + "," + map_y;
                    notMenuCnt++;
                }
                else{
                    result = st_id + "," + st_name + "," + st_re_menu1 + "," + st_re_menu2 + "," + map_location + "," + map_x + "," + map_y;
                }
            }
            cursor.close(); //커서 종료
        }
    }

    //store_frag 에서 사용 - 카테고리 별 음식점 리스트 조회
    public void selectData2(String tableName) {
        if (database != null) {
            switch (tableName) {
                case "store_cafe":
                    sql = "select st_id, st_name from " + tableName;
                    break;
                case "store_etc":
                    sql = "select st_id, st_name from " + tableName + " where attr1 like '" + attrarray[0] + "'" + "OR attr2 like '" + attrarray[0] + "'";
                    break;
                case "store_chi":
                    sql = "select st_id, st_name from " + tableName + " where (attr1 like '" + attrarray[0] + "'" + "OR attr2 like '" + attrarray[0] + "')" +
                            " AND (attr3 like '" + attrarray[1] + "'" + "OR attr4 like '" + attrarray[1] + "')" +
                            " AND (attr5 like '" + attrarray[2] + "'" + "OR attr6 like '" + attrarray[2] + "')" +
                            " AND (attr7 like '" + attrarray[3] + "'" + "OR attr8 like '" + attrarray[3] + "')" +
                            " AND (attr9 like '" + attrarray[4] + "'" + "OR attr10 like '" + attrarray[4] + "')";
                    break;
                case "store_west":
                    sql = "select st_id, st_name from " + tableName + " where (attr1 like '" + attrarray[0] + "'" + "OR attr2 like '" + attrarray[0] + "')" +
                            " AND (attr3 like '" + attrarray[1] + "'" + "OR attr4 like '" + attrarray[1] + "')" ;
                    break;
                case "store_meat":
                    sql = "select st_id, st_name from " + tableName + " where attr1 like '" + attrarray[0] + "'" ;
                    break;
                case "store_snack":
                    sql = "select st_id, st_name from " + tableName + " where (attr1 like '" + attrarray[0] + "'" + "OR attr2 like '" + attrarray[0] + "')" +
                            " AND (attr3 like '" + attrarray[1] + "'" + "OR attr4 like '" + attrarray[1] + "')" +
                            " AND (attr5 like '" + attrarray[2] + "'" + "OR attr6 like '" + attrarray[2] + "')" ;
                    break;
                case "store_kor":
                case "store_jpn":
                    sql = "select st_id, st_name from " + tableName + " where (attr1 like '" + attrarray[0] + "'" + "OR attr2 like '" + attrarray[0] + "')" +
                            " AND (attr3 like '" + attrarray[1] + "'" + "OR attr4 like '" + attrarray[1] + "')" +
                            " AND (attr5 like '" + attrarray[2] + "'" + "OR attr6 like '" + attrarray[2] + "')" +
                            " AND (attr7 like '" + attrarray[3] + "'" + "OR attr8 like '" + attrarray[3] + "')" ;
                    break;
            }
            Cursor cursor = database.rawQuery(sql, null); //execSQL 은 반환값이 없고 rawQuery는 반환값 있는 경우 사용. cursor을 이용해서 테이블 한 레코드씩 내려가며 검색
            cursor.move(0);
            cursorCnt = cursor.getCount();
            for(int i=0; i<cursor.getCount(); i++){
                cursor.moveToNext(); //for문 돌며 다음 레코드를 가리킴
                String st_id = cursor.getString(0);
                String st_name = cursor.getString(1);
                String packName = this.getPackageName();
                int lid = this.getResources().getIdentifier(st_id, "string", packName);
                String tag = this.getResources().getString(lid);
                result = st_id + "," + st_name;
                storeFrag.addadapter(st_name, tag);
            }
            cursor.move(0);
            cursor.close(); //커서 종료
        }
    }

    public void showdatalist(String tableName) {
        if (database != null) {
            sql = "select st_id, st_name from " + tableName;
            Cursor cursor1 = database.rawQuery(sql, null); //execSQL 은 반환값이 없고 rawQuery는 반환값 있는 경우 사용. cursor을 이용해서 테이블 한 레코드씩 내려가며 검색
            cursor1.move(0);
            cursorCnt = cursor1.getCount();
            for (int i = 0; i < cursor1.getCount(); i++) {
                cursor1.moveToNext(); //for문 돌며 다음 레코드를 가리킴
                String st_id = cursor1.getString(0);
                String st_name = cursor1.getString(1);
                String packName = this.getPackageName();
                int lid = this.getResources().getIdentifier(st_id, "string", packName);
                String tag = this.getResources().getString(lid);
                result = st_id + "," + st_name;
                storeFrag.addadapter(st_name, tag);
            }
            cursor1.move(0);
            cursor1.close(); //커서 종료
        }
    }

    // 조회한 데이터 개수 반환
    public static int getCursorCnt() {
        return cursorCnt;
    }

    // 조회한 데이터 return
    public String putData() {
        return result;
    }

    //데이터베이스 값을 받아와 로그 형태로 찍어주기 위함 (확인용)
    public void println(String data) {
        System.out.println(data);
    }


    public static void onFragmentChanged(int index) {
        FragmentTransaction transaction = manager.beginTransaction();

        //프래그먼트 전환
        switch (index) {
            case 0:
                transaction.replace(R.id.container, homeFrag);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.container, rouletteFrag);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.container, categoryFrag);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 3:
                transaction.replace(R.id.container, preferFragKor);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 4:
                transaction.replace(R.id.container, preferFragChi);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 5:
                transaction.replace(R.id.container, preferFragWest);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 6:
                transaction.replace(R.id.container, preferFragJpn);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 7:
                transaction.replace(R.id.container, preferFragSnack);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 8:
                transaction.replace(R.id.container, preferFragMeat);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 9:
                transaction.replace(R.id.container, preferFragEtc);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 10:
                MenuAdapter.items.clear();
                transaction.replace(R.id.container, storeFrag);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 11:
                transaction.replace(R.id.container, storeDetailFrag);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        deleteTable();
    }
}
