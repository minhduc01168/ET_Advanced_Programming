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
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ducbachkhoa.com.AddTableActivity;
import ducbachkhoa.com.R;

public class HienThiBanFagment extends Fragment {

    //public static int RESQUEST_CODE_THEN = 111;

    private final ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        boolean kiemtra = intent.getBooleanExtra("ketquathem", false);
                        if(kiemtra){
                            Toast.makeText(getActivity(),getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(),getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthiban,container,false);
        setHasOptionsMenu(true);
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
        switch (id){
            case R.id.itThemban:
                Intent iThemban = new Intent(getActivity(), AddTableActivity.class);
                //startActivityForResult(iThemban, RESQUEST_CODE_THEN);
                mActivityResultLauncher.launch(iThemban);
                ;break;
        }
        return true;
    }

    //  @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == RESQUEST_CODE_THEN){
//            if(resultCode == Activity.RESULT_OK){
//                Intent intent = data;
//                boolean kiemtra = intent.getBooleanExtra("KetquaThem",false);
//                if(kiemtra){
//                    Toast.makeText(getActivity(),getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getActivity(),getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
}
