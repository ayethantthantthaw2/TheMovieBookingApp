package viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import delegates.LoginButtonActionDelegate
import delegates.LoginDelegate
import delegates.RegisterDelegate
import kotlinx.android.synthetic.main.view_pod_sign_in.view.*

class SignInViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {


    lateinit var mDelegate: LoginButtonActionDelegate

    fun setDelegate(delegate: LoginButtonActionDelegate) {
        this.mDelegate = delegate

    }

    lateinit var mRegisterDelegate: RegisterDelegate

    fun setRegisterDelegate(delegate: RegisterDelegate) {
        this.mRegisterDelegate = delegate

    }

    lateinit var mLoginDelegate: LoginDelegate

    fun setLoginDelegate(delegate: LoginDelegate) {
        this.mLoginDelegate = delegate

    }

    override fun onFinishInflate() {

        btnConfirm.setOnClickListener {

            mDelegate.onTapConfirm()



        }
        super.onFinishInflate()
    }

}