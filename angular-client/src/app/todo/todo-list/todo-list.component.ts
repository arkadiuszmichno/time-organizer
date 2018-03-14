import {Component, OnInit} from '@angular/core';
import {Todo} from '../todo';
import {TodoDataService} from "../todo-data.service";
import {TodoComponent} from "../todo.component";
import {TaskDataService} from "../tasks/task-data.service";

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css'],
  providers: [TodoDataService]
})
export class TodoListComponent implements OnInit {

  todos: Todo[];

  constructor(private todoDataService: TodoDataService, private todoComponent: TodoComponent) {
  }

  ngOnInit() {
    this.getAllTodos();
  }

  getAllTodos(): void {
    this.todoDataService.getAllTodos()
      .subscribe(todos => {
        this.todos = todos;
      })
  }

  setTodo(todo: Todo) {
    this.todoComponent.setList(todo);
  }




}
