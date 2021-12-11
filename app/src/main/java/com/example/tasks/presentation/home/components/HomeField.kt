package com.example.tasks.presentation.home.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeField(
    onItemClicked: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerComponents(
                "Olivia Mitchell",
                "https://images.complex.com/complex/images/c_scale,f_auto,q_auto,w_1920/fl_lossy,pg_1/ok26lkxxcptihvwljzaw/girl-in-red?fimg-ssr-default",
                scope,
                drawerState,
                onItemClicked = onItemClicked
            )
        },
        content = {
            HomeComponents("Olivia Mitchell",scope, drawerState)
        }
    )
}
