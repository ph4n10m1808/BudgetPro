package com.ph4n10m.budgetpro;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.ph4n10m.budgetpro.databinding.ActivityMainBinding;
import com.ph4n10m.budgetpro.dialog.CategoryCollectDialog;
import com.ph4n10m.budgetpro.dialog.CategorySpendDialog;
import com.ph4n10m.budgetpro.dialog.CollectDialog;
import com.ph4n10m.budgetpro.dialog.SpendDialog;
import com.ph4n10m.budgetpro.ui.collect.ApproximatelyCollectFragment;
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectFragment;
import com.ph4n10m.budgetpro.ui.spend.ApproximatelySpendFragment;
import com.ph4n10m.budgetpro.ui.spend.CategorySpendFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fab = findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        setSupportActionBar(binding.appBarMain.toolbar);
        final MainActivity currentContext = this;
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                Fragment fragment = fragments.get(fragments.size() - 1);
                if (fragment instanceof CategoryCollectFragment) {
                    CategoryCollectDialog dialog = new CategoryCollectDialog(currentContext,
                            (CategoryCollectFragment) fragment);
                    dialog.show();
                } else if (fragment instanceof ApproximatelyCollectFragment) {
                    CollectDialog dialog = new CollectDialog(currentContext, (ApproximatelyCollectFragment) fragment);
                    dialog.show();
                } else if (fragment instanceof CategorySpendFragment) {
                    CategorySpendDialog dialog = new CategorySpendDialog(currentContext,
                            (CategorySpendFragment) fragment);
                    dialog.show();
                } else if (fragment instanceof ApproximatelySpendFragment) {
                    SpendDialog dialog = new SpendDialog(currentContext, (ApproximatelySpendFragment) fragment);
                    dialog.show();
                }
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                SparseArray<Integer> fabVisibilityMap = new SparseArray<>();
                fabVisibilityMap.put(R.id.nav_exit, View.VISIBLE);
                fabVisibilityMap.put(R.id.nav_home, View.GONE);
                fabVisibilityMap.put(R.id.nav_spend, View.VISIBLE);
                fabVisibilityMap.put(R.id.nav_collect, View.VISIBLE);
                fabVisibilityMap.put(R.id.nav_intro, View.GONE);
                fabVisibilityMap.put(R.id.nav_statistics, View.GONE);
                int fabVisibility = fabVisibilityMap.get(navDestination.getId(), View.GONE);
                fab.setVisibility(fabVisibility);

            }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}