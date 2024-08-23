package com.example.bmapp


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmapp.ui.theme.BMAppTheme
import com.example.bmapp.ui.theme.BM_Red
import com.example.bmapp.ui.theme.BM_Red_Disabled
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BMAppTheme {

                LoginDesign(modifier = Modifier)
            }
        }
    }
}


@Composable
fun LoginDesign(modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(start = 16.dp, end = 16.dp, top = 64.dp, bottom = 32.dp)


    )
    {
        LanguageBar(modifier = modifier)
        Column(verticalArrangement = Arrangement.Center) {
            Spacer(modifier = modifier.size(32.dp))
            AuthenticationArea(modifier)
            Spacer(modifier = modifier.size(48.dp))
            OtherServicesArea(modifier)
        }

    }
}


@Composable
fun LanguageBar(modifier: Modifier = Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bm_icon),
            contentDescription = "Bank Misr Lego"
        )
        val context = LocalContext.current

        ClickableText(
            text = AnnotatedString(
                text = stringResource(R.string.language),
                spanStyle = TextStyle(
                    fontSize = 20.sp,
                    color = BM_Red,
                    fontWeight = FontWeight.Bold
                ).toSpanStyle()
            ),

            onClick = {
                setAppLocale(context, context.getString(R.string.ar))

            }
        )


    }
}



@Composable
fun AuthenticationArea(modifier: Modifier = Modifier) {

    var textInUsername by remember { mutableStateOf(TextFieldValue("")) }
    var textInPassword by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    val isButtonEnabled = textInUsername.text.isNotBlank() && textInPassword.text.isNotBlank()
    Column(verticalArrangement = Arrangement.Center) {


        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black , unfocusedTextColor = Color.DarkGray, focusedBorderColor = Color.Gray),
            value = textInUsername,
            label = {
                Text(
                    text = stringResource(R.string.username_field),
                    color = Color.Gray
                )
            },
            onValueChange = { textInUsername = it }, modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = modifier.size(20.dp))



        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.Black , unfocusedTextColor = Color.DarkGray,focusedBorderColor = Color.Gray),
            value = textInPassword,
            label = {
                Text(
                    text = stringResource(R.string.password_field),
                    color = Color.Gray
                )
            },
            onValueChange = { textInPassword = it },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image =
                    if (passwordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )


        Spacer(modifier = modifier.size(16.dp))



        ClickableText(
            text = AnnotatedString(stringResource(R.string.forgot_username_password)),
            onClick = { },
            style = TextStyle(
                color = Color.Gray,
                textDecoration = TextDecoration.Underline,
                fontSize = 12.sp
            )
        )




        Spacer(modifier = modifier.size(36.dp))


        Button(
            onClick = { /*TODO*/ },
            enabled = isButtonEnabled,
            colors = ButtonColors(BM_Red, Color.White, BM_Red_Disabled, Color.White),
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(stringResource(R.string.login_button_text))
        }

        Spacer(modifier = modifier.size(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = stringResource(R.string.need_help),
                color = Color.DarkGray,
                fontSize = 12.sp
            )

            ClickableText(
                text = AnnotatedString(stringResource(R.string.contact_us)),
                onClick = { },
                style = TextStyle(
                    color = BM_Red,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.wrapContentSize()
            )
        }


    }
}

@Composable
fun OtherServicesArea(modifier: Modifier = Modifier) {

    Column(verticalArrangement = Arrangement.Center) {
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = modifier.size(16.dp))

        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
        {

            ServiceColumn(
                serviceName = stringResource(R.string.our_products),
                serviceImgID = R.drawable.our_products
            )

            ServiceColumn(
                serviceName = stringResource(R.string.exchange_rate),
                serviceImgID = R.drawable.exchange_rate
            )

            ServiceColumn(
                serviceName = stringResource(R.string.security_tips),
                serviceImgID = R.drawable.security_tips
            )

            ServiceColumn(
                serviceName = stringResource(R.string.nearest_branch_or_atm),
                serviceImgID = R.drawable.nearest_branch_or_atm
            )

        }
    }
}


@Composable
fun ServiceColumn(serviceName: String, serviceImgID: Int, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.width(64.dp)) {
        Image(
            painter = painterResource(id = serviceImgID),
            contentDescription = serviceName,
            modifier = modifier.size(64.dp)
        )
        Text(
            text = serviceName,
            modifier.fillMaxWidth(),
            fontSize = 10.sp, lineHeight = 15.sp,
            textAlign = TextAlign.Center
        )

    }

}


fun setAppLocale(context: Context, language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val resources = context.resources
    val configuration = Configuration(resources.configuration)
    configuration.setLocale(locale)

    configuration.setLocale(locale)

    resources.updateConfiguration(configuration, resources.displayMetrics)

//    (context as? Activity)?.recreate() // not Contestant with the RTL and the LTR transition ?!


    val refreshIntent = Intent(context, (context as? Activity)?.javaClass)
    context.startActivity(refreshIntent)
    (context as? Activity)?.finish()
}







@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, locale = "ar",
)
@Composable
fun GreetingPreview() {
    BMAppTheme {

        LoginDesign(modifier = Modifier)
    }
}