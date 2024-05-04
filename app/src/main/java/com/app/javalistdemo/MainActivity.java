package com.app.javalistdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.javalistdemo.adapter.UserAdapter;
import com.app.javalistdemo.databinding.ActivityMainBinding;
import com.app.javalistdemo.utils.NetworkStatus;
import com.app.javalistdemo.utils.TouchHelperClass;
import com.app.javalistdemo.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private UserViewModel userViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);
        setSupportActionBar(binding.myToolbar);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));


        boolean isConnected = NetworkStatus.isDeviceConnected(this);
        if (isConnected) {
            load_data();
        } else {
            binding.progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Device offline", Toast.LENGTH_LONG).show();
        }

    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    private void load_data() {
        userViewModel.getListUsers().observe(this, userResponse -> {

            binding.progressBar.setVisibility(View.GONE);
            if (userResponse.getError() != null) {

                binding.progressBar.setVisibility(View.GONE);

                showError("There was an error while processing your request " + userResponse.getError().getMessage());

            }

            if (userResponse.getUserList().size() >= 1) {
                try {

                    adapter = new UserAdapter(userResponse.getUserList());
                    ItemTouchHelper.Callback callback = new TouchHelperClass(adapter);
                    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
                    adapter.setTouchHelp(itemTouchHelper);
                    itemTouchHelper.attachToRecyclerView(binding.recyclerview);
                    binding.recyclerview.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }


        });
    }

}