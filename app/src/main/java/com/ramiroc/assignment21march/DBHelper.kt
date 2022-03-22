package com.ramiroc.assignment21march
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.content.contentValuesOf
import kotlin.math.E

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                FIRST_NAME_COl + " TEXT," +
                LAST_NAME_COL + " TEXT," + REWARDS + " INTEGER" +")")
        Log.i("QUINTRIX query",query)
        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun insert(firstName : String, lastName : String, uID: String, rewards: String){

        // below we are creating
        // a content values variable
        val values = ContentValues()


        // we are inserting our values
        // in the form of key-value pair
        values.put(FIRST_NAME_COl, firstName)
        values.put(LAST_NAME_COL, lastName)
        values.put(ID_COL, uID)
        values.put(REWARDS, rewards)
        Log.i("Inserting", values.toString())

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase


        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    fun update(id: String, firstName: String, lastName: String, rewards: String ){
        val values = ContentValues()
        values.put(ID_COL, id)
        values.put(FIRST_NAME_COl, firstName)
        values.put(LAST_NAME_COL, lastName)
        values.put(REWARDS, rewards)

        val db = this.writableDatabase
        db.update(TABLE_NAME, values, "$ID_COL= ?", arrayOf(id.toString()))
    }

    // below method is to get
    // all data from our database
    fun getAll(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        Log.e("QUNITRIX_HELP","Selecting data from table now")
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    fun delete(id: String){
        val db : SQLiteDatabase = this.writableDatabase
        db.delete(TABLE_NAME, "$ID_COL = ?", arrayOf(id.toString()))
    }

    fun getContact(id: String): Cursor? {
        val db = this.readableDatabase

        Log.e("CONTACT SEARCH", "Selecting data from table")

        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id=${id}",null)
    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "assignmentdb"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "simpleTable"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val FIRST_NAME_COl = "firstName"

        // below is the variable for age column
        val LAST_NAME_COL = "lastName"

        val REWARDS = "rewards"
    }
}