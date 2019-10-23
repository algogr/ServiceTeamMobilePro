package gr.algo.serviceteammobilepro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.joda.time.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        button.setOnClickListener(){
            val intent= Intent(this,MyTicketsActivity::class.java)
            startActivity(intent)

        }
    }
}
