package cc.loac.nola.ui.screens.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cc.loac.nola.R
import cc.loac.nola.ui.AppScreens
import cc.loac.nola.ui.component.Input
import cc.loac.nola.ui.component.SpacerVM
import cc.loac.nola.ui.theme.EXTRA_SMALL
import cc.loac.nola.ui.theme.LARGE
import cc.loac.nola.ui.theme.MEDIUM_LARGE
import cc.loac.nola.ui.theme.SMALL_MEDIUM
import cc.loac.nola.ui.theme.mSpring
import kotlinx.coroutines.delay

/**
 * 登录页面
 */
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel(),
    snackBarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()

    val animateState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    var url by remember {
        mutableStateOf("")
    }

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    LoginCard(
        url = url,
        username = username,
        password = password,
        onUrlChange = {
            url = it
        },
        onUsernameChange = {
            username = it
        },
        onPasswordChange = {
            password = it
        },
        onLogin = {
            navController.navigate(AppScreens.HOME.route) {
                popUpTo(AppScreens.LOGIN.route) {
                    inclusive = true
                }
            }
        }
    )
}

/**
 * 登录卡片
 * @param url Nola 站点地址
 * @param username 用户名
 * @param password 密码
 * @param onUrlChange 站点地址改变回调
 * @param onUsernameChange 用户名改变回调
 * @param onPasswordChange 密码改变回调
 * @param onLogin 登录回调
 */
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginCard(
    url: String = "",
    username: String = "",
    password: String = "",
    onUrlChange: (String) -> Unit = {},
    onUsernameChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onLogin: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    // 输入框是否显示（动画控制）
    var showInputs by remember {
        mutableStateOf(false)
    }
    // 按钮是否显示（动画控制）
    var showButton by remember {
        mutableStateOf(false)
    }
    // 设置动画显示时机
    LaunchedEffect(Unit) {
        delay(200)
        showInputs = true
        delay(600)
        showButton = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = showInputs,
            enter = expandVertically(
                animationSpec = mSpring()
            ) + fadeIn(),
            modifier = Modifier.padding(MEDIUM_LARGE)
        ) {
            Column {
                Input(
                    value = url,
                    onValueChange = onUrlChange,
                    label = stringResource(id = R.string.nola_web_url),
                    placeholder = stringResource(id = R.string.please_enter_web_url),
                    headIcon = painterResource(id = R.drawable.website)
                )
                SpacerVM()
                Input(
                    value = username,
                    onValueChange = onUsernameChange,
                    label = stringResource(id = R.string.username),
                    placeholder = stringResource(id = R.string.please_enter_username),
                    headIcon = painterResource(id = R.drawable.user)
                )
                SpacerVM()
                Input(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = stringResource(id = R.string.password),
                    placeholder = stringResource(id = R.string.please_enter_password),
                    password = true,
                    passwordCanVisible = true,
                    headIcon = painterResource(id = R.drawable.key)
                )
            }
        }

        AnimatedVisibility(
            visible = showButton,
            enter = scaleIn(mSpring()) + fadeIn(),
        ) {
            Button(
                onClick = onLogin,
                shape = CardDefaults.shape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MEDIUM_LARGE)
            ) {
                Text(text = stringResource(id = R.string.login))
            }
        }

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn()
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(bottom = SMALL_MEDIUM)
                    .fillMaxHeight()
            ) {
                Text(
                    text = stringResource(id = R.string.nola_for_android),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Bottom),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }

        }
    }
}