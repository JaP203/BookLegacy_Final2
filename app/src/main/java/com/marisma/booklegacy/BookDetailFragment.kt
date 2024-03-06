import BookAdapter.Companion.DRAWABLE
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.marisma.booklegacy.sampledata.Book
import com.marisma.booklegacy.databinding.FragmentBookDetailBinding

class BookDetailFragment : Fragment() {
    private lateinit var binding: FragmentBookDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = arguments?.getParcelable <Book>("libro")
        if (book != null) {
            binding.tvtitulo.text = book.nombre
            binding.tvUtor.text = book.autor
            binding.tvPaginas.text = book.paginas.toString()
            binding.tvDescripcion.text = book.description
            binding.tvGenero.text = book.genero
            Glide.with(binding.imgLibro)
                .load("https://covers.openlibrary.org/b/olid/${book.fotografia}-M.jpg")
                .into(binding.imgLibro)

        }
    }
}
