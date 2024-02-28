package com.lab.androidroom

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.lab.androidroom.FeedReaderContract.FeedEntry

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    public fun createEntries(db: SQLiteDatabase) {
        onCreate(db)
    }

    public fun deleteEntries(db: SQLiteDatabase) {
        db.execSQL(SQL_DELETE_ENTRIES)
    }

    companion object {
        const val DATABASE_NAME = "feedReader.db"
        const val DATABASE_VERSION = 1
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${FeedEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${FeedEntry.COLUMN_NAME_TITLE} TEXT," +
                    "${FeedEntry.COLUMN_NAME_SUBTITLE} TEXT)"
        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"
    }
}