package com.example.kolomachinetest.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kolomachinetest.R


class FilterFragment : Fragment() {

    private lateinit var mFormat: Spinner
    private lateinit var mFormatType: Spinner
    private lateinit var mNoVariants: Spinner
    private lateinit var mDateDescriptor: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFormat = view.findViewById(R.id.format)
        mFormatType = view.findViewById(R.id.formatType)
        mNoVariants = view.findViewById(R.id.noVariants)
        mDateDescriptor = view.findViewById(R.id.dateDescriptor)
        setAdapter(R.array.format, mFormat)
        setAdapter(R.array.formatType, mFormatType)
        setAdapter(R.array.dateDescriptor, mDateDescriptor)
        setAdapter(R.array.noVariants, mNoVariants)
        view.findViewById<View>(R.id.apply_button)
            .setOnClickListener {
                val args = Bundle()
                args.putString("format", mFormat.selectedItem.toString().lowercase())
                args.putString("formatType", mFormatType.selectedItem.toString().lowercase())
                args.putString("noVariants", mNoVariants.selectedItem.toString().lowercase())
                args.putString("dateDescriptor", mDateDescriptor.selectedItem.toString())
                findNavController()
                    .navigate(R.id.action_filterFragment_to_filterResultFragment, args)
            }
    }

    private fun setAdapter(arrayId: Int, spinner: Spinner) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_item, resources.getStringArray(arrayId)
        )
        spinner.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val findNavController = findNavController()
            findNavController.popBackStack()
            return true
        }
        return false
    }
}