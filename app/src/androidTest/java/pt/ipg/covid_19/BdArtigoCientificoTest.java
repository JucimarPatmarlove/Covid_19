package pt.ipg.covid_19;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BdArtigoCientificoTest {
    @Before
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
}
