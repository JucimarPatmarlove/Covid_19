package pt.ipg.covid_19;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EliminarArtigoCientificoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EliminarArtigoCientificoFragment extends Fragment {

    private TextView textViewTitulo;
    private TextView textViewCategoria;
    private ArtigoCientifico artigoCientifico;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eliminar_artigo_cientifico, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();

        ArtigoCientificoActivity activity = (ArtigoCientificoActivity) getActivity();
        activity.setFragmentActual(this);

        activity.setMenuActual(R.menu.menu_eliminar_artigo_cientifico);

        textViewTitulo = (TextView) view.findViewById(R.id.textViewTitulo);
        textViewCategoria = (TextView) view.findViewById(R.id.textViewCategoria);

        Button buttonEliminar = (Button) view.findViewById(R.id.buttonEliminar);
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });

        Button buttonCancelar = (Button) view.findViewById(R.id.buttonCancelar);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

        artigoCientifico = activity.getArtigoCientifico();
        textViewTitulo.setText(artigoCientifico.getTitulo());
        textViewCategoria.setText(artigoCientifico.getCategoria());
    }

    public void cancelar() {
        NavController navController = NavHostFragment.findNavController(EliminarArtigoCientificoFragment.this);
        navController.navigate(R.id.action_eliminar_artigo_cientifico);
    }

    public void eliminar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Eliminar Artigo Cientifico");
        builder.setMessage("Tem a certeza que pretende eliminar o Artigo Cientifico '" + artigoCientifico.getTitulo() + "'");
        builder.setIcon(R.drawable.ic_baseline_delete_24);
        builder.setPositiveButton("Sim, eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmaEliminar();
            }
        });

        builder.setNegativeButton("Não, cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // cancelar
            }
        });

        builder.show();
    }

    private void confirmaEliminar() {
        try {
            Uri enderecoLivro = Uri.withAppendedPath(ArtigoCientificoContentProvider.ENDERECO_LIVROS, String.valueOf(artigoCientifico.getId()));

            int apagados = getActivity().getContentResolver().delete(enderecoLivro, null, null);

            if (apagados == 1) {
                Toast.makeText(getContext(), "Artigo Cientifico eliminado com sucesso", Toast.LENGTH_SHORT).show();
                NavController navController = NavHostFragment.findNavController(EliminarArtigoCientificoFragment.this);
                navController.navigate(R.id.action_eliminar_artigo_cientifico);
                return;
            }
        } catch (Exception e) {
        }

        Snackbar.make(textViewTitulo, "Erro: Não foi possível eliminar o Artigo Cientifico", Snackbar.LENGTH_INDEFINITE).show();
    }
}