package br.edu.fateczl.dados;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    private RadioButton rbUm;
    private RadioButton rbDois;
    private RadioButton rbTres;
    private Spinner spQtdFace;
    private Button btnRolar;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rbUm = findViewById(R.id.rbUm);
        rbUm.setChecked(true);
        rbDois = findViewById(R.id.rbDois);
        rbTres = findViewById(R.id.rbTres);
        spQtdFace = findViewById(R.id.spQtdFace);
        tvResult = findViewById(R.id.tvResult);

        btnRolar = findViewById(R.id.btnRolar);
        btnRolar.setOnClickListener(op -> rolar());

        preencheSpinner();
    }

    private void rolar() {

        StringBuffer buffer = new StringBuffer();

        String dado = spQtdFace.getSelectedItem().toString();
        dado = dado.replaceAll("\\D", "");
        int faces = Integer.parseInt(dado);

        int rolagens = 1;
        if (rbDois.isChecked()){
            rolagens = 2;
        } else if (rbTres.isChecked()){
            rolagens = 3;
        }

        for (int i = 0; i < rolagens; i++) {
            buffer.append(gerarN(faces));
            if (i < rolagens - 1) {
                buffer.append(", ");
            }
        }

        tvResult.setText(buffer);
    }

    private int gerarN(int n){
        return (int)(Math.random() * n) + 1;
    }

    private void preencheSpinner(){
        List<String> lista = new ArrayList<>();

        lista.add("D4");
        lista.add("D6");
        lista.add("D8");
        lista.add("D10");
        lista.add("D12");
        lista.add("D20");
        lista.add("D100");


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQtdFace.setAdapter(adapter);
    }

}