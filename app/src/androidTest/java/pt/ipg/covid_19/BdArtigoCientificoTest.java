package pt.ipg.covid_19;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BdArtigoCientificoTest {
    @Before
    @After
    public void apagaBaseDados() {
        getTargetContext().deleteDatabase(BdArtigoCientificoOpenHelper.NOME_BASE_DADOS);
    }

    @Test
    public void consegueAbrirBaseDado() {
        // Context of the app under test.
        Context appContext = getTargetContext();

        BdArtigoCientificoOpenHelper openHelper = new BdArtigoCientificoOpenHelper(appContext);
        SQLiteDatabase bd = openHelper.getReadableDatabase();

        assertTrue(bd.isOpen());
        bd.close();
    }

    private Context getTargetContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }
    private long insereCategoria(BdTableCategorias tabelaCategorias, String descricao) {
        Categoria categoria = new Categoria();
        categoria.setDescricao(descricao);

        return insereCategoria(tabelaCategorias, categoria);
    }

    private long insereCategoria(BdTableCategorias tabelaCategorias, Categoria categoria) {
        long id = tabelaCategorias.insert(Converte.categoriaToContentValues(categoria));
        assertNotEquals(-1, id);

        return id;
    }

    private long insereArtigoCientifico(SQLiteDatabase bdArtigoCientifico, String titulo, String descCategoria) {
        BdTableCategorias tabelaCategorias = new BdTableCategorias(bdArtigoCientifico);

        long idCategoria = insereCategoria(tabelaCategorias, descCategoria);

        ArtigoCientifico artigoCientifico = new ArtigoCientifico();
        artigoCientifico.setTitulo(titulo);
        artigoCientifico.setIdCategoria(idCategoria);

        BdTableArtigoCientifico tabelaArtigoCientifico = new BdTableArtigoCientifico(bdArtigoCientifico);
        long id = tabelaArtigoCientifico.insert(Converte.artigoCientificoToContentValues(artigoCientifico));
        assertNotEquals(-1, id);

        return  id;
    }

    @Test
    public void consegueInserirCategorias() {
        Context appContext = getTargetContext();

        BdArtigoCientificoOpenHelper openHelper = new BdArtigoCientificoOpenHelper(appContext);
        SQLiteDatabase bdArtigoCientifico = openHelper.getWritableDatabase();

        BdTableCategorias tabelaCategorias = new BdTableCategorias(bdArtigoCientifico);

        insereCategoria(tabelaCategorias, "Acadêmico");

        bdArtigoCientifico.close();
    }

    @Test
    public void consegueLerCategorias() {
        Context appContext = getTargetContext();

        BdArtigoCientificoOpenHelper openHelper = new BdArtigoCientificoOpenHelper(appContext);
        SQLiteDatabase bdArtigoCientifico = openHelper.getWritableDatabase();

        BdTableCategorias tabelaCategorias = new BdTableCategorias(bdArtigoCientifico);

        Cursor cursor = tabelaCategorias.query(BdTableCategorias.TODOS_CAMPOS, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();

        insereCategoria(tabelaCategorias, "Survey");

        cursor = tabelaCategorias.query(BdTableCategorias.TODOS_CAMPOS, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        bdArtigoCientifico.close();
    }

    @Test
    public void consegueAlterarCategorias() {
        Context appContext = getTargetContext();

        BdArtigoCientificoOpenHelper openHelper = new BdArtigoCientificoOpenHelper(appContext);
        SQLiteDatabase bdArtigoCientifico = openHelper.getWritableDatabase();

        BdTableCategorias tabelaCategorias = new BdTableCategorias(bdArtigoCientifico);

        Categoria categoria = new Categoria();
        categoria.setDescricao("Revisão Bibliométrica");

        long id = insereCategoria(tabelaCategorias, categoria);

        categoria.setDescricao("Estudo de Caso");
        int registosAfetados = tabelaCategorias.update(Converte.categoriaToContentValues(categoria), BdTableCategorias._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosAfetados);

        bdArtigoCientifico.close();
    }

    @Test
    public void consegueEliminarCategorias() {
        Context appContext = getTargetContext();

        BdArtigoCientificoOpenHelper openHelper = new BdArtigoCientificoOpenHelper(appContext);
        SQLiteDatabase bdArtigoCientifico = openHelper.getWritableDatabase();

        BdTableCategorias tabelaCategorias = new BdTableCategorias(bdArtigoCientifico);

        long id = insereCategoria(tabelaCategorias, "TESTE");

        int registosEliminados = tabelaCategorias.delete(BdTableCategorias._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosEliminados);

        bdArtigoCientifico.close();
    }

    @Test
    public void consegueInserirArtigoCientifico() {
        Context appContext = getTargetContext();

        BdArtigoCientificoOpenHelper openHelper = new BdArtigoCientificoOpenHelper(appContext);
        SQLiteDatabase bdArtigoCientifico = openHelper.getWritableDatabase();

        insereArtigoCientifico(bdArtigoCientifico, "Estudo de Caso", "Acadêmico");

        bdArtigoCientifico.close();
    }

    @Test
    public void consegueLerArtigoCientifico() {
        Context appContext = getTargetContext();

        BdArtigoCientificoOpenHelper openHelper = new BdArtigoCientificoOpenHelper(appContext);
        SQLiteDatabase bdArtigoCientifico = openHelper.getWritableDatabase();

        BdTableArtigoCientifico tabelaArtigoCientifico = new BdTableArtigoCientifico(bdArtigoCientifico);

        Cursor cursor = tabelaArtigoCientifico.query(BdTableArtigoCientifico.TODOS_CAMPOS, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();

        insereArtigoCientifico(bdArtigoCientifico, "Não sabemos como a epidemia começou", "Survey");

        cursor = tabelaArtigoCientifico.query(BdTableArtigoCientifico.TODOS_CAMPOS, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        bdArtigoCientifico.close();
    }

    @Test
    public void consegueAlterarArtigoCientifico() {
        Context appContext = getTargetContext();

        BdArtigoCientificoOpenHelper openHelper = new BdArtigoCientificoOpenHelper(appContext);
        SQLiteDatabase bdArtigoCientifico = openHelper.getWritableDatabase();

        long idArtigoCientifico = insereArtigoCientifico(bdArtigoCientifico, "Estes são os maiores quebra-cabeças científicos sobre a pandemia", "Acadêmico");

        BdTableArtigoCientifico tabelaArtigoCientifico = new BdTableArtigoCientifico(bdArtigoCientifico);

        Cursor cursor = tabelaArtigoCientifico.query(BdTableArtigoCientifico.TODOS_CAMPOS, BdTableArtigoCientifico.CAMPO_ID_COMPLETO + "=?", new String[]{ String.valueOf(idArtigoCientifico) }, null, null, null);
        assertEquals(1, cursor.getCount());

        assertTrue(cursor.moveToNext());
        ArtigoCientifico artigoCientifico = Converte.cursorToArtigoCientifico(cursor);
        cursor.close();

        assertEquals("Estes são os maiores quebra-cabeças científicos sobre a pandemia", artigoCientifico.getTitulo());

        artigoCientifico.setTitulo("A mortalidade ainda não é conhecida com precisão");
        int registosAfetados = tabelaArtigoCientifico.update(Converte.artigoCientificoToContentValues(artigoCientifico), BdTableArtigoCientifico.CAMPO_ID_COMPLETO + "=?", new String[]{String.valueOf(artigoCientifico.getId())});
        assertEquals(1, registosAfetados);

        bdArtigoCientifico.close();
    }

    @Test
    public void consegueEliminarArtigoCientifico() {
        Context appContext = getTargetContext();

        BdArtigoCientificoOpenHelper openHelper = new BdArtigoCientificoOpenHelper(appContext);
        SQLiteDatabase bdArtigoCientifico = openHelper.getWritableDatabase();

        long id = insereArtigoCientifico(bdArtigoCientifico, "Estes são os maiores quebra-cabeças científicos sobre a pandemia", "Thriller");

        BdTableArtigoCientifico tabelaLivros = new BdTableArtigoCientifico(bdArtigoCientifico);
        int registosEliminados = tabelaLivros.delete(BdTableArtigoCientifico._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosEliminados);

        bdArtigoCientifico.close();
    }
}
