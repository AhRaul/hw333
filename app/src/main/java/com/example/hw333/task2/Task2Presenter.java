package com.example.hw333.task2;

import android.util.Log;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class Task2Presenter {

    private static final String TAG = "Task2Presenter";

    public Single<String> get1Message() {

        Single<String> single = Single.create((SingleOnSubscribe<String>) emitter -> {
                    String mess = "single SMS";
                    Log.d(TAG, "get my message:" + Thread.currentThread().getName() + ": " + mess);
                    emitter.onSuccess(mess);

        }).subscribeOn(Schedulers.io());

        return single;
    }
}
