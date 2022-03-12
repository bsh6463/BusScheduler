package com.example.busschedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao

@Database(entities = arrayOf(Schedule::class), version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao


    companion object{
        //companion object에 선언한 field 및 method 는 static 과 동일
        //Appdatabase 인스턴스 없이 사용 가능.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context:Context): AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context,
                AppDatabase::class.java, "app_database")
                    .createFromAsset("database/bus_schedule.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}