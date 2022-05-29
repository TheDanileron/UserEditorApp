package com.project.usereditorapp.presentation.details

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.project.usereditorapp.R
import com.project.usereditorapp.databinding.UserDetailsBinding
import com.project.usereditorapp.presentation.*

class DetailsFragment: Fragment(), BackPressListener {
    lateinit var binding: UserDetailsBinding
    lateinit var viewModel: UserDetailsViewModel
    lateinit var sharedModel: SharedViewModel
    var isEdit: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserDetailsBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedModel = ViewModelProviders.of(requireActivity(), UsersViewModelFactory).get(
            SharedViewModel::class.java)
        viewModel = ViewModelProviders.of(requireActivity(), UsersViewModelFactory).get(
            UserDetailsViewModel::class.java)
        sharedModel.state.observe(viewLifecycleOwner) {
            if(it.selectedUser?.second == EntityAction.DELETE) {
                applyEditMode(false)
                requireActivity().onBackPressed()
            } else {
                setup()
            }

            if(it.message.isNotEmpty()) {
                Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.state.observe(viewLifecycleOwner) {
            if(it == null)
                return@observe
            if(it.message != "")
                Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
        }
    }

    fun setup() {
        binding.title.text = sharedModel.selectedUserName
        binding.subTitle.text = sharedModel.selectedUserEmail
        binding.editName.setText(sharedModel.selectedUserName)
        binding.editEmail.setText(sharedModel.selectedUserEmail)
        binding.saveBtn.setOnClickListener {
            sharedModel.updateSelectedUser(
                binding.editName.text.toString(),
                binding.editEmail.text.toString()
            )
            applyEditMode(false)
        }
    }

    fun applyEditMode(isEdit: Boolean) {
        this.isEdit = isEdit
        if(isEdit) {
            binding.editLayout.visibility = View.VISIBLE
            binding.infoLayout.visibility = View.GONE
        } else {
            binding.editLayout.visibility = View.GONE
            binding.infoLayout.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_details, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_delete -> {
                sharedModel.deleteSelectedUser()
            }
            R.id.action_edit -> {
                applyEditMode(true)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun backPressed(): Boolean {
        if(isEdit){
            applyEditMode(false)
            return false
        }

        return true
    }
}