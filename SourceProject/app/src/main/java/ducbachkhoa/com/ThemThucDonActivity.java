package ducbachkhoa.com;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ducbachkhoa.com.CustomAdapter.AdapterHienThiLoaiMonAn;
import ducbachkhoa.com.DAO.TypeDrinkDAO;
import ducbachkhoa.com.DTO.TypeDrinkDTO;

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {
    public static int REQUEST_CODE_THEMLOAITHUCDON = 113;
    public static int REQUEST_CODE_MOHINH = 123;
    ImageButton imThemLoaiThucDon;
    Spinner spinLoaiThucDon;
    TypeDrinkDAO loaiMonAnDAO;
    List<TypeDrinkDTO> loaiMonAnDTOs;
    AdapterHienThiLoaiMonAn adapterHienThiLoaiMonAn;
    ImageView imHinhThucDon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themthucdon);
        loaiMonAnDAO = new TypeDrinkDAO(this);
        imThemLoaiThucDon = (ImageButton) findViewById(R.id.imThemLoaiThucDon);
        spinLoaiThucDon = (Spinner) findViewById(R.id.spinLoaiMonAn);
        imHinhThucDon = findViewById(R.id.imHinhThucDon);
        HienThiSpinerLoaiMonAn();

        imThemLoaiThucDon.setOnClickListener(this);
        imHinhThucDon.setOnClickListener(this);
    }

    private void HienThiSpinerLoaiMonAn(){
        loaiMonAnDTOs = loaiMonAnDAO.LayDanhSachLoaiMonAn();
        adapterHienThiLoaiMonAn = new AdapterHienThiLoaiMonAn(ThemThucDonActivity.this, R.layout.custom_layout_spinloaithucdon,loaiMonAnDTOs);
        spinLoaiThucDon.setAdapter(adapterHienThiLoaiMonAn);
        adapterHienThiLoaiMonAn.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imThemLoaiThucDon:
                Intent iThemLoaiMonAn = new Intent(ThemThucDonActivity.this, ThemLoaiThucDonActivity.class);
                startActivityForResult(iThemLoaiMonAn, REQUEST_CODE_THEMLOAITHUCDON);
                break;
            case R.id.imHinhThucDon:
                Intent iMoHinh = new Intent();
                iMoHinh.setType("image/*");
                iMoHinh.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(iMoHinh,"Chon hinh thuc don"),REQUEST_CODE_MOHINH);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_THEMLOAITHUCDON){
            if(resultCode == RESULT_OK){
                Intent dulieu = data;
                boolean kiemtra= dulieu.getBooleanExtra("kiemtraloaithucdon",false);
                if(kiemtra){
                    HienThiSpinerLoaiMonAn();
                    Toast.makeText(this,getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
                }
            }else if (REQUEST_CODE_MOHINH == resultCode) {
                if(resultCode == Activity.RESULT_OK  && data != null){
                    imHinhThucDon.setImageURI(data.getData());
                   // Log.d("duong dan", data.getData()+"");
                }
            }
        }
    }
}
