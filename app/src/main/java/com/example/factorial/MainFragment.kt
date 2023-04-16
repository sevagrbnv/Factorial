package com.example.factorial

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.factorial.databinding.FragmentMainBinding
import com.example.factorial.recView.HistoryAdapter


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    private lateinit var historyListAdapter: HistoryAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = MainViewModel(requireActivity().application as App)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val myApp = requireActivity().application as App
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEdText()
        setRecView()

        binding.calculateBtn.setOnClickListener {
            val query = binding.edText.text.toString()
            viewModel.getResult(query)
        }
    }

    private fun setEdText() {
        setTextChangeListener()
        viewModel.errorInput.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_in_line)
            } else {
                null
            }
            binding.til.error = message
        }
    }

    private fun setRecView() {
        historyListAdapter = HistoryAdapter()
        viewModel.history.observe(viewLifecycleOwner) {
            historyListAdapter.submitList(it)
        }
        with(binding.rcView) {
            adapter = historyListAdapter
        }
        setSwipeListener(binding.rcView)

    }

    private fun setTextChangeListener() {
        binding.edText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputDesc()
            }

        })
    }

    private fun setSwipeListener(rcViewTodoList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = historyListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteTodoItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rcViewTodoList)
    }
}