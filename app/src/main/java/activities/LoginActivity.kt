package activities

import adapters.ViewPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.attt.moviebookingapp.R
import com.google.android.material.tabs.TabLayout
import data.models.MovieBookingModel
import data.models.MovieBookingModelImpl
import delegates.LoginButtonActionDelegate
import delegates.LoginDelegate
import delegates.RegisterDelegate
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class LoginActivity : AppCompatActivity(), LoginButtonActionDelegate,
    RegisterDelegate, LoginDelegate {


    // models
    private val mRegisterModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupTabLayout()
        setupTabListeners()
        setupViewPagerAdapter()
    }

    private fun setupViewPagerAdapter() {
        viewPagerLogin.adapter = ViewPagerAdapter(this)
        viewPagerLogin.currentItem = 0
    }

    private fun setupTabListeners() {

        tabLayoutLoginAndSignIn.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text == getString(R.string.lbl_login)) {
                    viewPagerLogin.currentItem = 0
                } else {
                    viewPagerLogin.currentItem = 1
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setupTabLayout() {
        val tabLogin = tabLayoutLoginAndSignIn.newTab()
        tabLogin.text = getString(R.string.lbl_login)
        tabLayoutLoginAndSignIn.addTab(tabLogin)

        val tabSignIn = tabLayoutLoginAndSignIn.newTab()
        tabSignIn.text = getString(R.string.lbl_register)
        tabLayoutLoginAndSignIn.addTab(tabSignIn)
    }


    override fun onTapConfirm() {


    }

    override fun onRegister() {
        val email = edRegisterEmail.text.toString()
        val password = edRegisterPassword.text.toString()
        val name = edName.text.toString()
        val phone = edPhNo.text.toString()

        when {
            email.isEmpty() -> {
                edRegisterEmail.error = "Email Required"
                edRegisterEmail.requestFocus()
            }
            password.isEmpty() -> {
                edRegisterPassword.error = "Password Required"
                edRegisterPassword.requestFocus()

            }
            name.isEmpty() -> {
                edName.error = "Name Required"
                edName.requestFocus()

            }
            phone.isEmpty() -> {
                edPhNo.error = "Phone Number Required"
                edPhNo.requestFocus()

            }
            else -> {

                //Register
                mRegisterModel.registerWithEmail(
                    name = name,
                    email = email,
                    phone = phone,
                    password = password,
                    onSuccess = {
                        startActivity(
                            HomeActivity.newIntent(
                                this,
                                email = email,
                                password = password
                            )
                        )


                    },
                    onFailure = {

                    })


            }
        }


    }

    override fun onLogin() {
        val email = edEmail.text.toString()
        val password = edPassword.text.toString()
        if (email.isEmpty()) {
            edEmail.error = "Email Required"
            edEmail.requestFocus()
        } else if (password.isEmpty()) {
            edPassword.error = "Password Required"
            edPassword.requestFocus()

        } else {
            mRegisterModel.loginWithEmail(
                email = email,
                password = password,
                onSuccess = {
                    startActivity(
                        HomeActivity.newIntent(
                            this,
                            email = email,
                            password = password
                        )
                    )
                },
                onFailure = {}

            )

        }


    }


}