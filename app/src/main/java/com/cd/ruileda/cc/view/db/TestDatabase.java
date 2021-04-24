package com.cd.ruileda.cc.view.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cd.ruileda.cc.view.common.CommonPath;
import com.cd.ruileda.cc.view.dao.StudentDao;

import java.io.File;

@Database(entities = {TStudent.class}, version = 1, exportSchema = false)
public abstract class TestDatabase   extends RoomDatabase {
    private static final String DB_NAME = "test_table.db";
    private static volatile TestDatabase instance;

    public static synchronized TestDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static TestDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                TestDatabase.class,
                CommonPath.TABLE_DIR+ File.separator +DB_NAME)
                .build();
    }

    // DAO
    public abstract StudentDao getStudentDao();



}
