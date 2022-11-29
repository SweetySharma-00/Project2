package com.example.project2.MVP.login;

import android.widget.Toast;

import com.example.project2.RetrofitAPI.api.IRetrofitContract;
import com.example.project2.RetrofitAPI.api.RetrofitFactory;
import com.example.project2.RetrofitAPI.models.response.LoginResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Header;

public class loginPresenterImpl implements IloginPresenter{

private IloginView iloginView;
    @Override
    public void hitLogin() {
         String clientVersion= iloginView.getClientVersion();
        String platformType=iloginView.getPlatformType();
        String clientType=iloginView.getClientType();
        String firebaseToken=iloginView.getFirebaseToken();
        String loginId=iloginView.getLoginId();
        String password=iloginView.getPassword();
        RetrofitFactory retrofitFactory = RetrofitFactory.getInstance();
        IRetrofitContract iRetrofitContract = retrofitFactory.getRetrofitContract("https://d1-slp-api.supremelifeplatform.com/");
        Observable<LoginResponse> responseObservable=iRetrofitContract.Login(clientVersion,platformType,
                clientType,firebaseToken,loginId,password);
        responseObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).
                subscribe(new Observer<LoginResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

                    @Override
                    public void onNext(LoginResponse value) {
                            iloginView.setResponse(value);

                    }

            @Override
            public void onError(Throwable e) {
            iloginView.setError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setView(IloginView iloginView) {
        this.iloginView=iloginView;
    }
}
