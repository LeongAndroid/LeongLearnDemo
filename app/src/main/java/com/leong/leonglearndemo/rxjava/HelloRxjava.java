/*
 * Copyright LeongAndroid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.leong.leonglearndemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.leong.leonglearndemo.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by LeongAndroid on 2017/8/8.
 */
public class HelloRxjava extends AppCompatActivity {
    static final String TAG = "RXJAVA-Learn";
    private Button button = null;
    private Button mapButton = null;
    private Button flatMapButton = null;


    String[] array1 = {"1", "2", "3", "4"}, array2 = {"5", "6", "7", "8"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_activity1);
        button = (Button)this.findViewById(R.id.flowable);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*flatMapObservable.observeOn(AndroidSchedulers.mainThread())///UI线程执行观察者的方法
                        .subscribeOn(Schedulers.newThread()) ///总是启用新线程，并在新线程执行操作。
                        .subscribe(observer);*/
                ///subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.
                upstream.subscribe(subscriber);
            }
        });


        mapButton = (Button)this.findViewById(R.id.map);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                    }
                }).map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "This is result " + integer;
                    }
                }).subscribe(new Consumer<String>() { ///带有一个Consumer参数的方法表示下游只关心onNext事件, 其他的事件我假装没看见
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                });
            }
        });

        flatMapButton = (Button)this.findViewById(R.id.flatmap);
        ///这里需要注意的是, flatMap并不保证事件的顺序, 也就是图中所看到的, 并不是事件1就在事件2的前面. 如果需要保证顺序则需要使用concatMap.
        flatMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                    }
                }).flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add("I am value " + integer);
                        }
                        return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, s);
                    }
                });

            }
        });


    }
    ///发布者，或者叫被观察者。
    Observable<String> observable= Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        //将事件发射出去,持有观察者的对象
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {

            }
            e.onNext("第一次调用");
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {

            }
            e.onNext("第二次调用");
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {

            }
            e.onNext("第三次调用");

            e.onComplete();
        }
    });


    /*
    //flatMap可以实现一个双重转换，在它的回调方法中会返回一个observable对象，但它并不会直接发射这个对象
   //而是将这个observable对象要发射的值 集中到一个新的observable对象中依次发射
  //如本例，第一层Observable依次发射两个数组，经过flatmap转换之后，变成变成两个依次发射数组元素的observable
  // 最后在subscriber中接收到的直接是整型数，等于将两个数组"铺开"了，直接发射整数，这就是大概地"flat"的含义吧
  // flatMap方法可以很灵活的使用，实现双重变换，满足很多不同情况下的需求,比如处理嵌套的异步代码等，非常棒!
     */
    Observable flatMapObservable = Observable.just(array1, array2).flatMap(new Function<String[], ObservableSource<?>>() {
        @Override
        public ObservableSource<?> apply(@NonNull String[] integers) throws Exception {
            Observable observable = Observable.fromArray(integers);
            return observable;
        }
    });


    ///观察者或者叫订阅者
    Observer observer = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "onSubscribe d = "+d.isDisposed());

        }

        @Override
        public void onNext(String value) {
            Log.d(TAG, "onNext value = "+value);
            Toast.makeText(HelloRxjava.this, value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError e = "+e);
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete ");
        }
    };

    Subscriber<Integer> subscriber = new Subscriber<Integer>() {
        @Override
        public void onSubscribe(Subscription s) {
            //这一步是必须，我们通常可以在这里做一些初始化操作，调用request()方法表示初始化工作已经完成
            //调用request()方法，会立即触发onNext()方法
            //在onComplete()方法完成，才会再执行request()后边的代码
            s.request(Long.MAX_VALUE);
        }

        @Override
        public void onNext(Integer value) {
            Log.d(TAG, "value = "+value);
        }

        @Override
        public void onError(Throwable t) {
            Log.d(TAG, t.getMessage());
        }

        @Override
        public void onComplete() {
            //由于Reactive-Streams的兼容性，方法onCompleted被重命名为onComplete
            Log.d(TAG, "complete");
        }
    };

    Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
        @Override
        public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
            Log.d(TAG, "emit 1");
            emitter.onNext(1);
            Log.d(TAG, "emit 2");
            emitter.onNext(2);
            Log.d(TAG, "emit 3");
            emitter.onNext(3);
            Log.d(TAG, "emit complete");
            emitter.onComplete();
        }
    }, BackpressureStrategy.ERROR); //增加了一个参数

}
