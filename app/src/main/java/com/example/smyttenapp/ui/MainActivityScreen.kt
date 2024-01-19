package com.example.smyttenapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.smyttenapp.models.ProductDetails
import com.example.smyttenapp.theme.SmyttenAppTheme
import com.example.smyttenapp.viewModels.MainViewModel

@Composable
fun MainActivityScreen(
    viewModel: MainViewModel,
    onActivityButtonClick: (Int) -> Unit
) {
    SmyttenAppTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp)
        ) {
            if (viewModel.productData == null || viewModel.productData?.content.isNullOrEmpty()) {
                item(key = "emptyView") {
                    EmptyView()
                }

            } else {
                viewModel.productData?.content?.forEachIndexed { index, productType ->
                    item(key = "productCarousal$index") {
                        if (productType.type == "BUTTON") {
                            ButtonCarousal(
                                data = productType.data,
                                onClick = onActivityButtonClick
                            )
                        } else {
                            ProductCarousal(
                                data = productType.data,
                                index = index
                            )
                        }
                    }
                }
            }
        }
        if (viewModel.showDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.showDialog = false }
            )
        }
    }
}

@Composable
fun ProductCarousal(data: List<ProductDetails>?, index: Int) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        data?.forEachIndexed { i, productDetails ->
            item(key = "product$index$i") {
                ProductCard(productDetails)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductCard(product: ProductDetails) {
    var isInCart by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .width(170.dp)
            .height(280.dp)
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = rememberImagePainter(data = product.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "${product.brand} - ${product.name}",
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Button(
                onClick = { isInCart = !isInCart },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = if (isInCart) "In Cart" else "Add to Cart")
            }
        }
    }
}

@Composable
fun ButtonCarousal(data: List<ProductDetails>?, onClick: (Int) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        data?.forEachIndexed { index, productDetails ->
            item(key = "button$index") {
                Button(
                    onClick = { onClick(index) },
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(text = productDetails.name.orEmpty())
                }
            }
        }
    }
}

@Composable
fun EmptyView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Something Went Wrong!",
            modifier = Modifier.wrapContentSize(),
        )
    }
}

@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = "Smytten")
        },
        text = {
            Text(text = "Hello!")
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Ok")
            }
        }
    )
}