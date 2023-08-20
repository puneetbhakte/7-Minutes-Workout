package com.example.workout

object constants {
    fun exercise_list():ArrayList<model_exercise>{
        val execise__list = ArrayList<model_exercise>()

         val jumping_jacks = model_exercise(
             1, "jumping jacks",R.drawable.ic_jumping_jacks,false,false)
        execise__list.add(jumping_jacks)


        val abdominal_crunch = model_exercise(
            2, "abdominal_crunch",R.drawable.ic_abdominal_crunch,false,false)
        execise__list.add(abdominal_crunch)


        val High_Knees_Running_in_place = model_exercise(
            3, "High_Knees_Running_in_place",R.drawable.ic_high_knees_running_in_place,false,false)
        execise__list.add(High_Knees_Running_in_place)

        val lung = model_exercise(
            4, "lung",R.drawable.ic_lunge,false,false)
        execise__list.add(lung)

        val plank = model_exercise(
            5, "plank",R.drawable.ic_plank,false,false)
        execise__list.add(plank)

        val push_up = model_exercise(
            6, "push_up",R.drawable.ic_push_up,false,false)
        execise__list.add(push_up)

        val push_up_and_rotation = model_exercise(
            7, "push_up_and_rotation",R.drawable.ic_push_up_and_rotation,false,false)
        execise__list.add(push_up_and_rotation)

        val side_plank = model_exercise(
            8, "side_plank",R.drawable.ic_side_plank,false,false)
        execise__list.add(side_plank)

        val sqat = model_exercise(
            9, "sqat",R.drawable.ic_squat,false,false)
        execise__list.add(sqat)


        val step_up_onto_chair = model_exercise(
            10, "step_up_onto_chair",R.drawable.ic_step_up_onto_chair,false,false)
        execise__list.add(step_up_onto_chair)

        val triceps_dip_on_chair = model_exercise(
            11, "triceps_dip_on_chair",R.drawable.ic_triceps_dip_on_chair,false,false)
        execise__list.add(triceps_dip_on_chair)

        val wall_sit = model_exercise(
            12, "wall_sit",R.drawable.ic_wall_sit,false,false)
        execise__list.add(wall_sit)

        return execise__list
    }

}