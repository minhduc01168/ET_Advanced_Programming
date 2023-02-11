package ducbachkhoa.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ducbachkhoa.com.DAO.TableDAO;
import ducbachkhoa.com.FragmentApp.HienThiBanFagment;

public class AddTableActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edAddNameTable;
    Button btnDongYThemBan;
    TableDAO tableDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_table);

        edAddNameTable = this.<EditText> findViewById(R.id.edAddNameTable);
        btnDongYThemBan = this.<Button> findViewById(R.id.btnDongYThemBan);

        tableDAO = new TableDAO(this);
        btnDongYThemBan.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String sNameTable = edAddNameTable.getText().toString();
        if(sNameTable != null && sNameTable.length()>0){
            boolean kiemtra = tableDAO.AddTable(sNameTable);
            Intent intent = new Intent();
            intent.putExtra("KetQuaThem", kiemtra);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
