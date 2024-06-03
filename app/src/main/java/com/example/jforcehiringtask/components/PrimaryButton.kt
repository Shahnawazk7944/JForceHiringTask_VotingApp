package com.example.jforcehiringtask.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jforcehiringtask.ui.theme.FPrimaryGreen
import com.example.jforcehiringtask.ui.theme.FSecondaryBackgroundWhite
import com.example.jforcehiringtask.ui.theme.poppins

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    eventText: String,
    leadingIconComposable: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(15.dp),
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(10.dp),
        //.clip(RoundedCornerShape(50.dp))
        shape = shape,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 2.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = FPrimaryGreen,
            contentColor = FSecondaryBackgroundWhite,
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {


            leadingIconComposable()
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                eventText,
                fontFamily = poppins,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

        }
    }
}