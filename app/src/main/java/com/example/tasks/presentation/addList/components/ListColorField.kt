package com.example.tasks.presentation.addList.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasks.presentation.util.ListColor
import com.example.tasks.presentation.util.colorList

@Composable
fun ListColorField(
    selectedItemIndex: Int,
    onItemClickListener: (index: Int) -> Unit,
    list: List<ListColor> = colorList
) {
    LazyRow(
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
    ) {

        itemsIndexed(list) { index, item ->
            RadioButton(
                selected = list[selectedItemIndex] == item,
                onClick = { onItemClickListener(index) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = item.color,
                    unselectedColor = item.color.copy(alpha = .5f)
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}