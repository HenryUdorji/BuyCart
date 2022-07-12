package com.hashconcepts.buycart.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.domain.model.Product

/**
 * @created 12/07/2022 - 8:01 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Composable
fun WishListOrderContainer(
    product: Product,
    isWishList: Boolean = true,
    onDeleteClicked: () -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.placeholder_image),
            modifier = Modifier
                .size(70.dp)
                .fillMaxWidth()
        )

        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.h2,
                fontSize = 12.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            
            Text(text = "$${product.price}", style = MaterialTheme.typography.h2, fontSize = 14.sp)
        }
    }
}