package pl.olszak.michal.todo.tasks.create

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.databinding.FragmentQuickCreateTaskBinding
import pl.olszak.michal.todo.di.Injectable
import pl.olszak.michal.todo.util.viewModelProvider
import pl.olszak.michal.todo.view.animation.AnimationUtils
import pl.olszak.michal.todo.view.animation.model.RevealAnimationSetting
import javax.inject.Inject

/**
 * @author molszak
 *         created on 06.02.2018.
 */
class QuickCreateTaskFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentQuickCreateTaskBinding
    private var alreadyAnimated: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel: QuickCreateTaskViewModel = viewModelProvider(viewModelFactory)

        savedInstanceState?.let {
            alreadyAnimated = it.getBoolean(SAVED_ANIMATION_STATE, false)
        }

        if (!alreadyAnimated) {
            alreadyAnimated = true
            val revealAnimationSetting: RevealAnimationSetting? = arguments.getParcelable(ARG_REVEAL_SETTINGS)
            revealAnimationSetting?.let {
                AnimationUtils.registerCircularRevealAnimation(context, binding.root, it)
            }
        }

        binding.toolbar.apply {
            setNavigationIcon(R.mipmap.ic_arrow_left_black_24dp)
            setNavigationOnClickListener { }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_quick_create_task,
                container,
                false)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(SAVED_ANIMATION_STATE, alreadyAnimated)
    }

    companion object {
        private const val SAVED_ANIMATION_STATE = "saved_animation_state"
        private const val ARG_REVEAL_SETTINGS = "arg_reveal_settings"

        fun createWithReveal(revealSettings: RevealAnimationSetting): QuickCreateTaskFragment {
            val fragment = QuickCreateTaskFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_REVEAL_SETTINGS, revealSettings)

            fragment.arguments = bundle
            return fragment
        }
    }
}