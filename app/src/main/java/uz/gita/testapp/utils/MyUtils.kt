package uz.gita.testapp.utils

import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T : ViewBinding> T.myApply(block: T.() -> Unit) {
    block(this)
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun EditText.values(): String {
    return this.text.toString().trim()
}

fun EditText.addListener(block: (String) -> Unit) {
    this.addTextChangedListener {
        it?.let {
            block.invoke(it.toString().trim())
        }
    }
}

fun <T> Call<T>.myEnqueue(
    onSuccess: (response: Response<T>) -> Unit,
    onFailure: (t: Throwable) -> Unit
) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            onSuccess.invoke(response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure.invoke(t)
        }
    })
}



