package com.zhangyue.ireader.zyrouter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.zhangyue.ireader.api.interfaces.NavigationCallback
import com.zhangyue.ireader.api.launch.ZYRouter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.openDebug).setOnClickListener {
            ZYRouter.debuggable(true)
        }

        findViewById<TextView>(R.id.init).setOnClickListener {
            ZYRouter.init(application)
        }

        findViewById<TextView>(R.id.destroy).setOnClickListener {
            ZYRouter.destroy()
        }

        findViewById<TextView>(R.id.normalNavigation).setOnClickListener {
            ZYRouter.getInstance().build("xx").navigation(this, object : NavigationCallback {
                override fun onLost(path: String?) {
                    TODO("Not yet implemented")
                }

                override fun onFound(path: String?, destination: Class<*>?) {
                    TODO("Not yet implemented")
                }
            })
        }


    }
}