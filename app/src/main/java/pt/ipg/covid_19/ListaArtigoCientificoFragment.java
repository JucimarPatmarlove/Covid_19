package pt.ipg.covid_19;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class ListaArtigoCientificoFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_artigo_cientifico, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.buttonNovo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               novoArtigoCientifico();
            }
        });
        
        view.findViewById(R.id.buttonAlterar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarArtigoCientifico();
            }
        });


    }

    private void alterarArtigoCientifico() {
        NavController navController = NavHostFragment.findNavController(ListaArtigoCientificoFragment.this);
        navController.navigate(R.id.action_alterarArtigoCientificoFragment_to_ListaArtigoCientificoFragment);
    }

    private void novoArtigoCientifico() {
        NavController navController = NavHostFragment.findNavController(ListaArtigoCientificoFragment.this);
        navController.navigate(R.id.action_Novo_Artigo_Cientifico);
    }
}