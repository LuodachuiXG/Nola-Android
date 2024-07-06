package cc.loac.nola.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import cc.loac.nola.ui.theme.MEDIUM


/**
 * 垂直间间距组件（默认中大小）
 */
@Composable
fun SpacerVM() {
    MyVSpacer(MEDIUM)
}

/**
 * 水平间距组件（默认中大小）
 */
@Composable
fun SpacerHM() {
    MyHSpacer(MEDIUM)
}

/**
 * 垂直间距组件
 * @param height 高度
 */
@Composable
fun MyVSpacer(height: Dp) {
    Spacer(
        modifier = Modifier.height(height)
    )
}

/**
 * 水平间距组件
 * @param width 宽度
 */
@Composable
fun MyHSpacer(width: Dp) {
    Spacer(
        modifier = Modifier.height(width)
    )
}