package com.mauleco.bl.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mauleco.bl.ui.theme.viewModel.MainViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mauleco.bl.R

@Composable
fun PatientDetails(
    modifier: Modifier = Modifier,
    vm: MainViewModel = viewModel()
) {
    val patient = vm.patient.value

    Column(modifier = modifier.padding(8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (patient != null) {
            Text(
                text = "Patient Name",
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(id = R.color.primary)
            )
            Text(text = patient.fullName, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Date of Birth",
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(id = R.color.primary)
            )
            Text(text = patient.dateOfBirth, style = MaterialTheme.typography.bodyLarge)
        } else {
            Text(text = "Loading patient details...", style = MaterialTheme.typography.bodyLarge)
        }
    }
}