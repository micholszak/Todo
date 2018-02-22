package pl.olszak.michal.todo.tasks.tasklist.adapter

import android.view.View
import pl.olszak.michal.todo.R
import pl.olszak.michal.todo.data.model.Task
import pl.olszak.michal.todo.viewmodel.Binding
import pl.olszak.michal.todo.viewmodel.SingleBindingRecyclerAdapter

/**
 * @author molszak
 *         created on 22.02.2018.
 */
class TaskAdapter : SingleBindingRecyclerAdapter<Task>(R.layout.task_item) {


    private val tasks: MutableList<Task> = mutableListOf()

    fun setItems(tasks: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun getBindingForPosition(position: Int): Task = tasks[position]

    override fun getItemCount(): Int = tasks.size

    override fun onViewHolderBound(itemView: View, binding: Task, position: Int) {

    }



}