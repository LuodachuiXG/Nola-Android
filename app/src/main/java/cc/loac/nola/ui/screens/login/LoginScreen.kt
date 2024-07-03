package cc.loac.nola.ui.screens.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cc.loac.nola.ui.AppScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    AnimatedVisibility(
        visibleState = animateState,
        enter = scaleIn(
            animationSpec = tween(500)
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    AnimatedVisibility(
                        visibleState = animateState,
                        enter = slideInHorizontally(
                            animationSpec = tween(500, 400),
                            initialOffsetX = {
                                -1000
                            }
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "Nola",
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 64.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .alignByBaseline()
                            )
                            AnimatedVisibility(
                                visibleState = animateState,
                                enter = scaleIn(
                                    tween(300, 2000)
                                ),
                                modifier = Modifier.alignByBaseline()
                            ) {
                                Text(
                                    text = "Android",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(start = 5.dp)
                                        .alignByBaseline()
                                )
                            }
                        }
                    }

                    AnimatedVisibility(
                        visibleState = animateState,
                        enter = slideInVertically(
                            animationSpec = tween(600, 500),
                            initialOffsetY = {
                                -500
                            }
                        ) + fadeIn(tween(600, 500))
                    ) {
                        OutlinedTextField(
                            value = url,
                            onValueChange = {
                                url = it
                            },
                            placeholder = {
                                Text(text = "请输入 Nola 站点地址")
                            },
                            label = {
                                Text(text = "Nola 站点地址")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    AnimatedVisibility(
                        visibleState = animateState,
                        enter = slideInVertically(
                            animationSpec = tween(600, 800),
                            initialOffsetY = {
                                -500
                            }
                        ) + fadeIn(tween(600, 800))
                    ) {
                        OutlinedTextField(
                            value = username,
                            onValueChange = {
                                username = it
                            },
                            placeholder = {
                                Text(text = "请输入用户名")
                            },
                            label = {
                                Text(text = "用户名")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    AnimatedVisibility(
                        visibleState = animateState,
                        enter = slideInVertically(
                            animationSpec = tween(600, 1100),
                            initialOffsetY = {
                                -500
                            }
                        ) + fadeIn(tween(600, 1100))
                    ) {
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            placeholder = {
                                Text(text = "请输入密码")
                            },
                            label = {
                                Text(text = "密码")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    AnimatedVisibility(
                        visibleState = animateState,
                        enter = slideInHorizontally(
                            animationSpec = tween(600, 1400),
                            initialOffsetX = {
                                +500
                            }
                        ) + fadeIn(tween(600, 1400))
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(AppScreens.HOME.route) {
                                    popUpTo(AppScreens.LOGIN.route) {
                                        inclusive = true
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .weight(1f),
                            shape = RoundedCornerShape(10.dp),
                        ) {
                            Text(text = "登录")
                        }
                    }
                }

            }
        }
    }
}