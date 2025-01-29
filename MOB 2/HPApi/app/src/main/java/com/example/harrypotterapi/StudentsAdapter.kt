import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotterapi.CharacterResponse
import com.example.harrypotterapi.R

class StudentsAdapter(
    private val students: List<CharacterResponse>,
    private val context: Context
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvStudentName)
        val tvId: TextView = view.findViewById(R.id.tvStudentId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.tvName.text = "Nome: ${student.name}"
        holder.tvId.text = "ID: ${student.id}"

        holder.itemView.setOnClickListener {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Student ID", student.id)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "ID copiado para a área de transferência!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = students.size
}
