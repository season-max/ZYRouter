package com.zhangyue.ireader.zyrouter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhangyue.ireader.annotations.Route

@Route(path = "/main/activity_2")
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}