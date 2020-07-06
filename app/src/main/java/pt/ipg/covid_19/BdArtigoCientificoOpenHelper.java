package pt.ipg.covid_19;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BdArtigoCientificoOpenHelper extends SQLiteOpenHelper {

    public static final String NOME_BASE_DADOS = "ArtigoCientifico.bd";
    private static final int VERSAO_BASE_DADOS = 1;
    private static final boolean DESENVOLVIMENTO = true;

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
      */
    public BdArtigoCientificoOpenHelper(@Nullable Context context) {
        super(context, NOME_BASE_DADOS, null, VERSAO_BASE_DADOS);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        BdTableCategorias TableCategorias = new BdTableCategorias(db);
        TableCategorias.cria();

        BdTableArtigoCientifico tableArtigoCientifico = new BdTableArtigoCientifico(db);
        tableArtigoCientifico.cria();

        if (DESENVOLVIMENTO) {
            seedData(db);
        }
    }
    private void seedData(SQLiteDatabase db) {
        BdTableCategorias tabelaCategorias = new BdTableCategorias(db);

        Categoria categoria = new Categoria();
        categoria.setDescricao("Acadêmico");
        long idCatAcad = tabelaCategorias.insert(Converte.categoriaToContentValues(categoria));

        categoria = new Categoria();
        categoria.setDescricao("Estudo de Caso");
        long idCatEstudo = tabelaCategorias.insert(Converte.categoriaToContentValues(categoria));

        categoria = new Categoria();
        categoria.setDescricao("Revisão Bibliométrica");
        long idCatRevisao = tabelaCategorias.insert(Converte.categoriaToContentValues(categoria));

        categoria = new Categoria();
        categoria.setDescricao("Survey");
        tabelaCategorias.insert(Converte.categoriaToContentValues(categoria));

        BdTableArtigoCientifico tabelaArtigoCientifico = new BdTableArtigoCientifico(db);

        ArtigoCientifico artigoCientifico = new ArtigoCientifico();
        artigoCientifico.setTitulo("Víros Matador");
        artigoCientifico.setIdCategoria(idCatAcad);
        tabelaArtigoCientifico.insert(Converte.artigoCientificoToContentValues(artigoCientifico));

        artigoCientifico = new ArtigoCientifico();
        artigoCientifico.setTitulo("Estes são os maiores quebra-cabeças científicos sobre a pandemia");
        artigoCientifico.setIdCategoria(idCatAcad);
        tabelaArtigoCientifico.insert(Converte.artigoCientificoToContentValues(artigoCientifico));

        artigoCientifico = new ArtigoCientifico();
        artigoCientifico.setTitulo("Não sabemos como a epidemia começou");
        artigoCientifico.setIdCategoria(idCatEstudo);
        tabelaArtigoCientifico.insert(Converte.artigoCientificoToContentValues(artigoCientifico));

        artigoCientifico = new ArtigoCientifico();
        artigoCientifico.setTitulo("A mortalidade ainda não é conhecida com precisão");
        artigoCientifico.setIdCategoria(idCatRevisao);
        tabelaArtigoCientifico.insert(Converte.artigoCientificoToContentValues(artigoCientifico));
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
