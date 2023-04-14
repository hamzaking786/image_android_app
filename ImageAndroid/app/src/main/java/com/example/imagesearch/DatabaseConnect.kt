package com.example.imagesearch

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.imagesearch.Models.ResultModel
import java.util.*

class DatabaseConnect(var context: Context?) : SQLiteOpenHelper(
    context, database_name, null, 1
) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + table_results + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_Original + " TEXT," + COL_Result1 + " TEXT," + COL_Result2 + " TEXT," + COL_Result3 + " TEXT," + COL_Result4 + " TEXT," + COL_Result5 + " TEXT " + ")")
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_results)
        onCreate(sqLiteDatabase)
    }

    fun insertNewResult(model: ResultModel) {
        val sqLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COL_Original, model.original)
        values.put(COL_Result1, model.result1)
        values.put(COL_Result2, model.result2)
        values.put(COL_Result3, model.result3)
        values.put(COL_Result4, model.result4)
        values.put(COL_Result5, model.result5)
        sqLiteDatabase.insert(table_results, null, values)
        sqLiteDatabase.close()
    }

    fun fetchResults(): ArrayList<ResultModel> {
        val database = this.readableDatabase
        val resultList = ArrayList<ResultModel>()
        val fetchQuery = " SELECT * FROM " + table_results
        val cursor = database.rawQuery(fetchQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(COL_1))
                    val org = cursor.getString(cursor.getColumnIndex(COL_Original))
                    val r1 = cursor.getString(cursor.getColumnIndex(COL_Result1))
                    val r2 = cursor.getString(cursor.getColumnIndex(COL_Result2))
                    val r3 = cursor.getString(cursor.getColumnIndex(COL_Result3))
                    val r4 = cursor.getString(cursor.getColumnIndex(COL_Result4))
                    val r5 = cursor.getString(cursor.getColumnIndex(COL_Result5))
                    val resultModel = ResultModel(id, org, r1, r2, r3, r4, r5)
                    resultList.add(resultModel)
                } while (cursor.moveToNext())
            }
        }
        return resultList
    }

    fun deleteResult(id: Int) {
        val database = this.writableDatabase
        database.delete("SavedResults", "ID='$id'", null)
        database.close()
    }

    companion object {
        const val database_name = "Images.db"
        const val table_results = "SavedResults"
        const val COL_1 = "ID"
        const val COL_Original = "Original"
        const val COL_Result1 = "Result1"
        const val COL_Result2 = "Result2"
        const val COL_Result3 = "Result3"
        const val COL_Result4 = "Result4"
        const val COL_Result5 = "Result5"
    }
}