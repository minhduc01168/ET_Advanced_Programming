package ducbachkhoa.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import ducbachkhoa.com.DAO.StaffDAO;
import ducbachkhoa.com.DTO.StaffDTO;
import ducbachkhoa.com.Database.CreateDatabase;
import ducbachkhoa.com.FragmentApp.DatePickerFragment;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    EditText edTenDangNhapDK, edFullName, edMatKhauDK, edNgaySinhDK, edCCCDDK;
    Button btnDongYDK, btnThoat;
    RadioGroup rgGioiTinh;
    String sGioiTinh;
    StaffDAO staffDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        edTenDangNhapDK = this.<EditText> findViewById(R.id.edTenDangNhapDK);
        edFullName = this.<EditText> findViewById(R.id.edFullName);
        edMatKhauDK = this.<EditText> findViewById(R.id.edMatKhauDK);
        edNgaySinhDK = this.<EditText >findViewById(R.id.edNgaySinhDK);
        edCCCDDK = this.<EditText> findViewById(R.id.edCCCDDK);
        btnDongYDK = this.<Button> findViewById(R.id.btnDongYDK);
        btnThoat = this.<Button> findViewById(R.id.btnThoat);
        rgGioiTinh = this.<RadioGroup> findViewById(R.id.rgGioiTinh);

        btnDongYDK.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
        edNgaySinhDK.setOnFocusChangeListener(this);

        staffDAO = new StaffDAO(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnDongYDK:
                String sTenDangNhap = edTenDangNhapDK.getText().toString();
                String sFullName = edFullName.getText().toString();
                String sMatKhau = edMatKhauDK.getText().toString();

                switch (rgGioiTinh.getCheckedRadioButtonId()){
                    case R.id.rdNam:
                        sGioiTinh = "Nam";
                        break;
                    case R.id.rdNu:
                        sGioiTinh = "Nữ";
                        break;
                }
                String sNgaySinh = edNgaySinhDK.getText().toString();
                String sCCCD = edCCCDDK.getText().toString();

                if(sTenDangNhap == null || sTenDangNhap.equals("")){
                    Toast.makeText(DangKyActivity.this, getResources().getString(R.string.loinhaptendangnhap), Toast.LENGTH_SHORT).show();
                }
                else if(sMatKhau == null || sMatKhau.equals("")){
                    Toast.makeText(DangKyActivity.this, getResources().getString(R.string.loinhapmatkhau), Toast.LENGTH_SHORT).show();
                }
                else{
                    StaffDTO staffDTO = new StaffDTO();
                    staffDTO.setName_user(sTenDangNhap);
                    staffDTO.setFull_name(sFullName);
                    staffDTO.setPassword(sMatKhau);
                    staffDTO.setCccd(sCCCD);
                    staffDTO.setDate_birth(sNgaySinh);
                    staffDTO.setGender(sGioiTinh);

                    long kiemtra1 = staffDAO.AddStaff(staffDTO);
                    if(kiemtra1 !=0){
                        Toast.makeText(DangKyActivity.this, getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(DangKyActivity.this, getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
                    }
                }
                ;break;
            case R.id.btnThoat:
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        int id = view.getId();
        switch (id){
            case R.id.edNgaySinhDK:
                if (hasFocus){
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(),"Ngày Sinh");
                    //xuat lich ngay sinh
                }
                ;break;

        }
    }

}