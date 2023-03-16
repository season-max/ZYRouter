package com.zhangyue.ireader.module_a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhangyue.ireader.annotations.Route

@Route(path = "/module_a/test_1")
class Test_1_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)
    }
}