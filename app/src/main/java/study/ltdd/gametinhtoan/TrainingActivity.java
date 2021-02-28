package study.ltdd.gametinhtoan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class TrainingActivity extends AppCompatActivity {
    private CardView cViewAdd, cViewSub, cViewMul, cViewDiv, cViewModulo, cViewMulTable, cViewDivTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_trainning);
        mapping();

        cViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(TrainingActivity.this, AddActivity.class);
                startActivity(add);
            }
        });

        cViewSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sub = new Intent(TrainingActivity.this, SubActivity.class);
                startActivity(sub);
            }
        });

        cViewMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mul = new Intent(TrainingActivity.this, MulActivity.class);
                startActivity(mul);
            }
        });

        cViewDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent div = new Intent(TrainingActivity.this, DivActivity.class);
                startActivity(div);
            }
        });

        cViewModulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modulo = new Intent(TrainingActivity.this, ModuloActivity.class);
                startActivity(modulo);
            }
        });

        cViewMulTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mulTable = new Intent(TrainingActivity.this, MulTableActivity.class);
                startActivity(mulTable);
            }
        });

        cViewDivTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent divTable = new Intent(TrainingActivity.this, DivTableActivity.class);
                startActivity(divTable);
            }
        });
    }

    private void mapping(){
        cViewAdd = findViewById(R.id.cardViewAdd);
        cViewSub = findViewById(R.id.cardViewSub);
        cViewMul = findViewById(R.id.cardViewMulti);
        cViewDiv = findViewById(R.id.cardViewDiv);
        cViewModulo = findViewById(R.id.cardViewModulo);
        cViewMulTable = findViewById(R.id.cardViewMulTable);
        cViewDivTable = findViewById(R.id.cardViewDivTable);
    }
}