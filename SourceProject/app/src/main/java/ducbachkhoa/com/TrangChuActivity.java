package ducbachkhoa.com;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;

import ducbachkhoa.com.FragmentApp.HienThiBanFagment;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        drawerLayout = this.<DrawerLayout> findViewById(R.id.drawerLayout);
        navigationView = this.<NavigationView> findViewById(R.id.navigation_view_trangchu);
        toolbar = this.<Toolbar> findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.Mo, R.string.Dong){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tranHienThiBan = fragmentManager.beginTransaction();
        HienThiBanFagment hienThiBanFagment = new HienThiBanFagment();
        tranHienThiBan.replace(R.id.content, hienThiBanFagment);
        tranHienThiBan.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itTrangChu:
                FragmentTransaction tranHienThiBan = fragmentManager.beginTransaction();
                HienThiBanFagment hienThiBanFagment = new HienThiBanFagment();
                tranHienThiBan.replace(R.id.content, hienThiBanFagment);
                tranHienThiBan.commit();

                item.setChecked(true);
                drawerLayout.closeDrawers();
                ;break;
        }
        return false;
    }
}
