import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SharedViewModel : ViewModel() {
    val referenceCards: DatabaseReference = FirebaseDatabase.getInstance().getReference("cards")

    val referenceAC: DatabaseReference = FirebaseDatabase.getInstance().getReference("AC")
    val referenceForex: DatabaseReference = FirebaseDatabase.getInstance().getReference("forex")
}
