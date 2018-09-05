package com.stx.xhb.dmgameapp.data;

import android.support.annotation.NonNull;

/**
 * Author: Mr.xiao on 2018/9/5
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 数据仓库代理类，用于封装从不同来源处获取数据
 */
public class TasksRepositoryProxy implements TasksDataSource{

    private static TasksRepositoryProxy INSTANCE = null;

    private final TasksDataSource mRemoteDataSource;


    private TasksRepositoryProxy(@NonNull TasksDataSource remote) {
        this.mRemoteDataSource = remote;
    }

    public static TasksRepositoryProxy getInstance(TasksDataSource remote) {
        if (INSTANCE == null) {
            synchronized (TasksRepositoryProxy.class) {
                if (INSTANCE == null) {
                    return new TasksRepositoryProxy(remote);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void release() {
        mRemoteDataSource.release();
        INSTANCE = null;
    }



}
