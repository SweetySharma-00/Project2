package com.example.project2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.BuildConfig;
import com.example.project2.MVP.login.IloginPresenter;
import com.example.project2.MVP.login.IloginView;
import com.example.project2.MVP.login.loginPresenterImpl;
import com.example.project2.R;
import com.example.project2.RetrofitAPI.models.response.LoginResponse;
import com.example.project2.Utils.Connectivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener,
        IloginView {

    TextInputEditText edtMobileNumber,edtPin;
    TextInputLayout txtMobileNumber,txtPin;
    Button btn;
    TextView ForgetPin;
    String token="kkkkk";
    IloginPresenter iloginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        edtMobileNumber=findViewById(R.id.edtMobileNumber);
        edtPin=findViewById(R.id.edtPin);
        txtMobileNumber=findViewById(R.id.txtMobileNumber);
        txtPin=findViewById(R.id.txtPin);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(this);
        ForgetPin=findViewById(R.id.ForgetPin);
        ForgetPin.setOnTouchListener(this);

    }

    public boolean Validatee() {
        if(edtMobileNumber.getText().toString().length()==0){
            txtMobileNumber.setError("This field must be filled");
            return false;
        }
        if (edtMobileNumber.getText().length() < 10  ) {
            txtMobileNumber.setError("Mobile Number is not Valid");
            return false;
        }
        if (edtPin.getText().toString().length()==0) {
            txtPin.setError("This field must be filled");
            return false;
        }
        txtMobileNumber.setError(null);
        txtPin.setError(null);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn:
                if(Validatee()){
                    Toast.makeText(this, "Validated Both Fields", Toast.LENGTH_SHORT).show();
                    if (Connectivity.isConnected(this)){
                        hitApi();
                           Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Net issue", Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    private void hitApi() {

         iloginPresenter= new loginPresenterImpl();
        iloginPresenter.setView(this);
        iloginPresenter.hitLogin();

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()){
            case R.id.ForgetPin:
                Toast.makeText(this, "Forget Pin Touched", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void setResponse(LoginResponse loginResponse) {

        if (loginResponse!=null && loginResponse.getMessage()!=null && loginResponse.getMessage().
                getSuccessMessage()!=null && loginResponse.getMessage().getSuccessMessage().length()!=0){
            Toast.makeText(this,loginResponse.getMessage().getSuccessMessage(),Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"ABBBC"+ loginResponse.getMessage().getErrorMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void setError(Throwable error) {
        Toast.makeText(this, "e"+error, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getClientVersion() {
        return "1.0.4";
    }

    @Override
    public String getPlatformType() {
        return "JM";
    }

    @Override
    public String getClientType() {
        return "Android"+" "+ Build.VERSION.SDK_INT;
    }

    @Override
    public String getFirebaseToken() {
        return token;
    }

    @Override
    public String getLoginId() {
        String mobile_Num=edtMobileNumber.getText().toString();
        Toast.makeText(this,"id"+mobile_Num, Toast.LENGTH_SHORT).show();
        return mobile_Num;
    }

    @Override
    public String getPassword() {
        String password=edtPin.getText().toString();
        return password;
    }
}