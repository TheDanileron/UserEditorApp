package com.project.usereditorapp.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.usereditorapp.databinding.ListFragmentBinding
import com.project.usereditorapp.presentation.*

class ListFragment: Fragment(){
    lateinit var binding: ListFragmentBinding
    lateinit var viewModel: UsersListViewModel
    lateinit var sharedModel: SharedViewModel
    lateinit var activity: MainActivity
    val adapter by lazy{
        UsersAdapter{
            activity.navigateToDetails()
            sharedModel.userSelected(it)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater)
        activity = requireActivity() as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(requireActivity(), UsersViewModelFactory).get(
            UsersListViewModel::class.java)
        sharedModel = ViewModelProviders.of(requireActivity(), UsersViewModelFactory).get(
            SharedViewModel::class.java)
        viewModel.loadUsers()
        viewModel.state.observe(viewLifecycleOwner) {
            binding.recycler.adapter = adapter
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())
            adapter.setUsers(it.userList)
        }
        sharedModel.state.observe(viewLifecycleOwner) {
            if(it.selectedUser?.second == EntityAction.DELETE) {
                viewModel.deleteSelectedUser(it.selectedUser.first)
            } else if(it.selectedUser?.second == EntityAction.UPDATE){
                viewModel.updateSelectedUser(it.selectedUser.first)
            }
        }
    }

}