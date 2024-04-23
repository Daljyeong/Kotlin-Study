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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
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
data class ImgData(var img: Int, var counter: Int)

// ViewModel class (4주차 p.22) => 상태를 캐시해, 화면 회전/구성 변경되어도 데이터가 유지됨
class ImgViewModel : ViewModel() {
    var imglist = mutableStateListOf<ImgData>()
        private set // 밖에서의 변경을 금지하기 위해 set을 private로 설정

    // 객체가 만들어질 때 딱 한 번만 호출되는 초기화 부분
    init {
        imglist.add(ImgData(R.drawable.image01, 10))
        imglist.add(ImgData(R.drawable.image02, 20))
        imglist.add(ImgData(R.drawable.image03, 30))
        imglist.add(ImgData(R.drawable.image01, 40))
    }

    // index에 해당하는 위치의 counter를 증가시키는 함수
    fun increaseCount(index: Int) {
//        imglist[index].counter = imglist[index].counter + 1; // 이렇게 작성하면 작동 안됨

        // 객체의 일부 속성만 바뀔 때 recomposition이 이뤄지지 않음 => 객체 자체를 copy해줘야 함
        imglist[index] = imglist[index].copy(counter = imglist[index].counter + 1)
    }
}

@Composable
fun MainScreen(imgViewModel: ImgViewModel = viewModel()) {
    var scrollState = rememberScrollState() // scroll 정보를 담고 있음

    // counter state 선언 => ViewModel을 사용하게 되면 기존에 rememberSaveable을 사용하던 방법은 필요없어짐
//    var img1 by rememberSaveable {
//        mutableStateOf(10)
//    }
//    var img2 by rememberSaveable {
//        mutableStateOf(20)
//    }
//    var img3 by rememberSaveable {
//        mutableStateOf(30)
//    }
//    var img4 by rememberSaveable {
//        mutableStateOf(40)
//    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        ImageWithSlot(imgId = imgViewModel.imglist[0].img) {
            BtnWithIcon(
                counter = imgViewModel.imglist[0].counter,
                onClick = { imgViewModel.increaseCount(0) })
        }
        ImageWithSlot(imgId = imgViewModel.imglist[1].img) {
            IconWithBadge(
                counter = imgViewModel.imglist[1].counter,
                onClick = { imgViewModel.increaseCount(1) })
        }
        ImageWithSlot(imgId = imgViewModel.imglist[2].img) {
            BtnWithIcon(
                counter = imgViewModel.imglist[2].counter,
                onClick = { imgViewModel.increaseCount(2) })
        }
        ImageWithSlot(imgId = imgViewModel.imglist[3].img) {
            BtnWithIcon(
                counter = imgViewModel.imglist[3].counter,
                onClick = { imgViewModel.increaseCount(3) })
        }
    }
}
