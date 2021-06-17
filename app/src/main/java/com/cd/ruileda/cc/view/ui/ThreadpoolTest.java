package com.cd.ruileda.cc.view.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cd.ruileda.cc.view.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadpoolTest  extends AppCompatActivity  implements View.OnClickListener{
    private static ExecutorService mCacheThreadExcutor =null;
    private static final int count =3;
    private static ExecutorService mFixThreadExecutor = null;
    private static ScheduledExecutorService mScheduledThreadExecutor = null; //能和Timer/TimerTask类似，解决那些需要任务重复执行的问题
    private static ExecutorService mSingleThreadExecutor = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_threadpool);
        initView();
        initExecutorService();



    }
    private void initExecutorService() {
        //ExecutorService  线程池接口
        mCacheThreadExcutor = Executors.newCachedThreadPool(); //可缓存线程池，当线程池大小超过了处理任务所需的线程，那么就会回收部分空闲（一般是60秒无执行）的线程，当有任务来时，又智能的添加新线程来执行
        mFixThreadExecutor = Executors.newFixedThreadPool(count); //固定数量的线程池，每提交一个任务就是一个线程，直到达到线程池的最大数量，然后后面进入等待队列直到前面的任务完成才继续执行
        mScheduledThreadExecutor = Executors.newScheduledThreadPool(count);//大小无限制的线程池，支持定时和周期性的执行线程
        mSingleThreadExecutor = Executors.newSingleThreadExecutor(); //单个线程的线程池，即线程池中每次只有一个线程工作，单线程串行执行任务
    }

    private void initView() {
        findViewById(R.id.cache_thread_executorbtn).setOnClickListener(this);
        findViewById(R.id.fixed_thread_executorbtn).setOnClickListener(this);
        findViewById(R.id.scheduled_thread_executorbtn).setOnClickListener(this);
        findViewById(R.id.single_thread_executorbtn).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cache_thread_executorbtn:
                ExecutorServiceThread(mCacheThreadExcutor);
                break;
            case R.id.fixed_thread_executorbtn:
                ExecutorServiceThread(mFixThreadExecutor);
                break;
            case R.id.scheduled_thread_executorbtn:
                ExecutorScheduleServiceThread(mScheduledThreadExecutor);
                break;
            case R.id.single_thread_executorbtn:
                ExecutorServiceThread(mSingleThreadExecutor);
                break;

        }
    }


    private void ExecutorScheduleServiceThread(ScheduledExecutorService mScheduledThreadExecutor) {
        for (int i = 0; i < 9; ++i) {
            final int index = i;
            mScheduledThreadExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("--------ThreadpoolTest-------1------","Thread_ScheduledThread"+Thread.currentThread().getId()+"activeCount:"+Thread.activeCount()
                            +" index "+index);
                }
            },2, TimeUnit.SECONDS);
        }
    }

    private void ExecutorServiceThread(ExecutorService executorService) {
        for (int i =0;i<9;++i){
            final  int index =i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("--------ThreadpoolTest-------2----","Thread"+Thread.currentThread().getId()+"activeCount:"+Thread.activeCount()
                            +" index "+index);
                }
            });
        }
    }

}
