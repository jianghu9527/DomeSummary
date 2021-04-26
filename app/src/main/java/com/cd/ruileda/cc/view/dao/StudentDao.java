package com.cd.ruileda.cc.view.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cd.ruileda.cc.view.db.TStudent;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface StudentDao {
    /**
     * 查询某类性别的学生
     * @return 返回LiveData观察者数据，当数据有变更时会同步通知
     */
    @Query("SELECT * FROM "+ TStudent.TABLE_NAME+" WHERE Gender=:gender")
    LiveData<List<TStudent>> getStudentsByGender(int gender);
    // 查询某一班级学生记录
    @Query("SELECT * FROM "+TStudent.TABLE_NAME+" WHERE Class=:class_ AND Grade=:grade_")
    Single<List<TStudent>> getStudent(int class_, int grade_);
    // 清空某一班级的学生记录
//    @Delete(entity = TStudent.class)
//    void deleteStudents(TStudent.DeleteByClassGrade data);
    // 新增学生记录
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudents(List<TStudent>  students);
    // 更新学号
//    @Update(entity = TStudent.class)
//    void updateCode(TStudent.UpdateCode data);
    // 查询
    @Query("SELECT * FROM "+TStudent.TABLE_NAME+" WHERE id IN(:ids)")
    Single<List<TStudent>> findByIds(int []ids);

  }
