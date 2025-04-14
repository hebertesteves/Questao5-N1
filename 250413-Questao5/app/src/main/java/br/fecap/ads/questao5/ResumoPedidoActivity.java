package br.fecap.ads.questao5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResumoPedidoActivity extends AppCompatActivity {

    // Declaração dos elementos da interface (View)
    private TextView textResultadoSabores;
    private TextView textResultadoTamanho;
    private TextView textResultadoPagamento;
    private TextView textResultadoValorFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resumo_pedido);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Instanciar os elementos e vincular ao id:
        textResultadoSabores = findViewById(R.id.textResultadoSabores);
        textResultadoTamanho = findViewById(R.id.textResultadoTamanho);
        textResultadoPagamento = findViewById(R.id.textResultadoPagamento);
        textResultadoValorFinal = findViewById(R.id.textResultadoValorFinal);

        // Recebendo os dados da Tela que foram enviados pelo Intent:
        Bundle bundle = getIntent().getExtras();

        // Decomposição dos dados do objeto enviado:
        String sabores = bundle.getString("sabor");
        String tamanho = bundle.getString("tamanho");
        String pagamento = bundle.getString("pagamento");

        // Mostrar os dados:
        textResultadoSabores.setText("Sabores: " + sabores);
        textResultadoTamanho.setText("Tamanho: " + tamanho);
        textResultadoPagamento.setText("Pagamento: " + pagamento);

        // Calculando valor
        double valorFinal = 0.0;

        // Estrutura de decisão para determinar o valor da pizza com base no seu tamanho
        switch (tamanho) {
            case "Pequena":
                valorFinal = 30.0;
                break;
            case "Média":
                valorFinal = 45.0;
                break;
            case "Grande":
                valorFinal = 60.0;
                break;
        }

        textResultadoValorFinal.setText(String.format("Valor Final: R$ %.2f", valorFinal));

    }

    // Função para voltar para a Tela Inicial
    public void voltar(View view) {
        // Invoca a "MainActivity"
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}