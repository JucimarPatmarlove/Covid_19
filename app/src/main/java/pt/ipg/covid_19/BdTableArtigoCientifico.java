package pt.ipg.covid_19;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTableArtigoCientifico implements BaseColumns {
    private final SQLiteDatabase db;

    public BdTableArtigoCientifico(SQLiteDatabase db) {
        this.db = db;
    }

    public void cria() {

    }
}
