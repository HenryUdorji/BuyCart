package com.hashconcepts.buycart.presentation.screens.auth.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hashconcepts.buycart.R
import com.hashconcepts.buycart.domain.model.OnBoardingItem
import com.hashconcepts.buycart.presentation.components.Indicators
import com.hashconcepts.buycart.presentation.screens.auth.AuthViewModel
import com.hashconcepts.buycart.presentation.screens.destinations.WelcomeScreenDestination
import com.hashconcepts.buycart.ui.theme.backgroundColor
import com.hashconcepts.buycart.ui.theme.primaryColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

/**
 * @created 26/06/2022 - 4:18 AM
 * @project BuyCart
 * @author  ifechukwu.udorji
 */

@Destination
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navigator: DestinationsNavigator,
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
        systemUiController.setNavigationBarColor(backgroundColor)
    }

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val onBoardingItems = OnBoardingItem.provideOnBoardingData()
    val viewModel = hiltViewModel<AuthViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(10.dp)
    ) {
        TopSection(
            onSkipClicked = {
                viewModel.saveFirstAppLaunch(false)
                navigator.popBackStack()
                navigator.navigate(WelcomeScreenDestination)
            },
            onBackClicked = {
                if (pagerState.currentPage - 1 > -1) {
                    scope.launch {
                        pagerState.scrollToPage(pagerState.currentPage - 1)
                    }
                } else {
                    //onCloseApp()
                }
            }
        )

        HorizontalPager(
            count = onBoardingItems.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            val onBoardingItem = onBoardingItems[pagerState.currentPage]

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = onBoardingItem.image),
                    contentDescription = "Image"
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(id = onBoardingItem.title),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(id = onBoardingItem.information),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
            }
        }

        BottomSection(size = pagerState.pageCount, index = pagerState.currentPage) {
            if (pagerState.currentPage + 1 < pagerState.pageCount) {
                scope.launch {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                }
            } else {
                viewModel.saveFirstAppLaunch(false)
                navigator.popBackStack()
                navigator.navigate(WelcomeScreenDestination)
            }
        }
    }
}

@Composable
fun TopSection(
    onSkipClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "back",
            modifier = Modifier.clickable { onBackClicked() }
        )
        Text(
            text = "Skip",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.clickable { onSkipClicked() }
        )
    }
}

@Composable
fun BottomSection(
    size: Int,
    index: Int,
    onNextClicked: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Indicators(size = size, index = index)

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .background(primaryColor)
                .size(50.dp)
                .clickable { onNextClicked() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = "forward",
            )
        }
    }
}
