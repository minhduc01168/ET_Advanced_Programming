package ducbachkhoa.com.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import ducbachkhoa.com.AddTableActivity;
import ducbachkhoa.com.CustomAdapter.AdapterHienThiBanAn;
import ducbachkhoa.com.DAO.TableDAO;
import ducbachkhoa.com.DTO.TableDTO;
import ducbachkhoa.com.R;
import ducbachkhoa.com.TrangChuActivity;

public class HienThiBanFagment extends Fragment {

    public static int RESQUEST_CODE_THEN = 111;
    GridView gvhienthiban;
    List<TableDTO> banAnDTOList;
    TableDAO banAnDAO;
    AdapterHienThiBanAn adapterHienThiBanAn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthiban,container,false);
        setHasOptionsMenu(true);
        ((TrangChuActivity) getActivity()).getSupportActionBar().setTitle(R.string.banan);
        gvhienthiban = view.findViewById(R.id.gvHienBanAn);
        banAnDAO = new TableDAO(getActivity());
        HienThiDanhSachBanAn();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemBan = menu.add(1,R.id.itThemban,1,R.string.ThemBan);
        itThemBan.setIcon(R.drawable.themban);
        itThemBan.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.itThemban){
            Intent iThemban = new Intent(getActivity(), AddTableActivity.class);
            startActivityForResult(iThemban, RESQUEST_CODE_THEN);
        }
        return true;
    }
    private void HienThiDanhSachBanAn(){
        banAnDTOList = banAnDAO.LayTatCaBanAn();
        adapterHienThiBanAn = new AdapterHienThiBanAn(getActivity(),R.layout.custom_layout_hienthiban,banAnDTOList);
        gvhienthiban.setAdapter(adapterHienThiBanAn);
        adapterHienThiBanAn.notifyDataSetChanged();
    }
      @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESQUEST_CODE_THEN){
            if(resultCode == Activity.RESULT_OK){
                boolean kiemtra = data.getBooleanExtra("KetQuaThem",false);
                if(kiemtra){
                    HienThiDanhSachBanAn();
                    Toast.makeText(getActivity(),getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
