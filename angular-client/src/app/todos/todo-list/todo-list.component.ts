import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs/Subscription';
import {TodoService} from '../todo.service';
import {NgForm} from '@angular/forms';
import {Todo} from '../todo.model';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit, OnDestroy {
  private subscription: Subscription;
  todos = [];

  constructor(private todoService: TodoService) {
  }

  ngOnInit() {
    this.todos = this.todoService.getTodos();
    this.subscription = this.todoService.todosUpdated.subscribe(
      (newTodos: Todo[]) => this.todos = newTodos
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  OnAddList(form: NgForm) {
    const listName = form.value['name'];
    const newList = new Todo(listName);
    this.todoService.addTodo(newList);
  }
}
