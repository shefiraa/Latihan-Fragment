import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.example.myflexiblefragment.DetailCategoryFragment
import com.example.myflexiblefragment.R
import kotlinx.android.synthetic.main.fragment_option_dialog.*

class OptionDialogFragment : DialogFragment(), View.OnClickListener {

    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbKjs: RadioButton
    private lateinit var rbKjn: RadioButton
    private lateinit var rbPc: RadioButton
    private lateinit var rbLj: RadioButton
    private lateinit var rbLm: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnChoose.setOnClickListener(this)
        btnClose = view.findViewById(R.id.btn_close)
        btnClose.setOnClickListener(this)
        rgOptions = view.findViewById(R.id.rg_Options)
        rbKjs = view.findViewById(R.id.rb_Kjs)
        rbKjn = view.findViewById(R.id.rb_Kjn)
        rbPc = view.findViewById(R.id.rb_Pc)
        rbLj = view.findViewById(R.id.rb_Lj)
        rbLm = view.findViewById(R.id.rb_Lm)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailCategoryFragment) {
            val DetailCategoryFragment = fragment
            this.optionDialogListener = DetailCategoryFragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_close -> dialog?.cancel()

            R.id.btn_choose -> {
                val checkRadioButtonId = rg_Options.checkedRadioButtonId
                if (checkRadioButtonId != -1) {
                    lateinit var member: String
                    when (checkRadioButtonId) {
                        R.id.rb_Kjs -> member = rbKjs.text.toString().trim()

                        R.id.rb_Kjn -> member = rbKjn.text.toString().trim()

                        R.id.rb_Pc -> member = rbPc.text.toString().trim()

                        R.id.rb_Lj -> member = rbLj.text.toString().trim()

                        R.id.rb_Lm -> member = rbLm.text.toString().trim()
                    }

                    if (optionDialogListener != null) {
                        optionDialogListener?.onOptionChosen(member)
                    }
                    dialog?.dismiss()
                }
            }
        }
    }
    interface OnOptionDialogListener {
        fun onOptionChosen(text: String)
    }
}

