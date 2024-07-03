package cc.loac.nola.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/**
 * 主页
 */
@Composable
fun HomeScreen(
    navController: NavController
) {
    var showMsg by remember {
        mutableStateOf(false)
    }

    if (showMsg) {
        AlertDialog(
            onDismissRequest = { showMsg = false },
            confirmButton = {
                TextButton(onClick = { showMsg = false }) {
                    Text("确定")
                }
            },
            title = {
                Text("温馨提示")
            },
            text = {
                Text("你点击了测试按钮")
            }
        )
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 40.dp)
    ) {
        Text(
            text = "Home",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 30.sp
        )

        Button(
            onClick = {
                showMsg = true
            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text("Message Box")
        }
    }
}