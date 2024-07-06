package cc.loac.nola.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import cc.loac.nola.R
import cc.loac.nola.ui.theme.LARGE
import cc.loac.nola.ui.theme.SMALL
import cc.loac.nola.ui.theme.SMALL_MEDIUM

/**
 * 默认输入框
 * @param modifier 修饰符
 * @param value 内容
 * @param onValueChange 输入框内容改变回调
 * @param label 标签
 * @param placeholder 占位符
 * @param password 是否为密码输入框
 * @param passwordCanVisible 是否允许密码可见（前提：password = true，且 tailIcon 将失效）
 * @param headIcon 头部图标
 * @param tailIcon 尾部图标
 */
@Composable
fun Input(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    password: Boolean = false,
    passwordCanVisible: Boolean = false,
    headIcon: Painter? = null,
    tailIcon: Painter? = null
) {
    // 是否显示密码
    var showPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        visualTransformation = if (password) {
            if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        } else {
            VisualTransformation.None
        },
        leadingIcon = if (headIcon != null) {
            {
                Icon(
                    painter = headIcon,
                    contentDescription = label,
                    modifier = Modifier.size(LARGE)
                )
            }
        } else null,
        trailingIcon = if (passwordCanVisible && password) {
            {
                // 当前是密码模式，并且允许密码可见
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        painter = if (showPassword) {
                            painterResource(id = R.drawable.password_open)
                        } else {
                            painterResource(id = R.drawable.password)
                        },
                        contentDescription = label,
                        modifier = Modifier.size(LARGE)
                    )
                }
            }
        } else if (tailIcon != null) {
            {
                Icon(
                    painter = tailIcon,
                    contentDescription = label,
                    modifier = Modifier.size(LARGE)
                )
            }
        } else null,
        placeholder = {
            Text(text = placeholder)
        },
        shape = CardDefaults.shape
    )
}