package com.aya.taskdetails.network

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.view.LayoutInflater
import com.aya.taskdetails.R


class LoadingDialogue(var mContext: Context) {
    fun show() {
        if (ourInstance != null)
            (mContext as Activity).runOnUiThread({
                if (!(mContext as Activity).isFinishing)
                    doShow()
            })
    }

    fun doShow() {
        var isStarted = false
        object : CountDownTimer(3000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                if (!isStarted)
                    (mContext as Activity).runOnUiThread({
                        if (!(mContext as Activity).isFinishing) {
                            ourInstance?.show()
                            isStarted = true
                        }
                    })
            }

            override fun onFinish() {
                hide()
                isStarted = true
            }
        }.start()
    }

    fun hide() {
        if (ourInstance != null)
            (mContext as Activity).runOnUiThread({
                if (!(mContext as Activity).isFinishing && ourInstance != null)
                    ourInstance?.dismiss()
            })
    }


    companion object {
        private var ourInstance: AlertDialog? = null
        private fun getInstance(mContext: Context): AlertDialog? {
            if (ourInstance == null) {
                (mContext as Activity).runOnUiThread({
                    val dialogBuilder =
                        AlertDialog.Builder(mContext)
                    val inflater =
                        mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val dialogView =
                        inflater.inflate(R.layout.loading_dialoge_layout, null)
                    dialogBuilder.setView(dialogView)
                    dialogBuilder.setCancelable(true)
                    ourInstance = dialogBuilder.create()
                    ourInstance?.window?.setDimAmount(0f)
                    ourInstance?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
                    if (!mContext.isFinishing && ourInstance != null)
                        ourInstance?.show()
                })
            }
            return ourInstance
        }

        fun destroy() {
            if (ourInstance != null) {
                ourInstance?.dismiss()
                ourInstance = null
            }
        }
    }

    init {
        getInstance(
            mContext
        )
    }


}