package com.example.ecommereceapp.Activity;

import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommereceapp.Adapter.CartAdapter;
import com.example.ecommereceapp.Helper.ChangeNumberItemsListener;
import com.example.ecommereceapp.Helper.ManagmentCart;
import com.example.ecommereceapp.R;
import com.example.ecommereceapp.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity
{
    private ManagmentCart managmentCart;
    ActivityCartBinding binding;
    double Tax;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart = new ManagmentCart(this);
        setVariable();
        initlist();
        calculatorCart();
        statusBarColor();
    }
    private void statusBarColor()
    {
        Window window = CartActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CartActivity.this,R.color.purpule_dark));
    }

    private void initlist()
    {
        if(managmentCart.getListCart().isEmpty())
        {
            binding.emptyTxt.setVisibility(VISIBLE);
            binding.scrol.setVisibility(View.GONE);
        }
        else
        {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrol.setVisibility(VISIBLE);
        }

        binding.cartView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.cartView.setAdapter(new CartAdapter(managmentCart.getListCart(), new ChangeNumberItemsListener() {
            @Override
            public void change()
            {
                calculatorCart();
            }

            @Override
            public void changed()
            {
            }
        }));

    }
    private void calculatorCart()
    {
        double percentTax = 0.02;
        double delivery = 10;
        Tax = Math.round(managmentCart.getTotalFee() * percentTax* 100)/100;

        double total = Math.round(managmentCart.getTotalFee() + Tax + delivery * 100)/100;
        double itemsTotal = Math.round(managmentCart.getTotalFee() * 100)/100;
        binding.totalFeeTxt.setText("$"+itemsTotal);
        binding.taxTxt.setText("$"+Tax);
        binding.deliveryTxt.setText("$"+delivery);
        binding.totalTxt.setText("$"+total);
    }

    private void setVariable()
    {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}