package com.example.calculator.ui.standard

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.R
import com.example.calculator.databinding.FragmentStandardBinding

class StandardFragment : Fragment() {

    private lateinit var standardViewModel: StandardViewModel
    private var _binding: FragmentStandardBinding? = null
    private lateinit var editText:EditText
    private lateinit var answerText:TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        standardViewModel =
            ViewModelProvider(this).get(StandardViewModel::class.java)
        standardViewModel.answer().observe(viewLifecycleOwner, {
            answerText.text = it
        })
        _binding = FragmentStandardBinding.inflate(inflater, container, false)


        editText = binding.editTextMultiLine
        editText.requestFocus()

        answerText = binding.answer
        //disable editText keyboard
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
            editText.showSoftInputOnFocus = false
        } else { // API 11-20
            editText.setTextIsSelectable(true)
        }

        //----------OnClickListeners-----------//
        binding.zeroUIBtn.setOnClickListener { setAndReadText(R.string.BtnStr_0) }
        binding.oneUIBtn.setOnClickListener { setAndReadText(R.string.BtnStr_1) }
        binding.twoUIBtn.setOnClickListener { setAndReadText(R.string.BtnStr_2) }
        binding.threeUIBtn.setOnClickListener { setAndReadText(R.string.BtnStr_3) }
        binding.fourUIBtn.setOnClickListener { setAndReadText(R.string.BtnStr_4) }
        binding.fiveUIBtn.setOnClickListener { setAndReadText(R.string.BtnStr_5) }
        binding.sixUIBtn.setOnClickListener { setAndReadText(R.string.BtnStr_6) }
        binding.sevenUIBtn.setOnClickListener { setAndReadText(R.string.BtnStr_7) }
        binding.eightUIBtn.setOnClickListener { setAndReadText(R.string.BtnStr_8) }
        binding.nineUIBtn.setOnClickListener { setAndReadText(R.string.BtnStr_9) }

        binding.deleteUIBtn.setOnClickListener {
            val pos: Int = editText.selectionStart
            if (pos > 0)
            editText.text.delete(pos - 1, pos).toString()
        }

        binding.ACUIBtn.setOnClickListener {
            editText.text.clear()
            answerText.text = ""
            standardViewModel.setDigit("")
        }

        binding.addUIBtn.setOnClickListener {
            editText.text.insert(editText.selectionStart, resources.getString(R.string.BtnStr_add))
            standardViewModel.setOper(resources.getString(R.string.BtnStr_add))
        }

        binding.editTextMultiLine.addTextChangedListener {
            standardViewModel.setDigit(editText.text.toString())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //set button string to editText display and read button to pass to logic controller
    private fun setAndReadText(RID:Int){
        editText.text.insert(editText.selectionStart, resources.getString(RID))
    }

}