package com.hashconcepts.buycart.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hashconcepts.buycart.ui.theme.*

/**
 * @created 01/07/2022 - 8:41 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */
@Composable
fun CustomTextField(
    label: String,
    text: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    onHideKeyboard: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 30.dp, top = 30.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.body1)

        TextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = background,
                textColor = secondary,
                cursorColor = primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.body1,
                    color = disable
                )
            },
            textStyle = MaterialTheme.typography.body1,
            shape = RoundedCornerShape(10.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    onHideKeyboard()
                }
            )
        )
    }
}