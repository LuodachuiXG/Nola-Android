package cc.loac.nola.ui.theme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Stable

/**
 * 自定义全局弹簧动画配置
 * 所有应用弹簧动画的地方都应使用本方法，而不是默认的 spring 或自定 spring
 * 因此，修改此处弹簧配置，将会影响整个 App 的弹簧动画效果
 */
@Stable
fun <T> mSpring() = spring<T>(
    dampingRatio = Spring.DampingRatioLowBouncy,
    stiffness = Spring.StiffnessLow
)