package david.com.moviebrowser.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import david.com.moviebrowser.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var passwordDisplayed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etPassword.addTextChangedListener(textChangedListener())
        ibPasswordVisibility.setOnClickListener(onClickListenerVisibility())

        rvLogin.startAnimation(animation(3000))
        rvPassword.startAnimation(animation(4000))
        rvLoginBottom.startAnimation(animation(5000))
    }

    private fun textChangedListener(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                if (editable.isNotEmpty()) {
                    ibPasswordVisibility.visibility = View.VISIBLE
                    ibPasswordVisibility.isEnabled = true
                } else {
                    ibPasswordVisibility.visibility = View.GONE
                    ibPasswordVisibility.isEnabled = false
                }
            }

            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    private fun onClickListenerVisibility(): View.OnClickListener {
        return View.OnClickListener {
            if (passwordDisplayed) {
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                etPassword.setSelection(etPassword.text.length)
                ibPasswordVisibility.setImageResource(R.drawable.ic_visibility_off_black_24dp)
                passwordDisplayed = false
            } else {
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                etPassword.setSelection(etPassword.text.length)
                ibPasswordVisibility.setImageResource(R.drawable.ic_visibility_black_24dp)
                passwordDisplayed = true
            }
        }
    }

    private fun animation(duration: Long): TranslateAnimation {
        val translateAnimation = TranslateAnimation(0.0f, 0.0f, 3000f, 0.0f)

        translateAnimation.duration = duration
        return translateAnimation
    }

}