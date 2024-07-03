package cc.loac.nola

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cc.loac.nola.ui.NolaApp
import cc.loac.nola.ui.theme.NolaTheme

open class MainActivity : ComponentActivity() {

    companion object {
        // 全局 Context
        lateinit var appContext: Context
            private set
        // 主 Activity
        lateinit var mainActivity: ComponentActivity
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = applicationContext
        mainActivity = this
        enableEdgeToEdge()
        setContent {
            NolaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Nola 程序入口
                    NolaApp()
                }
            }
        }
    }

}