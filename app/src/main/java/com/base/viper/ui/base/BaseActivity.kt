package com.base.viper.common.base

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

abstract class BaseActivity: AppCompatActivity(), BaseContracts.View {

    var loadingDialog: Dialog? = null

    override fun getActivityContext(): Context? {
        return this
    }

    override fun showLoadingDialog() {

            var llPadding = 30;
            var ll = LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ll.setPadding(llPadding, llPadding, llPadding, llPadding);
            ll.setGravity(Gravity.CENTER);
            var llParam = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
            llParam.gravity = Gravity.CENTER;
            ll.setLayoutParams(llParam);

            var progressBar = ProgressBar(this);
            progressBar.setIndeterminate(true);
            progressBar.setPadding(0, 0, llPadding, 0);
            progressBar.setLayoutParams(llParam);

            llParam = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
            llParam.gravity = Gravity.CENTER;
            var tvText = TextView(this);
            tvText.setText("Loading ...");
            tvText.setTextColor(Color.parseColor("#000000"));
            tvText.setTextSize(20f);
            tvText.setLayoutParams(llParam);

            ll.addView(progressBar);
            ll.addView(tvText);

            var builder = AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setView(ll);

            loadingDialog = builder.create();
            loadingDialog?.show();
            var window = loadingDialog?.getWindow();
            if (window != null) {
                var layoutParams = WindowManager.LayoutParams();
                layoutParams.copyFrom(loadingDialog?.getWindow()?.getAttributes());
                layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                loadingDialog?.getWindow()?.setAttributes(layoutParams);
            }

        /*loadingDialog?.dismiss()
        val message = error ?: getString(R.string.default_error_message)
        loadingDialog = ErrorDialog.showWithMessage(message, this)*/
    }

    override fun closeLoadingDialog() {
        loadingDialog?.dismiss()
        /*loadingDialog?.dismiss()
        val message = error ?: getString(R.string.default_error_message)
        loadingDialog = ErrorDialog.showWithMessage(message, this)*/
    }
}