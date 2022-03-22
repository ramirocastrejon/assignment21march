package com.ramiroc.assignment21march

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val EXTERNAL_STORAGE_PERMISSION_CODE = 23

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val firstName = findViewById<EditText>(R.id.firstName)
        val lastName = findViewById<EditText>(R.id.lastName)
        val uID = findViewById<EditText>(R.id.uniqueID)
        val rewards = findViewById<EditText>(R.id.rewards)

        val addButton = findViewById<Button>(R.id.add)
        val displayButton = findViewById<Button>(R.id.displayInfo)
        val searchID = findViewById<EditText>(R.id.search)
        val deleteButton = findViewById<Button>(R.id.delete)
        val infoResults = findViewById<TextView>(R.id.infoResults)

        addButton.setOnClickListener {
            val db = DBHelper(this, null)
            db.insert(firstName.text.toString(),lastName.text.toString(), uID.text.toString(), rewards.text.toString() )
        }

        displayButton.setOnClickListener {
            val db = DBHelper(this, null)
            val cursor = db.getContact(searchID.text.toString())

            if (cursor != null) {
                cursor!!.moveToFirst()
                val stringBuffer = StringBuffer()

                stringBuffer.append("UID:" + cursor.getInt(0).toString() + "\n")
                stringBuffer.append("First Name:" + cursor.getInt(1).toString() + "\n")
                stringBuffer.append("Last Name:" + cursor.getInt(2).toString() + "\n")
                stringBuffer.append("Rewards: " + cursor.getInt(3).toString() + "\n\n")

                infoResults!!.text =stringBuffer.toString()
                    //cursor.getString(cursor.getColumnIndex(cursor.columnNames.toString()))

            } else {
                Toast.makeText(this, "Please input valid id (1-99)", Toast.LENGTH_LONG).show()
            }
        }

    }
}