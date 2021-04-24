package com.cd.ruileda.cc.day01.db;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * https://blog.csdn.net/tywfeng/article/details/109175630
 */
@Entity(tableName = TStudent.TABLE_NAME)
public class TStudent {

    public static final String TABLE_NAME = "t_student";


    @PrimaryKey(autoGenerate = true)
    public int id;             // 自增id
    public String Name;
    public int Gender;         // 性别
    public int Age;
    public String Code;        // 学号
    public int Class;          // 班级
    public int Grade;          // 年级

    /**
     * 删除某一班级的数据
     */
    public static class DeleteByClassGrade{
        public DeleteByClassGrade(int _class, int _grade){
            Class = _class;
            Grade = _grade;
        }
        public int Class;
        public int Grade;
    }

    /**
     * 根据id更新某一学生的学号
     */
    public static class UpdateCode {
        public UpdateCode(int _id, String _code)
        {
            id = _id;
            Code = _code;
        }
        public int id;             // 自增id
        public String Code;        // 学号
    }

    public TStudent() {}
    public TStudent(String name, int age ,int gender) {Name = name; Age = age; Gender = gender;}

    @Override
    public String toString() {
        return "TStudent{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Gender=" + Gender +
                ", Age=" + Age +
                ", Code='" + Code + '\'' +
                ", Class=" + Class +
                ", Grade=" + Grade +
                '}';
    }
}
