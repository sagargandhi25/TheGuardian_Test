package com.test.theguardian_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.theguardian_test.databinding.FragmentArticlelistBinding
import com.test.theguardian_test.domain.Article
import com.test.theguardian_test.utils.LoadingState
import com.test.theguardian_test.viewadapters.ArticleAdapter
import com.test.theguardian_test.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_articlelist.*

import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ArticleListFragment : Fragment() {

    private var viewModelAdapter: ArticleAdapter? = null

    /**
     *Here, by viewModel() creates the instance for the ViewModel and it will also resolve the dependency required by it.
     */
    private val viewModel by viewModel<ArticleViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    val binding : FragmentArticlelistBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_articlelist,container,false)

        //Set the lifecycleOwner so DataBinding can observe LiveData
        binding.setLifecycleOwner (viewLifecycleOwner)
        binding.viewmodel =viewModel
        viewModelAdapter = ArticleAdapter()

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager =LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.articleResults.observe(viewLifecycleOwner, Observer <List<Article>> {article ->
        article?.apply {
            viewModelAdapter?.result = article
        }
        })

        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(activity, it.msg, Toast.LENGTH_SHORT).show()
                }
                LoadingState.Status.RUNNING -> {
                    progressBar.visibility = View.VISIBLE
                }
                LoadingState.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE

                }
            }
        })
    }
}