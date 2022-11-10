package fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.attt.moviebookingapp.R
import delegates.LoginButtonActionDelegate
import delegates.RegisterDelegate
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.view_pod_sign_in.*
import viewpods.SignInViewPod


class RegisterFragment : Fragment() {


    lateinit var mDelegate: LoginButtonActionDelegate
    lateinit var mRegisterDelegate:RegisterDelegate


    private lateinit var loginButtonViewPod: SignInViewPod
    override fun onAttach(context: Context) {
        mDelegate = context as LoginButtonActionDelegate
        mRegisterDelegate=context as RegisterDelegate
        super.onAttach(context)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginButtonViewPod = vpRegister as SignInViewPod
        loginButtonViewPod.setDelegate(mDelegate)
        loginButtonViewPod.setRegisterDelegate(mRegisterDelegate)

        super.onViewCreated(view, savedInstanceState)

        btnConfirm.setOnClickListener {
            mRegisterDelegate.onRegister()
        }

    }



}