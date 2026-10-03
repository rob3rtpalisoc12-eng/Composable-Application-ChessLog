package com.example.chesslog.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ChessGame::class],
    version = 1,
    exportSchema = false
)
abstract class ChessDatabase : RoomDatabase() {

    abstract fun chessGameDao(): ChessGameDao

    companion object {
        @Volatile
        private var INSTANCE: ChessDatabase? = null

        fun getDatabase(context: Context): ChessDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChessDatabase::class.java,
                    "chesslog_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}