package ducbachkhoa.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ducbachkhoa.com.DAO.StaffDAO;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDongYDN, btnDangKyDN;
    EditText edTenDangNhapDN, edMatKhauDN;
    StaffDAO staff;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        btnDangKyDN = this.<Button> findViewById(R.id.btnDangKyDN);
        btnDongYDN = this.<Button> findViewById(R.id.btnDongYDN);
        edMatKhauDN = this.<EditText> findViewById(R.id.edMatKhauDN);
        edTenDangNhapDN = this.<EditText>findViewById(R.id.edTenDangNhapDN);

        staff = new StaffDAO(this);
        btnDongYDN.setOnClickListener(this);
        btnDangKyDN.setOnClickListener(this);

        HienThiButtonDangKyDongY();
    }
    private void HienThiButtonDangKyDongY(){ //Hien thi nut dong y khi co nhan vien, hien thi nut dang ky khi khong co nhan vien
        //StaffDAO staff_DAO = new StaffDAO(this);
        boolean kiemtra = staff.CheckStaff();
        if (kiemtra) {
            btnDangKyDN.setVisibility(View.VISIBLE);
            btnDongYDN.setVisibility(View.GONE);
            btnDangKyDN.setVisibility(View.GONE);
            btnDongYDN.setVisibility(View.VISIBLE);
        } else {
            btnDangKyDN.setVisibility(View.VISIBLE);
            btnDongYDN.setVisibility(View.GONE);
        }
    }
    private void btnDongY(){
        String sTenDangNhap = edTenDangNhapDN.getText().toString();
        String sMatKhau = edMatKhauDN.getText().toString();
        //StaffDAO staff_DAO = new StaffDAO(this);
        boolean kiemtra = staff.CheckDangNhap(sTenDangNhap, sMatKhau);
        if( kiemtra){
            Intent iTrangChu = new Intent(DangNhapActivity.this,TrangChuActivity.class);
            startActivity(iTrangChu);
        }
        else {
            Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại !", Toast.LENGTH_SHORT).show();
        }
    }
    private void btnDangKy(){
        Intent iDangKy = new Intent(DangNhapActivity.this, DangKyActivity.class);
        startActivity(iDangKy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HienThiButtonDangKyDongY();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnDongYDN:
                btnDongY();
                ;break;
            case R.id.btnDangKyDN:
                btnDangKy();
                ;break;
        }

    }
}
