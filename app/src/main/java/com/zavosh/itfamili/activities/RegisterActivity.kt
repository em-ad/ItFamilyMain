package com.zavosh.itfamili.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.zavosh.itfamili.R
import com.zavosh.itfamili.helper.PageManager
import com.zavosh.itfamili.myviews.MyToast
import com.zavosh.itfamili.retrofit.Server
import com.zavosh.itfamili.retrofit.mymodels.Callback
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private var sex = ""


    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setup()
        listeners()
    }

    private fun listeners() {

        chk_male.setOnCheckedChangeListener { buttonView, isChecked ->


            if (isChecked){
                if (chk_female.isChecked) {
                    chk_female.isChecked = false
                }
            }


        }

        chk_female.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked){
                if (chk_male.isChecked) {
                    chk_male.isChecked = false
                }
            }

        }


        iv_register.setOnClickListener {

            if (etv_name.text.toString().isEmpty() || etv_mail.text.toString().isEmpty() || etv_password.text.toString().isEmpty()) {
                MyToast.showToast(this@RegisterActivity, "لطفا تمام فیلدها را وارد کنید")
            }
           else if (!chk_male.isChecked && !chk_female.isChecked) {
                MyToast.showToast(this@RegisterActivity, "لطفا جنسیت را مشخص کنید")
            }else{
                sendRegisterRequest()
            }
        }
        tv_skip.setOnClickListener {
            PageManager.getInstance().goHomeActivity(this@RegisterActivity)
        }
    }

    private fun sendRegisterRequest() {
        Server.getInstance(this@RegisterActivity)
            .sendProfile(etv_name.text.toString().trim(), etv_mail.text.toString().trim(),
                sex, register_loader,
                object : Callback.PostProfile {
                    override fun callback(result: String?) {
                   MyToast.showToast(this@RegisterActivity,result)
                    }
                }
            )
    }
    private fun setup() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        //hide statusBar
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}