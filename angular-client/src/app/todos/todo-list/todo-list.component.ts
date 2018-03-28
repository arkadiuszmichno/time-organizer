import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs/Subscription';
import {TodoService} from '../todo.service';

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
      (newTodos) => this.todos = newTodos
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
