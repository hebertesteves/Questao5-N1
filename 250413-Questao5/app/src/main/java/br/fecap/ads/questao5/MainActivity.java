package br.fecap.ads.questao5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declaração dos elementos da interface (View)
    private Button btnTelaPedido;

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

        // Vinculando o elemento com o View
        btnTelaPedido = findViewById(R.id.btnTelaPedido);

        // Função para chama a Tela Formulario de Pedido
        btnTelaPedido.setOnClickListener(view -> {
            // Invoca a "FormularioPedidoActivity"
            Intent intent = new Intent(this, FormularioPedidoActivity.class);
            startActivity(intent);
        });
    }
}