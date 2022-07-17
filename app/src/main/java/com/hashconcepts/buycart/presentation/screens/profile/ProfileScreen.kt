package com.hashconcepts.buycart.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.domain.model.UserProfile
import com.hashconcepts.buycart.presentation.screens.destinations.LoginScreenDestination
import com.hashconcepts.buycart.presentation.screens.destinations.PaymentInfoScreenDestination
import com.hashconcepts.buycart.presentation.screens.destinations.ProfileScreenDestination
import com.hashconcepts.buycart.presentation.screens.destinations.WelcomeScreenDestination
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.hashconcepts.buycart.ui.theme.disableColor
import com.hashconcepts.buycart.utils.UIEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

/**
 * @created 08/07/2022 - 4:49 PM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(Color.White)
    }


    val state = profileViewModel.profileScreenState

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        profileViewModel.eventChannelFlow.collectLatest { uiEvent ->
            when (uiEvent) {
                is UIEvents.ErrorEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                        duration = SnackbarDuration.Short
                    )
                }
                else -> {
                    //Logout User
                    navigator.popBackStack()
                    navigator.navigate(WelcomeScreenDestination)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = backgroundColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
                    .padding(20.dp)
            ) {
                ProfileHeaderSection(state.userProfile)

                Spacer(modifier = Modifier.height(50.dp))

                ProfileItem(icon = R.drawable.ic_cart, title = "My Order") {

                }

                Spacer(modifier = Modifier.height(25.dp))

                ProfileItem(icon = R.drawable.ic_card, title = "Payment Information") {
                    navigator.navigate(PaymentInfoScreenDestination)
                }

                Spacer(modifier = Modifier.height(25.dp))

                ProfileItem(icon = R.drawable.ic_log_out, title = "Log out") {
                    profileViewModel.logoutUser()
                }
            }
        }
    }
}

@Composable
fun ProfileHeaderSection(userProfile: UserProfile?) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = userProfile?.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Column(modifier = Modifier.weight(1f)) {
            val name = "${userProfile?.name?.firstname} ${userProfile?.name?.lastname}"
            Text(text = name, style = MaterialTheme.typography.h2, fontSize = 18.sp)
            Text(
                text = userProfile?.phone ?: "",
                style = MaterialTheme.typography.body1,
                fontSize = 12.sp,
                color = disableColor
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
                .size(40.dp)
                .padding(5.dp)
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
    }
}

@Composable
fun ProfileItem(
    icon: Int,
    title: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .background(backgroundColor)
                .size(40.dp)
                .padding(5.dp)
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = null)
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.h2,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}
