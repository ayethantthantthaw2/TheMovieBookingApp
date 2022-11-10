package fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.attt.moviebookingapp.R
import delegates.LoginButtonActionDelegate
import delegates.LoginDelegate
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.view_pod_sign_in.*
import viewpods.SignInViewPod


class LoginFragment : Fragment() {

    lateinit var mDelegate: LoginButtonActionDelegate
    lateinit var mLoginDelegate:LoginDelegate
    private lateinit var loginButtonViewPod: SignInViewPod

    override fun onAttach(context: Context) {
        mDelegate = context as LoginButtonActionDelegate
        mLoginDelegate=context as LoginDelegate
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginButtonViewPod = vpLogin as SignInViewPod
        loginButtonViewPod.setDelegate(mDelegate)
        loginButtonViewPod.setLoginDelegate(mLoginDelegate)
        super.onViewCreated(view, savedInstanceState)

        btnConfirm.setOnClickListener {
            mLoginDelegate.onLogin()
        }

    }


}