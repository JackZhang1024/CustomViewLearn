package com.lucky.customviewlearn.material;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2017/7/12.
 */

public class SimpleMaterialDesignActivity extends AppCompatActivity implements View.OnClickListener {

    private View rootView;
    private FloatingActionButton floatingActionButton;
    private boolean isFloatingActionButtonShowing = true;
    private FloatingActionMenu floatingActionMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_material_design);
        rootView = findViewById(R.id.root);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_search);
        findViewById(R.id.snack_bar).setOnClickListener(this);
        findViewById(R.id.btn_float_button).setOnClickListener(this);
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.float_menu);
        floatingActionMenu.showMenuButton(true);
        floatingActionMenu.setClosedOnTouchOutside(true);
        floatingActionMenu.findViewById(R.id.menu_info).setOnClickListener(this);
        floatingActionMenu.findViewById(R.id.menu_address).setOnClickListener(this);
        floatingActionMenu.findViewById(R.id.menu_contact).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.snack_bar:
                //showSnackBarOne("SnackBarOne");
                showSnackBarTwo("SnackBarTwo");
                break;
            case R.id.btn_float_button:
                showOrHideFabButton();
                break;
            case R.id.menu_info:
                showSnackBarOne("MenuInfo");
                break;
            case R.id.menu_address:
                showSnackBarOne("Address");
                break;
            case R.id.menu_contact:
                showSnackBarOne("Contact");
                break;
        }
    }


    private void showSnackBarOne(String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
    }

    private void showSnackBarTwo(String message) {
//        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).setAction("Try Again", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SimpleMaterialDesignActivity.this, "HaHa", Toast.LENGTH_SHORT).show();
//            }
//        }).setActionTextColor(Color.BLUE).show();
        //floatingActionMenu
        Snackbar.make(floatingActionMenu, message, Snackbar.LENGTH_SHORT).setAction("Try Again", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleMaterialDesignActivity.this, "HaHa", Toast.LENGTH_SHORT).show();
            }
        }).setActionTextColor(Color.BLUE).show();
    }

    private void showOrHideFabButton() {
        if (!isFloatingActionButtonShowing) {
            floatingActionButton.show();
        } else {
            floatingActionButton.hide();
        }
        isFloatingActionButtonShowing = !isFloatingActionButtonShowing;
    }

    @Override
    protected void onResume() {
        super.onResume();
        floatingActionMenu.close(false);
    }
}
