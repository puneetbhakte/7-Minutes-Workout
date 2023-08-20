package com.example.workout

class model_exercise (
    private var id:Int,
    private var exercise_name:String,
    private var image:Int,
    private var iscompleted:Boolean,
    private var isSelected:Boolean
    ){

    //geters
fun getid(): Int {
    return id
}
    fun getexercise_name(): String {
        return exercise_name
    }
    fun getimage(): Int {
        return image
    }
    fun getiscompleted(): Boolean {
        return iscompleted
    }
    fun getisSelected(): Boolean {
        return isSelected
    }
// setters
    fun setid(id: Int){
        this.id = id
    }
    fun set_exercise_name(exercise_name: String){
        this.exercise_name = exercise_name
    }

    fun set_image(image: Int){
        this.image = image
    }
    fun set_isSelected(isSelected: Boolean){
        this.isSelected = isSelected
    }
    fun set_iscompleted(iscompleted: Boolean){
        this.iscompleted = iscompleted
    }

}