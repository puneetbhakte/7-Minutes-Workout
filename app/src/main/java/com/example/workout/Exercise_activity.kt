package com.example.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.workout.databinding.ActivityExerciseBinding
import java.util.*
import kotlin.collections.ArrayList

class Exercise_activity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts:TextToSpeech?= null

    private var exercise_list :ArrayList<model_exercise> ? = null
    private var current_position = -1
    private var current_exercise = false
    private var Rest:Boolean = false
    private var binding : ActivityExerciseBinding? = null
    private var resetimer: CountDownTimer? = null
    private var restprogress = 0
    private var exercisetimer: CountDownTimer? = null
    private var exercise_progress = 0

    private var ExerciseAdapter:ExerciseStatusAdapter? = null

    val exercise = constants.exercise_list()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
         binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
     setSupportActionBar(binding?.toolbar)

        tts = TextToSpeech(this,this)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    binding?.toolbar?.setNavigationOnClickListener{
        onBackPressed()
    }
        exercise_list = constants.exercise_list()
        setupRestView()
        setUpRecuclerView()

    }

    private fun setUpRecuclerView(){
        binding?.rvExerciseStatus?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false,)
        ExerciseAdapter = ExerciseStatusAdapter(exercise_list!!)
        binding?.rvExerciseStatus?.adapter = ExerciseAdapter


    }


private fun set_rest_image(){
    if (Rest == true){
        binding?.exerciseImage?.setImageResource(R.drawable.rest)
        binding?.exerciseName?.text = "REST"
    }
}

    private fun set_exercise() {
        if(current_exercise == true){
//var exercise:model_exercise = exercise_list!![current_position]
       binding?.exerciseImage?.setImageResource(exercise[current_position].getimage())
            binding?.exerciseName?.text = exercise[current_position].getexercise_name()
        }
    }

    private fun setupRestView() {

        binding?.flProgress?.visibility = View.VISIBLE
        binding?.tvTimerExercies?.text = "exercise name"
        binding?.flProgressExercise?.visibility = View.GONE

        if (resetimer != null) {
            resetimer!!.cancel()
            restprogress = 0
        }
        setprogressbar()
    }


    private fun setup_exercise_view() {

        binding?.flProgress?.visibility = View.GONE
        binding?.tvTimerExercies?.text = "exercise name"
        binding?.flProgressExercise?.visibility = View.VISIBLE
        exercise_list?.get(current_position)?.let { speak_Text(it.getexercise_name()) }
        if (exercisetimer != null) {
            exercisetimer?.cancel()
            exercise_progress = 0
        }
      set_exersice_progress()
    }


    private fun setprogressbar(){
        binding?.progressbar?.progress = restprogress
        Rest = true
        set_rest_image()
        resetimer = object :CountDownTimer(11000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restprogress++
                binding?.progressbar?.progress = 10-restprogress
               binding?.tvTimer?.text = (10 - restprogress).toString()
                speak_Text((10 - restprogress).toString())
            }
            override fun onFinish() {
            // Toast.makeText(this, "Here now we will start the exercise", Toast.LENGTH_SHORT).show()
            //Toast.makeText(this@Exercise_activity,"Here now we will start the exercise",Toast.LENGTH_LONG).show()
                //speak_Text(exercise_list?.get(current_position)?.getexercise_name().toString())
                Rest = false
            //    exercise_list!![current_position].set_isSelected(true)
              //  ExerciseAdapter?.notifyDataSetChanged()


                    current_position++
                    setup_exercise_view()


            }

        }.start()

    }

    fun set_exersice_progress(){
        binding?.progressbarExercise?.progress = exercise_progress
          current_exercise = true
        set_exercise()
        exercisetimer = object :CountDownTimer(31000,1000){
            override fun onTick(millisUntilFinished: Long) {
                exercise_progress++
                binding?.progressbarExercise?.progress = 30-exercise_progress
                binding?.tvTimerExercies?.text = (30 - exercise_progress).toString()
                speak_Text((30 - exercise_progress).toString())
            }


            override fun onFinish() {
                // Toast.makeText(this, "Here now we will start the exercise", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@Exercise_activity,"exercise finish",Toast.LENGTH_LONG).show()
                speak_Text("exercise finish")
                current_exercise = false
                if (current_position < exercise_list?.size!! - 1) {
                    exercise_list!![current_position].set_isSelected(false) // exercise is completed so selection is set to false
                    exercise_list!![current_position].set_iscompleted(true) // updating in the list that this exercise is completed
                    ExerciseAdapter?.notifyDataSetChanged()
                    setupRestView()
                }else{
                    finish()
                    val intent = Intent(this@Exercise_activity,MainActivity::class.java)
                    startActivity(intent)
                }
            }

        }.start()


    }

    fun speak_Text(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {

        if (resetimer != null) {
            resetimer?.cancel()
            restprogress = 0
        }
        super.onDestroy()
        if (tts != null) {
            tts?.stop()
            tts?.shutdown()
        }
        if (exercisetimer != null) {
            exercisetimer?.cancel()
            exercise_progress = 0
        }
        binding = null
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.ENGLISH)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }

        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }
}