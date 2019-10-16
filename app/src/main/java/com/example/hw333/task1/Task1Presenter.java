package com.example.hw333.task1;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class Task1Presenter {

    private static final String TAG = "RxPresenter";

    public Observable<String> getMessage() {

        Observable<String> observable = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            try {
                for (int i = 0; i < 5;i++) {
                    TimeUnit.SECONDS.sleep(1);
                    String mess = "SMS: " + i;
                    Log.d(TAG, "get my message:" + Thread.currentThread().getName() + ": " + mess);
                    emitter.onNext(mess);
                }

                emitter.onComplete();

            } catch (InterruptedException e) {
                Log.d(TAG, "getMessage: not disposed");
            }
        }).subscribeOn(Schedulers.io());

        return observable;
    }
}
