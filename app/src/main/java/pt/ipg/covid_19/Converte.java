package pt.ipg.covid_19;

import android.content.ContentValues;
import android.database.Cursor;

public class Converte {
    public static ContentValues categoriaToContentValues(Categoria categoria) {
        ContentValues valores = new ContentValues();

        valores.put(BdTableCategorias.CAMPO_DESCRICAO, categoria.getDescricao());

        return valores;
    }

    public static ArtigoCientifico cursorToArtigoCientifico(Cursor cursor) {
        return null;
    }
}

