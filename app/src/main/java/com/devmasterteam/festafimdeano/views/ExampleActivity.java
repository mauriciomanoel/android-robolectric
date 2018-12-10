package com.devmasterteam.festafimdeano.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devmasterteam.festafimdeano.R;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        LinearLayout cardVantagens = (LinearLayout) findViewById(R.id.layoutCardVantagens);

        cardVantagens.removeAllViews();

        LinearLayout linha = (LinearLayout) getLayoutInflater().inflate(R.layout.list_detalhe_evento_contracheque, null);
        TextView lblEvento = (TextView) linha.findViewById(R.id.lblEvento);
        TextView lblValor = (TextView) linha.findViewById(R.id.lblValor);
        lblEvento.setText("Descricao");
        lblValor.setText("2.93");
        cardVantagens.addView(linha);

        LinearLayout linha2 = (LinearLayout) getLayoutInflater().inflate(R.layout.list_detalhe_evento_contracheque, null);
        TextView lblEvento2 = (TextView) linha2.findViewById(R.id.lblEvento);
        TextView lblValor2 = (TextView) linha2.findViewById(R.id.lblValor);
        lblEvento2.setText("Descricao2");
        lblValor2.setText("2.94");
        cardVantagens.addView(linha2);

    }
}
