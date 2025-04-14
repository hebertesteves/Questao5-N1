package br.fecap.ads.questao5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormularioPedidoActivity extends AppCompatActivity {

    // Declaração dos elementos da interface (View)
    private CheckBox checkCalabresa;
    private CheckBox checkMarguerita;
    private CheckBox checkPortuguesa;
    private RadioGroup radioGroupTamanho;
    private RadioGroup radioGroupPagamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_pedido);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vinculando os elementos com os Views
        checkCalabresa = findViewById(R.id.checkCalabresa);
        checkMarguerita = findViewById(R.id.checkMarguerita);
        checkPortuguesa = findViewById(R.id.checkPortuguesa);
        radioGroupTamanho = findViewById(R.id.radioGroupTamanho);
        radioGroupPagamento = findViewById(R.id.radioGroupPagamento);

    }

    // Função para realizar pedido
    public void fazerPedido(View view) {

        // Obter o ID do RadioButton de Tamanho selecionado e atribuir a variavel selecionarIdTamanho
        int selecionarIdTamanho = radioGroupTamanho.getCheckedRadioButtonId();

        // Obter o ID do RadioButton de Pagamento selecionado e atribuir a variavel selecionarIdPagamento
        int selecionarIdPagamento = radioGroupPagamento.getCheckedRadioButtonId();

        // Verificar se nenhum radio button do Tamanho está selecionado
        if (selecionarIdTamanho == -1) {
            Toast.makeText(this, "Escolha o Tamanho da Pizza", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar se nenhum radio button do Pagamento está selecionado
        if (selecionarIdPagamento == -1) {
            Toast.makeText(this, "Escolha a Forma de Pagamento", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica se algum sabor foi selecionado
        if (!checkCalabresa.isChecked() && !checkMarguerita.isChecked() && !checkPortuguesa.isChecked()) {
            Toast.makeText(this, "Escolha um sabor", Toast.LENGTH_SHORT).show();
            return;
        }

        // Variavel para armazenar o valor de sabor
        String sabor = "";
        if (checkCalabresa.isChecked()) { // Calabresa selecionada
            sabor += checkCalabresa.getText().toString() + ", ";
        }

        if (checkMarguerita.isChecked()) { // Marguerita selecionada
            sabor += checkMarguerita.getText().toString() + ", ";
        }

        if (checkPortuguesa.isChecked()) { // Portuguesa selecionada
            sabor += checkPortuguesa.getText().toString() + ", ";
        }

        // Verifica se a string sabor não está vazia
        if (!sabor.isEmpty()) {
            sabor = sabor.substring(0, sabor.length() - 2); // Remove os dois ultimos caracteres, para tirar a virgula
        }

        // Pega o texto dos RadioButtons
        RadioButton radioTamanho = findViewById(selecionarIdTamanho);
        RadioButton radioPagamento = findViewById(selecionarIdPagamento);
        String tamanho = radioTamanho.getText().toString();
        String pagamento = radioPagamento.getText().toString();

        // Invoca a "ResumoPedidoActivity"
        Intent intent = new Intent(this, ResumoPedidoActivity.class);

        // Adicionar parametros para outra Activity:
        intent.putExtra("sabor", sabor);
        intent.putExtra("tamanho", tamanho);
        intent.putExtra("pagamento", pagamento);

        startActivity(intent);

    }
}