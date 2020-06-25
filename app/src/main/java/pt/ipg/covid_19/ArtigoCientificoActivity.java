package pt.ipg.covid_19;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class ArtigoCientificoActivity extends AppCompatActivity {

    private Fragment fragmentActual = null;
    private int menuActual = R.menu.menu_lista_artigo_cientifico;
    private Menu menu;
    private ArtigoCientifico artigoCientifico = null;

    public ArtigoCientifico getArtigoCientifico() {
        return artigoCientifico;
    }

    public void setFragmentActual(Fragment fragmentActual) {
        this.fragmentActual = fragmentActual;
    }

    public void setMenuActual(int menuActual) {
        if (menuActual != this.menuActual) {
            this.menuActual = menuActual;
            invalidateOptionsMenu();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void artigoCientificoAlterado(ArtigoCientifico artigoCientifico) {
        this.artigoCientifico = artigoCientifico;

        boolean mostraEditarEliminar = (artigoCientifico != null);

        menu.findItem(R.id.action_alterar_artigo_cientifico).setVisible(mostraEditarEliminar);
        menu.findItem(R.id.action_eliminar_artigo_cientifico).setVisible(mostraEditarEliminar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(menuActual, menu);

        this.menu = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (menuActual == R.menu.menu_lista_artigo_cientifico) {
            if (processaOpcoesMenuListaLivros(id)) return true;
        } else if (menuActual == R.menu.menu_inserir_artigo_cientifico) {
            if (processaOpcoesMenuInserirArtigoCientifico(id)) return true;
        } else if (menuActual == R.menu.menu_alterar_artigo_cientifico) {
            if (processaOpcoesMenuAlterarArtigoCientifico(id)) return true;
        } else if (menuActual == R.menu.menu_eliminar_artigo_cientifico) {
            if (processaOpcoesMenuEliminarArtigoCientifico(id)) return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean processaOpcoesMenuEliminarArtigoCientifico(int id) {
        EliminarArtigoCientificoFragment eliminarArtigoCientificoFragment = (EliminarArtigoCientificoFragment) fragmentActual;

        if (id == R.id.action_cancelar) {
            eliminarArtigoCientificoFragment.cancelar();
            return true;
        }

        return false;
    }

    private boolean processaOpcoesMenuAlterarArtigoCientifico(int id) {
        AlteraArtigoCientificoFragment alterarArtigoCientificoFragment = (AlteraArtigoCientificoFragment) fragmentActual;

        if (id == R.id.action_guardar) {
            alterarArtigoCientificoFragment.guardar();
            return true;
        } else if (id == R.id.action_cancelar) {
            alterarArtigoCientificoFragment.cancelar();
            return true;
        }

        return false;
    }

    private boolean processaOpcoesMenuInserirArtigoCientifico(int id) {
        AdicionarArtigoCientificoFragment adicionarArtigoCientificoFragment = (AdicionarArtigoCientificoFragment) fragmentActual;

        if (id == R.id.action_guardar) {
            adicionarArtigoCientificoFragment.guardar();
            return true;
        } else if (id == R.id.action_cancelar) {
            adicionarArtigoCientificoFragment.cancelar();
            return true;
        }

        return false;
    }

    private boolean processaOpcoesMenuListaLivros(int id) {
        ListaArtigoCientificoFragment listaArtigoCientificoFragment = (ListaArtigoCientificoFragment) fragmentActual;

        if (id == R.id.action_inserir_artigo_cientifico) {
            listaArtigoCientificoFragment.novoArtigoCientifico();
            return true;
        } else if (id == R.id.action_alterar_artigo_cientifico) {
            listaArtigoCientificoFragment.alterarArtigoCientifico();
            return true;
        } else if (id == R.id.action_eliminar_artigo_cientifico) {
            listaArtigoCientificoFragment.eliminaArtigoCientifico();
            return true;
        }

        return false;
    }

    public void ArtigoCientificoAlterado(ArtigoCientifico artigoCientifico) {
        this.artigoCientifico = artigoCientifico;

        boolean mostraEditarEliminar = (artigoCientifico != null);

        menu.findItem(R.id.action_alterar_artigo_cientifico).setVisible(mostraEditarEliminar);
        menu.findItem(R.id.action_eliminar_artigo_cientifico).setVisible(mostraEditarEliminar);
    }
}