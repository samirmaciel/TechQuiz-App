package com.samirmaciel.techquiz.view.challenge.quizFinal

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.samirmaciel.techquiz.R
import com.samirmaciel.techquiz.databinding.BottomsheetEndgameBinding

class EndGameBottomSheet  : BottomSheetDialogFragment() {

    private var _binding : BottomsheetEndgameBinding? = null
    private val binding: BottomsheetEndgameBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomsheetEndgameBinding.bind(inflater.inflate(R.layout.bottomsheet_endgame, container, false))
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expandBottomSheet()

    }


    private fun expandBottomSheet() {
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = true
            state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}