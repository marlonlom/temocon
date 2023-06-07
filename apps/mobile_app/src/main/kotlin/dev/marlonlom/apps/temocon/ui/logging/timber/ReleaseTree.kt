package dev.marlonlom.apps.temocon.ui.logging.timber

import android.annotation.SuppressLint
import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.Tree() {
  companion object {
    private const val MAX_LOG_LENGTH: Int = 4000

    private const val SPACE = " "

    @JvmStatic
    private fun isVerboseDebugOrInfo(priority: Int): Boolean = priority in arrayListOf(Log.VERBOSE, Log.DEBUG, Log.INFO)
  }

  override fun isLoggable(tag: String?, priority: Int): Boolean = !isVerboseDebugOrInfo(priority)

  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    if (!isLoggable(tag, priority)) {
      return
    }

    if (message.length < MAX_LOG_LENGTH) {
      logAssert(priority, tag, message)
      return
    }

    splitMessageIntoMultipleLines(message).forEach { msg ->
      logAssert(priority, tag, msg)
    }
  }

  private fun splitMessageIntoMultipleLines(
    message: String
  ) = mutableListOf<String>().apply {
    val currString = StringBuilder()
    message.split(SPACE).forEach { txt ->
      val currStringLen = currString.length + txt.length
      if (currStringLen > MAX_LOG_LENGTH) {
        add(currString.toString())
        currString.setLength(0)
      }
      currString.append("$txt$SPACE")
    }
  }.toList()

  @SuppressLint("LogNotTimber")
  private fun logAssert(priority: Int, tag: String?, message: String) {
    if (priority == Log.ASSERT) {
      Log.wtf(tag, message)
    } else {
      Log.println(priority, tag, message);
    }
  }

}

