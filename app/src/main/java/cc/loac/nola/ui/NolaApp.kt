package cc.loac.nola.ui

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cc.loac.nola.MainActivity
import cc.loac.nola.R
import cc.loac.nola.ui.screens.home.HomeScreen
import cc.loac.nola.ui.screens.login.LoginScreen
import cc.loac.nola.ui.screens.me.MeScreen

/**
 * 所有页面枚举类
 */
enum class AppScreens(val route: String) {
    /** 登录页 **/
    LOGIN("login"),

    /** 个人页 **/
    ME("me"),

    /** 主页 **/
    HOME("home")
}

/**
 * 底部导航栏密封类
 */
private sealed class BottomNav(
    val route: AppScreens,
    val name: String,
    val icon: Int
) {
    data object Me : BottomNav(AppScreens.ME, "我", R.drawable.user)
    data object HOME : BottomNav(AppScreens.HOME, "首页", R.drawable.home)
}


/**
 * Nola App 入口
 */
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun NolaApp() {
    // 程序导航（Screen 入口）
    AppNavigation()
}

/**
 * App 页面导航
 */
@Composable
private fun AppNavigation() {
    // 导航控制器
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    // 是否显示退出对话框
    var showExitDialog by remember {
        mutableStateOf(false)
    }

    // 底部导航栏项
    val bottomNavItems = listOf(
        BottomNav.HOME,
        BottomNav.Me
    )

    // 底部导航栏项路由名数组
    val bottomNavItemRouteNames = bottomNavItems.map {
        it.route.route
    }

    // 获取导航的当前路由
    // 使用 NavController 的 currentBackStackEntryAsState 扩展函数来获取当前导航堆栈的条目，并将其作为一个状态进行观察
    // 这允许 UI 根据导航状态的变化自动更新
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // 解析当前导航目的地的路由字符串
    // 这对于根据当前路由来决定 UI 行为是非常重要的，例如，根据当前路由来决定显示哪个Fragment
    val currentRoute = navBackStackEntry?.destination?.route

    // 脚手架
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        bottomBar = {
            // 只有当前页面存在于底部导航栏才显示底部导航栏
            if (currentRoute in bottomNavItemRouteNames) {
                NavigationBar {
                    bottomNavItems.forEach {
                        NavigationBarItem(
                            alwaysShowLabel = false,
                            selected = currentRoute == it.route.route,
                            onClick = {
                                navController.navigate(it.route.route) {
                                    // 清空栈内到 popUpTo ID 之间的所有 Item
                                    navController.currentDestination?.route?.let { route ->
                                        popUpTo(route) {
                                            inclusive = true
                                        }
                                        // 避免多次点击产生多个实例
                                        launchSingleTop = true
                                        // 再次点击之前的 Item 时恢复状态
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = it.icon),
                                    contentDescription = it.name,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            label = {
                                Text(it.name)
                            }
                        )
                    }
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = AppScreens.LOGIN.route,
            enterTransition = {
                fadeIn()
            },
            exitTransition = {
                fadeOut()
            },
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            // 登录页面
            composable(route = AppScreens.LOGIN.route) {
                LoginScreen(
                    navController = navController,
                    snackBarHostState = snackBarHostState
                )
            }

            // 主页
            composable(route = AppScreens.HOME.route) {
                HomeScreen(navController = navController)
            }

            // 个人页
            composable(route = AppScreens.ME.route) {
                MeScreen(navController = navController)
            }
        }
    }
}