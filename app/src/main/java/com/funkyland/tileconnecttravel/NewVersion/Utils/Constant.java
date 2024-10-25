package com.funkyland.tileconnecttravel.NewVersion.Utils;

import android.view.View;

import com.funkyland.tileconnecttravel.R;

public class Constant {

    public static final String ORDERID_LIST = "ORDERID_LIST";
    public static final String ONE_KIND = "One Kind";
    public static final String SUFFLE = "Suffle";
    public static final String HINT = "Hint";
    public static final String Heart = "Heart";


    public static final String START_ACTIVITY = "StartActivity";
    public static final String SPARKELA_ACTIVITY = "SparkelaActivity";
    public static final String COUNTRY_ACTIVITY = "CountryActivity";
    public static final String WORLD_ACTIVITY = "WorldActivity";


//    public static final String PACK_1 = "android.test.purchased";
        public static final String PACK_1 = "starter_pack";
    public static final String PACK_2 = "medium_pack";
    public static final String PACK_3 = "advanced_pack";
    public static final String PACK_4 = "remove_ads";
    public static final String PACK_5 = "30_onekind";
    public static final String PACK_6 = "50_onekind";
    public static final String PACK_7 = "100_onekind";
    public static final String PACK_8 = "30_shuffle";
    public static final String PACK_9 = "50_shuffle";
    public static final String PACK_10 = "100_shuffle";
    public static final String PACK_11 = "30_hint";
    public static final String PACK_12 = "50_hint";
    public static final String PACK_13 = "100_hint";
    public static final String PACK_14 = "ultimate_pack";
//    public static final String PACK_14 = "android.test.purchased";

    public static final String PACK_1_PRICE = "PACK_1_PRICE";
    public static final String PACK_2_PRICE = "PACK_2_PRICE";
    public static final String PACK_3_PRICE = "PACK_3_PRICE";
    public static final String PACK_4_PRICE = "PACK_4_PRICE";
    public static final String PACK_5_PRICE = "PACK_5_PRICE";
    public static final String PACK_6_PRICE = "PACK_6_PRICE";
    public static final String PACK_7_PRICE = "PACK_7_PRICE";
    public static final String PACK_8_PRICE = "PACK_8_PRICE";
    public static final String PACK_9_PRICE = "PACK_9_PRICE";
    public static final String PACK_10_PRICE = "PACK_10_PRICE";
    public static final String PACK_11_PRICE = "PACK_11_PRICE";
    public static final String PACK_12_PRICE = "PACK_12_PRICE";
    public static final String PACK_13_PRICE = "PACK_13_PRICE";
    public static final String PACK_14_PRICE = "PACK_14_PRICE";

    public static final String PACK_1_CURRENCY_CODE = "PACK_1_PRICE_CURRENCY_CODE";
    public static final String PACK_2_CURRENCY_CODE = "PACK_2_PRICE_CURRENCY_CODE";
    public static final String PACK_3_CURRENCY_CODE = "PACK_3_PRICE_CURRENCY_CODE";
    public static final String PACK_4_CURRENCY_CODE = "PACK_4_PRICE_CURRENCY_CODE";
    public static final String PACK_5_CURRENCY_CODE = "PACK_5_PRICE_CURRENCY_CODE";
    public static final String PACK_6_CURRENCY_CODE = "PACK_6_PRICE_CURRENCY_CODE";
    public static final String PACK_7_CURRENCY_CODE = "PACK_7_PRICE_CURRENCY_CODE";
    public static final String PACK_8_CURRENCY_CODE = "PACK_8_PRICE_CURRENCY_CODE";
    public static final String PACK_9_CURRENCY_CODE = "PACK_9_PRICE_CURRENCY_CODE";
    public static final String PACK_10_CURRENCY_CODE = "PACK_10_PRICE_CURRENCY_CODE";
    public static final String PACK_11_CURRENCY_CODE = "PACK_11_PRICE_CURRENCY_CODE";
    public static final String PACK_12_CURRENCY_CODE = "PACK_12_PRICE_CURRENCY_CODE";
    public static final String PACK_13_CURRENCY_CODE = "PACK_13_PRICE_CURRENCY_CODE";
    public static final String PACK_14_CURRENCY_CODE = "PACK_14_PRICE_CURRENCY_CODE";

    //For Game Type Like (Around the World,, Around the Sparkela, Adventure , Classic)
    public static final String GAME_TYPE = "GAME_TYPE";
    public static final String GAME_TYPE_EASY = "GAME_TYPE_EASY";
    public static final String GAME_TYPE_HARD = "GAME_TYPE_HARD";
    public static final String GAME_TYPE_RELAXED = "GAME_TYPE_RELAXED";

    public static final String GAME_MODE = "GAME_MODE";
    public static final String GAME_MODE_ATW = "GAME_MODE_ATW";//Around the World
    public static final String GAME_MODE_ATS = "GAME_MODE_ATS";//Around the Sparkela
    public static final String GAME_MODE_ADVE = "GAME_MODE_ADVE";//Adventure
    public static final String GAME_MODE_CLAS = "GAME_MODE_CLAS";//Classic

    public static final String GAME_SUB_MODE = "GAME_SUB_MODE";


    public static int ui_flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;


    public static Integer[] Country_Asia = new Integer[]{R.string.india, R.string.indonesia, R.string.japan, R.string.korea, R.string.russia, R.string.thailand, R.string.vietNam};
    public static Integer[] Country_Europe = new Integer[]{R.string.belgium, R.string.france, R.string.germany, R.string.greece, R.string.ireland, R.string.italy, R.string.portugal, R.string.spain, R.string.netherlands, R.string.uk};
    public static Integer[] Country_America = new Integer[]{R.string.argentina, R.string.brazil, R.string.canada, R.string.colombia, R.string.mexico, R.string.peru, R.string.usa};
    public static Integer[] Country_Australia = new Integer[]{R.string.australia};
    public static Integer[] Country_Africa = new Integer[]{R.string.moroco, R.string.south_africa};

    public static String[] Country_Asia_path = new String[]{"india", "indonesia", "japan", "korea", "russia", "thailand", "vietnam"};
    public static String[] Country_Europe_path = new String[]{"belgium", "france", "germany", "greece", "ireland", "italy", "portugal", "spain", "netherlands", "uk"};
    public static String[] Country_America_path = new String[]{"argentina", "brazil", "canada", "colombia", "mexico", "peru", "usa"};
    public static String[] Country_Australia_path = new String[]{"australia"};
    public static String[] Country_Africa_path = new String[]{"moroco", "southafrica"};

    public static Integer[] Country_Asia_image = new Integer[]{R.drawable.india, R.drawable.indonesia, R.drawable.japan, R.drawable.korea, R.drawable.russia, R.drawable.thailand, R.drawable.vietnam};
    public static Integer[] Country_Europe_image = new Integer[]{R.drawable.belgium, R.drawable.france, R.drawable.germany, R.drawable.greece, R.drawable.ireland, R.drawable.italy, R.drawable.portugal, R.drawable.spain, R.drawable.netherlands, R.drawable.uk};
    public static Integer[] Country_America_image = new Integer[]{R.drawable.argentina, R.drawable.brazil, R.drawable.canada, R.drawable.colombia, R.drawable.mexico, R.drawable.peru, R.drawable.usa};
    public static Integer[] Country_Australia_image = new Integer[]{R.drawable.australia};
    public static Integer[] Country_Africa_image = new Integer[]{R.drawable.moroco, R.drawable.southafrica};


    public static Integer[] theme = new Integer[]{R.string.animal, R.string.bike, R.string.buiding, R.string.candy, R.string.car, R.string.diamond, R.string.fans, R.string.flight, R.string.food, R.string.fruit, R.string.jungle, R.string.motor,/*R.string.ocean,*/ R.string.ocean_animal, R.string.phone, R.string.planet, R.string.sea_food, R.string.ship, R.string.smile, R.string.train};
    public static String[] theme_path = new String[]{"animal", "bike", "buiding", "candy", "car", "diamond", "fans", "flight", "food", "fruit", "jungle", "motor", /*"ocean",*/ "oceananimal", "phone", "planet", "seafood", "ship", "smile", "train"};
    public static Integer[] theme_image = new Integer[]{R.drawable.theme_animal, R.drawable.theme_bike, R.drawable.theme_buiding, R.drawable.theme_candy, R.drawable.theme_car, R.drawable.theme_diamond, R.drawable.theme_fans, R.drawable.theme_flight, R.drawable.theme_food, R.drawable.theme_fruit, R.drawable.theme_jungle, R.drawable.theme_motor, R.drawable.theme_ocean_animal, R.drawable.theme_phone, R.drawable.theme_planet, R.drawable.theme_sea_food, R.drawable.theme_ship, R.drawable.theme_smile, R.drawable.theme_train};

}
