package pt.ipg.covid_19;

import android.content.ContentValues;
import android.database.Cursor;

public class Converte {
    public static ContentValues categoriaToContentValues(Categoria categoria) {
        ContentValues valores = new ContentValues();

        valores.put(BdTableCategorias.CAMPO_DESCRICAO, categoria.getDescricao());

        return valores;
    }

    public static Categoria contentValuesToCategoria(ContentValues valores) {
        Categoria categoria = new Categoria();

        categoria.setId(valores.getAsLong(BdTableCategorias._ID));
        categoria.setDescricao(valores.getAsString(BdTableCategorias.CAMPO_DESCRICAO));

        return categoria;
    }

    public static ContentValues ArtigoCientificoToContentValues(ArtigoCientifico artigoCientifico) {
        ContentValues valores = new ContentValues();

        valores.put(BdTableArtigoCientifico.CAMPO_TITULO, artigoCientifico);
        valores.put(BdTableArtigoCientifico.CAMPO_ID_CATEGORIA, artigoCientifico.getIdCategoria());

        return valores;
    }

    public static ArtigoCientifico contentValuesToArtigoCientifico(ContentValues valores) {
        ArtigoCientifico artigoCientifico = new ArtigoCientifico();

        artigoCientifico.setId(valores.getAsLong(BdTableArtigoCientifico._ID));
        artigoCientifico.setTitulo(valores.getAsString(BdTableArtigoCientifico.CAMPO_TITULO));
        artigoCientifico.setIdCategoria(valores.getAsLong(BdTableArtigoCientifico.CAMPO_ID_CATEGORIA));
        artigoCientifico.setCategoria(valores.getAsString(BdTableArtigoCientifico.CAMPO_CATEGORIA));

        return artigoCientifico;
    }

    public static ArtigoCientifico cursorToArtigoCientifico(Cursor cursor) {
        ArtigoCientifico artigoCientifico = new ArtigoCientifico();

        artigoCientifico.setId(cursor.getLong(cursor.getColumnIndex(BdTableArtigoCientifico._ID)));
        artigoCientifico.setTitulo(cursor.getString(cursor.getColumnIndex(BdTableArtigoCientifico.CAMPO_TITULO)));
        artigoCientifico.setIdCategoria(cursor.getLong(cursor.getColumnIndex(BdTableArtigoCientifico.CAMPO_ID_CATEGORIA)));
        artigoCientifico.setCategoria(cursor.getString(cursor.getColumnIndex(BdTableArtigoCientifico.CAMPO_CATEGORIA)));

        return artigoCientifico;
    }
}
