import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.week04c_again.R

// Image & slotBtn을 보여주는 함수 (일종의 큰 틀)
@Composable
fun ImageWithSlot(imgId: Int, slotBtn: @Composable () -> Unit) {
    Image(
        painter = painterResource(id = imgId),
        contentDescription = "이미지",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
    )
    slotBtn()
}

// slotBtn에 넣을 항목 후보 1 - 아이콘 버튼
@Composable
fun BtnWithIcon(counter: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Icon(imageVector = Icons.Default.Favorite, contentDescription = "이미지", tint = Color.Red)
        if (counter > 0)
            Text(text = counter.toString())
        else
            Text(text = "Like")
    }
}

// slotBtn에 넣을 항목 후보 2 - 아이콘 뱃지
@OptIn(ExperimentalMaterial3Api::class) // 아직 BadgedBox는 테스트 중인 기능이라 이 문장을 추가해줘야 함
@Composable
fun IconWithBadge(
    counter: Int,
    onClick: () -> Unit
) {
    BadgedBox(
        badge = {
            Badge {
                Text(text = "$counter")
            }
        }) {
        // content에 해당하는 부분(마지막 인자가 람다인 경우 바깥으로 뺄 수 있음)
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "이미지",
            modifier = Modifier.clickable { onClick() },
            tint = Color.Red
        )
    }
}

// data class (4주차 p.22)

// ViewModel class (4주차 p.22)

@Composable
fun MainScreen() {
    var scrollState = rememberScrollState() // scroll 정보를 담고 있음

    // counter state 선언
    var img1 by rememberSaveable {
        mutableStateOf(10)
    }
    var img2 by rememberSaveable {
        mutableStateOf(20)
    }
    var img3 by rememberSaveable {
        mutableStateOf(30)
    }
    var img4 by rememberSaveable {
        mutableStateOf(40)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        ImageWithSlot(imgId = R.drawable.image01) {
            BtnWithIcon(counter = img1, onClick = { img1++ })
        }
        ImageWithSlot(imgId = R.drawable.image02) {
            IconWithBadge(counter = img2, onClick = { img2++ })
        }
        ImageWithSlot(imgId = R.drawable.image03) {
            BtnWithIcon(counter = img3, onClick = { img3++ })
        }
        ImageWithSlot(imgId = R.drawable.image01) {
            BtnWithIcon(counter = img4, onClick = { img4++ })
        }
    }
}
