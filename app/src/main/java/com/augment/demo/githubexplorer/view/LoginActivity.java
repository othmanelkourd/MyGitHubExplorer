package com.augment.demo.githubexplorer.view;




import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.augment.demo.githubexplorer.R;
import com.augment.demo.githubexplorer.model.GitHubService;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Othman
 * The Login UI Activity
 */


public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.txtLogin)
    EditText et_Login;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }



    public void showRepositories(View source){

        if(TextUtils.isEmpty(et_Login.getText().toString())){
            et_Login.setError("this item cannot be empty, please enter your Github login ");
        }else{
            GitHubService.USER_NAME = et_Login.getText().toString();
            startActivity(new Intent(this, ListRepositoryActivity.class));
        }
    }
}

