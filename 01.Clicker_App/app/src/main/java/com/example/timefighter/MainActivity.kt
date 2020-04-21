package com.example.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var score = 0
    var gameStarted = false
    lateinit var coutntDownTimer: CountDownTimer
    var initialCountDown: Long = 60000
    var countDownInterval: Long = 1000
    var timeLeftOnTimer: Long = 60000

    companion object {
        private const val SCORE_KEY = "SCORE KEY"
        private const val TIME_LEFT_KEY = "TIME_LEFT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tapButton = findViewById<Button>(R.id.tapMeButton)

        tapButton.setOnClickListener { view ->
            val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
            view.startAnimation(bounceAnimation)
            incrementScore()
        }
        if (savedInstanceState != null){
            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeftOnTimer = savedInstanceState.getLong(TIME_LEFT_KEY)
            restoreGame()
        } else {
            resetGame()
        }
    }

// info button
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionAbout){
            showinfo()
        }
        return true
    }
    private fun showinfo(){
        val dialogTitle = getString(R.string.aboutTitle)
        val dialogMessage = getString(R.string.aboutMessage)

        val builder =  AlertDialog.Builder(this)
        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.create().show()
    }

// reset game
    private fun resetGame(){
        score = 0
        gameScoreTextView.text = getString(R.string.score, score)
        val initialTimeLeft = initialCountDown / 1000
        timeLeftTextView.text = getString(R.string.time_left, initialTimeLeft)

        coutntDownTimer = object : CountDownTimer (initialCountDown, countDownInterval){

            override fun onFinish() {
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeLeftOnTimer = millisUntilFinished
                val timeLeft = millisUntilFinished / 1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft)
            }
        }
        gameStarted = false
    }

// save and restore game after destroy
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(SCORE_KEY, score)
        outState.putLong(TIME_LEFT_KEY, timeLeftOnTimer)
        coutntDownTimer.cancel()
    }
    private fun restoreGame(){
        gameScoreTextView.text = getString(R.string.score, score)
        val restoredTime = timeLeftOnTimer / 1000
        timeLeftTextView.text = getString(R.string.time_left, restoredTime)

        coutntDownTimer = object : CountDownTimer (timeLeftOnTimer, countDownInterval){

            override fun onFinish() {
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeLeftOnTimer = millisUntilFinished
                val timeLeft = millisUntilFinished / 1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft)
            }
        }
        coutntDownTimer.start()
        gameStarted = true
    }

// points
    private fun incrementScore() {
        if (!gameStarted){
            startGame()
        }
        score += 1
        val newScore = getString(R.string.score, score)
        gameScoreTextView.text = newScore
        val blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink)
        gameScoreTextView.startAnimation(blinkAnimation)
    }

    private fun startGame(){
        coutntDownTimer.start()
        gameStarted = true
    }

    private fun endGame(){
        Toast.makeText(this, getString(R.string.gameOver, score), Toast.LENGTH_LONG).show()
        resetGame()
    }
}
