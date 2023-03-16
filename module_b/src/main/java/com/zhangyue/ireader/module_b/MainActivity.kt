package com.zhangyue.ireader.module_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhangyue.ireader.annotations.Route

@Route(path = "/module_b/main")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}