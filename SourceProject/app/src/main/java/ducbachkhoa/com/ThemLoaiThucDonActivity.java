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

import ducbachkhoa.com.DAO.TypeDrinkDAO;

public class ThemLoaiThucDonActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDongYThemLoaiThucDon;
    EditText edTenLoai;
    TypeDrinkDAO loaiMonAnDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.layout_themloaithucdon);
        super.onCreate(savedInstanceState);

        loaiMonAnDAO = new TypeDrinkDAO(this);

        btnDongYThemLoaiThucDon = (Button) findViewById(R.id.btnDongYThemLoaiThucDon);
        edTenLoai = (EditText) findViewById(R.id.edThemLoaiThucDon);

        btnDongYThemLoaiThucDon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String sTenLoaiThucDon = edTenLoai.getText().toString();
        if(sTenLoaiThucDon != null && sTenLoaiThucDon.length()>0){
            Intent iDuLieu = new Intent();
            setResult(Activity.RESULT_OK,iDuLieu);
            boolean kiemtra = loaiMonAnDAO.ThemLoaiMonAn(sTenLoaiThucDon);
            iDuLieu.putExtra("kiemtraloaithucdon",kiemtra);
            finish();
        }else{
            Toast.makeText(this,getResources().getString(R.string.vuilongnhapdulieu), Toast.LENGTH_SHORT).show();
        }
    }
}
