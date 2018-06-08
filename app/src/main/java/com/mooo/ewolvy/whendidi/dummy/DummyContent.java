package com.mooo.ewolvy.whendidi.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 15;


    private static final String[] names = {
            "Cambiar aceite del coche",
            "Pasar tratamiento anti-bichos",
            "Limpiar filtro lavadora",
            "Poner sal en lavavajillas"
    };

    private static final int[] colors = {
            0xFFAAAAAA,
            0xFFAAAA00,
            0xFF00AAAA,
            0xFF309040
    };

    private static final String[] lastTimes = {
            "2018-05-05",
            "2017-06-04",
            "2016-07-03",
            "2015-08-02",
    };

    private static final String[] remindOns = {
            "2019-07-05",
            "2020-06-04",
            "2021-07-03",
            "NO",
    };

    private static final String[] datesHistories = {
            "2014-12-21\n2015-06-21",
            "2013-11-19",
            "2013-10-18\n2013-12-25",
            "2013-09-21\n2013-10-12\n2013-11-01"
    };


    static {
        // Add some sample items.
        Random random = new Random();

        for (int i = 0; i <= COUNT; i++){
            int randOne = random.nextInt(4);
            int randTwo = random.nextInt(4);
            int randThree = random.nextInt(4);
            int randFour = random.nextInt(4);
            int randFive = random.nextInt(4);

            addItem(createDummyItem(String.valueOf(i),
                    names[randOne],
                    datesHistories[randFive],
                    colors[randTwo],
                    lastTimes[randThree],
                    remindOns[randFour]));

        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(String id, String name, String datesHistory, int color, String lastTime, String remindOn) {
        return new DummyItem(id, name, datesHistory, color, lastTime, remindOn);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        /*public final String id;
        public final String content;
        public final String details;*/

        public final String id;
        public final String name;
        public final String datesHistory;
        public final int color;
        public final String lastTime;
        public final String remindOn;

        DummyItem(String id, String name, String datesHistory, int color, String lastTime, String remindOn) {
            this.id = id;
            this.name = name;
            this.datesHistory = datesHistory;
            this.color = color;
            this.lastTime = lastTime;
            this.remindOn = remindOn;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
