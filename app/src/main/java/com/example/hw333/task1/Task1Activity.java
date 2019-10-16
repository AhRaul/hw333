package com.example.hw333.task1;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hw333.R;

public class Task1Activity extends AppCompatActivity {

    private static final String TAG = "RxObserverActivity";

    private Task1Presenter rxPresenter;
    private Observable<String> observable;
    private Disposable disposable;

    private Button bsubscribe;
    private Button bunsubscribe;
    private TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        bsubscribe = findViewById(R.id.b_subscrybe);
        bunsubscribe = findViewById(R.id.b_un_subscrybe);
        editText = findViewById(R.id.editText);

        rxPresenter = new Task1Presenter();
        observable = rxPresenter.getMessage();

        bsubscribe.setOnClickListener(v -> {
            disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(string -> {
                Log.d(TAG, "onNext: " + Thread.currentThread().getName() + string);
                editText.append(string);
            }, throwable -> {
                Log.d(TAG, "onError: ");
            }, () -> {
                Log.d(TAG, "onComplete: ");
            });
            Log.d(TAG, "subscribe: end " + Thread.currentThread().getName());
        });

        bunsubscribe.setOnClickListener(v -> disposable.dispose());
    }
}
