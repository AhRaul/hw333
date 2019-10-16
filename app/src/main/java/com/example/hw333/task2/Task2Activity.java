package com.example.hw333.task2;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hw333.R;

public class Task2Activity extends AppCompatActivity {

    private static final String TAG = "Task2Activity";

    private Task2Presenter rxPresenter;
    private Single<String> single;
    private Disposable disposable;

    @BindView(R.id.b_subscrybe_single)
    Button bsubscribesingle;
    @BindView(R.id.edit_text_single)
    TextView editTextSingle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
        ButterKnife.bind(this);

        rxPresenter = new Task2Presenter();
        single = rxPresenter.get1Message();
    }

    @OnClick(R.id.b_subscrybe_single)
    void onSubscrybe1Click(View view) {
        disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(string -> {
            Log.d(TAG, "onNext: " + Thread.currentThread().getName() + string);
            editTextSingle.append(string);      //вывод ожидаемого текста в текстовое поле активити
            }, throwable -> {
            Log.d(TAG, "onError: ", throwable);
        });
        Log.d(TAG, "subscribe: end " + Thread.currentThread().getName());
    }
}
